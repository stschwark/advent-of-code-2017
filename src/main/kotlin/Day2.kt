object Day2 {
    fun part1(input: String) =
            sheetFromInput(input).map(List<Int>::checksum).toIntArray().sum()

    fun part2(input: String) =
            sheetFromInput(input)
                    .map(List<Int>::firstEvenlyDivisiblePair)
                    .map(Pair<Int, Int>::divide)
                    .sum()
}

private fun List<Int>.checksum() =
        max()!! - min()!!

private fun sheetFromInput(input: String) =
        input
            .split("\n")
            .map { it.split("\t").map(String::toInt) }



private fun List<Int>.firstEvenlyDivisiblePair() =
        sortedDescending()
            .combineUnique()
            .first { it.first % it.second == 0 }

private fun Pair<Int, Int>.divide() = first / second

private fun List<Int>.combineUnique() =
        (0 until size).flatMap { first ->
            (first + 1 until size).map { second -> this[first].to(this[second])}
        }
