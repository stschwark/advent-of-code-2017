object Day18Part1 {
    fun part1(input: String) : Long {
        return Program(input.split("\n")).execute()
    }

    private class Program(private val instructions: List<String>) {
        val registers = mutableMapOf<String, Long>()

        fun execute() : Long {
            var position = 0
            var lastFrequencyPlayed = 0L

            while(true) {
                val parts = instructions[position].split(" ")
                when(parts[0]) {
                    "snd" -> {
                        lastFrequencyPlayed = valueOf(parts[1])
                        position += 1
                    }
                    "set" -> {
                        registers[parts[1]] = valueOf(parts[2])
                        position += 1
                    }
                    "add" -> {
                        registers[parts[1]] = valueOf(parts[1]) + valueOf(parts[2])
                        position += 1
                    }
                    "mul" -> {
                        registers[parts[1]] = valueOf(parts[1]) * valueOf(parts[2])
                        position += 1
                    }
                    "mod" -> {
                        registers[parts[1]] = valueOf(parts[1]) % valueOf(parts[2])
                        position += 1
                    }
                    "rcv" -> {
                        if (valueOf(parts[1]) != 0L) {
                            return lastFrequencyPlayed
                        }
                        position += 1
                    }
                    "jgz" -> {
                        position += if (valueOf(parts[1]) > 0) { valueOf(parts[2]).toInt() } else { 1 }
                    }
                    else -> throw IllegalStateException("Unknown command")
                }
            }
        }

        private fun valueOf(text: String) : Long =
                if (text.any { it.isDigit() }) {
                    text.toLong()
                } else {
                    registers.getOrDefault(text, 0)
                }
    }
}