import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class Day23Test : StringSpec() {
    init {
        "should solve part 1" {
            "/day23.txt".asResource {
                Day23.part1(it) shouldEqual 6724
            }
        }

        "should solve part 2" {
            Day23.part2() shouldEqual 903
        }
    }
}