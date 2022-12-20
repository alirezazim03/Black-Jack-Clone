package application;

public class Card {
	private Suit suitValue;
	private Rank rankValue;
	private String imageLink;
	private int cardValue;

	// constructor for a card, including its suit, rank, and image
	public Card(Suit suit, Rank rank, String image) {
		suitValue = suit;
		rankValue = rank;
		imageLink = image;
	}

	// getter for the card's image file (in a seperate folder)
	public String getFilename() {
		return imageLink;
	}

	// getter for the card's suit
	public Suit getSuit() {
		return suitValue;
	}

	// getter for the card's rank
	public Rank getRank() {
		return rankValue;
	}

	// getter for the card's value
	public int getValue() {
		return cardValue;
	}

	// setter for when an ace is changed from being worth 11 to being worth 1
	public void setValue() {
		cardValue = 1;
	}

	// assigns the card an initial value based on its Rank (rankValue.getName())
	public void assignValue() {
		switch (rankValue.getName()) {
		case "Two":
			cardValue = 2;
			break;
		case "Three":
			cardValue = 3;
			break;
		case "Four":
			cardValue = 4;
			break;
		case "Five":
			cardValue = 5;
			break;
		case "Six":
			cardValue = 6;
			break;
		case "Seven":
			cardValue = 7;
			break;
		case "Eight":
			cardValue = 8;
			break;
		case "Nine":
			cardValue = 9;
			break;
		case "Ten":
		case "Jack":
		case "Queen":
		case "King":
			cardValue = 10;
			break;
		case "Ace":
			cardValue = 11;
			break;

		}
	}

}
