import java.io.File

fun main() {
    val input = File("input/6.txt").readText().split("\n\n")

    val ans = input.map { line -> line
        .filter { it in 'a'..'z' }
        .toList()
        .distinct()
        .count()
    }.reduce(Integer::sum)

    println(ans)
    require(ans == 6506)
}