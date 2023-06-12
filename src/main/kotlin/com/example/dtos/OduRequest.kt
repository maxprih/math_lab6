package com.example.dtos

import kotlinx.serialization.Serializable

@Serializable
data class OduRequest (
    val equation_id: Int,
    val x0: Double,
    val xn: Double,
    val y0: Double,
    val h: Double,
    val eps: Double,
)