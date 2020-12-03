import java.io.File

fun main() {
    val input = File("input/3.txt").readLines()

    val width = input[0].length
    var result = 1L

    val steps = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2
    )

    for ((stepX, stepY) in steps) {
        var x = 0
        var y = 0
        var trees = 0
        while (y < input.size) {
            if (input[y][x % width] == '#') {
                trees++
            }
            x += stepX
            y += stepY
        }
        result *= trees
    }

    println(result)
    require(result == 2265549792)
}