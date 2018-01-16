object Day24 {
    fun part1(input: String) : Int {
        return validBridges(input).map { it.sumBy(Component::strength) }.max() ?: 0
    }

    fun part2(input: String) : Int {
        val validBridges = validBridges(input)
        val maxLength = validBridges.map { it.size }.max()
        return validBridges.filter { it.size == maxLength }.map { it.sumBy(Component::strength) }.max() ?: 0
    }

    private fun validBridges(input: String) : List<List<Component>> {
        val availableComponents = input.split("\n").map { row ->
            val ports = row.split("/").map { it.toInt() }.toList()
            Component(ports[0], ports[1])
        }.toSet()

        return findValidBridges(availableComponents)
    }

    private fun findValidBridges(available: Set<Component>, current: List<Component> = emptyList(), port: Int = 0) : List<List<Component>> {
        val compatible = available.filter { it.hasPort(port) }

        return if (compatible.isEmpty()) {
            listOf(current)
        } else {
            compatible.flatMap {
                findValidBridges(available - it, current + it, it.otherPort(port))
            }
        }
    }

    private data class Component(private val port1: Int, private val port2: Int) {
        fun hasPort(port: Int) = port1 == port || port2 == port
        fun otherPort(port: Int) = if (port1 == port) port2 else port1
        fun strength() = port1 + port2
    }
}