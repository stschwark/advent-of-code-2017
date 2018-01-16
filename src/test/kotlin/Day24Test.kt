import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val EXAMPLE = """0/2
2/2
2/3
3/4
3/5
0/1
10/1
9/10"""

class Day24Test : StringSpec() {
    init {
        "should calculate strength of strongest bridge" {
            Day24.part1(EXAMPLE) shouldEqual 31
        }

        "should solve part 1" {
            "/day24.txt".asResource {
                Day24.part1(it) shouldEqual 1859
            }
        }

        "should calculate strength of longest, then strongest bridge" {
            Day24.part2(EXAMPLE) shouldEqual 19
        }

        "should solve part 2" {
            "/day24.txt".asResource {
                Day24.part2(it) shouldEqual 1799
            }
        }
    }
}