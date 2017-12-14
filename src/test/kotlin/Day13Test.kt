import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private val SIMPLE_EXAMPLE = """0: 3
1: 2
4: 4
6: 4"""

class Day13Test : StringSpec() {
    init {
        "should calculate score for simple example" {
            Day13.part1(SIMPLE_EXAMPLE) shouldEqual 24
        }

        "should solve part 1" {
            "/day13.txt".asResource { input ->
                Day13.part1(input) shouldEqual 1728
            }
        }

        "should get through with a delay of 10" {
            Day13.part2(SIMPLE_EXAMPLE) shouldEqual 10
        }

        "should solve part 2" {
            "/day13.txt".asResource { input ->
                Day13.part2(input) shouldEqual 3946838
            }
        }
    }
}