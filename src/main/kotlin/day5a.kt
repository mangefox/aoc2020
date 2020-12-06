import java.io.File

fun main() {
    val input = File("input/5.txt").readText()
        .replace(Regex("[BR]"), "1")
        .replace(Regex("[FL]"), "0")
        .lines()

    val best = input.map { line ->
        val row = line.take(7).toInt(radix = 2)
        val col = line.takeLast(3).toInt(radix = 2)
        row * 8 + col
    }.max()

    println(best)
    require(best == 801)
}