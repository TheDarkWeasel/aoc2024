import java.io.File
import java.net.URI

class Day7 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day7_1.txt")?.let { readFile(it.toURI()) }!!

        val validOperators = listOf(Oparator.Add, Oparator.Multiply)
        val result = solve(inputs, validOperators)

        println("Day7 Part1: $result")
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day7_1.txt")?.let { readFile(it.toURI()) }!!

        val validOperators = Oparator.entries
        val result = solve(inputs, validOperators)

        println("Day7 Part2: $result")
    }

    private fun solve(
        inputs: List<String>,
        validOperators: List<Oparator>
    ): Long {
        // one line looks like this: "7628244: 4 6 99 4 321"
        val equations = inputs.map { line ->
            val (result, numbers) = line.split(": ")
            Equation(result.toLong(), numbers.split(" ").map { it.toLong() })
        }

        val result = equations.filter {
            val operatorList = MutableList(it.numbers.size) { Oparator.Add }
            isValidEquation(it, validOperators, operatorList, 0)
        }.fold(0L) { acc, equation -> acc + equation.result }
        return result
    }

    private fun isValidEquation(
        equation: Equation,
        validOperators: List<Oparator>,
        operatorList: MutableList<Oparator>,
        operatorToFlip: Int
    ): Boolean {
        if (checkSolution(equation, operatorList)) {
            return true
        }

        for (op in validOperators) {
            operatorList[operatorToFlip] = op
            if (checkSolution(equation, operatorList)) {
                return true
            } else {
                if (operatorToFlip < operatorList.size - 1) {
                    if (isValidEquation(equation, validOperators, operatorList, operatorToFlip + 1))
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
                Oparator.Concat -> result = (result.toString() + num2.toString()).toLong()
            }
        }

        return result == equation.result
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    private data class Equation(val result: Long, val numbers: List<Long>)

    private enum class Oparator {
        Add,
        Multiply,
        Concat,
    }
}