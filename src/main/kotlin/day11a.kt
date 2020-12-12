import java.io.File

fun main() {
    var input = File("input/11.txt").readLines().map { it.toCharArray() }.toTypedArray()
    val seats = input.copy()

    val height = input.indices
    val width = input.first().indices

    val directions = listOf(-1 to 0, +1 to 0, 0 to -1, 0 to +1, -1 to -1, +1 to +1, -1 to +1, +1 to -1)

    fun updateSeat(arr: Array<CharArray>, x: Int, y: Int) {
        val occupied = directions.count { (dy, dx) ->
            y+dy in height && x+dx in width && input[y+dy][x+dx] == '#'
        }
        if (input[y][x] == 'L' && occupied == 0) arr[y][x] = '#'
        if (input[y][x] == '#' && occupied >= 4) arr[y][x] = 'L'
    }

    do {
        input = seats.map { it.clone() }.toTypedArray()
        for (y in seats.indices) {
            for (x in seats[y].indices) {
                updateSeat(seats, x, y)
            }
        }
    } while (!input.contentDeepEquals(seats))

    val occupied = seats.map { it.count { seat -> seat == '#' } }.sum()
    println(occupied)
    require(occupied == 2494)
}