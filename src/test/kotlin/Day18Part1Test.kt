import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val EXAMPLE = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""

class Day18Part1Test : StringSpec() {
    init {
        "should find last frequency played" {
            Day18Part1.part1(EXAMPLE) shouldEqual 4L
        }

        "should solve part 1" {
            "/day18.txt".asResource {
                Day18Part1.part1(it) shouldEqual 1187L
            }
        }
    }
}