import java.io.File

fun main() {
    val input = File("input/13.txt").readLines()

    val time = input[0].toInt()
    val intervals = input[1].split(",").mapNotNull { it.toIntOrNull() }

    println("time = $time")
    println("intervals = $intervals")

    var best = Int.MAX_VALUE
    var bestBus = -1
    for (interval in intervals) {
        var i = 1
        while (i * interval < time) i++
        println("$interval - $i * $interval = ${i * interval}")

        val waitTime = i*interval - time
        if (waitTime < best) {
            best = waitTime
            bestBus = interval
        }
    }

    println(best * bestBus)
    require(best * bestBus == 161)
}