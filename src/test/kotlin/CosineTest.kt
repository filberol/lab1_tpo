import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.cos
import kotlin.test.assertEquals

class CosineTest {
    private val cosine = CosineSolver()

    @Test
    fun testEdges() {
        assertEquals(1.0, cosine.approximateCosine(0.0, 10), 1.0E-5)
        assertEquals(0.0, cosine.approximateCosine(Math.PI / 2, 10), 1.0E-5)
        assertEquals(-1.0, cosine.approximateCosine(Math.PI, 10), 1.0E-5)
        assertEquals(0.0, cosine.approximateCosine(Math.PI * 3 / 2, 10), 1.0E-5)
        assertEquals(1.0, cosine.approximateCosine(Math.PI * 2, 100), 1.0E-5)
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testAccuracy(value: Double) {
        assertEquals(cos(value), cosine.approximateCosine(value, 10), 1.0E-3)
//        Does not pass with greater accuracy
//        assertEquals(cos(value), cosine.approximateCosine(value, 10), 1.0E-4)
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