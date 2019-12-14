package cn.tursom.treediagram.annotations

@Target(AnnotationTarget.CLASS)
annotation class Require(vararg val require: RequireInfo)