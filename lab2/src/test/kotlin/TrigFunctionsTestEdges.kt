import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TrigFunctionsTestEdges {
    private val solver: TrigMathInterface = IterMathModule()

    @Test
    fun testEdgesSine() {
        assertEquals(0.0, solver.sine(0.0, 10), 1.0E-5)
        assertEquals(1.0, solver.sine(Math.PI / 2, 10), 1.0E-5)
        assertEquals(0.0, solver.sine(Math.PI, 10), 1.0E-5)
        assertEquals(-1.0, solver.sine(Math.PI * 3 / 2, 10), 1.0E-5)
        assertEquals(0.0, solver.sine(Math.PI * 2, 100), 1.0E-5)
    }

    @Test
    fun testEdgesCosine() {
        assertEquals(1.0, solver.cosine(0.0, 10), 1.0E-5)
        assertEquals(0.0, solver.cosine(Math.PI / 2, 10), 1.0E-5)
        assertEquals(-1.0, solver.cosine(Math.PI, 10), 1.0E-5)
        assertEquals(0.0, solver.cosine(Math.PI * 3 / 2, 10), 1.0E-5)
        assertEquals(1.0, solver.cosine(Math.PI * 2, 100), 1.0E-5)
    }

    @Test
    fun testEdgesSecant() {
        assertEquals(1.0, solver.secant(0.0, 10), 1.0E-5)
        assertThrows<ArithmeticException>("Secant out of bounds") { solver.secant(Math.PI / 2, 10) }
        assertEquals(-1.0, solver.secant(Math.PI, 10), 1.0E-5)
        assertThrows<ArithmeticException>("Secant out of bounds") { solver.secant(Math.PI * 3 / 2, 10) }
        assertEquals(1.0, solver.secant(Math.PI * 2, 100), 1.0E-5)
    }

    @Test
    fun testEdgesCotangent() {
        assertEquals(0.0, solver.tangent(0.0, 10), 1.0E-5)
        assertThrows<ArithmeticException>("Tangent out of bounds") { solver.cotangent(Math.PI / 2, 10) }
        assertEquals(0.0, solver.cotangent(Math.PI, 100), 1.0E-5)
        assertThrows<ArithmeticException>("Tangent out of bounds") { solver.cotangent(Math.PI * 3 / 2, 10) }
        assertEquals(0.0, solver.cotangent(Math.PI * 2, 100), 1.0E-5)
    }
}