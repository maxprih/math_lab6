package com.example.dtos

import kotlin.math.exp

data class ODU (
    val int: Int,
    val function: ODUFunction,
    val exact: ODUFunction,
    val coef: ODUFunction,
    val representation: String,
)

class OduDao {
    val Odu1 = ODU(
        1,
        { x, y -> x + y },
        { x, c -> c * exp(x) - x - 1},
        { x, y -> (y + x + 1) * exp(-x) },
        "x + y",
    )
    val Odu2 = ODU(
        2,
        { x, y -> 2*x*y },
        { x, y -> y * exp(-x*x) },
        { x, c -> c * exp(x*x) },
        "2*x*y",
    )
    val Odu3 = ODU(
        3,
        { x, y -> exp(x) - 2*y },
        { x, y -> (y - exp(x)/3) * exp(2*x)},
        { x, c -> c * exp(-2*x) + exp(x)/3 },
        "e^x - 2*y",
    )
    fun getOdu(id: Int): ODU {
        return when (id) {
            1 -> Odu1
            2 -> Odu2
            else -> Odu3
        }
    }
}
// @FunctionalInterface
// public interface ODUFunction{
//     fun evaluate(x: Double, y: Double): Double
// }

typealias ODUFunction = (Double, Double) -> Double
