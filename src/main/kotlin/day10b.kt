import java.io.File

fun main() {
    val input = File("input/10.txt").readLines().toInts().sorted()
    val inp = listOf(0) + input + listOf(Int.MAX_VALUE)

    val series = mutableListOf<List<Int>>()
    var tmp = mutableListOf<Int>()
    for ((a, b) in inp.zipWithNext()) {
        if (b - a == 1) {
            tmp.add(a)
            tmp.add(b)
        } else {
            series.add(tmp.distinct().sorted())
            tmp = mutableListOf()
        }
    }

    var sum = 1L
    for (l in series) {
        if (l.size == 3) sum *= 2
        if (l.size == 4) sum *= 4
        if (l.size == 5) sum *= 7
    }
    println(sum)
    require(sum == 43406276662336L)
}