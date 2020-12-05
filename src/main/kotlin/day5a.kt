import java.io.File

fun main() {
    val input = File("input/5.txt").readLines()

    val best = input.map { line ->
        val row = line.take(7)
            .replace("F", "0")
            .replace("B", "1")
            .toInt(radix = 2)
        val col = line.takeLast(3)
            .replace("L", "0")
            .replace("R", "1")
            .toInt(radix = 2)
        row * 8 + col
    }.max()

    println(best)
    require(best == 801)
}