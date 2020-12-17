import java.io.File

fun main() {
    val input = File("input/13.txt").readLines()

    val time = input[0].toInt()
    val buses = input[1].split(",")

    println("time = $time")
    println("buses = $buses")

    buses
        .mapIndexed { idx, bus -> idx to bus.toIntOrNull() }
        .filter { it.second != null }
        .onEach { (idx, bus) -> println("$idx\t$bus") }
        .forEach { (idx, bus) -> println(bus!! - (idx % bus)) }

    // 29	29
    // 22	41
    // 632	661
    // 10	13
    // 8	17
    // 17	23
    // 461	521
    // 8	37
    // 16	19
    // paste into here
    //https://www.dcode.fr/chinese-remainder
    //require(answer == 213890632230818L)
}