package chucknorris

import chucknorris.enums.Action

object UserInterface {
    fun getAction(): Action {
        println("Please input operation (encode/decode/exit):")
        return when (val input = readln().trim()) {
            "encode" -> Action.ENCODE
            "decode" -> Action.DECODE
            "exit" -> {
                displayEndMsg()
                Action.EXIT
            }
            else -> {
                displayError(("There is no '%s' operation".format(input)))
                Action.ERROR
            }
        }
    }

    fun askStringToEncode(): CharArray {
        println("Input string:")
        return readln().trim().toCharArray()
    }

    fun askStringToDecode(): List<String> {
        println("Input encoded string:")
        return readln().trim().split(" ")
    }

    fun displayUnary(message: String) {
        println("encoded string:\n$message")
    }

    fun displayDecoded(message: String) {
        println("decoded string:\n$message")
    }

    fun displayError(message: String) {
        println(message)
    }

    private fun displayEndMsg() {
        println("Bye!")
    }
}