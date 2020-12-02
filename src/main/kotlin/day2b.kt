import java.io.File

fun main() {
    val input = File("input/2.txt").readLines()

    var goodPasswords = 0

    for (line in input) {
        val (count, letter, password) = line.split(" ")
        val (pos1, pos2) = count.split("-").toInts()
        var matches = 0
        if (password[pos1-1] == letter.first()) matches++
        if (password[pos2-1] == letter.first()) matches++
        if (matches == 1) goodPasswords += 1
    }

    println(goodPasswords)
    require(goodPasswords == 294)
}