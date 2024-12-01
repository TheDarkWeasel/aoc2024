import java.io.File
import java.net.URI
import kotlin.math.abs

class Day1 {

    fun solvePart1() {
        val (list1, list2) = readLists()

        list1.sortDescending()
        list2.sortDescending()

        var result = 0

        list1.forEachIndexed { index, i ->
            result += abs(i - list2[index])
        }

        println("Day1 Part1: $result")
    }

    fun solvePart2() {
        val (list1, list2) = readLists()

        val countMap = list2.groupingBy { it }.eachCount()

        var result = 0
        list1.forEach {
            result += it * (countMap[it] ?: 0)
        }

        println("Day1 Part2: $result")
    }

    private fun readLists(): Pair<MutableList<Int>, MutableList<Int>> {
        val inputs = this::class.java.getResource("day1_1.txt")?.let { readFile(it.toURI()) }

        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        inputs?.forEach {
            val values = it.split("   ")
            list1.add(values[0].toInt())
            list2.add(values[1].toInt())
        }
        return Pair(list1, list2)
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()
}