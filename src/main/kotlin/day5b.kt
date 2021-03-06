import java.io.File

fun main() {
    val input = File("input/5.txt").readText()
        .replace(Regex("[BR]"), "1")
        .replace(Regex("[FL]"), "0")
        .lines()

    val passes = mutableSetOf<Int>()
    for (line in input) {
        val row = line.take(7).toInt(radix = 2)
        val col = line.takeLast(3).toInt(radix = 2)
        passes.add( row * 8 + col)
    }

    val missing = (passes.min()!!..passes.max()!!).first { it !in passes }

    println(missing)
    require(missing == 597)
}