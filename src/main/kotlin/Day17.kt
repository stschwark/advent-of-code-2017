object Day17 {
    fun part1(step: Int, iterations: Int) : Int {
        val buffer = mutableListOf(0)
        var position = 0
        (1..iterations).forEach { value ->
            position = (position + step) % buffer.size + 1
            buffer.add(position, value)
        }

        return buffer[(position + 1) % buffer.size]
    }

    fun part2(step: Int, iterations: Int) : Int {
        var position = 0
        var valueAtPosition1 = 0
        (1..iterations).forEach { value ->
            position = (position + step) % value + 1
            if (position == 1) valueAtPosition1 = value
        }

        return valueAtPosition1
    }
}