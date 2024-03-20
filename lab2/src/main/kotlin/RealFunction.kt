import kotlin.math.*

class RealFunction {
    fun get(x: Double): Double {
        val cos = cos(x)
        val cot = cos(x)/sin(x)
        val sec = 1/cos(x)
        val tan = tan(x)

        val log = ln(x)
        val log2 = log2(x)
        val log5 = log(x, 5.0)
        val log10 = log10(x)

        return if (x <= 0) {
            ((cot/sec).pow(3) + cot).pow(2) + tan + cos
        } else {
            (log2 - log2 - log5) * log * log2 * log10
        }
    }
}