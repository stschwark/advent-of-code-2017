object Day6 {
    fun part1(memoryConfiguration: String) : Int {
        val memory = Memory(memoryConfiguration)

        memory.rebalance()

        return memory.redistributionCycles
    }

    fun part2(memoryConfiguration: String) : Int {
        val memory = Memory(memoryConfiguration)

        memory.rebalance()

        return memory.sizeOfLoop()
    }

    private class Memory(memoryConfiguration: String) {
        private val banks = memoryConfiguration.split("\t").map { it.toInt() }.toMutableList()
        private val previousStates = mutableListOf<String>()

        var redistributionCycles = 0
            private set

        fun rebalance() {
            previousStates.clear()
            redistributionCycles = 0
            do {
                storeState()
                redistributionCycles += 1
                val blocksToDistribute = banks.max() ?: 0
                val indexOfBankThatRequiredRedistribution = banks.indexOf(blocksToDistribute)
                banks[indexOfBankThatRequiredRedistribution] = 0
                (1 .. blocksToDistribute)
                        .map { (it + indexOfBankThatRequiredRedistribution) % banks.size }
                        .forEach { banks[it] += 1 }
            } while (!isInLoop())
        }

        private fun storeState() {
            previousStates += banks.joinToString(",")
        }

        private fun isInLoop() = previousStates.contains(banks.joinToString(","))

        fun sizeOfLoop() = previousStates.size - previousStates.indexOf(banks.joinToString(","))
    }
}

