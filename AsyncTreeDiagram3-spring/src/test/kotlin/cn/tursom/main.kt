package cn.tursom

import cn.tursom.spring.ApplicationStater
import cn.tursom.spring.WebModule
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.result.Text
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component
import kotlin.math.absoluteValue
import kotlin.random.Random

@Component
class HelloWorld : WebModule() {
  @GetMapping("/")
  @Text
  fun index() = "hello, world!"
}

@Component
class TestController : WebModule() {
  @GetMapping("random")
  @Text
  fun random() = (Random(System.currentTimeMillis()).nextInt() % 999999).absoluteValue
}

@ComponentScan
@Component
class Application

fun main() {
  ApplicationStater.run(Application::class.java)
}