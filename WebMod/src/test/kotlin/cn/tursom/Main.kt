package cn.tursom

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import cn.tursom.log.colorfulConsoleLogger
import cn.tursom.log.configureLogbackDirectly
import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.log.setLogLevel
import cn.tursom.treediagram.loader.ModLoader
import cn.tursom.treediagram.web.TreeDiagramHttpHandler
import cn.tursom.web.netty.NettyHttpServer
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory


object Main : Slf4jImpl() {
  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    setLogLevel(org.slf4j.event.Level.INFO)
    (LoggerFactory.getLogger("cn.tursom") as Logger).level = Level.DEBUG
    configureLogbackDirectly("log", "test")
    colorfulConsoleLogger()

    ModLoader.loadModByPackage("cn.tursom.test")
    val server = NettyHttpServer(8086, TreeDiagramHttpHandler)
    server.run()
  }
}