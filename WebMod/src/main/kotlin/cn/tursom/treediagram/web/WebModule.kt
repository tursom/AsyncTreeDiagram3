package cn.tursom.treediagram.web

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.AbstractModule
import cn.tursom.treediagram.loader.ModLoader
import cn.tursom.web.HttpContent
import cn.tursom.web.router.RoutedHttpHandler

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class WebModule : AbstractModule() {
  final override suspend fun init() {
    super.init()
    logger.debug("mod $javaClass init")
    TreeDiagramHttpHandler.addRouter(this)

  }

  final override suspend fun destroy() {
    super.destroy()
    logger.debug("mod $javaClass destroy")
    TreeDiagramHttpHandler.deleteRouter(this)
  }

  @Throws(Throwable::class)
  abstract suspend fun handle(content: HttpContent): Any?

  companion object : Slf4jImpl()
}

