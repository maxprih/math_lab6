package com.example.dtos

import kotlinx.serialization.Serializable

@Serializable
data class OduResponse(
    val euler: List<Point>,
    val runge: List<Point>,
    val miln: List<Point>,
    val exact: List<Point>,
)

@Serializable
data class Point(
    val x: Double,
    val y: Double,
)