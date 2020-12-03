import java.io.File

fun main() {
    val input = File("input/3.txt").readLines()

    var x = 0
    var trees = 0
    for (line in input) {
        if (line[x % line.length] == '#') {
            trees++
        }
        x += 3
    }

    println(trees)
    require(trees == 242)
}