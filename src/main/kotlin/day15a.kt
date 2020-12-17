import java.io.File

fun main() {
    val input = File("input/15.txt").readText().split(",").toLongs()

    val mem = mutableMapOf<Long, MutableList<Long>>()
    var iter = 1L
    var last = -1L

    for (i in input) {
        mem.getOrPut(i) { mutableListOf() }.add(iter++)
        last = i
    }

    while (iter <= 2020) {
        val hist = mem[last]
        last = if (hist == null || hist.size < 2) {
            0
        } else {
            hist[hist.lastIndex] - hist[hist.lastIndex-1]
        }
        mem.getOrPut(last) { mutableListOf() }.add(iter++)
    }

    println(last)
    require(last == 959L)
}