import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import kotlin.test.assertEquals

class MockFunctionWithModuleTest {
    private val iterSolver: AbstractMathModule = IterMathModule()
    private val mockSolver: AbstractMathModule = Mockito.mock(IterMathModule::class.java)

    private val iterFunction = FunctionWithModule(100, iterSolver)
    private val mockFunction = FunctionWithModule(100, createMockSolver())

    private fun createMockSolver(): AbstractMathModule {
        val realSolver = RealMathModule()
        Mockito.`when`(mockSolver.sine(anyDouble(), anyInt())).thenAnswer {
            realSolver.sine(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.cosine(anyDouble(), anyInt())).thenAnswer {
            realSolver.cosine(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.tangent(anyDouble(), anyInt())).thenAnswer {
            realSolver.tangent(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.cotangent(anyDouble(), anyInt())).thenAnswer {
            realSolver.cotangent(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.secant(anyDouble(), anyInt())).thenAnswer {
            realSolver.secant(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.log(anyDouble(), anyInt())).thenAnswer {
            realSolver.log(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.log2(anyDouble(), anyInt())).thenAnswer {
            realSolver.log2(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.log5(anyDouble(), anyInt())).thenAnswer {
            realSolver.log5(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        Mockito.`when`(mockSolver.log10(anyDouble(), anyInt())).thenAnswer {
            realSolver.log10(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        return mockSolver
    }

    private fun assertBothThrowsOrEquals(
        func1: FunctionWithModule,
        func2: FunctionWithModule,
        value: Double,
        acc: Double
    ) {
        try {
            val res1 = func1.get(value)
            try {
                val res2 = func2.get(value)
                assertEquals(res1, res2, acc)
            } catch (_: ArithmeticException) {
                assertThrows<ArithmeticException> { func1.get(value) }
            }
        } catch (_: ArithmeticException) {
            assertThrows<ArithmeticException> { func2.get(value) }
        }
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testAllMock(value: Double) {
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-2)
    }

    @ParameterizedTest
    @MethodSource("getDataLeft")
    fun testMockSine(value: Double) {
        Mockito.`when`(mockSolver.sine(anyDouble(), anyInt())).thenAnswer {
            iterSolver.sine(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getDataLeft")
    fun testMockCosine(value: Double) {
        Mockito.`when`(mockSolver.cosine(anyDouble(), anyInt())).thenAnswer {
            iterSolver.cosine(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getDataLeft")
    fun testMockSecant(value: Double) {
        Mockito.`when`(mockSolver.secant(anyDouble(), anyInt())).thenAnswer {
            iterSolver.secant(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getDataLeft")
    fun testMockTangent(value: Double) {
        Mockito.`when`(mockSolver.tangent(anyDouble(), anyInt())).thenAnswer {
            iterSolver.tangent(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getDataLeft")
    fun testMockCotangent(value: Double) {
        Mockito.`when`(mockSolver.cotangent(anyDouble(), anyInt())).thenAnswer {
            iterSolver.cotangent(it.arguments[0] as Double, it.arguments[1] as Int)
        }
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-3)
    }

    @ParameterizedTest
    @MethodSource("getDataRight")
    fun testMockLog(value: Double) {
        try {
            Mockito.`when`(mockSolver.log(anyDouble(), anyInt())).thenAnswer {
                iterSolver.log(it.arguments[0] as Double, it.arguments[1] as Int)
            }
        } catch (_: ArithmeticException) {}
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-2)
    }

    @ParameterizedTest
    @MethodSource("getDataRight")
    fun testMockLog2(value: Double) {
        try {
            Mockito.`when`(mockSolver.log2(anyDouble(), anyInt())).thenAnswer {
                iterSolver.log2(it.arguments[0] as Double, it.arguments[1] as Int)
            }
        } catch (_: ArithmeticException) {}
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-2)
    }

    @ParameterizedTest
    @MethodSource("getDataRight")
    fun testMockLog5(value: Double) {
        try {
            Mockito.`when`(mockSolver.log5(anyDouble(), anyInt())).thenAnswer {
                iterSolver.log5(it.arguments[0] as Double, it.arguments[1] as Int)
            }
        } catch (_: ArithmeticException) {}
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-2)
    }

    @ParameterizedTest
    @MethodSource("getDataRight")
    fun testMockLog10(value: Double) {
        try {
            Mockito.`when`(mockSolver.log10(anyDouble(), anyInt())).thenAnswer {
                iterSolver.log10(it.arguments[0] as Double, it.arguments[1] as Int)
            }
        } catch (_: ArithmeticException) {}
        assertBothThrowsOrEquals(mockFunction, iterFunction, value, 1.0E-2)
    }

    companion object {
        private const val SPLITS = 100

        @JvmStatic
        fun getDataLeft(): List<Arguments> {
            return (0..<SPLITS).map {
                Arguments.of(6.0 / SPLITS * it - 6.0)
            }
        }

        @JvmStatic
        fun getDataRight(): List<Arguments> {
            return (0..<SPLITS).map {
                Arguments.of(2.0 / SPLITS * it)
            }
        }

        @JvmStatic
        fun getData(): List<Arguments> {
            return (0..<SPLITS).map {
                Arguments.of(8.0 / SPLITS * it - 6.0)
            }
        }
    }
}