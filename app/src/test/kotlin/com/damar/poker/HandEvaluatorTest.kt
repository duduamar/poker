package com.damar.poker

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class HandEvaluatorTest {
  @Test
  fun evaluateHand() {
    val handEvaluator = HandEvaluator()
    val cards = listOf(
      Card(Rank.SIX, Suit.CLUBS),
      Card(Rank.ACE, Suit.HEARTS),
      Card(Rank.KING, Suit.HEARTS),
      Card(Rank.QUEEN, Suit.HEARTS),
      Card(Rank.JACK, Suit.HEARTS),
      Card(Rank.TEN, Suit.HEARTS)
    )
    val result = handEvaluator.evaluateHand(cards)
    assertTrue(result.isStraightFlush)
  }
}