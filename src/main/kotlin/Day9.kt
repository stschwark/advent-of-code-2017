object Day9 {
    fun part1(input: String) : Int {
        return cleanup(input).score
    }

    fun part2(input: String) : Int {
        return cleanup(input).cleaned
    }

    private fun cleanup(input: String): Stats {
        val stats = Stats()

        var level = 0
        var garbage = false
        var cancelled = false

        input.forEach { c ->
            when {
                cancelled -> cancelled = false
                c == '!' -> cancelled = true
                garbage -> when (c) {
                    '>' -> garbage = false
                    else -> stats.cleaned += 1
                }
                else -> when (c) {
                    '{' -> level += 1
                    '}' -> { stats.score += level; level -= 1 }
                    '<' -> garbage = true
                }
            }
        }

        return stats
    }

    private data class Stats(
            var score: Int = 0,
            var cleaned: Int = 0
    )
}