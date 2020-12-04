import java.io.File

fun main() {
    val input = File("input/4.txt").readText()
        .split("\n\n")
        .map { it.replace("\n", " ") }

    val tags: Map<String, (String) -> Boolean> = mapOf(
        "byr" to { it.toInt() in 1920..2002 },
        "iyr" to { it.toInt() in 2010..2020 },
        "eyr" to { it.toInt() in 2020..2030 },
        "hcl" to { Regex("#[a-f0-9]{6}").matches(it) },
        "ecl" to { Regex("(amb|blu|brn|gry|grn|hzl|oth)").matches(it) },
        "pid" to { Regex("[0-9]{9}").matches(it) },
        "hgt" to { when {
            it.endsWith("cm") -> it.dropLast(2).toInt() in 150..193
            it.endsWith("in") -> it.dropLast(2).toInt() in 59..76
            else -> false
        }})

    var valid = 0
    for (line in input) {
        val matches = Regex("((byr|iyr|eyr|hgt|hcl|ecl|pid):([#a-z0-9]*))").findAll(line)
        val numValid = matches.count { tags[it.groupValues[2]]?.invoke(it.groupValues[3]) ?: false }
        if (numValid == 7) valid++
    }

    println(valid)
    require(valid == 114)
}