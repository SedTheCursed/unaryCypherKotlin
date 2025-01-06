package chucknorris

fun CharArray.toBinary(): List<String> = map { char ->
    char.code.toString(2).toInt().let { "%07d".format(it) }
}

fun List<String>.toUnary(): String = this.joinToString("").run {
    val unary = mutableListOf<String>()
    val current = Current(first())

    for (i in 1..lastIndex) {
        if (this[i] == current.value) current.count++
        else unary.changeCurrent(this[i], current)
    }

    unary.changeCurrent('N', current)

    unary.joinToString(" ")
}

fun List<String>.fromUnary(): String {
    if (this.isEmpty() || this.size % 2 != 0) throw IllegalArgumentException("Encoded string is not valid")

    var binaryString = ""

    for (i in indices step 2) {
        if(this[i].isInvalidKey()) throw IllegalArgumentException("Encoded string is not valid")
        if(this[i + 1].isInvalidLength()) throw IllegalArgumentException("Encoded string is not valid")
        val char = if (this[i] == "00") "0" else "1"
        binaryString += char.repeat(this[i + 1].length)
    }

    return binaryString
}

fun String.fromBinary(): String {
    if (this.length % 7 != 0) throw IllegalArgumentException("Encoded string is not valid")
    var result = ""
    for (i in indices step 7) {
        result += Integer.parseInt(this.substring(i, i + 7), 2).toChar()
    }
    return result
}

private data class Current(var value: Char, var count: Int = 1)

private fun MutableList<String>.changeCurrent(c: Char, current: Current) {
    current.let {
        add("%s %s".format(
            if (it.value == '0') "00" else "0",
            "0".repeat(it.count)
        ))

        it.apply {
            value = c
            count = 1
        }
    }
}

private fun String.isInvalidKey(): Boolean = this != "0" && this != "00"
private fun String.isInvalidLength(): Boolean = this.matches(Regex("[^0]"))