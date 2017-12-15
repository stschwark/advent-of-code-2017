object Day15 {
    fun part1(generatorAStart: Int, generatorBStart: Int) : Int {
        return generate(generatorAStart, 16807)
                .zip(generate(generatorBStart, 48271))
                .take(40_000_000)
                .count { (a, b) -> a and 0xFFFF == b and 0xFFFF }
    }

    fun part2(generatorAStart: Int, generatorBStart: Int) : Int {
        return generate(generatorAStart, 16807).filter { it % 4 == 0 }
                .zip(generate(generatorBStart, 48271).filter { it % 8 == 0 })
                .take(5_000_000)
                .count { (a, b) -> a and 0xFFFF == b and 0xFFFF }
    }

    private fun generate(startWith: Int, factor: Int) = generateSequence(startWith) { prev ->
        ((prev.toLong() * factor.toLong()) % 2147483647L).toInt()
    }.drop(1)
}