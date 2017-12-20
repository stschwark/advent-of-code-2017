import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val TWO_PARTICLES = """p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>
p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"""

class Day20Test : StringSpec() {
    init {
        "should find closest particle" {
            Day20.part1(TWO_PARTICLES) shouldEqual 0
        }

        "should solve part 1" {
            "/day20.txt".asResource {
                Day20.part1(it) shouldEqual 308
            }
        }

        "should solve part 2" {
            "/day20.txt".asResource {
                Day20.part2(it) shouldEqual 504
            }
        }
    }
}