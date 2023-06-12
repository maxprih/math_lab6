package com.example.math

import com.example.dtos.ODUFunction
import com.example.dtos.Point
import kotlin.math.abs

class RungeMethod {
    fun rungeMethod(x0: Double, xn: Double, h: Double, y0: Double,
        func: ODUFunction)
        : MutableList<Point>{
        var x = x0
        var y = y0

        val res = mutableListOf<Point>()
        res.add(Point(x, y))
        while (x < xn-0.01) {
            val k1 = h * func(x, y)
            val k2 = h * func(x + h / 2, y + k1 / 2)
            val k3 = h * func(x + h / 2, y + k2 / 2)
            val k4 = h * func(x + h, y + k3)
            y += (k1 + 2 * k2 + 2 * k3 + k4) / 6
            x += h

            res.add(Point(x, y))
        }
        return res
    }

    fun doIteration(x0: Double, xn: Double, h: Double, y0: Double,
        func: ODUFunction, eps: Double)
        : MutableList<Point> {
        val y1 = rungeMethod(
            x0,
            xn,
            h,
            y0,
            func,
        )
        val y2 = rungeMethod(
            x0,
            xn,
            h/2,
            y0,
            func,
        )
        val r = abs(y1.last().y - y2.last().y) / (2*2 - 1)
        return if (r <= eps) {
            y2.add(Point(r, 0.0))
            y2
        } else {
            doIteration(x0, xn, h/2, y0, func, eps)
        }
    }
}