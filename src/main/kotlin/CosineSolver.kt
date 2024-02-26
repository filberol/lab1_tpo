import kotlin.math.pow

/*
Функция cos(x)
 */
class CosineSolver {
    fun approximateCosine(x: Double, iterations: Int): Double {
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
}