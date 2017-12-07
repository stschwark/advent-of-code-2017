object Day4 {
    fun part1(passphrases: String) : Int {
        return passphrases.split("\n")
                .filterNot(::containsDuplicatePhrases)
                .count()
    }

    fun part2(passphrase: String) : Int {
        return passphrase.split("\n")
                .filterNot(::containsAnagrams)
                .count()
    }
}

private fun containsDuplicatePhrases(passphrase: String) : Boolean {
    val words = passphrase.split(" ")
    return words.distinct() != words
}

private fun containsAnagrams(passphrase: String) : Boolean {
    val words = passphrase.split(" ").map(String::sorted)
    return  words.distinct() != words
}

private fun String.sorted(): String = this.toList().sorted().joinToString()