package cn.tursom

import cn.tursom.log.colorfulConsoleLogger
import cn.tursom.log.configureLogbackDirectly
import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.log.setLogLevel
import cn.tursom.log.simpPattern
import cn.tursom.treediagram.loader.ModLoader
import cn.tursom.treediagram.web.TreeDiagramHttpHandler
import cn.tursom.web.netty.NettyHttpServer
import kotlinx.coroutines.runBlocking


object Main : Slf4jImpl() {
  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    setLogLevel(org.slf4j.event.Level.INFO)
    setLogLevel(org.slf4j.event.Level.DEBUG, "cn.tursom")
    setLogLevel(org.slf4j.event.Level.INFO, "cn.tursom.web")
    configureLogbackDirectly("log", "test")
    colorfulConsoleLogger(simpPattern)

    ModLoader.loadModByPackage("cn.tursom.test")
    val server = NettyHttpServer(8086, TreeDiagramHttpHandler)
    server.run()
  }
}