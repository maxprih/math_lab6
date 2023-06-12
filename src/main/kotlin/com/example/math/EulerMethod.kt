package com.example.math

import com.example.dtos.ODUFunction
import com.example.dtos.Point
import kotlin.math.abs

class EulerMethod {
    fun eulerMethod(x0: Double, xn: Double, h: Double, y0: Double,
        func: ODUFunction)
    : MutableList<Point>{
        var x = x0
        var y = y0

        val res = mutableListOf<Point>()
        res.add(Point(x, y))
        while (x < xn-0.01) {
            val f = func(x, y)
            y += h * f
            x += h

            res.add(Point(x, y))
        }
        return res
    }

    fun doIteration(x0: Double, xn: Double, h: Double, y0: Double,
    func: ODUFunction, eps: Double)
    : MutableList<Point> {
        val y1 = eulerMethod(
            x0,
            xn,
            h,
            y0,
            func,
        )
        val y2 = eulerMethod(
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