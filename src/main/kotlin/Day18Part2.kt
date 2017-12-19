
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
import java.util.concurrent.TimeUnit

object Day18Part2 {
    fun part2(input: String) : Long {
        val instructions = input.split("\n")

        val channel0 = Channel<Long>(Channel.UNLIMITED)
        val channel1 = Channel<Long>(Channel.UNLIMITED)

        val program0 = Program(0, instructions, channel0, channel1)
        val program1 = Program(1, instructions, channel1, channel0)

        try {
            runBlocking {
                async { program0.execute() }
                async { program1.execute() }.await()
            }
        } catch (ignored: Exception) {}

        return program1.sent
    }

    private class Program(
            private val id: Long,
            private val instructions: List<String>,
            private val receiver: Channel<Long>,
            private val sender: Channel<Long>
    ) {
        val registers = mutableMapOf<String, Long>()
        var sent = 0L

        init {
            registers["p"] = id
        }

        suspend fun execute() : Long {
            var position = 0

            while(true) {
                val parts = instructions[position].split(" ")
                when(parts[0]) {
                    "snd" -> {
                        sender.send(valueOf(parts[1]))
                        sent += 1
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
                        withTimeout(100, TimeUnit.MILLISECONDS) {
                            registers[parts[1]] = receiver.receive()
                            position += 1
                        }
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