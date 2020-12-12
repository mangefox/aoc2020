import java.io.File
import kotlin.math.abs

private data class Coords(var x: Int, var y: Int) {
    fun rotate(dir: String, degrees: Int) {
        val deg = when (dir) {
            "R" -> (degrees + 360) % 360
            "L" -> (-degrees + 360) % 360
            else -> panic()
        }
        when (deg) {
            90 -> x = y.also { y = -x }
            180 -> x = -x.also { y = -y }
            270 -> x = -y.also { y = x }
            360 -> { /* noop */ }
        }
    }
}

fun main() {
    val input = File("input/12.txt").readLines()

    val ship = Coords(x = 0, y = 0)
    val waypoint = Coords(x = 10, y = 1)

    fun move(dir: String, amount: Int) {
        when (dir) {
            "N" -> waypoint.y += amount
            "S" -> waypoint.y -= amount
            "W" -> waypoint.x -= amount
            "E" -> waypoint.x += amount
            "F" -> {
                ship.x += waypoint.x * amount
                ship.y += waypoint.y * amount
            }
            "L", "R" -> waypoint.rotate(dir, amount)
        }
    }

    for (line in input) {
        val dir = line.take(1)
        val amount = line.drop(1).toInt()
        move(dir, amount)
    }

    println(abs(ship.x) + abs(ship.y))
    require(abs(ship.x) + abs(ship.y) == 138669)
}