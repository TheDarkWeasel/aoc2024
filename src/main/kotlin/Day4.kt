import java.io.File
import java.net.URI

class Day4 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day4_1.txt")?.let { readFile(it.toURI()) }
        val matrix = inputs?.map { it.toCharArray().toList() }

        var result = 0

        matrix?.let { nonNullMatrix ->
            for (y in nonNullMatrix.indices) {
                for (x in nonNullMatrix[y].indices) {
                    for (direction in Direction.entries) {
                        var word = ""
                        for (i in 0..3) {
                            val newX = x + (direction.x * i)
                            val newY = y + (direction.y * i)

                            if (newX < 0 || newX >= nonNullMatrix.size || newY < 0 || newY >= nonNullMatrix[y].size) {
                                break
                            }
                            word += nonNullMatrix[newY][newX]

                            if (word == "XMAS") {
                                result++
                                break
                            }
                        }
                    }
                }
            }
        }

        println("Day4 Part1: $result")
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day4_1.txt")?.let { readFile(it.toURI()) }


        // println("Day4 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    // May not be the correct names, but it does not matter for this problem
    private enum class Direction(val x: Int, val y: Int) {
        Up(0, -1),
        Down(0, 1),
        Left(-1, 0),
        Right(1, 0),
        DiagonalUpLeft(-1, -1),
        DiagonalUpRight(1, -1),
        DiagonalDownLeft(-1, 1),
        DiagonalDownRight(1, 1)
    }
}