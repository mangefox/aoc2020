import java.io.File

fun main() {
    val input = File("input/6.txt").readText().split("\n\n")

    val answer = input.sumBy { group -> group.groupingBy { it }.eachCount().count { (_, count) -> count == group.lines().size } }

    println(answer)
    require(answer == 3243)
}