import java.io.File
import java.util.LinkedList

fun main() {
    val input = File("input/8.txt").readLines()

    for ((j, line) in input.withIndex()) {

        val modified = when {
            line.startsWith("nop") -> line.replace("nop", "jmp")
            line.startsWith("jmp") -> line.replace("jmp", "nop")
            else -> line
        }

        val seen = mutableSetOf<Int>()
        var acc = 0
        var i = 0

        while (i in input.indices) {
            if (!seen.add(i)) break

            val instr = if (i == j) modified else input[i]
            when (instr.take(3)) {
                "nop" -> { /* noop */ }
                "acc" -> { acc += instr.drop(4).toInt() }
                "jmp" -> { i += instr.drop(4).toInt()-1 }
            }
            i++
        }

        if (i !in seen) {
            println(acc)
            require(acc == 920)
            break
        }
    }
}