import java.io.File

fun File.intLines() = this.readLines().map(String::toInt)
