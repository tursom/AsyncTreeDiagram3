package cn.tursom.spring

import cn.tursom.web.ExceptionContent
import cn.tursom.web.HttpContent
import cn.tursom.web.HttpHandler
import cn.tursom.web.netty.NettyHttpServer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
open class NettyHttpServerContent {
  @Value("\${server.port:8080}")
  private val port: Int = 8080

  @Bean
  open fun getServer(
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    handler: HttpHandler<HttpContent, ExceptionContent>
  ): NettyHttpServer {
    return NettyHttpServer(port, handler).apply { run() }
  }
}