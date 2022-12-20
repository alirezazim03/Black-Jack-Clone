package application;

import java.util.ArrayList;

// Hand inherits from a deck, so it extends the Deck class
public class Hand extends Deck {

	// a Hand is an arrayList of Card objects
	private ArrayList<Card> hand = new ArrayList<>();

	// a counter that is used in the Main method to see how many times a player has
	// hit for a new card
	public static int hits = 0;

	// constructor for a hand (gets two cards from the deck at indices i and j)
	public Hand(int i, int j) {
		hand.add(deck.get(i));
		hand.add(deck.get(j));
	}

	// a hit adds a card from the deck (first 4 cards were dealt to dealer and
	// player, so it is hits + 4)
	public void hit() {
		hand.add(deck.get(hits + 4));

	}

	// getter for a Hand
	public ArrayList<Card> getHand() {
		return hand;
	}

}
