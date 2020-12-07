import java.io.File

data class Bag(val n: Int, val name: String)

fun main() {
    val input = File("input/7.txt").readLines()

    val edges = mutableMapOf<String, MutableList<Bag>>()
    for (line in input) {
        val matches = Regex("""(\d+ \w+ \w+|\w+ \w+) bag""").findMatches(line)
        for (match in matches.drop(1)) {
            if (match == "no other") continue
            edges.getOrPut(matches.first()) { mutableListOf() }
                .add(Bag(n = match.take(1).toInt(), name = match.drop(2)))
        }
    }

    fun countBags(start: String): Int =
        edges[start].orEmpty().sumBy { (n, name) -> n + (n * countBags(name)) }

    val count = countBags(start = "shiny gold")

    println(count)
    require(count == 27526)
}