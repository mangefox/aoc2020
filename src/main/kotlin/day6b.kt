import java.io.File

fun main() {
    val input = File("input/6.txt").readText().split("\n\n")

    var answer = 0
    for (group in input) {
        val lines = group.lines()
        val seen = mutableSetOf<Char>()
        for (line in lines)
            for (ch in line)
                if (lines.all { ch in it })
                    seen.add(ch)
        answer += seen.size
    }

    println(answer)
    require(answer == 3243)
}