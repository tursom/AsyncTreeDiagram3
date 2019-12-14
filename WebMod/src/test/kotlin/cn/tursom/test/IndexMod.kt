package cn.tursom.test

import cn.tursom.treediagram.web.WebModule
import cn.tursom.utils.AsyncHttpRequest
import cn.tursom.web.HttpContent
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.result.Text
import cn.tursom.web.utils.ContextType
import cn.tursom.web.utils.ContextTypeEnum
import cn.tursom.web.utils.NoReturnLog

@Suppress("unused")
class IndexMod : WebModule() {
  private val index = "https://zblog.tursom.cn"

  @NoReturnLog
  @ContextType(ContextTypeEnum.html)
  @GetMapping("/")
  suspend fun index(content: HttpContent): ByteArray {
    log.debug("index trace to {}", index)
    return AsyncHttpRequest.getByteArray(index)
    //content.jump("https://zblog.tursom.cn")
  }

  @Text
  @GetMapping("hello")
  fun hello() = "hello!"

  override fun toString(): String {
    return "IndexMod()"
  }
}