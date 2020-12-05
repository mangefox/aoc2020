import java.io.File

fun main() {
    val input = File("input/5.txt").readLines()

    val passes = mutableSetOf<Int>()
    for (line in input) {
        val row = line.take(7)
            .replace("B", "1")
            .replace("F", "0")
            .toInt(radix = 2)
        val col = line.takeLast(3)
            .replace("R", "1")
            .replace("L", "0")
            .toInt(radix = 2)
        passes.add( row * 8 + col)
    }

    val missing = (passes.min()!!..passes.max()!!).first { it !in passes }

    println(missing)
    require(missing == 597)
}