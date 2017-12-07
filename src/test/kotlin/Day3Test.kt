import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

class Day3Test : StringSpec() {

    init {
        "should calculate distance as 0 for square 1" {
            Day3.part1(1) shouldEqual 0
        }

        "should calculate distance as 3 for square 12" {
            Day3.part1(12) shouldEqual 3
        }

        "should calculate distance as 2 for square 23" {
            Day3.part1(23) shouldEqual 2
        }

        "should calculate distance as 31 for square 1024" {
            Day3.part1(1024) shouldEqual 31
        }

        "should confirm solution for part 1" {
            Day3. part1(325489) shouldEqual 552
        }

        "should store value 1 at square 1" {
            Day3.part2(1) shouldEqual 1
        }

        "should store value 1 at square 2" {
            Day3.part2(2) shouldEqual 1
        }

        "should store value 2 at square 3" {
            Day3.part2(3) shouldEqual 2
        }

        "should store value 5 at square 5" {
            Day3.part2(5) shouldEqual 5
        }

        "should confirm solution for part 2" {
            Day3.part2(62) shouldEqual 330785
        }

    }
}