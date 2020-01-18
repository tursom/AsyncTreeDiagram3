package cn.tursom.spring

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.AbstractModule

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class WebModule : AbstractModule() {
  companion object : Slf4jImpl()
}

