package com.damar.poker

class HandEvaluator {
  fun evaluateHand(cards: List<Card>): PokerHand {
    val allCombinations = combinations(cards, 5)
    return allCombinations.max()
  }

  // Helper function to generate combinations of cards
  fun combinations(cards: List<Card>, size: Int): List<PokerHand> {
    val result = mutableListOf<PokerHand>()
    generateCombinations(cards, size, 0, mutableListOf(), result)
    return result
  }

  fun generateCombinations(cards: List<Card>, size: Int, start: Int, current: MutableList<Card>, result: MutableList<PokerHand>) {
    if (current.size == size) {
      result.add(PokerHand.fromList(current.toList()))
      return
    }
    for (i in start until cards.size) {
      current.add(cards[i])
      generateCombinations(cards, size, i + 1, current, result)
      current.removeLast()
    }
  }
}