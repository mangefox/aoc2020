import java.io.File

fun main() {
    val input = File("input/16.txt").readText()

    val rules = mutableMapOf<String, (Int) -> Boolean>()
    for (line in input.lines()) {
        val matches = Regex("""(.*): (\d+\-\d+) or (\d+\-\d+)""").find(line)
        if (matches != null) {
            val (name, g1, g2) = matches.destructured
            println("name = $name")
            println("g1 = $g1")
            println("g2 = $g2")
            rules[name] = { i ->
                val r1 = g1.split("-").toInts()
                val r2 = g2.split("-").toInts()
                i in r1[0]..r1[1] || i in r2[0]..r2[1]
            }
        }
    }

    val tickets = Regex("""\R([0-9\,]+)""").findMatches(input).map { it.split(",").toInts() }
    val myTicket = tickets.first()
    val nearbyTickets = tickets.drop(1)

    val result = mutableListOf<Int>()
    for (i in nearbyTickets[0].indices) {
        for (ticket in nearbyTickets) {
            println("ticket[i] = ${ticket[i]} - ${rules.values.map { it.invoke(ticket[i]) }}")
            if (rules.values.map { it.invoke(ticket[i]) }.filter { it }.isEmpty()) {
                println("${ticket[i]} sucks")
                result.add(ticket[i])
            }
        }
    }

    println("myTicket = $myTicket")
    nearbyTickets.forEach { println(it) }

    println(rules["class"]!!.invoke(4))
    println(rules["class"]!!.invoke(1))

    println(result.sum())
    require(result.sum() == 23122)
}