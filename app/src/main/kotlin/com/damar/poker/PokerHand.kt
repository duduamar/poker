package com.damar.poker

class PokerHand private constructor(val cards: List<Card>) : Comparable<PokerHand> {

  companion object {
    const val WIN = 1
    const val TIE = 0
    const val LOSS = -1

    fun fromList(cards : List<Card>): PokerHand {
      return PokerHand(cards.sortedBy { it.rank.ordinal })
    }

    fun createDeck(): List<Card> {
      val deck = mutableListOf<Card>()
      for (suit in Suit.entries) {
        for (rank in Rank.entries) {
          deck.add(Card(rank, suit))
        }
      }
      return deck
    }
  }

  val isStraightFlush; get() = isStraight && isFlush

  val isFourOfAKind = cards.groupBy { it.rank }.map { it.value }.any { it.size == 4 }

  val isFullHouse = cards.groupBy { it.rank }.map { it.value }.size == 2

  val isFlush = cards.groupBy { it.suit }.map { it.value }.size == 1

  val isStraight = cards.map { it.rank.ordinal } ==
    (cards[0].rank.ordinal..cards[0].rank.ordinal + 4).toList()

  val isThreeOfAKind = cards.groupBy { it.rank }.map { it.value }.any { it.size == 3 }

  val isTwoPair = cards.groupBy { it.rank }.map { it.value }.filter { it.size == 2 }.count() == 2

  val isPair = cards.groupBy { it.rank }.map { it.value }.any { it.size == 2 }

  override fun compareTo(other: PokerHand): Int = when {
    (this.isStraightFlush || other.isStraightFlush) ->
      if (this.isStraightFlush) if (!other.isStraightFlush) WIN else compareByHighCard(other) else LOSS
    (this.isFourOfAKind || other.isFourOfAKind) ->
      if (this.isFourOfAKind) if (!other.isFourOfAKind) WIN else compareByHighCard(other) else LOSS
    (this.isFullHouse || other.isFullHouse) ->
      if (this.isFullHouse) if (!other.isFullHouse) WIN else compareByHighCard(other) else LOSS
    (this.isFlush || other.isFlush) ->
      if (this.isFlush) if (!other.isFlush) WIN else compareByHighCard(other) else LOSS
    (this.isStraight || other.isStraight) ->
      if (this.isStraight) if (!other.isStraight) WIN else compareByHighCard(other) else LOSS
    (this.isThreeOfAKind || other.isThreeOfAKind) ->
      if (this.isThreeOfAKind) if (!other.isThreeOfAKind) WIN else compareByHighCard(other) else LOSS
    (this.isTwoPair || other.isTwoPair) ->
      if (this.isTwoPair) if (!other.isTwoPair) WIN else compareByHighCard(other) else LOSS
    (this.isPair || other.isPair) ->
      if (this.isPair) if (!other.isPair) WIN else compareByHighCard(other) else LOSS
    else -> compareByHighCard(other)
  }

  private fun compareByHighCard(other: PokerHand, index: Int = 4): Int = when {
    (index < 0) -> TIE
    cards[index].rank === other.cards[index].rank -> compareByHighCard(other, index - 1)
    cards[index].rank.ordinal > other.cards[index].rank.ordinal -> WIN
    else -> LOSS
  }

}