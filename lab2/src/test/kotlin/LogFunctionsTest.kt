import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.log
import kotlin.test.assertEquals

class LogFunctionsTest {
    private val solver: LogMathInterface = IterMathModule()

    @Test
    fun testEdgesLog() {
        assertThrows<ArithmeticException>("Out of log approximation") { solver.log(0.0, 10) }
        assertThrows<ArithmeticException>("Out of log approximation") { solver.log(2.0, 10) }
        assertEquals(0.0, solver.log(1.0, 10), 1.0E-5)
    }


    @ParameterizedTest
    @MethodSource("getData")
    fun testAccuracyLog(value: Double) {
        val iters = 500
        if (value <= 0.0 || value >= 2.0) {
            assertThrows<ArithmeticException>("Out of log approximation") { solver.log(value, iters) }
        } else {
            assertEquals(log(value, Math.E), solver.log(value, iters), 1.0)
            assertEquals(log(value, 2.0), solver.log2(value, iters), 1.0)
            assertEquals(log(value, 5.0), solver.log5(value, iters), 1.0)
            assertEquals(log(value, 10.0), solver.log10(value, iters), 1.0)
        }
    }

    companion object {
        private const val SPLITS = 100
        @JvmStatic
        fun getData(): List<Arguments> {
            return (0..<SPLITS).map {
                Arguments.of(2.0 / SPLITS * it)
            }
        }
    }
}