package cn.tursom

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.loader.ModLoader
import cn.tursom.treediagram.web.TreeDiagramHttpHandler
import cn.tursom.web.netty.NettyHttpServer
import kotlinx.coroutines.runBlocking

object Main : Slf4jImpl() {
  @JvmStatic
  fun main(args: Array<String>) {
    runBlocking {
      ModLoader.run {
        getModLoader("cn.tursom.test", true)
      }
    }
    @Suppress("UNCHECKED_CAST")
    val server = NettyHttpServer(8086, TreeDiagramHttpHandler)
    server.run()
  }
}