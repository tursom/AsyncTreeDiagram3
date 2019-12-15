package cn.tursom.treediagram

import cn.tursom.treediagram.annotations.ApiVersion
import cn.tursom.treediagram.annotations.Require
import cn.tursom.treediagram.annotations.Version

open class AbstractModule : Module {
  override val require = javaClass.getAnnotation(Require::class.java)?.require
  override val version: Int = javaClass.getAnnotation(Version::class.java)?.version ?: 0
  override val apiVersion: Int = javaClass.getAnnotation(ApiVersion::class.java)?.version ?: 0
  override val modId: String = super.modId
  override val modDescription: String = super.modDescription
  override val modHelper: String = super.modHelper

  override fun toString(): String {
    return "$modId version $apiVersion.$version${if (modDescription.isEmpty()) "" else ": $modDescription"}"
  }
}