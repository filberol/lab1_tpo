import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.collections.ArrayList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HashTest {
    private val list = HashTable(10)

    @Test
    fun testDuplicates() {
        val digit = 10
        list.insertInto(digit)
        assertTrue(list.isIn(digit))
        list.insertInto(digit)
        assertTrue(list.isIn(digit))
        list.deleteFrom(digit)
        assertTrue(list.isIn(digit))
        list.deleteFrom(digit)
        assertFalse(list.isIn(digit))
    }

    @Test
    fun testToString() {
        for (i in 1..10) {
            list.insertInto(i)
        }
        assertEquals(list.toString(), "List: [10] [1] [2] [3] [4] [5] [6] [7] [8] [9]")
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun testPlacement(elements: ArrayList<Int>) {
        for (element in elements) {
            list.insertInto(element)
            assertTrue(list.isIn(element))
        }
        for (element in elements) {
            list.deleteFrom(element)
            assertFalse(list.isIn(element))
        }
    }

    companion object {
        private val arr: ArrayList<Int> = ArrayList()
        @JvmStatic
        fun getData(): List<Arguments> {
            return (1..120 step 3).map {
                arr.add(it)
                Arguments.of(arrayListOf<Int>().apply { addAll(arr) })
            }
        }
    }

}