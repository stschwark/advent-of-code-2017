import kotlin.math.absoluteValue

object Day11 {
    fun part1(input: String) : Int {
        return input.split(",")
                .fold(CubeCoordinate.CENTER) { current, direction -> current.move(direction) }
                .distanceTo(CubeCoordinate.CENTER)
    }

    fun part2(input: String) : Int {
        val visited = mutableListOf<CubeCoordinate>()

        input.split(",")
                .fold(CubeCoordinate.CENTER) { current, direction ->
                    val next = current.move(direction)
                    visited += next
                    next
                }

        return visited.map { it.distanceTo(CubeCoordinate.CENTER) }.max() ?: 0
    }

    private data class CubeCoordinate(val x: Int, val y: Int, val z: Int) {
        companion object {
            val CENTER = CubeCoordinate(0, 0, 0)
        }

        fun move(direction: String) =
                when(direction) {
                    "n" -> CubeCoordinate(x, y + 1, z - 1)
                    "ne" -> CubeCoordinate(x + 1, y, z -1)
                    "se" -> CubeCoordinate(x  + 1, y - 1, z)
                    "s" -> CubeCoordinate(x, y - 1, z + 1)
                    "sw" -> CubeCoordinate(x - 1, y, z + 1)
                    "nw" -> CubeCoordinate(x - 1, y + 1, z)
                    else -> throw IllegalArgumentException()
                }

        fun distanceTo(b: CubeCoordinate) =
                ((x - b.x).absoluteValue + (y - b.y).absoluteValue + (z - b.z).absoluteValue) / 2
    }
}