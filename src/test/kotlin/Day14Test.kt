import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val INPUT = "hxtvlmkl"

class Day14Test : StringSpec() {
    init {
        "should count squares used in grid for example" {
            Day14.part1("flqrgnkx") shouldEqual 8108
        }

        "should solve part 1" {
            Day14.part1(INPUT) shouldEqual 8214
        }

        "should solve part 2" {
            Day14.part2(INPUT) shouldEqual 1093
        }
    }
}