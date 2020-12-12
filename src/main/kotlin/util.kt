fun List<String>.toInts() = this.map(String::toInt)

fun List<String>.toLongs() = this.map(String::toLong)

fun Regex.findMatches(input: String): List<String> = this.findAll(input).map { it.groupValues[1] }.toList()

fun Array<CharArray>.copy() = this.map { it.clone() }.toTypedArray()