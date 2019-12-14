package cn.tursom.treediagram

import cn.tursom.treediagram.annotations.*
import org.slf4j.LoggerFactory


interface Module {
  val require: Array<out RequireInfo>? get() = javaClass.getAnnotation(Require::class.java)?.require
  val prettyJson: Boolean get() = false
  val version: Int get() = javaClass.getAnnotation(Version::class.java)?.version ?: 0
  val apiVersion: Int get() = javaClass.getAnnotation(ApiVersion::class.java)?.version ?: 0

  val modDescription: String
    get() = javaClass.getAnnotation(ModDescription::class.java)?.description ?: "no description"
  val modHelper: String get() = javaClass.getAnnotation(ModHelper::class.java)?.helper ?: "no helper"

  val modId: String
    get() = javaClass.getAnnotation(ModId::class.java)?.id?.let {
      if (it.isEmpty()) null else it
    } ?: javaClass.simpleName

  /**
   * 当模组被初始化时被调用
   */
  suspend fun init() {
    try {
      LoggerFactory.getLogger(this.javaClass).info("mod {} init", modId)
    } catch (e: Throwable) {
    }
  }

  /**
   * 当模组生命周期结束时被调用
   */
  suspend fun destroy() {
    try {
      LoggerFactory.getLogger(this.javaClass).info("mod {} destroy", modId)
    } catch (e: Throwable) {
    }
  }
}