package application;

import javafx.scene.image.ImageView;

public class Chip {
	private int value;
	private ImageView chipImage;

	// chip constructor
	public Chip(int val, ImageView image) {
		value = val;
		chipImage = image;
	}

	// getter for the value
	public int getValue() {
		return value;
	}

	// getter for the image
	public ImageView getImage() {
		return chipImage;
	}
}
