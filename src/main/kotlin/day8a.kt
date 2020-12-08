import java.io.File
import java.util.LinkedList

fun main() {
    val input = File("input/8.txt").readLines()

    val seen = mutableSetOf<Int>()
    var acc = 0
    var i = 0

    while (i in input.indices) {
        if (!seen.add(i)) break

        val instr = input[i]
        when (instr.take(3)) {
            "nop" -> { /* noop */ }
            "acc" -> { acc += instr.drop(4).toInt() }
            "jmp" -> { i += instr.drop(4).toInt()-1 }
        }
        i++
    }

    println(acc)
    require(acc == 1584)
}
