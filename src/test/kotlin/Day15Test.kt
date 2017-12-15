import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val GENERATOR_1_START = 634
private const val GENERATOR_2_START = 301

class Day15Test : StringSpec() {
    init {
        "should count matches for part 1 example" {
            Day15.part1(65, 8921) shouldEqual 588
        }

        "should solve part 1" {
            Day15.part1(GENERATOR_1_START, GENERATOR_2_START) shouldEqual 573
        }

        "should count matches for part 2 example" {
            Day15.part2(65, 8921) shouldEqual 309
        }

        "should solve part 2" {
            Day15.part2(GENERATOR_1_START, GENERATOR_2_START) shouldEqual 294
        }
    }
}