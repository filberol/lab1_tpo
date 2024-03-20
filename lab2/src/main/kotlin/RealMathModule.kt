import kotlin.math.*

class RealMathModule: AbstractMathModule() {

    private val trigBreakWindow = 0.05

    override fun sine(radian: Double, iterations: Int): Double {
        return sin(radian)
    }

    override fun cosine(radian: Double, iterations: Int): Double {
        return cos(radian)
    }

    override fun secant(radian: Double, iterations: Int): Double {
        if (abs(abs(radian % Math.PI) - Math.PI/2) <= trigBreakWindow) throw ArithmeticException("Secant out of bounds")
        return 1 / cos(radian)
    }

    override fun tangent(radian: Double, iterations: Int): Double {
        if (abs(abs(radian % Math.PI) - Math.PI/2) <= trigBreakWindow) throw ArithmeticException("Tangent out of bounds")
        return sin(radian) / cos(radian)
    }

    override fun cotangent(radian: Double, iterations: Int): Double {
        if (abs(abs(radian % Math.PI) - Math.PI) <= trigBreakWindow) throw ArithmeticException("Cotangent out of bounds")
        return cos(radian) / sin(radian)
    }

    override fun log(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return ln(x)
    }

    override fun log2(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return log2(x)
    }

    override fun log5(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return log(x, 5.0)
    }

    override fun log10(x: Double, iterations: Int): Double {
        if (x <= trigBreakWindow) throw ArithmeticException("Log out of bounds")
        return log10(x)
    }
}