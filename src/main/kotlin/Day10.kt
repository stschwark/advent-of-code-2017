object Day10 {

    fun part1(lengths: String, ringSize: Int = 256) : Int {
        val hash = knotHash(lengths.split(",").map(String::toInt), ringSize = ringSize)
        return hash[0] * hash[1]
    }

    fun part2(input: String) : String {
        val hash = knotHash(input.map(Char::toInt) + arrayOf(17, 31, 73, 47, 23), iterations = 64, ringSize = 256)

        return hash.chunked(16)
                .map { it.xor() }
                .map { it.toHex() }
                .joinToString("")
    }

    private fun knotHash(lengths: List<Int>, ringSize: Int = 256, iterations: Int = 1) : List<Int> {
        val hash = CircularList((0 until ringSize).toList())
        var skipSize = 0
        var position = 0

        repeat(iterations) {
            for(length in lengths) {
                hash.addAll(position, hash.subList(position, position + length).reversed())
                position += length + skipSize
                skipSize += 1
            }
        }
        return hash.toList()
    }

    private fun List<Int>.xor() : Int = this.reduce { acc, it -> acc.xor(it) }

    private fun Int.toHex() = this.toString(16).padStart(2, '0')

    private class CircularList<T>(list : List<T>) {
        private val delegate = list.toMutableList()

        operator fun get(index: Int) = delegate[index % size()]

        operator fun set(index: Int, element: T) { delegate.set(index % size(), element)}

        fun size() = delegate.size

        fun subList(from: Int, to: Int) : List<T> = (from until to).map { get(it) }

        fun addAll(index: Int, elements: Collection<T>) {
            (index until index + elements.size).forEachIndexed { source, target ->
                set(target, elements.toList()[source])
            }
        }

        fun toList() = delegate.toList()
    }
}