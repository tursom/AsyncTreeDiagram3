package cn.tursom

import cn.tursom.spring.ApplicationStater
import cn.tursom.spring.WebModule
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.result.Text
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import kotlin.math.absoluteValue
import kotlin.random.Random

@Controller
class HelloWorld : WebModule() {
  @GetMapping("/")
  @Text
  fun index() = "hello, world!"
}

@Controller
class TestController : WebModule() {
  @GetMapping("random")
  @Text
  fun random() = (Random(System.currentTimeMillis()).nextInt() % 999999).absoluteValue
}

@ComponentScan
@Configuration
open class Application

fun main() {
  val applicationContext = ApplicationStater.run(Application::class.java)
}