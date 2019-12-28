package cn.tursom.treediagram.web

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.AbstractModule

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class WebModule : AbstractModule() {
  override suspend fun init() {
    super.init()
    addRouter(this)
  }

  override suspend fun destroy() {
    super.destroy()
    deleteRouter(this)
  }

  fun addRouter(handler: Any) {
    TreeDiagramHttpHandler.addRouter(handler)
  }

  fun deleteRouter(handler: Any) {
    TreeDiagramHttpHandler.deleteRouter(handler)
  }

  companion object : Slf4jImpl()
}

