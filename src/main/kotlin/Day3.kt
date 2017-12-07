import kotlin.math.absoluteValue

object Day3 {
    fun part1(square: Int): Int {
        val storage = SpiralMemory()

        val location = storage.locationFor(square)

        return location.first.absoluteValue + location.second.absoluteValue
    }

    fun part2(square: Int): Int {
        val storage = SpiralMemory()

        storage.store(1, 1)

        (2..square).forEach { s ->
            val location = storage.locationFor(s)
            storage.store(location, storage.sumOfNeighbouringSquares(location))
        }

        return storage.retrieve(square)!!
    }

    private class SpiralMemory {

        private val memory : HashMap<Pair<Int, Int>, Int> = HashMap()

        fun locationFor(square: Int) : Pair<Int, Int> {
            var x = 0
            var y = 0
            var direction = Direction.RIGHT
            var length = 1

            (1 until square).forEach { index ->
                when(direction) {
                    Direction.RIGHT -> x += 1
                    Direction.UP -> y += 1
                    Direction.LEFT -> x -= 1
                    Direction.DOWN -> y -= 1
                }

                if (index % length == 0) {
                    direction = when(direction) {
                        Direction.RIGHT -> Direction.UP
                        Direction.UP -> Direction.LEFT
                        Direction.LEFT -> Direction.DOWN
                        Direction.DOWN -> Direction.RIGHT
                    }

                    if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                        length += 1
                    }
                }
            }

            return Pair(x, y)
        }

        fun store(square: Int, value: Int) {
            memory[locationFor(square)] = value
        }

        fun store(location: Pair<Int, Int>, value: Int) {
            memory[location] = value
        }

        fun retrieve(square: Int) = retrieve(locationFor(square))

        fun retrieve(location: Pair<Int, Int>) = memory[location]

        fun sumOfNeighbouringSquares(location: Pair<Int, Int>) : Int {
            var sum = 0
            for(dx in -1..1) {
                for (dy in -1..1) {
                    if (!(dx == 0 && dy == 0)) {
                        val lookupLocation = Pair(location.first+dx, location.second+dy)
                        retrieve(lookupLocation)?.let { value ->
                            sum += value
                        }
                    }
                }
            }
            return sum
        }

    }

    private enum class Direction {
        RIGHT,
        UP,
        LEFT,
        DOWN
    }
}

