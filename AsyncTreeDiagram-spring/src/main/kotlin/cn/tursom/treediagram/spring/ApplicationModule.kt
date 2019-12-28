package cn.tursom.treediagram.spring

import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.Module
import cn.tursom.treediagram.annotations.ModId
import org.springframework.boot.runApplication

@Suppress("unused")
@ModId("SpringBootStater")
class ApplicationModule : Module {
  init {
    logger.info("ApplicationModule create")
  }

  override suspend fun init() {
    super.init()
    logger.info("ApplicationModule init")
    runApplication<ApplicationStarter>()
  }

  companion object : Slf4jImpl()
}