import java.io.File
import java.net.URI

class Day3 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day3_1.txt")?.let { readFile(it.toURI()) }
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()

        var result = 0

        inputs?.forEach { line ->
            val matches = regex.findAll(line).toList()

            matches.forEach { match ->
                val firstNumber = match.value.substringAfter("mul(").substringBefore(",").toInt()
                val secondNumber = match.value.substringAfter(",").substringBefore(")").toInt()

                result += firstNumber * secondNumber
            }
        }

        println("Day3 Part1: $result")
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day3_1.txt")?.let { readFile(it.toURI()) }

        //println("Day3 Part2: $safeCount")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()
}