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

        pagesToCheck?.filter { pages ->
            rules!!.all {
                !it.isRuleRelevant(pages) || it.isRuleApplied(
                    pages
                )
            }
        }
            ?.forEach { valid ->
                result += valid[valid.size / 2]
            }


        println("Day5 Part1: $result")
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day5_1.txt")?.let { readFile(it.toURI()) }

        val rules = inputs?.filter { it.contains('|') }?.map {
            val split = it.split("|")
            Rule(split[0].toInt(), split[1].toInt())
        }

        val pagesToCheck = inputs?.filter { it.contains(',') }?.map {
            it.split(",").map { it.toInt() }
        }

        var result = 0

        pagesToCheck?.filter { pages ->
            rules!!.any {
                it.isRuleRelevant(pages) && !it.isRuleApplied(pages)
            }
        }?.forEach { invalid ->
            var correct = invalid
            val relevantRules = rules!!.filter { it.isRuleRelevant(invalid) }
            while (relevantRules.any { !it.isRuleApplied(correct) }) {
                val rule = relevantRules.first { !it.isRuleApplied(correct) }
                correct = rule.swapAccordingToRule(correct)
            }
            result += correct[correct.size / 2]
        }


        println("Day5 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    private data class Rule(val firstPage: Int, val secondPage: Int) {
        fun isRuleRelevant(allPages: List<Int>): Boolean {
            return firstPage in allPages && secondPage in allPages
        }

        fun isRuleApplied(allPages: List<Int>): Boolean {
            return allPages.indexOf(firstPage) < allPages.indexOf(secondPage)
        }

        fun swapAccordingToRule(allPages: List<Int>): List<Int> {
            val firstPagePos = allPages.indexOf(firstPage)
            val secondPagePos = allPages.indexOf(secondPage)
            val newPages = allPages.toMutableList()
            newPages[firstPagePos] = secondPage
            newPages[secondPagePos] = firstPage
            return newPages
        }
    }
}