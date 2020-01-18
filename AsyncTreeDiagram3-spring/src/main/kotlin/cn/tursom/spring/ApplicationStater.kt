package cn.tursom.spring

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
open class ApplicationStater {
  companion object {
    fun run(vararg classes: Class<*>) =
      AnnotationConfigApplicationContext(*classes, ApplicationStater::class.java)
  }
}


