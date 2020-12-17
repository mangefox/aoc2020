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

    val invalidTickets = mutableSetOf<List<Int>>()
    for (i in nearbyTickets[0].indices) {
        for (ticket in nearbyTickets) {
            println("ticket[i] = ${ticket[i]} - ${rules.values.map { it.invoke(ticket[i]) }}")
            if (rules.values.map { it.invoke(ticket[i]) }.filter { it }.isEmpty()) {
                println("${ticket[i]} sucks")
                invalidTickets.add(ticket)
            }
        }
    }

    println("myTicket = $myTicket")
    nearbyTickets.forEach { println(it) }

    println(invalidTickets)
    val validTickets = nearbyTickets - invalidTickets
    println(validTickets)


    var possibleRules = mutableMapOf<String, MutableList<Int>>() // rule name -> column indexes that match the rule
    for (i in validTickets[0].indices) {
        val col = validTickets.map { it[i] }
        println("col = $col")
        for ((name, rule) in rules.entries) {
            val allColsMatchRule = col.all(rule)
            if (allColsMatchRule) {
                println("$col matches $name rule")
                possibleRules.getOrPut(name) { mutableListOf() }.add(i)
            }
        }
        val matchingRules = rules.entries.map { (name, rule) -> col.filter { n -> !rule.invoke(n) }.isEmpty() }
        println(matchingRules)
    }

    possibleRules.forEach { println(it) }

    val departureNumbers = mutableListOf<Long>()
    val seen = mutableSetOf<Int>()
    for ((name, indices) in possibleRules.toList().sortedBy { it.second.size }) {
//        println("name=$name indices=$indices")
        for (i in indices) {
            if (i !in seen) {
                println("column $i (myticket=${myTicket[i]}) belongs to rule '$name'")
                seen.add(i)
                if ("departure" in name) departureNumbers.add(myTicket[i].toLong())
            }
        }
    }

    println("departure values = $departureNumbers")
    val answer = departureNumbers.reduce { acc, cur -> acc * cur }
    println(answer)
    require(answer == 362974212989L)
}