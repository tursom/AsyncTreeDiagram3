package cn.tursom.treediagram.web

import cn.tursom.web.router.AsyncRoutedHttpHandler
import cn.tursom.web.router.impl.ColonRouter

object TreeDiagramHttpHandler : AsyncRoutedHttpHandler(routerMaker = { ColonRouter() })