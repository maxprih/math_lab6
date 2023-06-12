package com.example.math

import com.example.dtos.ODUFunction
import com.example.dtos.Point

class MilneMethod {
    fun milneMethod(x0: Double, xn: Double, h: Double, y0: Double,
        func: ODUFunction)
        : MutableList<Point>{
        val result = mutableListOf(Point(x0, y0))
        var xi = x0
        var yi = y0
        val n = ((xn - x0) / h).toInt()
        repeat(3) {
            val k1 = h * func(xi, yi)
            val k2 = h * func(xi + h / 2, yi + k1 / 2)
            val k3 = h * func(xi + h / 2, yi + k2 / 2)
            val k4 = h * func(xi + h, yi + k3)
            yi += (k1 + 2 * k2 + 2*k3 + k4) / 6
            xi += h
            result.add(Point(xi, yi))
        }
        repeat(n - 3) {
            val yip1 = result.last().y + 4 * h / 3 * (2 * func(xi, yi) - func(xi - h, result[result.size - 2].y))
            val yip2 = result.last().y + h / 3 * (func(xi + h, yip1) + 4 * func(xi, yi) + func(xi - h, result[result.size - 2].y))
            val yip3 = result.last().y + h / 3 * (func(xi + 2 * h, yip2) + 4 * func(xi + h, yip1) + func(xi, yi))
            xi += h
            yi = yip3
            result.add(Point(xi, yi))
        }
        return result

    }
}