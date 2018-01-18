private const val START = ".#./..#/###"

object Day21 {
    fun part1(input: String, iterations: Int) : Int {
        val rules = rulesFor(input)

        var artwork = START.toGrid()

        repeat(iterations) {
            val grids = artwork.split()
            val gridCount = grids.size

            artwork = grids
                    .flatten()
                    .map { rules[it] ?: throw IllegalStateException("No transformation found for $it") }
                    .chunked(gridCount)
                    .join()
        }

        return artwork.activePixels()
    }

    private fun rulesFor(input: String) : Map<Grid, Grid> {
        return input.split("\n").map { row ->
            """([\.#\/]+) => ([\.#\/]+)""".toRegex().matchEntire(row)!!.groupValues?.let { groups ->
                Pair(
                        groups[1].toGrid(),
                        groups[2].toGrid()
                )
            }
        }.flatMap { rule ->
                    listOf(
                            Pair(rule.first, rule.second),
                            Pair(rule.first.rotate(), rule.second),
                            Pair(rule.first.rotate().rotate(), rule.second),
                            Pair(rule.first.rotate().rotate().rotate(), rule.second),
                            Pair(rule.first.flip(), rule.second),
                            Pair(rule.first.rotate().flip(), rule.second),
                            Pair(rule.first.rotate().rotate().flip(), rule.second),
                            Pair(rule.first.rotate().rotate().rotate().flip(), rule.second)
                    )
        }.toMap()
    }

    data class Grid(private val matrix: List<List<Char>>) {
        fun split() : List<List<Grid>> {
            val gridSizeAfterSplit = if (matrix.size % 2 == 0) 2 else 3
            val gridCount = matrix.size / gridSizeAfterSplit
            val chunked = matrix.map { it.chunked(gridSizeAfterSplit) }

            return (0 until gridCount).map { gridY ->
                (0 until gridCount).map { gridX ->
                    Grid((0 until gridSizeAfterSplit).map { offset -> chunked[gridY * gridSizeAfterSplit + offset][gridX] })
                }
            }.flatten().chunked(gridCount)
        }

        fun rotate() =
                Grid(
                        (0 until matrix.size).map { row ->
                            (0 until matrix.size).map { col ->
                                matrix[col][matrix.size - row - 1]
                            }
                        }
                )

        fun flip() = Grid(matrix.map { it.reversed()} )

        fun addRight(other: Grid) = Grid(matrix.mapIndexed { index, matrix -> matrix + other.matrix[index] })

        fun addBelow(other: Grid) = Grid(matrix + other.matrix)

        fun activePixels() = matrix.flatten().count { it == '#' }

        override fun toString() = matrix.joinToString("/") { it.joinToString("") }
    }

    private fun List<List<Grid>>.join() : Grid =
            this.map { row ->
                row.reduce {
                    previous, next -> previous.addRight(next)
                }
            }.reduce {
                previous, next -> previous.addBelow(next)
            }

    private fun String.toGrid() : Grid =
            Grid(this.split("/").map { it.map { it }})
}

