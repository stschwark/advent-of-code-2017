import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val EXAMPLE = """snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d"""

class Day18Part2Test : StringSpec() {
    init {
        "should observe 3 transmissions" {
            Day18Part2.part2(EXAMPLE) shouldEqual 3L
        }

        "should solve part 2" {
            "/day18.txt".asResource {
                Day18Part2.part2(it) shouldEqual 5969L
            }
        }
    }
}