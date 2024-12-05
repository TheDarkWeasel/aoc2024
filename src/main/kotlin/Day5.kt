import java.io.File
import java.net.URI

class Day5 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day5_1.txt")?.let { readFile(it.toURI()) }

        val rules = inputs?.filter { it.contains('|') }?.map {
            val split = it.split("|")
            Rule(split[0].toInt(), split[1].toInt())
        }

        val pagesToCheck = inputs?.filter { it.contains(',') }?.map {
            it.split(",").map { it.toInt() }
        }

        var result = 0

        pagesToCheck?.filter { pages -> rules!!.all { it.isRuleApplied(pages) } }
            ?.forEach { valid ->
                result += valid[valid.size / 2]
            }


        println("Day5 Part1: $result")
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day5_1.txt")?.let { readFile(it.toURI()) }


        //println("Day5 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    private data class Rule(val firstPage: Int, val secondPage: Int) {
        fun isRuleApplied(allPages: List<Int>): Boolean {
            if (firstPage !in allPages || secondPage !in allPages) {
                return true
            }
            return allPages.indexOf(firstPage) < allPages.indexOf(secondPage)
        }
    }
}