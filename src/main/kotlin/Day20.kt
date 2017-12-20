import kotlin.math.absoluteValue

private const val REQUIRED_CONFIDENCE = 500
object Day20 {
    fun part1(input: String) : Int {
        var particles = particlesFrom(input)

        var confidence = 0
        var mostRecentClosestParticle = particles.first()

        while(true) {
            particles = particles.map { particle -> particle.next() }

            val closestParticle = particles.minBy { it.distance() }!!

            if (closestParticle.id == mostRecentClosestParticle.id) {
                confidence += 1

                if (confidence >= REQUIRED_CONFIDENCE) {
                    break
                }
            } else {
                confidence = 0
            }

            mostRecentClosestParticle = closestParticle
        }

        return mostRecentClosestParticle.id
    }

    fun part2(input: String) : Int {
        var particles = particlesFrom(input)

        var confidence = 0
        var mostRecentRemainingParticles = particles.size

        while(true) {
            particles = particles.map { particle -> particle.next() }
                    .groupBy { it.position }
                    .map { it.value }
                    .filter { it.size == 1 }
                    .flatMap { it }

            if (particles.size == mostRecentRemainingParticles) {
                confidence += 1

                if (confidence >= REQUIRED_CONFIDENCE) {
                    break
                }
            } else {
                confidence = 0
            }

            mostRecentRemainingParticles = particles.size
        }

        return mostRecentRemainingParticles
    }

    private fun particlesFrom(input: String) =
            input.split("\n").mapIndexed { index, row ->
                val groups = """p=<(-?\d+),(-?\d+),(-?\d+)>, v=<(-?\d+),(-?\d+),(-?\d+)>, a=<(-?\d+),(-?\d+),(-?\d+)>""".toRegex().matchEntire(row)!!.groupValues
                ParticleState(
                        id = index,
                        position = Triple(groups[1].toLong(), groups[2].toLong(), groups[3].toLong()),
                        velocity = Triple(groups[4].toLong(), groups[5].toLong(), groups[6].toLong()),
                        acceleration = Triple(groups[7].toLong(), groups[8].toLong(), groups[9].toLong())
                )
            }


    private data class ParticleState(val id: Int, var position: Triple<Long, Long, Long>, var velocity: Triple<Long, Long, Long>, val acceleration: Triple<Long, Long, Long>) {
        fun next() = ParticleState(
            id = id,
            position = position + velocity + acceleration,
            velocity = velocity + acceleration,
            acceleration = acceleration
        )

        fun distance() = position.first.absoluteValue + position.second.absoluteValue + position.third.absoluteValue
    }

}

operator fun Triple<Long, Long, Long>.plus(increment: Triple<Long, Long, Long>) =
        Triple(first + increment.first, second + increment.second, third + increment.third)

