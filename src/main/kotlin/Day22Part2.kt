object Day22Part2 {
    fun part2(input: String, bursts: Int) : Int {
        val cluster = Cluster(input)
        val virus = Sporifica(cluster)

        repeat(bursts) {
            virus.activate()
        }

        return cluster.infections
    }

    private class Sporifica(private val cluster: Cluster) {
        private var current = Pair(0,0)
        private var direction = Direction.North as Direction

        fun activate() {
            turn()

            interact()

            moveForward()
        }

        private fun turn() {
            direction = when (cluster.health(current)) {
                NodeHealth.CLEAN -> direction.turnLeft()
                NodeHealth.WEAKENED -> direction
                NodeHealth.INFECTED -> direction.turnRight()
                NodeHealth.FLAGGED -> direction.turnRight().turnRight()
            }
        }

        private fun interact() {
            when(cluster.health(current)) {
                NodeHealth.CLEAN -> cluster.weaken(current)
                NodeHealth.WEAKENED -> cluster.infect(current)
                NodeHealth.INFECTED -> cluster.flag(current)
                NodeHealth.FLAGGED -> cluster.clean(current)
            }
        }

        private fun moveForward() {
            current = when(direction) {
                Direction.North -> Pair(current.first - 1, current.second)
                Direction.East -> Pair(current.first, current.second + 1)
                Direction.South -> Pair(current.first + 1, current.second)
                Direction.West -> Pair(current.first, current.second - 1)
            }
        }

        private sealed class Direction {

            abstract fun turnLeft() : Direction

            abstract fun turnRight() : Direction

            object North : Direction() {
                override fun turnLeft() = West
                override fun turnRight() = East
            }

            object East : Direction() {
                override fun turnLeft() = North
                override fun turnRight() = South
            }

            object South : Direction() {
                override fun turnLeft() = East
                override fun turnRight() = West
            }

            object West : Direction() {
                override fun turnLeft() = South
                override fun turnRight() = North
            }
        }
    }

    private data class Cluster(val start: String) {
        val nodes = mutableMapOf<Pair<Int, Int>, NodeHealth>()

        var infections = 0
            private set

        init {
            val rows = start.split("\n")
            rows.mapIndexed { rowIndex, row ->
                row.mapIndexed{ colIndex, status ->
                    if (status == '#') {
                        nodes[Pair(rowIndex - rows.size / 2, colIndex - row.length / 2)] = NodeHealth.INFECTED
                    }
                }
            }
        }

        fun weaken(coors: Pair<Int, Int>) { nodes[coors] = NodeHealth.WEAKENED }

        fun infect(coors: Pair<Int, Int>) { nodes[coors] = NodeHealth.INFECTED; infections += 1 }

        fun flag(coors: Pair<Int, Int>) { nodes[coors] = NodeHealth.FLAGGED }

        fun clean(coors: Pair<Int, Int>) { nodes[coors] = NodeHealth.CLEAN }

        fun health(coors: Pair<Int, Int>) = nodes.getOrDefault(coors, NodeHealth.CLEAN)

    }

    private enum class NodeHealth { CLEAN, WEAKENED, INFECTED, FLAGGED }
}