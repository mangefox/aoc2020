import java.io.File

fun main() {
    val input = File("input/14.txt").readLines()

    fun applyMask(mask: String, value: Long): Long {
        val mutValue = value.toString(2)
            .padStart(36, '0')
            .takeLast(36)
            .toCharArray()

        for ((idx, char) in mask.withIndex()) {
            if (char != 'X') mutValue[idx] = char
        }

        return mutValue.joinToString("").takeLast(36).toLong(2)
    }

    val memory = mutableMapOf<Int, Long>()

    var mask = ""
    for (line in input) {
        val (instr, value) = line.split(" = ")
        if (instr == "mask") {
            mask = value
        } else {
            val idx = Regex("""\[(\d+)\]""").findMatches(instr)[0].toInt()
            memory[idx] = applyMask(mask, value.toLong())
        }
    }

    println(memory.values.sum())
    require(memory.values.sum() == 15172047086292L)
}