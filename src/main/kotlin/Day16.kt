object Day16 {
    fun part1(start: String, moves: String): String {
        val dancers = DanceCompany(start)

        return dancers.dance(moves.split(","))
    }

    fun part2(start: String, moves: String): String {
        val dancers = DanceCompany(start)
        val movesAsList = moves.split(",")
        val history = mutableMapOf<String, String>()

        return (1..1_000_000_000).fold("") { prev, _ -> history.getOrPut(prev) { dancers.dance(movesAsList) } }
    }

    private class DanceCompany(start: String) {
        private var dancers = start.map { it }.toMutableList()

        fun dance(moves: List<String>) : String {
            moves.forEach { move ->
                when (move[0]) {
                    's' -> {
                        spin(move.drop(1).toInt())
                    }
                    'x' -> {
                        val (a, b) = move.drop(1).split("/").map { it.toInt() }
                        swap(a, b)
                    }
                    'p' -> {
                        val (a, b) = move.drop(1).split("/").map { it[0] }
                        partner(a, b)
                    }
                }
            }

            return dancers.joinToString("")
        }

        private fun spin(last: Int) {
            dancers = (dancers.takeLast(last) + dancers.take(dancers.size - last)).toMutableList()
        }

        private fun swap(a: Int, b: Int) {
            val dancerA = dancers[a]
            dancers[a] = dancers[b]
            dancers[b] = dancerA
        }

        private fun partner(a: Char, b: Char) {
            swap(dancers.indexOf(a), dancers.indexOf(b))
        }
    }
}