package chucknorris

import chucknorris.enums.Action
import kotlin.system.exitProcess

fun main() {

    do {
        when (UserInterface.getAction()) {
            Action.ENCODE -> encode()
            Action.DECODE -> decode()
            Action.EXIT -> exit()
            Action.ERROR -> continue
        }

    } while (true)
}

private fun encode() {
    UserInterface.askStringToEncode()
        .toBinary()
        .toUnary()
        .also(UserInterface::displayUnary)
}

private fun decode() {
    try {
        UserInterface.askStringToDecode()
            .fromUnary()
            .fromBinary()
            .also(UserInterface::displayDecoded)
    } catch (e: IllegalArgumentException) {
        UserInterface.displayError(e.message ?: "not valid")
    }
}

private fun exit() {
    exitProcess(0)
}