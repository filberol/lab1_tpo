import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.test.assertEquals

class TrigFunctionsTestAccuracy {
    private val solver: TrigMathInterface = IterMathModule()
    @ParameterizedTest
    @MethodSource("getData")
    fun testSine(value: Double) {
        assertEquals(sin(value), solver.sine(value, 10), 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testCosine(value: Double) {
        assertEquals(cos(value), solver.cosine(value, 10), 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testSecant(value: Double) {
        if (abs(value % Math.PI) - Math.PI/2 <= 1.0E-5) {
            assertThrows<ArithmeticException>("Secant out of bounds") { solver.secant(Math.PI / 2, 10) }
        } else {
            assertEquals(1/cos(value), solver.secant(value, 10), 1.0E-3)
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testCotangent(value: Double) {
        if (abs(value % Math.PI) - Math.PI/2 <= 1.0E-5) {
            assertThrows<ArithmeticException>("Tangent out of bounds") { solver.cotangent(Math.PI / 2, 10) }
        } else {
            assertEquals(cos(value)/sin(value), solver.cotangent(value, 10), 1.0E-2)
        }
    }

    companion object {
        private const val SPLITS = 100
        @JvmStatic
        fun getData(): List<Arguments> {
            return (0..<SPLITS).map {
                Arguments.of(Math.PI * 2 / SPLITS * it)
            }
        }
    }
}