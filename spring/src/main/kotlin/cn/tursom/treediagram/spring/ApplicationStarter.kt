package cn.tursom.treediagram.spring

import cn.tursom.log.impl.Slf4jImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ApplicationStarter {
  init {
    logger.info("ApplicationStarter create")
  }

  companion object : Slf4jImpl() {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<ApplicationStarter>()
    }
  }
}