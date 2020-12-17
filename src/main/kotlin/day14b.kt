import java.io.File

import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    val input = File("input/14.txt").readLines()

    fun decodeMemory(mask: String, value: Long): List<Long> {
        val mutValue = value.toString(2)
            .padStart(36, '0')
            .takeLast(36)
            .toCharArray()

        val numX = mask.count { it == 'X' }

        for ((idx, char) in mask.withIndex()) {
            if (char == '1') mutValue[idx] = '1'
        }

        val xIndices = mask.mapIndexedNotNull { idx, ch -> if (ch == 'X') idx else null }

        val result = mutableListOf<Long>()
        for (i in 0 until 2.0.pow(numX.toDouble()).roundToInt()) {
            var seenX = 0
            val bits = i.toString(2).padStart(numX, '0')
            for (xind in xIndices) {
                mutValue[xind] = bits[seenX++]
            }
            result.add(mutValue.joinToString("").takeLast(36).toLong(2))
        }

        return result
    }

    val memory = mutableMapOf<Long, Long>()

    var mask = ""
    for (line in input) {
        val (instr, value) = line.split(" = ")
        if (instr == "mask") {
            mask = value
        } else {
            val idx = Regex("""\[(\d+)\]""").findMatches(instr)[0].toInt()
            for (memlocation in decodeMemory(mask, idx.toLong())) {
                memory[memlocation] = value.toLong()
            }
        }
    }

    val sum = memory.values.sum()
    println(sum)
    require(sum == 4197941339968)
}