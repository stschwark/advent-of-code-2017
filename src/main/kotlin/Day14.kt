object Day14 {
    fun part1(input: String) : Int =
        gridFrom(input).sumBy { it.count { it == '1'} }

    fun part2(input: String) : Int {
        val grid = gridFrom(input).map { row -> row.map { it } }
        val visited = hashSetOf<Pair<Int, Int>>()
        var groups = 0
        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, cell ->
                val coordinate = Pair(x, y)
                if (cell == '1' && !visited.contains(coordinate)) {
                    groups += 1
                    visitRegion(grid, visited, coordinate)
                }
            }
        }
        return groups
    }

    private fun gridFrom(input: String) =
            (0..127).map { rowIndex -> Day10.part2("$input-$rowIndex").hexToBinaryString() }

    private fun String.hexToBinaryString() =
            this.map { Integer.valueOf(it.toString(), 16) }
                    .map(Integer::toBinaryString)
                    .map{ it.padStart(length = 4, padChar = '0') }
                    .joinToString(separator = "")

    private fun visitRegion(grid: List<List<Char>>, visited: HashSet<Pair<Int, Int>>, coordinate: Pair<Int, Int>) {
        if (grid[coordinate.first][coordinate.second] == '1' && !visited.contains(coordinate)) {
            visited += coordinate
            neighborsOf(coordinate).forEach { neighbour ->
                visitRegion(grid, visited, neighbour)
            }
        }
    }

    private fun neighborsOf(coordinate: Pair<Int, Int>): List<Pair<Int, Int>> =
            listOf(
                    Pair(coordinate.first - 1, coordinate.second),
                    Pair(coordinate.first + 1, coordinate.second),
                    Pair(coordinate.first, coordinate.second - 1),
                    Pair(coordinate.first, coordinate.second + 1)
            )
                    .filter { it.first in 0..127 }
                    .filter { it.second in 0..127 }
}

