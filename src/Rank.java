package application;

public class Rank {
	private String name;

	// declaring the thirteen final ranks with their corresponding name
	public final static Rank ACE = new Rank("Ace");
	public final static Rank TWO = new Rank("Two");
	public final static Rank THREE = new Rank("Three");
	public final static Rank FOUR = new Rank("Four");
	public final static Rank FIVE = new Rank("Five");
	public final static Rank SIX = new Rank("Six");
	public final static Rank SEVEN = new Rank("Seven");
	public final static Rank EIGHT = new Rank("Eight");
	public final static Rank NINE = new Rank("Nine");
	public final static Rank TEN = new Rank("Ten");
	public final static Rank JACK = new Rank("Jack");
	public final static Rank QUEEN = new Rank("Queen");
	public final static Rank KING = new Rank("King");

	// constructor for Rank
	private Rank(String nameValue) {
		name = nameValue;
	}

	// getter for Rank name
	public String getName() {
		return name;
	}

}
