import java.io.File
import java.net.URI

class Day7 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day7_1.txt")?.let { readFile(it.toURI()) }!!

        // one line looks like this: "7628244: 4 6 99 4 321"
        val equations = inputs.map { line ->
            val (result, numbers) = line.split(": ")
            Equation(result.toLong(), numbers.split(" ").map { it.toLong() })
        }

        val result = equations.filter {
            val operatorList = MutableList(it.numbers.size) { Oparator.Add }
            isValidEquation(it, operatorList, 0)
        }.fold(0L) { acc, equation -> acc + equation.result }


        println("Day7 Part1: $result")
    }

    private fun isValidEquation(
        equation: Equation,
        operatorList: MutableList<Oparator>,
        operatorToFlip: Int
    ): Boolean {
        if (checkSolution(equation, operatorList)) {
            return true
        }

        for (op in Oparator.entries) {
            operatorList[operatorToFlip] = op
            if (checkSolution(equation, operatorList)) {
                return true
            } else {
                if (operatorToFlip < operatorList.size - 1) {
                    if (isValidEquation(equation, operatorList, operatorToFlip + 1))
                        return true
                }
            }
        }

        return false
    }

    private fun checkSolution(
        equation: Equation,
        operatorList: MutableList<Oparator>
    ): Boolean {
        var result = equation.numbers[0]
        for (i in 1 until equation.numbers.size) {
            val op = operatorList[i]
            val num2 = equation.numbers[i]

            when (op) {
                Oparator.Add -> result += num2
                Oparator.Multiply -> result *= num2
            }
        }

        return result == equation.result
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day6_1.txt")?.let { readFile(it.toURI()) }!!

        //println("Day7 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    private data class Equation(val result: Long, val numbers: List<Long>)

    private enum class Oparator {
        Add,
        Multiply,
    }
}