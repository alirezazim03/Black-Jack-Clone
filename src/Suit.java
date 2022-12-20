package application;

public class Suit {
	private String name;

	// declaring the 4 final suits with their corresponding name
	public final static Suit CLUBS = new Suit("Clubs");
	public final static Suit DIAMONDS = new Suit("Diamonds");
	public final static Suit HEARTS = new Suit("Hearts");
	public final static Suit SPADES = new Suit("Spades");

	// constructor for Suit
	private Suit(String nameValue) {
		name = nameValue;
	}

	// getter for Suit name
	public String getName() {
		return name;
	}

}