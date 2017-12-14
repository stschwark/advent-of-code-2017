object Day13 {
    fun part1(input: String) : Int {
        val layers = layersFrom(input)
        return layers.map { layer -> if (layer.containsBadPacket()) layer.score() else 0 }.sum()
    }

    fun part2(input: String) : Int {
        val layers = layersFrom(input)

        var delay = 0
        while (layers.filter { layer -> layer.containsBadPacket(delay) }.isNotEmpty()) {
            delay++
        }

        return delay
    }

    private fun layersFrom(input: String) =
            input.split("\n")
                    .map { row -> """(\d+): (\d+)""".toRegex().matchEntire(row)!!.groupValues}
                    .map { groups -> Layer(groups[1].toInt(), groups[2].toInt()) }

    private data class Layer(val depth: Int, val range: Int) {
        fun containsBadPacket(delay: Int = 0) = (depth + delay) % (2 * (range - 1)) == 0
        fun score() = depth * range
    }
}