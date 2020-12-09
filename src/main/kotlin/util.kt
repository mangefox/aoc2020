fun List<String>.toInts() = this.map(String::toInt)

fun List<String>.toLongs() = this.map(String::toLong)

fun Regex.findMatches(input: String): List<String> = this.findAll(input).map { it.groupValues[1] }.toList()