package cn.tursom.test

import cn.tursom.core.buffer.ByteBuffer
import cn.tursom.core.buffer.impl.HeapByteBuffer
import cn.tursom.core.buffer.write
import cn.tursom.log.impl.Slf4jImpl
import cn.tursom.treediagram.web.WebModule
import cn.tursom.web.HttpContent
import cn.tursom.web.mapping.GetMapping
import cn.tursom.web.router.BlockHandler
import cn.tursom.web.utils.Chunked
import kotlin.math.min
import kotlin.random.Random

@Suppress("unused")
class RandomMod : WebModule() {
  companion object : Slf4jImpl() {
    private const val chunkSize = 8192
    private val random = Random(System.currentTimeMillis())
  }

  @BlockHandler
  @GetMapping("random", "random/:size")
  fun random(content: HttpContent) {
    val size = content["size"]?.toIntOrNull() ?: 6
    log.debug("random {}", size)
    if (size <= chunkSize) {
      val buffer = HeapByteBuffer(size)
      buffer.write { writeBuffer ->
        repeat(size) {
          writeBuffer.put(('0' + random.nextInt(0, 10)).toByte())
        }
      }
      content.finishText(buffer)
    } else {
      content.finishChunked(object : Chunked {
        override val endOfInput: Boolean get() = progress >= length
        override val length: Long = ((size - 1) / chunkSize + 1).toLong()
        override var progress: Long = 0

        override fun close() {}

        override fun readChunk(): ByteBuffer {
          val bufSize = min(chunkSize, (size - progress * chunkSize).toInt())
          val buffer = HeapByteBuffer(bufSize)
          buffer.write { writeBuffer ->
            repeat(bufSize) {
              writeBuffer.put(('0' + random.nextInt(0, 10)).toByte())
            }
          }
          progress++
          log.trace("readChunk: {}", buffer)
          return buffer
        }
      })
    }
  }
}