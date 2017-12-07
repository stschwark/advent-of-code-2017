object Day7 {
    fun part1(input: String) : String {
        return parseInput(input).name
    }

    fun part2(input: String) : Int {
        val bottomMostProgram = parseInput(input)

        return requiredWeight(bottomMostProgram)
    }

    private fun parseInput(input: String) : Program {
        val allPrograms = mutableMapOf<String, Program>()

        val rows = input.split("\n")

        rows.forEach { row ->
            """(?<name>[a-z]+) \((?<weight>\d+)\).*""".toRegex().matchEntire(row)?.let { match ->
                val name = match.groups["name"]!!.value
                val weight = match.groups["weight"]!!.value.toInt()
                allPrograms[name] = Program(name, weight)
            }
        }

        rows.forEach { row ->
            """(?<name>[a-z]+) .* -> (?<supports>.*)?""".toRegex().matchEntire(row)?.let { match ->
                val name = match.groups["name"]!!.value
                val supports = match.groups["supports"]!!.value

                allPrograms[name]!!.supports = supports.split(", ").map { allPrograms[it]!! }
            }
        }

        return bottomMostProgram(allPrograms.values)
    }

    private fun bottomMostProgram(allPrograms: Collection<Day7.Program>): Day7.Program {
        val supportedPrograms = allPrograms.flatMap { it.supports }.map { it.name }
        return allPrograms.single { it.name !in supportedPrograms }
    }

    private fun requiredWeight(program: Program) : Int =
        if (program.isBalanced()) {
            0
        } else {
            val result = program.supports.map { requiredWeight(it) }.max()!!
            if (program.supports.map { it.isBalanced() }.reduce { acc, next -> acc && next }) {
                val groupedSupports = program.supports.groupBy { it.totalWeight() }
                val balancedSupport = groupedSupports.values.first { it.size > 1 }.first()
                val unbalancedSupport = groupedSupports.values.first { it.size == 1 }.first()

                balancedSupport.totalWeight() - unbalancedSupport.totalWeight() + unbalancedSupport.weight
            } else {
                result
            }
        }


    private data class Program(
            val name: String,
            var weight: Int,
            var supports: List<Program> = emptyList()) {

        fun supportedWeight(): Int = supports.sumBy(Program::totalWeight)

        fun totalWeight(): Int = weight + supportedWeight()

        fun isBalanced(): Boolean = supports.isEmpty() || supports.map(Program::totalWeight).toSet().size == 1
    }
}



