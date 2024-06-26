/*
 * This source file was generated by the Gradle 'init' task
 */
package com.damar.poker

import com.damar.poker.PokerHand.Companion.LOSS
import com.damar.poker.PokerHand.Companion.WIN
import com.damar.poker.PokerHand.Companion.createDeck

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    val deck = createDeck()
    val handEvaluator = HandEvaluator()

    while (true) {
        val shuffledDeck = deck.shuffled().toMutableList()
        val turn = takeAndDrop(shuffledDeck, 4)
        val hand1Cards = takeAndDrop(shuffledDeck, 2)
        val hand2Cards = takeAndDrop(shuffledDeck, 2)

        val hand1 = handEvaluator.evaluateHand(hand1Cards + turn)
        val hand2 = handEvaluator.evaluateHand(hand2Cards + turn)
        val result = hand1.compareTo(hand2)

        var leading: List<Card>
        var behind: List<Card>

        if (result == WIN) {
            leading = hand1Cards
            behind = hand2Cards
        } else if (result == LOSS) {
            leading = hand2Cards
            behind = hand1Cards
        } else {
            continue
        }

        var success = true
        for (i in 0 until 44) {
            val river = takeAndDrop(shuffledDeck, 1)
            val leadingFinalHand = handEvaluator.evaluateHand(leading + turn + river)
            val behindFinalHand = handEvaluator.evaluateHand(behind + turn + river)
            if (leadingFinalHand > behindFinalHand) {
                success = false
                break
            }
        }

        if (success) {
            println("Success!")
            println("Turn: $turn")
            println("Hand1: $hand1Cards")
            println("Hand2: $hand2Cards")
            break
        }
    }
}

fun <T> takeAndDrop(list: MutableList<T>, num: Int) : List<T> {
    val taken = list.take(num)
    repeat(num) {
        list.removeAt(0)
    }
    return taken
}
