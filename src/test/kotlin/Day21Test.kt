import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val EXAMPLE = """../.# => ##./#../...
.#./..#/### => #..#/..../..../#..#"""

class Day21Test : StringSpec() {
    init {
        "should calculate how many pixels stay on" {
            Day21.part1(EXAMPLE, 2) shouldEqual 12
        }

        "should solve part 1" {
            "/day21.txt".asResource {
                Day21.part1(it, 5) shouldEqual 110
            }
        }

        "should solve part 2" {
            "/day21.txt".asResource {
                Day21.part1(it, 18) shouldEqual 1277716
            }
        }
    }
}