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
        val mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        val doRegex = "do\\(\\)".toRegex()
        val dontRegex = "don't\\(\\)".toRegex()

        var result = 0

        var complete = ""

        inputs?.forEach { line ->
            complete += line
        }

        val muls = mulRegex.findAll(complete).toList()

        muls.filter { mul ->
            val before = complete.substring(0, mul.range.first)
            val dontsBeforeMul = dontRegex.findAll(before)
            val dosBeforeMul = doRegex.findAll(before)

            if (dosBeforeMul.count() == 0 && dontsBeforeMul.count() > 0)
                false
            else if (dosBeforeMul.count() > 0 && dontsBeforeMul.count() == 0)
                true
            else if (dosBeforeMul.count() == 0 && dontsBeforeMul.count() == 0)
                true
            else
                dosBeforeMul.last().range.last > dontsBeforeMul.last().range.last
        }.forEach { enabled ->
            val firstNumber = enabled.value.substringAfter("mul(").substringBefore(",").toInt()
            val secondNumber = enabled.value.substringAfter(",").substringBefore(")").toInt()

            result += firstNumber * secondNumber
        }

        println("Day3 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()
}