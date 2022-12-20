package application;

import java.util.ArrayList;

public interface PokerDeckInterface {
	
	//every poker deck has the same base deck
	public final static ArrayList<Card> baseDeck = new ArrayList<>(); 
	
	// every poker deck needs to have a shuffle method 
	void shuffle(); 	
	
	//all poker games (blackjack, texas hold 'em, etc.) all need a deal method for two cards (at index i and j)
	Hand deal(int i, int j);
	
}
