package cn.tursom.treediagram.web

import cn.tursom.treediagram.AbstractModule
import cn.tursom.treediagram.Module
import cn.tursom.web.router.AsyncRoutedHttpHandler
import cn.tursom.web.router.impl.ColonRouter

object TreeDiagramHttpHandler : AsyncRoutedHttpHandler(routerMaker = { ColonRouter() }), Module by AbstractModule()