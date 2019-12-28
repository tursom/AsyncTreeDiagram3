package cn.tursom.test

import cn.tursom.core.buffer.ByteBuffer
import cn.tursom.core.buffer.impl.HeapByteBuffer
import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.web.WebModule
import cn.tursom.utils.AsyncHttpRequest
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.mapping.Mapping
import cn.tursom.web.result.ContextType
import cn.tursom.web.result.Text
import cn.tursom.web.utils.ContextTypeEnum
import cn.tursom.web.utils.MethodEnum

@Suppress("unused")
class IndexMod : WebModule() {
  companion object : Slf4jImpl()

  private val index = "https://zblog.tursom.cn"

  //@NoReturnLog
  @ContextType(ContextTypeEnum.html)
  @Mapping("/", methodEnum = MethodEnum.GET)
  suspend fun index(): ByteBuffer {
    log.debug("index trace to {}", index)
    return HeapByteBuffer(AsyncHttpRequest.getByteArray(index))
  }

  @Text
  @GetMapping("hello")
  fun hello() = "hello!"

  @GetMapping("index")
  override fun toString(): String = super.toString()
}