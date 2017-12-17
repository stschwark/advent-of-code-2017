import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val START = "abcdefghijklmnop"

class Day16Test : StringSpec() {
    init {
        "should spin" {
            Day16.part1("abcde", "s1") shouldEqual "eabcd"
        }

        "should swap" {
            Day16.part1("abcde", "x3/4") shouldEqual "abced"
        }

        "should partner" {
            Day16.part1("abcde", "pe/b") shouldEqual "aecdb"
        }

        "should dance" {
            Day16.part1("abcde", "s1,x3/4,pe/b") shouldEqual "baedc"
        }

        "should solve part 1" {
            "/day16.txt".asResource {
                Day16.part1(START, it) shouldEqual "ionlbkfeajgdmphc"
            }
        }

        "should solve part 2" {
            "/day16.txt".asResource {
                Day16.part2(START, it) shouldEqual "fdnphiegakolcmjb"
            }
        }

    }
}