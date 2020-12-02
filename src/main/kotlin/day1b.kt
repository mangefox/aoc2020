import java.io.File

fun main() {
    val input = File("input/1.txt").readLines().toInts()

    for (i in input.indices) {
        for (j in i+1..input.lastIndex) {
            for (k in j+1..input.lastIndex) {
                if (input[i] + input[j] + input[k] == 2020) {
                    println(input[i] * input[j] * input[k])
                    require(input[i] * input[j] * input[k] == 195700142)
                }
            }
        }
    }
}