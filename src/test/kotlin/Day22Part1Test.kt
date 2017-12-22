import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val SIMPLE_MAP = """..#
#..
..."""

class Day22Part1Test : StringSpec() {
    init {
        "should count infections after 70 burst" {
            Day22Part1.part1(SIMPLE_MAP, 70) shouldEqual 41
        }

        "should solve part 1" {
            "/day22.txt".asResource {
                Day22Part1.part1(it, 10_000) shouldEqual 5256
            }
        }
    }
}