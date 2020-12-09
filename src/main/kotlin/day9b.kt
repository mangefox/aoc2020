import java.io.File
import java.util.LinkedList

fun main() {
    val input = File("input/9.txt").readLines().toLongs()

    val goal = 1504371145L

    for (i in 2..input.size) {
        for (window in input.windowed(size = i, step = 1)) {
            if (window.sum() == goal) {
                println(window.min()!! + window.max()!!)
                require(window.min()!! + window.max()!! == 183278487L)
                return
            }
        }
    }
}