import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val INPUT = "76,1,88,148,166,217,130,0,128,254,16,2,130,71,255,229"

class Day10Test : StringSpec() {
    init {
        "should solve simple example" {
            Day10.part1("3,4,1,5", 5) shouldEqual 12
        }

        "should solve part 1" {
            Day10.part1(INPUT) shouldEqual 29240
        }

        "should hash empty string" {
            Day10.part2("") shouldEqual "a2582a3a0e66e6e86e3812dcb672a272"
        }

        "should hash 'AoC 2017'" {
            Day10.part2("AoC 2017") shouldEqual "33efeb34ea91902bb2f59c9920caa6cd"
        }

        "should hash '1,2,3" {
            Day10.part2("1,2,3") shouldEqual "3efbe78a8d82f29979031a4aa0b16a9d"
        }

        "should hash '1,2,4" {
            Day10.part2("1,2,4") shouldEqual "63960835bcdc130f0b66d7ff4f6a5a8e"
        }

        "should solve part 2" {
            Day10.part2(INPUT) shouldEqual "4db3799145278dc9f73dcdbc680bd53d"
        }
    }
}