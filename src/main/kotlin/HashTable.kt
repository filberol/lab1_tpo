import java.util.ArrayList
import java.util.LinkedList

/*
Программный модуль для работы с хеш-таблицей с открытой адресацией (Hash Integer, http://www.cs.usfca.edu/~galles/visualization/OpenHash.html)
 */
class HashTable(
    private val initialCapacity: Int
) {
    private var hashList: ArrayList<LinkedList<Int>> = ArrayList<LinkedList<Int>>(initialCapacity)

    init {
        for ( i in 0..<initialCapacity) {
            hashList.add(LinkedList())
        }
    }

    fun insertInto(element: Int) {
        hashList[countHash(element)].push(element)
    }

    fun isIn(element: Int): Boolean {
        return hashList[countHash(element)].find { it == element } != null
    }

    fun deleteFrom(element: Int) {
        hashList[countHash((element))].remove(element)
    }

    private fun countHash(value: Int): Int {
        return value % initialCapacity
    }

    override fun toString(): String {
        return "List: " +  hashList.map { it.toString() }.reduce { acc, s -> "$acc $s" }
    }

}