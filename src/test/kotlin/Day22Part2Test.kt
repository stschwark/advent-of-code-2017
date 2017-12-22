import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val SIMPLE_MAP = """..#
#..
..."""

class Day22Part2Test : StringSpec() {
    init {
        "should count infections after 1 burst" {
            Day22Part2.part2(SIMPLE_MAP, 100) shouldEqual 26
        }

        "should solve part 2" {
            "/day22.txt".asResource {
                Day22Part2.part2(it, 10_000_000) shouldEqual 2511345
            }
        }
    }
}