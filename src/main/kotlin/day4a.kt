import java.io.File

fun main() {
    val input = File("input/4.txt").readText()
        .split("\n\n")
        .map { it.replace("\n", " ") }

    val tags = setOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

    var valid = 0
    for (line in input) {
        val validTags = tags.count { tag -> line.contains(tag) }
        if (validTags == 7) valid++
    }

    println(valid)
    require(valid == 196)
}