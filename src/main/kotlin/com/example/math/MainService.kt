package com.example.math

import com.example.dtos.ODU
import com.example.dtos.ODUFunction
import com.example.dtos.OduDao
import com.example.dtos.OduRequest
import com.example.dtos.OduResponse
import com.example.dtos.Point
import java.util.Collections.max
import kotlin.math.abs

class MainService {
    fun solve(oduRequest: OduRequest): OduResponse {
        val oduDao = OduDao()
        val odu = oduDao.getOdu(oduRequest.equation_id)

        val euler = EulerMethod()
        val eulerRes = euler.doIteration(
            oduRequest.x0,
            oduRequest.xn,
            oduRequest.h,
            oduRequest.y0,
            odu.function,
            oduRequest.eps,
        )
        println("Euler's accuracy is ${eulerRes.removeLast().y}")

        val runge = RungeMethod()
        val rungeRes = runge.doIteration(
            oduRequest.x0,
            oduRequest.xn,
            oduRequest.h,
            oduRequest.y0,
            odu.function,
            oduRequest.eps
        )
        println("Runge's accuracy is ${rungeRes.removeLast().y}")

        val milne = MilneMethod()
        val milneRes = milne.milneMethod(
            oduRequest.x0,
            oduRequest.xn,
            oduRequest.h,
            oduRequest.y0,
            odu.function,
        )

        val real = exact(
            oduRequest.x0,
            oduRequest.xn,
            oduRequest.h,
            oduRequest.y0,
            odu,
        )
        println("Milne's accuracy is ${milneAcc(milneRes, real)}")
        val ret = OduResponse(eulerRes, rungeRes, milneRes, real)
        return ret
    }


    fun exact(x0: Double, xn: Double, h: Double, y0: Double,
        odu: ODU)
        : MutableList<Point>{
        var x = x0
        var y = y0
        val coef = odu.coef(x0, y0)
        val res = mutableListOf<Point>()
        res.add(Point(x, y))
        while (x < xn-0.01) {
            x += h
            y = odu.exact(x, coef)
            res.add(Point(x, y))
        }
        return res
    }

    fun milneAcc(milne: MutableList<Point>, exact: MutableList<Point>): Double {
        val accuracies = mutableListOf<Double>()
        for (i in 0 until milne.size) {
            accuracies.add(abs(exact[i].y - milne[i].y))
        }
        return max(accuracies)
    }
}