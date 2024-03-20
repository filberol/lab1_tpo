import kotlin.math.*

class FunctionWithModule(
    private val iter: Int,
    private val solver: AbstractMathModule
) {

    fun get(x: Double): Double {
        if (x == 0.0) throw ArithmeticException("Div in 0")
        return if (x < 0) {
            val cos = solver.cosine(x, iter)
            val cot = cos / solver.sine(x, iter)
            val sec = 1 / solver.cosine(x, iter)
            val tan = solver.tangent(x, iter)
            ((cot/sec).pow(3) + cot).pow(2) + tan + cos
        } else {
            val log = solver.log(x, iter)
            val log2 = solver.log2(x ,iter)
            val log5 = solver.log5(x, iter)
            val log10 = log10(x)
            (log2 - log2 - log5) * log * log2 * log10
        }
    }
}