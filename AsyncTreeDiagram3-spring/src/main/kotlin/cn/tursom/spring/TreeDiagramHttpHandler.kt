package cn.tursom.spring

import cn.tursom.treediagram.Module
import cn.tursom.web.router.AsyncRoutedHttpHandler
import cn.tursom.web.router.impl.ColonRouter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class TreeDiagramHttpHandler : AsyncRoutedHttpHandler(routerMaker = { ColonRouter() }) {
  @Autowired
  private lateinit var applicationContext: ApplicationContext

  @PostConstruct
  fun init() {
    applicationContext.getBeansOfType(WebModule::class.java).forEach { _, mod ->
      addRouter(mod)
    }
  }

  override fun toString(): String = "TreeDiagramHttpHandler"
}