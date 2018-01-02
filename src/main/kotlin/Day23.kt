object Day23 {
    fun part1(input: String) : Int {
        return Program(input.split("\n")).execute()
    }

    fun part2() : Int {
        var h = 0

        val start = 84 * 100 + 100000
        val end = start + 17000

        (start..end step 17).forEach {
            if (!it.toBigInteger().isProbablePrime(Int.MAX_VALUE)) {
                h += 1
            }
        }

        return h
    }

    private class Program(private val instructions: List<String>) {
        val registers = mutableMapOf<String, Long>()

        fun execute() : Int {
            var position = 0
            var mulCount = 0

            while(position >=0 && position < instructions.size) {
                val parts = instructions[position].split(" ")
                when(parts[0]) {
                    "set" -> {
                        registers[parts[1]] = valueOf(parts[2])
                        position += 1
                    }
                    "sub" -> {
                        registers[parts[1]] = valueOf(parts[1]) - valueOf(parts[2])
                        position += 1
                    }
                    "mul" -> {
                        registers[parts[1]] = valueOf(parts[1]) * valueOf(parts[2])
                        mulCount += 1
                        position += 1
                    }
                    "jnz" -> {
                        position += if (valueOf(parts[1]) != 0L) { valueOf(parts[2]).toInt() } else { 1 }
                    }
                    else -> throw IllegalStateException("Unknown command")
                }
            }

            return mulCount
        }

        private fun valueOf(text: String) : Long =
                if (text.any { it.isDigit() }) {
                    text.toLong()
                } else {
                    registers.getOrDefault(text, 0)
                }
    }
}