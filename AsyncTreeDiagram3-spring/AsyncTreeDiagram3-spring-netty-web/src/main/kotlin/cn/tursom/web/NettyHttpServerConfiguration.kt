package cn.tursom.web

import cn.tursom.web.netty.NettyHttpServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class NettyHttpServerConfiguration {
    @Bean
    open fun getServer(
        @Suppress("SpringJavaInjectionPointsAutowiringInspection") handler: HttpHandler<HttpContent, ExceptionContent>
    ): NettyHttpServer {
        return NettyHttpServer(12345, handler)
    }
}