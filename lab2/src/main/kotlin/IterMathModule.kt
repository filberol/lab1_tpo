import kotlin.math.abs
import kotlin.math.pow

class IterMathModule: AbstractMathModule() {
    private val logLeftBorder = 0.0
    private val logRightBorder = 2.0

    private val trigBreakWindow = 0.05

    private fun approximateLog(x: Double, iterations: Int): Double {
        if (x <= logLeftBorder || x >= logRightBorder) throw ArithmeticException("Out of log approximation")
        var res = 0.0
        for (iter in 1..<iterations) {
            val sign = if (iter % 2 == 1) 1.0 else -1.0
            res += sign * (x - 1).pow(iter) / iter
        }
        return res
    }
    private fun approximateCosine(x: Double, iterations: Int): Double {
        var res = 1.0
        for (iter in 1..iterations) {
            val sign = if (iter % 2 == 1) -1.0 else 1.0
            val add = sign * x.pow(2 * iter)
            res += divideFactorial(add, iter * 2)
        }
        return res
    }

    private fun divideFactorial(add: Double, n: Int): Double {
        var divi = add
        for (i in 1..n) {
            divi /= i.toDouble()
        }
        return divi
    }

    override fun sine(radian: Double, iterations: Int): Double {
        return approximateCosine(Math.PI/2 - radian, iterations)
    }

    override fun cosine(radian: Double, iterations: Int): Double {
        return approximateCosine(radian, iterations)
    }

    override fun secant(radian: Double, iterations: Int): Double {
        if (abs(abs(radian) % Math.PI - Math.PI/2) <= trigBreakWindow) throw ArithmeticException("Secant out of bounds")
        return 1 / cosine(radian, iterations)
    }

    override fun tangent(radian: Double, iterations: Int): Double {
        if (abs(abs(radian) % Math.PI - Math.PI/2) <= trigBreakWindow) throw ArithmeticException("Tangent out of bounds")
        return sine(radian, iterations) / cosine(radian, iterations)
    }

    override fun cotangent(radian: Double, iterations: Int): Double {
        if (abs(abs(radian) % Math.PI - Math.PI) <= trigBreakWindow) throw ArithmeticException("Cotangent out of bounds")
        return cosine(radian, iterations) / sine(radian, iterations)
    }

    override fun log(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return approximateLog(x, iterations)
    }

    override fun log2(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return approximateLog(x, iterations) / kotlin.math.log(2.0, Math.E)
    }

    override fun log5(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return approximateLog(x, iterations) / kotlin.math.log(5.0, Math.E)
    }

    override fun log10(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return approximateLog(x, iterations) / kotlin.math.log(10.0, Math.E)
    }
}