import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.util.color.Color
import java.io.File

fun main() {
    val leftBorder = -6.0
    val length = 8
    val splits = 10000

    val realSolver = RealFunction()
    val iterSolver = FunctionWithModule(100, IterMathModule())

    val sampleX = (0..<splits).map {
        length.toDouble() / splits.toDouble() * it + leftBorder
    }
    val realY = sampleX.map {
        realSolver.get(it)
    }
    val calc10 = sampleX.map { value ->
        try {
            iterSolver.get(value)
        } catch (e: ArithmeticException) {
            null
        }
    }

    val filePath = "./lab2/functions/result.csv"
    val file = File(filePath).also { it.createNewFile() }
    val fileWriter = file.bufferedWriter()

    fileWriter.write("X, Результаты модуля (RealMathModule), Результаты модуля (IterMathModule)")
    sampleX.forEachIndexed { index, value ->
        fileWriter.write("${value}, ${realY[index]}, ${calc10[index]}")
        fileWriter.newLine()
    }
    fileWriter.close()

    val realFunction = dataFrameOf(
        "x" to sampleX,
        "y" to realY,
        "f" to calc10
    )

    realFunction.plot {
        points {
            x("x")
            y("y") {
                scale = continuous(-30, 30)
            }
            size = 1.0
            color = Color.BLUE
        }
        points {
            x("x")
            y("f") {
                scale = continuous(-30, 30)
            }
            size = 1.0
            color = Color.BLACK
        }
    }.save("../lab2/functions/functions.png")
}