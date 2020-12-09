import java.io.File

fun main() {
    val input = File("input/9.txt").readLines().toLongs()

    for (window in input.windowed(size = 25+1, step = 1)) {
        if (!canSumToTarget(window.last(), window.dropLast(1))) {
            println(window.last())
            require(window.last() == 1504371145L)
            break
        }
    }
}

fun canSumToTarget(target: Long, numbers: List<Long>): Boolean {
    for (i in numbers) {
        for (j in numbers) {
            if (i != j && i + j == target) return true
        }
    }
    return false
}