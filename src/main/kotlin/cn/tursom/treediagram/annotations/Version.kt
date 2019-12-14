package cn.tursom.treediagram.annotations

@Target(AnnotationTarget.CLASS)
annotation class Version(val version: Int = 0)