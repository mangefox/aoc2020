import java.io.File

fun main() {
    val input = File("input/2.txt").readLines()

    var goodPasswords = 0

    for (line in input) {
        val (count, letter, password) = line.split(" ")
        val occurrences = password.count { it == letter.first()  }
        val (low, high) = count.split("-").toInts()
        if (occurrences in low..high) goodPasswords += 1
    }

    println(goodPasswords)
    require(goodPasswords == 465)
}