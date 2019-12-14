package cn.tursom.treediagram.annotations

@Target(AnnotationTarget.CLASS)
annotation class ApiVersion(val version: Int = 0)
