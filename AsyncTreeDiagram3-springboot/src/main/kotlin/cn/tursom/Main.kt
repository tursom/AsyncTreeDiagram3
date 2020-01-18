package cn.tursom

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.loader.ModLoader
import kotlinx.coroutines.runBlocking

object Main : Slf4jImpl() {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        ModLoader.run {
            getModLoader("cn.tursom.treediagram.spring").load()
        }
        Main.logger.info("start finished")
    }
}