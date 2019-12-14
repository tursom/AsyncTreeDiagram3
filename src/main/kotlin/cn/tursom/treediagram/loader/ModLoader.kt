package cn.tursom.treediagram.loader

import cn.tursom.core.ListClassLoader
import cn.tursom.core.getClassByPackage
import cn.tursom.log.impl.TrySlf4jImpl
import cn.tursom.treediagram.Module
import cn.tursom.utils.AsyncHttpRequest
import kotlinx.coroutines.delay
import java.io.File
import java.lang.Integer.min
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.ConcurrentHashMap
import java.util.jar.JarFile


/**
 * 用于加载模组
 * 模组可由网络或者本地加载
 * 亦可将配置写入一个文件中
 * 会优先尝试从本地加载模组
 * 本地文件不存在则会从网络加载模组
 */
@Suppress("unused")
class ModLoader constructor(
  private val className: List<String>,
  private val classLoader: ClassLoader
) {
  /**
   * 手动加载模组
   * @return 是否所有的模组都加载成功
   */
  suspend fun load(): List<String> {
    return className.mapNotNull { className ->
      try {
        loadSingleMod(className)
      } catch (e: Exception) {
        logger?.error("exception caused on load mod {}", className, e)
        null
      }
    }
  }

  private suspend fun loadSingleMod(className: String): String? {
    return try {
      //获取一个指定模组的对象
      val modClass = classLoader.loadClass(className)
      if (!Module::class.java.isAssignableFrom(modClass)) return null
      logger?.info("loading mod {}", className)
      val modObject = try {
        modClass.newInstance() as Module
      } catch (e: Exception) {
        logger?.debug("class {} haven't an default constructor", className)
        return null
      }
      //加载模组
      loadedMod.put(modObject.modId, modObject)?.destroy()
      modObject.init()
      className
    } catch (e: InvocationTargetException) {
      throw e.targetException
    }
  }

  companion object : TrySlf4jImpl() {
    private val loadedMod = ConcurrentHashMap<String, Module>()

    fun getMod(id: String) = loadedMod[id]

    suspend fun getMod(id: String, maxRetry: Int = 10, delayMs: Long = 10, handler: suspend (Module) -> Unit) {
      repeat(min(maxRetry, 100)) {
        handler(loadedMod[id] ?: run {
          delay(delayMs)
          return@repeat
        })
        return
      }
    }

    private fun getClassName(jarPath: String): List<String> {
      val myClassName = ArrayList<String>()
      for (entry in JarFile(jarPath).entries()) {
        val entryName = entry.name
        if (entryName.endsWith(".class")) {
          myClassName.add(entryName.replace("/", ".").substring(0, entryName.lastIndexOf(".")))
        }
      }
      return myClassName
    }

    suspend inline fun <reified T> T.loadMod(
      pkg: String,
      parentClassLoader: ClassLoader = T::class.java.classLoader
    ) {
      val classLoader = ListClassLoader(arrayOf(), parentClassLoader)
      val classList = parentClassLoader.getClassByPackage(pkg)
      ModLoader(classList, classLoader).load()
    }

    suspend inline fun <reified T> T.getModLoader(
      pkg: String,
      loadInstantly: Boolean = false,
      parentClassLoader: ClassLoader = T::class.java.classLoader
    ): ModLoader {
      val classLoader = ListClassLoader(arrayOf(), parentClassLoader)
      val classList = parentClassLoader.getClassByPackage(pkg)
      return ModLoader(classList, classLoader).also { loader ->
        if (loadInstantly) {
          loader.load()
        }
      }
    }

    suspend fun getModLoader(
      configData: ClassData,
      rootPath: String? = null,
      loadInstantly: Boolean = false,
      parentClassLoader: ClassLoader = Thread.currentThread().contextClassLoader
    ): ModLoader {
      val file = if (rootPath == null) {
        configData.path
      } else {
        rootPath + configData.path
      }
      val jarFile = if (file != null && !File(file).exists()) {
        val localJarPath = configData.url!!.split('?')[0].split('/').last()
        val localJarFile = File(localJarPath)
        if (!localJarFile.exists()) localJarFile.outputStream().use {
          it.write(AsyncHttpRequest.getByteArray(configData.url))
        }
        localJarPath
      } else {
        file
      }!!
      @Suppress("BlockingMethodInNonBlockingContext")
      val classLoader = ListClassLoader(arrayOf(File(jarFile).toURI().toURL()), parentClassLoader)
      val classList = configData.classname ?: getClassName(jarFile)
      return ModLoader(classList, classLoader).also { loader ->
        if (loadInstantly) {
          loader.load()
        }
      }
    }
  }
}

