import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val SIMPLE_MAP = """     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+"""
class Day19Test : StringSpec() {
    init {
        "should find landmarks" {
            Day19.part1(SIMPLE_MAP) shouldEqual "ABCDEF"
        }

        "should solve part 1" {
            "/day19.txt".asResource {
                Day19.part1(it) shouldEqual "GPALMJSOY"
            }
        }

        "should count steps" {
            Day19.part2(SIMPLE_MAP) shouldEqual 38
        }

        "should solve part 2" {
            "/day19.txt".asResource {
                Day19.part2(it) shouldEqual 16204
            }
        }
    }
}