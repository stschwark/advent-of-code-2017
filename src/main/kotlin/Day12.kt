object Day12 {
    fun part1(input: String) : Int {
        val allPrograms = programsFrom(input)

        allPrograms["0"]!!.visit(allPrograms)

        return allPrograms.values.filter { it.group != null }.count()
    }

    fun part2(input: String) : Int {
        val programs = programsFrom(input)

        for (start in ProgramsWithoutGroup(programs)) {
            start.visit(programs)
        }

        return programs.values.distinctBy { it.group }.count()
    }

    private fun programsFrom(input: String) : MutableMap<String, Program> =
            input.split("\n")
                    .map { row -> programFrom(row) }
                    .associateBy({ it.id }, { it })
                    .toMutableMap()

    private fun programFrom(row: String) : Program {
        val groups = """(\d+) <-> ([\d ,]+)""".toRegex().matchEntire(row)!!.groupValues
        return Program(
                id = groups[1],
                connectedTo = groups[2].split(", ")
        )
    }

    private data class Program(val id: String, val connectedTo: List<String>, var group: String? = null) {
        fun visit(allPrograms: MutableMap<String, Program>, group: String = id) {
            this.group = group
            connectedTo
                    .map { id -> allPrograms[id]!! }
                    .filter { it.group == null }
                    .forEach { it.visit(allPrograms, group) }
        }
    }

    private class ProgramsWithoutGroup(private val programs: Map<String, Program>) : Iterator<Program> {
        override fun next(): Program = programs.values.first { it.group == null }
        override fun hasNext(): Boolean = programs.values.firstOrNull { it.group == null } != null
    }
}