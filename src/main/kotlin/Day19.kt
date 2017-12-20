private const val JUNCTION = '+'
private const val EMPTY = ' '
object Day19 {
    fun part1(input: String) : String {
        var landmarks = ""

        val map = Map(input.split("\n").map { it.map { it } })
        var position = map.start()

        while (map.featureAt(position.current) != EMPTY) {
            if (map.featureAt(position.current).isLetter()) {
                landmarks += map.featureAt(position.current)
            }

            position = position.next(map)
        }

        return landmarks
    }

    fun part2(input: String) : Int {
        var steps = 0

        val map = Map(input.split("\n").map { it.map { it } })
        var position = map.start()

        while (map.featureAt(position.current) != EMPTY) {
            steps += 1

            position = position.next(map)
        }

        return steps
    }

    private sealed class Move(val current: Pair<Int, Int>) {

        abstract fun next(map: Map) : Move

        class North(coors: Pair<Int, Int>) : Move(coors) {
            override fun next(map: Map) : Move =
                if (map.featureAt(current) == JUNCTION) {
                    when {
                        map.hasFeatureAt(current.toEast()) -> East(current.toEast())
                        map.hasFeatureAt(current.toWest()) -> West(current.toWest())
                        else -> throw IllegalStateException()
                    }
                } else {
                    North(current.toNorth())
                }
            }

        class West(coors: Pair<Int, Int>) : Move(coors) {
            override fun next(map: Map) : Move =
                    if (map.featureAt(current) == JUNCTION) {
                        when {
                            map.hasFeatureAt(current.toNorth()) -> North(current.toNorth())
                            map.hasFeatureAt(current.toSouth()) -> South(current.toSouth())
                            else -> throw IllegalStateException()
                        }
                    } else {
                        West(current.toWest())
                    }
        }

        class South(coors: Pair<Int, Int>) : Move(coors) {
            override fun next(map: Map) : Move =
                    if (map.featureAt(current) == JUNCTION) {
                        when {
                            map.hasFeatureAt(current.toEast()) -> East(current.toEast())
                            map.hasFeatureAt(current.toWest()) -> West(current.toWest())
                            else -> throw IllegalStateException()
                        }
                    } else {
                        South(current.toSouth())
                    }
        }

        class East(coors: Pair<Int, Int>) : Move(coors) {
            override fun next(map: Map) : Move =
                    if (map.featureAt(current) == JUNCTION) {
                        when {
                            map.hasFeatureAt(current.toNorth()) -> North(current.toNorth())
                            map.hasFeatureAt(current.toSouth()) -> South(current.toSouth())
                            else -> throw IllegalStateException()
                        }
                    } else {
                        East(current.toEast())
                    }
        }
    }

    private class Map(private val data : List<List<Char>>) {

        fun hasFeatureAt(coors: Pair<Int, Int>) = featureAt(coors) != EMPTY

        fun featureAt(coors: Pair<Int, Int>) =
                if (coors.first < 0 || coors.second < 0 || coors.first >= data.size || coors.second >= data[coors.first].size) {
                    EMPTY
                } else {
                    data[coors.first][coors.second]
                }

        fun start() = Move.South(Pair(0, data[0].indexOf('|'))) as Move

    }
}

private fun Pair<Int, Int>.toNorth() = Pair(this.first - 1, this.second)
private fun Pair<Int, Int>.toEast() = Pair(this.first, this.second + 1)
private fun Pair<Int, Int>.toSouth() = Pair(this.first + 1, this.second)
private fun Pair<Int, Int>.toWest() = Pair(this.first, this.second - 1)
