package com.damar.poker

import java.util.Locale

enum class Rank(val value: Int, val str: String) {
  TWO(2, "2"),
  THREE(3, "3"),
  FOUR(4, "4"),
  FIVE(5, "5"),
  SIX(6, "6"),
  SEVEN(7, "7"),
  EIGHT(8, "8"),
  NINE(9, "9"),
  TEN(10, "T"),
  JACK(11, "J"),
  QUEEN(12, "Q"),
  KING(13, "K"),
  ACE(14, "A");

  override fun toString(): String {
    return str
  }
}

enum class Suit {
  HEARTS,
  DIAMONDS,
  CLUBS,
  SPADES;

  override fun toString(): String {
    return name[0].lowercase()
  }
}

data class Card(val rank: Rank, val suit: Suit) {
  override fun toString(): String {
    return rank.toString() + suit.toString()
  }
}
