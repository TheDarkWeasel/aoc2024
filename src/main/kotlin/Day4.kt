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

                            if (newX < 0 || newX >= nonNullMatrix.size || newY < 0 || newY >= nonNullMatrix[x].size) {
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

        val matrix = inputs?.map { it.toCharArray().toList() }

        var result = 0

        matrix?.let { nonNullMatrix ->
            for (y in nonNullMatrix.indices) {
                for (x in nonNullMatrix[y].indices) {
                    var masCount = 0
                    for (xMasShapeDirection in XMASShapeDirections.entries) {
                        val yChar1 = y + xMasShapeDirection.dir1.y
                        val yChar2 = y + xMasShapeDirection.dir2.y
                        val xChar1 = x + xMasShapeDirection.dir1.x
                        val xChar2 = x + xMasShapeDirection.dir2.x

                        if (yChar1 < 0 || yChar1 >= nonNullMatrix.size || yChar2 < 0 || yChar2 >= nonNullMatrix.size) {
                            continue
                        }
                        if (xChar1 < 0 || xChar1 >= nonNullMatrix[y].size || xChar2 < 0 || xChar2 >= nonNullMatrix[y].size) {
                            continue
                        }

                        val char1 = nonNullMatrix[yChar1][xChar1]
                        val char2 = nonNullMatrix[y][x]
                        val char3 = nonNullMatrix[yChar2][xChar2]

                        if (char1 == 'M' && char2 == 'A' && char3 == 'S') {
                            masCount++
                        }
                    }
                    if (masCount == 2) {
                        result++
                    }
                }
            }
        }

        println("Day4 Part2: $result")
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

    private enum class XMASShapeDirections(val dir1: Direction, val dir2: Direction) {
        TopLeftDownRight(Direction.DiagonalUpLeft, Direction.DiagonalDownRight),
        TopRightDownLeft(Direction.DiagonalUpRight, Direction.DiagonalDownLeft),
        BottomLeftUpRight(Direction.DiagonalDownLeft, Direction.DiagonalUpRight),
        BottomRightUpLeft(Direction.DiagonalDownRight, Direction.DiagonalUpLeft)
    }
}