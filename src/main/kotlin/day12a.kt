import java.io.File
import kotlin.math.abs

private enum class Direction(private val n: Int) {
    N(0), E(90), S(180), W(270);

    fun turn(dir: String, deg: Int): Direction {
        return when (dir) {
            "R" -> fromInt((this.n + deg + 360) % 360)
            "L" -> fromInt((this.n - deg + 360) % 360)
            else -> panic()
        }
    }

    private fun fromInt(deg: Int) = values().first { it.n == deg}
}

fun main() {
    val input = File("input/12.txt").readLines()

    var x = 0
    var y = 0
    var shipDir = Direction.E

    fun move(dir: String, amount: Int) {
        when (dir) {
            "N" -> y += amount
            "S" -> y -= amount
            "W" -> x -= amount
            "E" -> x += amount
            "F" -> move(shipDir.name, amount)
            "L", "R" -> shipDir = shipDir.turn(dir, deg = amount)
        }
    }

    for (line in input) {
        val dir = line.take(1)
        val amount = line.drop(1).toInt()
        move(dir, amount)
    }

    println(abs(x) + abs(y))
    require(abs(x) + abs(y) == 2270)
}