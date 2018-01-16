object Day25 {
    fun part1(rules: List<Condition>, checksumAfterStep: Int, startState: String) : Int {
        val tape = mutableMapOf<Int, Int>()
        var cursorPosition = 0
        var state = startState

        (0 until checksumAfterStep).forEach {
            rules.first { it.matches(state, tape.getOrDefault(cursorPosition, 0)) }.action.let { action ->
                tape[cursorPosition] = action.nextValue
                cursorPosition += action.nextMove.step
                state = action.nextState
            }
        }

        return tape.values.sum()
    }

    data class Condition(val matchesState: String, val matchesValue: Int, val action : Action) {
        fun matches(state: String, value: Int) = state == matchesState && value == matchesValue
    }

    data class Action(val nextValue: Int, val nextMove: Direction, val nextState: String)

    enum class Direction(val step: Int) {
        LEFT(-1), RIGHT(1)
    }

}