package cn.tursom.test

import cn.tursom.treediagram.web.WebModule
import cn.tursom.utils.AsyncHttpRequest
import cn.tursom.web.HttpContent
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.result.Html
import cn.tursom.web.result.Text
import java.lang.StringBuilder
import kotlin.math.min
import kotlin.random.Random

@Suppress("unused")
class IndexMod : WebModule() {
  private val random = Random(System.currentTimeMillis())

  @Html
  @GetMapping("")
  override suspend fun handle(content: HttpContent) {
    content.finishHtml(AsyncHttpRequest.getByteArray("https://zblog.tursom.cn"))
    //content.jump("https://zblog.tursom.cn")
  }

  @Text
  @GetMapping("hello")
  fun hello() = "hello!"

  @Text
  @GetMapping("random")
  fun random(): StringBuilder {
    val stringBuilder = StringBuilder()
    repeat(6) {
      stringBuilder.append(random.nextInt(0, 10))
    }
    return stringBuilder
  }

  @GetMapping("random/:size")
  fun random(content: HttpContent) {
    val size = min(content["size"]?.toIntOrNull() ?: 6, 100)
    val stringBuilder = StringBuilder()
    repeat(size) {
      stringBuilder.append(random.nextInt(0, 10))
    }
    content.finishText(stringBuilder.toString().toByteArray())
  }
}