import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val SIMPLE_INPUT = """0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5"""

class Day12Test : StringSpec() {
    init {
        "should find all 6 programs in group" {
            Day12.part1(SIMPLE_INPUT) shouldEqual 6
        }

        "should solve part 1" {
            "/day12.txt".asResource {
                Day12.part1(it) shouldEqual 239
            }
        }

        "should find two groups" {
            Day12.part2(SIMPLE_INPUT) shouldEqual 2
        }

        "should solve part 2" {
            "/day12.txt".asResource {
                Day12.part2(it) shouldEqual 215
            }
        }
    }
}