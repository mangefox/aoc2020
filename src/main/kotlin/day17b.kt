import java.io.File


fun main() {
    data class Coord(val x: Int, val y: Int, val z: Int, val u: Int)

    fun getNeighbours(coord: Coord): List<Coord> {
        val neighbours = mutableListOf<Coord>()
        for (x in coord.x-1..coord.x+1) {
            for (y in coord.y-1..coord.y+1) {
                for (z in coord.z-1..coord.z+1) {
                    for (u in coord.u-1..coord.u+1) {
                        neighbours += Coord(x, y, z, u)
                    }
                }
            }
        }
        return neighbours
    }

    val input = File("input/17.txt").readLines()

    var cubes = mutableMapOf<Coord, Boolean>()
    for ((r, row) in input.withIndex()) {
        for ((c, ch) in row.withIndex()) {
            val coord = Coord(c, r, 0, 0)
            val active = ch == '#'
            cubes[coord] = active
        }
    }

    val tmpCubes = mutableMapOf<Coord, Boolean>()
    tmpCubes.putAll(cubes)

    repeat(7) {
        cubes = HashMap(tmpCubes)
        
        // create/add the next outer shell of nodes
        for ((coord, _) in tmpCubes) {
            val nextLayer = getNeighbours(coord)
            for (n in nextLayer) {
                if (n !in cubes) cubes[n] = false
            }
        }

        for ((coord, active) in cubes) {
            val neighbours = getNeighbours(coord)
            val activeNeighbours = neighbours.count { n ->
                n != coord && n in cubes && cubes[n] == true
            }
            when {
                active && activeNeighbours in 2..3 -> tmpCubes[coord] = true
                !active && activeNeighbours == 3 -> tmpCubes[coord] = true
                else -> tmpCubes[coord] = false
            }
        }
    }

    val answer = cubes.count { it.value }
    println(answer)
    require(answer == 2136)
}