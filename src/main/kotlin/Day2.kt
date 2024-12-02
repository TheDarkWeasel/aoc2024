import java.io.File
import java.net.URI
import kotlin.math.abs

class Day2 {

    fun solvePart1() {
        val inputs = this::class.java.getResource("day2_1.txt")?.let { readFile(it.toURI()) }

        var safeCount = 0

        inputs?.forEach { input ->
            val levels = input.split(" ")
            var lastDiff = levels[1].toInt() - levels[0].toInt()

            if (isDiffDangerous(lastDiff))
                return@forEach

            var lastValue = levels[0]

            val remainingLevels = levels.subList(1, levels.size)
            var isSafe = true

            remainingLevels.forEach { level ->
                val newDiff = level.toInt() - lastValue.toInt()
                if (!isDiffDangerous(newDiff) && !(lastDiff > 0 && newDiff < 0) && !(lastDiff < 0 && newDiff > 0)) {
                    lastValue = level
                    lastDiff = newDiff
                } else {
                    isSafe = false
                }
            }

            if (isSafe) {
                safeCount++
            }
        }

        println("Day2 Part1: $safeCount")
    }

    private fun isDiffDangerous(diff: Int) = abs(diff) < 1 || abs(diff) > 3

    private fun readFile(fileName: URI): List<String> = File(fileName).readLines()
}