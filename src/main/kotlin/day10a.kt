import java.io.File

fun main() {
    val input = (listOf(0) + File("input/10.txt").readLines().toInts()
        .sorted())
        .zipWithNext { a, b -> b - a }

    val ones = input.count { it == 1 }
    val threes = input.count { it == 3 } + 1

    println(ones * threes)
    require(ones * threes == 1836)
}