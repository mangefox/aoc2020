import java.io.File

fun main() {
    val input = File("input/2.txt").readLines()

    var goodPasswords = 0

    for (line in input) {
        val (count, letter, password) = line.split(" ")
        val letterCount = password.count { it == letter.first()  }
        val (low, high) = count.split("-")
        if (letterCount >= low.toInt() && letterCount <= high.toInt()) goodPasswords += 1
    }

    println(goodPasswords)
}