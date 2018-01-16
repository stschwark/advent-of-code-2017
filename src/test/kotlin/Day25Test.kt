import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class Day25Test : StringSpec() {
    init {
        "should calculate diagnostic checksum" {
            Day25.part1(
                    listOf(
                            Day25.Condition("A", 0, Day25.Action(1, Day25.Direction.RIGHT, "B")),
                            Day25.Condition("A", 1, Day25.Action(0, Day25.Direction.LEFT, "B")),
                            Day25.Condition("B", 0, Day25.Action(1, Day25.Direction.LEFT, "A")),
                            Day25.Condition("B", 1, Day25.Action(1, Day25.Direction.RIGHT, "A"))
                    ),
                    6,
                    "A"
            ) shouldEqual 3
        }

        "should solve part 1" {
            Day25.part1(
                    listOf(
                            Day25.Condition("A", 0, Day25.Action(1, Day25.Direction.RIGHT, "B")),
                            Day25.Condition("A", 1, Day25.Action(1, Day25.Direction.LEFT, "E")),

                            Day25.Condition("B", 0, Day25.Action(1, Day25.Direction.RIGHT, "C")),
                            Day25.Condition("B", 1, Day25.Action(1, Day25.Direction.RIGHT, "F")),

                            Day25.Condition("C", 0, Day25.Action(1, Day25.Direction.LEFT, "D")),
                            Day25.Condition("C", 1, Day25.Action(0, Day25.Direction.RIGHT, "B")),

                            Day25.Condition("D", 0, Day25.Action(1, Day25.Direction.RIGHT, "E")),
                            Day25.Condition("D", 1, Day25.Action(0, Day25.Direction.LEFT, "C")),

                            Day25.Condition("E", 0, Day25.Action(1, Day25.Direction.LEFT, "A")),
                            Day25.Condition("E", 1, Day25.Action(0, Day25.Direction.RIGHT, "D")),

                            Day25.Condition("F", 0, Day25.Action(1, Day25.Direction.RIGHT, "A")),
                            Day25.Condition("F", 1, Day25.Action(1, Day25.Direction.RIGHT, "C"))
                    ),
                    12459852,
                    "A"
            ) shouldEqual 4217
        }
    }
}
