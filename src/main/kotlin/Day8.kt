object Day8 {
    fun part1(input: String) : Int {
        val registers = mutableMapOf<String, Int>()

        input.split("\n")
                .map { """(\w+) (inc|dec) (-?\d+) if (\w+) (.{1,2}) (-?\d+)""".toRegex().matchEntire(it)!!.groupValues }
                .forEach { groups ->
                    if (condition(groups[5])(Register(groups[4], registers), groups[6].toInt())) {
                        operation(groups[2])(Register(groups[1], registers), groups[3].toInt())
                    }
                }

        return registers.values.max()!!
    }

    fun part2(input: String) : Int {
        val registers = mutableMapOf<String, Int>()

        return input.split("\n")
                .map { """(\w+) (inc|dec) (-?\d+) if (\w+) (.{1,2}) (-?\d+)""".toRegex().matchEntire(it)!!.groupValues }
                .map { groups ->
                    if (condition(groups[5])(Register(groups[4], registers), groups[6].toInt())) {
                        operation(groups[2])(Register(groups[1], registers), groups[3].toInt())
                        registers.values.max()!!
                    } else {
                        Int.MIN_VALUE
                    }
                }.max() ?: throw IllegalStateException("No rows executed")
    }

    private fun operation(operator: String) : (Register, Int) -> (Unit) =
        when (operator) {
            "inc" -> { register, value -> register += value }
            "dec" -> { register, value -> register -= value }
            else -> throw IllegalArgumentException("Unknown operator $operator")
        }

    private fun condition(comparator: String) : (Register, Int) -> Boolean =
        when (comparator) {
            "<" -> { a, b -> a.value < b }
            "<=" -> { a, b -> a.value <= b }
            "==" -> { a, b -> a.value == b }
            "!=" -> { a, b -> a.value != b }
            ">" -> { a, b -> a.value > b }
            ">=" -> { a, b -> a.value >= b }
            else -> throw IllegalArgumentException("Unknown comparator $comparator")
        }

    private class Register(private val name: String, private val storage: MutableMap<String, Int>) {
        operator fun plusAssign(b: Int) { storage[name] = value + b }
        operator fun minusAssign(b: Int) { storage[name] =  value - b }
        val value get() = storage.getOrDefault(name, 0)
    }
}