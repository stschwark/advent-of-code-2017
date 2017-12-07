object Day5 {
    fun part1(code: String): Int {
        val program = Program(parse(code))
        do {
            program.jump()
        } while (program.hasNext())
        return program.executedSteps
    }

    fun part2(code: String): Int {
        val program = Program(parse(code))
        do {
            program.strangerJumps()
        } while (program.hasNext())
        return program.executedSteps
    }

    private fun parse(code: String) : MutableList<Int> =
            code.split("\n").map { it.toInt() }.toMutableList()

    private class Program(val steps : MutableList<Int>) {
        private var position = 0

        var executedSteps = 0
            private set

        fun jump() {
            val instruction = steps[position]
            steps[position] = instruction + 1
            position += instruction
            executedSteps += 1
        }

        fun strangerJumps() {
            val instruction = steps[position]
            steps[position] = if (instruction >= 3) instruction -1 else instruction + 1
            position += instruction
            executedSteps += 1
        }

        fun hasNext() = position < steps.size
    }
}

