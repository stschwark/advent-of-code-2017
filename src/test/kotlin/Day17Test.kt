import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class Day17Test : StringSpec() {
    init {
        "should return next value after 1 iteration" {
            Day17.part1(3, 1) shouldEqual 0
        }

        "should return next value after 2 iteration" {
            Day17.part1(3, 2) shouldEqual 1
        }

        "should return next value after 3 iteration" {
            Day17.part1(3, 3) shouldEqual 1
        }

        "should return next value after 9 iteration" {
            Day17.part1(3, 9) shouldEqual 5
        }

        "should solve part 1" {
            Day17.part1(386, 2017) shouldEqual 419
        }

        "should return the value next to value '0'" {
            Day17.part2(3, 9) shouldEqual 9
        }

        "should solve part 2" {
            Day17.part2(386, 50_000_000) shouldEqual 46038988
        }
    }
}