import java.io.File
import java.util.LinkedList

fun main() {
    val input = File("input/7.txt").readLines()

    val edges = mutableMapOf<String, MutableList<String>>()
    for (line in input) {
        val matches = Regex("""(\w+ \w+) bag""").findMatches(line)
        for (match in matches.drop(1)) {
            edges.getOrPut(matches.first()) { mutableListOf() }.add(match)
        }
    }

    var count = 0
    for ((_, v) in edges) {
        val queue = LinkedList(v)
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            if (cur == "shiny gold") {
                count++
                break
            }
            queue += edges[cur].orEmpty()
        }
    }

    println(count)
    require(count == 378)
}
