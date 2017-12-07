object Day1 {
    fun part1(captcha: String): Int {
        val digits = CircularList(captcha)

        return (0 until digits.size())
                .map { index -> Pair(digits[index], digits[index+1]) }
                .filter { it.first == it.second }
                .sumBy { it.first }
    }

    fun part2(captcha: String): Int {
        val digits = CircularList(captcha)

        return (0 until digits.size())
                .map { index -> Pair(digits[index], digits[index + digits.size() / 2])}
                .filter { it.first == it.second }
                .sumBy { it.first }
    }

    private class CircularList(text : String) {
        val digits = text.map { it.toString().toInt() }

        operator fun get(index: Int) = digits[index % digits.size]

        fun size() = digits.size
    }
}


