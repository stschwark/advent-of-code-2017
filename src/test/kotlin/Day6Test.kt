import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val INPUT = "0\t5\t10\t0\t11\t14\t13\t4\t11\t8\t8\t7\t1\t4\t12\t11"
class Day6Test : StringSpec() {
    init {
        "should determine number of redistribution cycles" {
            Day6.part1("0\t2\t7\t0") shouldEqual 5
        }

        "should solve part1" {
            Day6.part1(INPUT) shouldEqual 7864
        }

        "should determine number of cycles in loop" {
            Day6.part2("0\t2\t7\t0") shouldEqual 4
        }

        "should solve part2" {
            Day6.part2(INPUT) shouldEqual 1695
        }
    }
}
