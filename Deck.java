package application;

import java.util.*;

public class Deck implements PokerDeckInterface {
	
	// this is the deck that we will use with all of the cards.
	public final static ArrayList<Card> baseDeck = new ArrayList<>(); 

	// this is the deck object (will be shuffled)
	public ArrayList<Card> deck = new ArrayList<>();

	// adds all of the Card objects to the baseDeck 
	public Deck() { 
		//the deck in blackjack is actually two decks, so we repeat the assigning process twice
		for (int x = 0; x < 2; x++) {
			// Clubs
			baseDeck.add(new Card(Suit.CLUBS, Rank.ACE, "application/CardImage/AC.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.TWO, "application/CardImage/2C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.THREE, "application/CardImage/3C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.FOUR, "application/CardImage/4C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.FIVE, "application/CardImage/5C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.SIX, "application/CardImage/6C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.SEVEN, "application/CardImage/7C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.EIGHT, "application/CardImage/8C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.NINE, "application/CardImage/9C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.TEN, "application/CardImage/10C.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.JACK, "application/CardImage/JC.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.QUEEN, "application/CardImage/QC.png"));
			baseDeck.add(new Card(Suit.CLUBS, Rank.KING, "application/CardImage/KC.png"));
			// Spades
			baseDeck.add(new Card(Suit.SPADES, Rank.ACE, "application/CardImage/AS.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.TWO, "application/CardImage/2S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.THREE, "application/CardImage/3S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.FOUR, "application/CardImage/4S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.FIVE, "application/CardImage/5S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.SIX, "application/CardImage/6S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.SEVEN, "application/CardImage/7S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.EIGHT, "application/CardImage/8S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.NINE, "application/CardImage/9S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.TEN, "application/CardImage/10S.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.JACK, "application/CardImage/JS.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.QUEEN, "application/CardImage/QS.png"));
			baseDeck.add(new Card(Suit.SPADES, Rank.KING, "application/CardImage/KS.png"));
			// Hearts
			baseDeck.add(new Card(Suit.HEARTS, Rank.ACE, "application/CardImage/AH.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.TWO, "application/CardImage/2H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.THREE, "application/CardImage/3H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.FOUR, "application/CardImage/4H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.FIVE, "application/CardImage/5H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.SIX, "application/CardImage/6H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.SEVEN, "application/CardImage/7H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.EIGHT, "application/CardImage/8H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.NINE, "application/CardImage/9H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.TEN, "application/CardImage/10H.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.JACK, "application/CardImage/JH.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.QUEEN, "application/CardImage/QH.png"));
			baseDeck.add(new Card(Suit.HEARTS, Rank.KING, "application/CardImage/KH.png"));
			// Diamonds
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.ACE, "application/CardImage/AD.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.TWO, "application/CardImage/2D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.THREE, "application/CardImage/3D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.FOUR, "application/CardImage/4D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.FIVE, "application/CardImage/5D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.SIX, "application/CardImage/6D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.SEVEN, "application/CardImage/7D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.EIGHT, "application/CardImage/8D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.NINE, "application/CardImage/9D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.TEN, "application/CardImage/10D.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.JACK, "application/CardImage/JD.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.QUEEN, "application/CardImage/QD.png"));
			baseDeck.add(new Card(Suit.DIAMONDS, Rank.KING, "application/CardImage/KD.png"));
		}
		//the deck we use starts as a baseDeck
		deck = baseDeck;
	}

	// shuffles the cards
	public void shuffle() {
		deck = baseDeck;
		Collections.shuffle(deck);
	}

	//returns two cards based on their index in the deck
	public Hand deal(int i, int j) {
		return new Hand(i, j);
	}
}
