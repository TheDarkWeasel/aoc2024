import java.io.File
import java.net.URI

class Day6 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day6_1.txt")?.let { readFile(it.toURI()) }!!

        val matrix = inputs.map { it.toCharArray().toList().toMutableList() }.toMutableList()

        var guardX = 0
        var guardY = 0

        for (y in matrix.indices) {
            for (x in matrix[y].indices) {
                if (matrix[y][x] == '^') {
                    guardX = x
                    guardY = y
                }
            }
        }

        var currentDirection = Direction.Up

        try {
            while (true) {
                var nextX = guardX + currentDirection.x
                var nextY = guardY + currentDirection.y
                while (matrix[nextY][nextX] != '#') {
                    guardX = nextX
                    guardY = nextY
                    matrix[guardY][guardX] = 'X'
                    nextX = guardX + currentDirection.x
                    nextY = guardY + currentDirection.y
                }
                currentDirection = currentDirection.nextDirection()
            }
        } catch (e: IndexOutOfBoundsException) {
            printMatrix(matrix)
        }

        var result = 0
        for (y in matrix.indices) {
            for (x in matrix[y].indices) {
                if (matrix[y][x] == 'X' || matrix[y][x] == '^') {
                    result++
                }
            }
        }

        println("Day6 Part1: $result")
    }

    private fun printMatrix(matrix: List<List<Char>>) {
        for (y in matrix.indices) {
            println()
            for (x in matrix[y].indices) {
                print(matrix[y][x])
            }
        }
        println()
    }

    fun solvePart2() {
        val inputs = this::class.java.getResource("day6_1.txt")?.let { readFile(it.toURI()) }


        //println("Day6 Part2: $result")
    }

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()

    private fun Direction.nextDirection(): Direction {
        return when (this) {
            Direction.Up -> Direction.Right
            Direction.Right -> Direction.Down
            Direction.Down -> Direction.Left
            Direction.Left -> Direction.Up
        }
    }

    // May not be the correct names, but it does not matter for this problem
    private enum class Direction(val x: Int, val y: Int) {
        Up(0, -1),
        Down(0, 1),
        Left(-1, 0),
        Right(1, 0),
    }
}