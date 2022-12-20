//Cameron Overvelde and Alireza AzimiTabrizi FINAL Computer Science Project
//BlackJack

package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//declaring variables
public class Main extends Application {
	public static int bank;
	public static int clicked;
	public static int betTotal = 0;

	public static Button menuButton;
	public static Button stand;
	public static Button allin;
	public static Button cashout;

	public static Pane root = new Pane();
	public static Scene menu = new Scene(root, 800, 600);
	public static Pane root2 = new Pane();
	public static Scene maingame = new Scene(root2, 800, 600);
	public static Stage stage;
	public static double start;
	public static DropShadow shadow = new DropShadow();
	private final static Deck deck = new Deck();
	private static Hand playerhand;
	private static Hand dealerhand;
	public static ArrayList<ImageView> pcards = new ArrayList<>();
	public static ArrayList<ImageView> dcards = new ArrayList<>();
	public static int nmbplayercards = 0;
	public static int nmbdealercards = 0;
	public static ImageView shuffling;
	public static TranslateTransition tipmove;
	public static TranslateTransition reverse;

	public static ImageView win;
	public static boolean alreadywon;
	public static ImageView playersumborder;
	public static ImageView dealersumborder;
	public static ArrayList<Integer> playeraces = new ArrayList<>();
	public static ArrayList<Integer> dealeraces = new ArrayList<>();
	public static Text playerHandSum = new Text();
	public static Text dealerHandSum = new Text();
	public static Text bankAmount = new Text();
	public static Text betAmountText = new Text();
	public static ImageView backofcard = new ImageView(new Image("application/card.png"));
	public static ArrayList<TranslateTransition> pcardmove = new ArrayList<>();
	public static ArrayList<TranslateTransition> dcardmove = new ArrayList<>();
	public static MediaPlayer player;
	public static int cardsdealt = 0;
	public static ImageView volumeonicon;
	public static ImageView volumeofficon;
	public static boolean volumeon;

	public static ImageView hitButton;
	public static ImageView instructionButton;
	public static ImageView playAgainButton;
	public static ImageView playButtonImage;
	public static ImageView settingButton;
	public static ImageView deal;
	public static ImageView hit;
	public static ImageView pauseButton;

	public static ImageView bankAmountGraphic;
	public static ImageView hintButton;
	public static Chip whiteChip;
	public static Chip redChip;
	public static Chip blueChip;
	public static Chip greenChip;
	public static ImageView betAmount;
	public static ImageView chipValueTip; // when hovering over a chip, this menu opens
	public static boolean betChecking = false; // makes sure that player has already bet before starting to play each
												// round
	public static Timer timer;
	public static Button closeChipTip;
	public static ImageView betTip;
	public static ImageView noMoney;
	public static ImageView winGraphic = new ImageView(new Image("application/winGraphic.gif"));
	public static ImageView loseGraphic = new ImageView(new Image("application/loseGraphic.gif"));
	public static ImageView pushGraphic = new ImageView(new Image("application/200.gif"));
	public static ImageView brokeGraphic = new ImageView(new Image("application/broke.png"));
	public static Button closeInstruction;
	public static Button closeBroke;
	public static ImageView pauseMenueButton;
	public static ImageView standButton;
	public static ImageView dealButton;
	public static ImageView exitButton;
	public static ImageView backToGameButton;
	public static int tester = 0;
	public static ImageView instructionPage;

	@Override
	public void start(Stage stage) {

		// opens up stage that houses all of the panes
		Main.stage = stage;
		stage.setResizable(false);
		stage.setScene(menu);
		stage.show();
		// creates an image that will be the title... URL:
		// https://www.arkadium.com/cn/games/blackjack
		Image title = new Image("application/title.png");
		ImageView title$ = new ImageView(title);
		title$.setFitHeight(650);
		title$.setFitWidth(850);
		title$.setTranslateY(-50);
		title$.setTranslateX(-30);
		root.getChildren().add(title$);

		// Gets the music file and starts it
		String filePath = Main.class.getResource("/application/music.mp3").toString();
		Media song = new Media(filePath);
		player = new MediaPlayer(song);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
		player.setVolume(0.2);
		volumeon = true;

		// Play Button:
		playButtonImage = new ImageView(new Image("application/playButton.png"));
		playButtonImage.setFitHeight(70);
		playButtonImage.setFitWidth(200);
		playButtonImage.setTranslateY(430);
		playButtonImage.setTranslateX(293);
		root2.getChildren().add(playButtonImage);
		// Makes the game run when play button is clicked
		playButtonImage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// sets up game background
			gamebackground();
			// bank is equal to the value from the bankAmount file (saves between games)
			bank = bankAmountGetter();
			shufflinggraphic();
			stage.setScene(maingame);
			stage.show();
		});
		// adding shadow effects to the play button
		setFX(playButtonImage);

		// makes the buttons show on screen
		root.getChildren().addAll(playButtonImage);
	}

	// When the instruction button in the pause menu is pressed, this page is shown
	public void instructionPage() {
		// Sets the location and the size of the instruction menu
		instructionPage = new ImageView(new Image("application/InstructionPage.png"));
		instructionPage.setFitHeight(330);
		instructionPage.setFitWidth(630);
		instructionPage.setTranslateY(135);
		instructionPage.setTranslateX(100);
		root2.getChildren().addAll(instructionPage);
		// adds the close button to the instruction menu
		closeInstruction = new Button("X");
		closeInstruction.setMinWidth(30);
		closeInstruction.setMinHeight(30);
		closeInstruction.setTranslateX(690);
		closeInstruction.setTranslateY(130);
		closeInstruction.setStyle("-fx-font-size: 20; -fx-base: #806000;");
		root2.getChildren().addAll(closeInstruction);
		// adds the event handler to the X button, when pressed both the instruction
		// page and the button are removed
		closeInstruction.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			root2.getChildren().removeAll(instructionPage);
			root2.getChildren().removeAll(closeInstruction);
		});
	}

	public void volumebutton() {
		// makes the image for the volume on button which is used in the pause menu
		volumeonicon = new ImageView(new Image("application/volumeon.png"));
		volumeonicon.setFitHeight(30);
		volumeonicon.setFitWidth(40);
		volumeonicon.setTranslateY(175);
		volumeonicon.setTranslateX(120);
		// makes the image for the volume off button which
		volumeofficon = new ImageView(new Image("application/volumeoff.png"));
		volumeofficon.setFitHeight(30);
		volumeofficon.setFitWidth(40);
		volumeofficon.setTranslateY(175);
		volumeofficon.setTranslateX(120);
		setFX(volumeonicon);
		setFX(volumeofficon);

		// code for if the on volume button is clicked (the music is turned off)
		volumeonicon.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			player.setVolume(0);
			volumeon = false;
			root2.getChildren().removeAll(volumeonicon);
			root2.getChildren().addAll(volumeofficon);

		});

		// code for if the volume off image is clicked (the music is turned on)
		volumeofficon.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			player.setVolume(0.2);
			volumeon = true;
			root2.getChildren().removeAll(volumeofficon);
			root2.getChildren().addAll(volumeonicon);
		});

	}

	// when the play button or playagain button is clicked, this is called
	public void runningGame() {
		// resets the bet counter because a new round has started
		betTotal = 0;
		// changing this boolean to false because the player has not made a bet
		betChecking = false;
		// remove all of the graphics that are put on screen after a round ends
		root2.getChildren().removeAll(winGraphic);
		root2.getChildren().removeAll(playAgainButton);
		root2.getChildren().removeAll(loseGraphic);
		root2.getChildren().removeAll(pushGraphic);
		// call the bankamoutshower so that the bank balance is updated
		bankAmountShower();
		// calls the backGraphic method which creates all the betting and playing
		// components
		bankgraphic();

	}

	// checks if the player wins, pushes or loses to the dealer
	public int winChecker(int playerHandSum, int dealerHandSum) { // return 0 if lose, 1 if push, 2 if win
		if (playerHandSum == dealerHandSum && dealerHandSum <= 21) {
			return 1; // push
		} else if (playerHandSum <= 21 && dealerHandSum > 21) {
			return 2; // win
		} else if (playerHandSum > dealerHandSum && playerHandSum <= 21) {
			return 2; // win
		} else if (playerHandSum < dealerHandSum && dealerHandSum <= 21) {
			return 0; // lose
		}
		return 0; // lose
	}

	// the graphic on the top right of the screen showing the amount of bet
	public void betAmountShower() {
		// remove the previous bet amount
		root2.getChildren().removeAll(betAmount);
		root2.getChildren().removeAll(betAmountText);
		// make the updated bet
		betAmount = new ImageView(new Image("application/betAmount.png"));
		betAmount.setFitHeight(90);
		betAmount.setFitWidth(200);
		betAmount.setTranslateY(20);
		betAmount.setTranslateX(570);
		root2.getChildren().add(betAmount);
		// makes the text of the current bet amount
		betAmountText.setText(betTotal + "");
		betAmountText.setFont(Font.font("Verdana", 40));
		betAmountText.setFill(Color.WHITE);
		betAmountText.setY(90);
		betAmountText.setX(620);
		root2.getChildren().add(betAmountText);
	}

	// saves the players balance by writing it into a text file
	public void bankAmountSaver(int bankAmount) {
		try {
			// makes a new file writer
			FileWriter myWriter = new FileWriter("bankAmount.txt");
			// makes the balance into a string and writes it to the file
			myWriter.write(bankAmount + "");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// gets the balance of the player from the text file
	public static int bankAmountGetter() {
		// the string that the balance is written onto
		String data = "";
		try {

			File myObj = new File("bankAmount.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				// reads the integer from the file and turns it into a string
				data = myReader.nextLine();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// return the integer value
		return Integer.parseInt(data);
	}

	// updates the graphic on the top left with the new bank amount
	public void bankAmountShower() {
		// saves the new balance
		bankAmountSaver(bank);
		// removes the old graphics
		root2.getChildren().removeAll(bankAmountGraphic);
		root2.getChildren().removeAll(bankAmount);
		// makes the base of the graphic
		bankAmountGraphic = new ImageView(new Image("application/balance.png"));
		bankAmountGraphic.setFitHeight(90);
		bankAmountGraphic.setFitWidth(200);
		bankAmountGraphic.setTranslateY(20);
		bankAmountGraphic.setTranslateX(40);
		root2.getChildren().add(bankAmountGraphic);
		// makes the text part of the graphic
		bankAmount.setText(bank + "");
		bankAmount.setFont(Font.font("Verdana", 40));
		bankAmount.setFill(Color.WHITE);
		bankAmount.setY(90);
		bankAmount.setX(90);
		root2.getChildren().add(bankAmount);
	}

	public void betFailError() {
		// If the player tries to make a bet that they cannot afford open this
		// makes the image for the error
		noMoney = new ImageView(new Image("application/betFail.png"));
		noMoney.setFitHeight(300);
		noMoney.setFitWidth(600);
		noMoney.setTranslateY(150);
		noMoney.setTranslateX(100);
		root2.getChildren().add(noMoney);
		// removes all other elements that are extra on the screen
		root2.getChildren().removeAll(dealButton);
		root2.getChildren().removeAll(hintButton);
		root2.getChildren().removeAll(redChip.getImage());
		root2.getChildren().removeAll(dealButton);
		root2.getChildren().removeAll(whiteChip.getImage());
		root2.getChildren().removeAll(greenChip.getImage());
		root2.getChildren().removeAll(blueChip.getImage());
		// add the button used to close the tip
		closeChipTip = new Button("X");
		closeChipTip.setMinWidth(30);
		closeChipTip.setMinHeight(30);
		closeChipTip.setTranslateX(670);
		closeChipTip.setTranslateY(150);
		closeChipTip.setStyle("-fx-font-size: 20; -fx-base: #806000;");
		root2.getChildren().addAll(closeChipTip);
		// add the event handler for the button
		closeChipTip.addEventHandler(MouseEvent.MOUSE_CLICKED, l -> {
			root2.getChildren().removeAll(noMoney);
			root2.getChildren().removeAll(closeChipTip);
			// all the elements that were removed are added again
			root2.getChildren().addAll(dealButton);
			root2.getChildren().addAll(hintButton);
			root2.getChildren().addAll(redChip.getImage());
			root2.getChildren().addAll(whiteChip.getImage());
			root2.getChildren().addAll(greenChip.getImage());
			root2.getChildren().addAll(blueChip.getImage());
		});
	}

	// the graphic for all of the chips
	public void chipGrapic() {
		// makes the graphic for the chip value tip
		chipValueTip = new ImageView(new Image("application/ValueMenue.png"));
		chipValueTip.setFitHeight(300);
		chipValueTip.setFitWidth(300);
		chipValueTip.setTranslateY(140);
		chipValueTip.setTranslateX(-400);
		// makes a transition for the chip tip
		tipmove = new TranslateTransition();
		tipmove.setDuration(Duration.millis(500)); // the transition take 0.5 seconds
		tipmove.setNode(chipValueTip);
		tipmove.setByX(400); // the new X of the tip menu will be 400
		tipmove.setAutoReverse(false);
		// makes the transition for the graphic in the backwards direction
		reverse = new TranslateTransition();
		reverse.setDuration(Duration.millis(500));// the transition take 0.5 seconds
		reverse.setNode(chipValueTip);
		reverse.setByX(-400); // the new X of the tip menu will be -400
		reverse.setAutoReverse(false);
		// hint Button and the animations for it:
		hintButton = new ImageView(new Image("application/hint.png"));
		hintButton.setFitHeight(100);
		hintButton.setFitWidth(100);
		hintButton.setTranslateY(480);
		hintButton.setTranslateX(30);
		setFX(hintButton);
		root2.getChildren().add(hintButton);
		// clicked is use to see if the window is open or not (if it is divisible by 2,
		// it is not open)
		clicked = 0;
		hintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (clicked % 2 == 0) {
				// timer is used to execute code at designated time intervals
				Timer myTimer = new Timer();
				myTimer.scheduleAtFixedRate(new TimerTask() {
					// once a chunk of code has run, this becomes true and the next chunk will run
					boolean hasrun = false;

					@Override
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								if (!hasrun) {
									hintButton.setDisable(true);// disables the hint button
									hasrun = true;
									root2.getChildren().add(chipValueTip);
									clicked++;
									tipmove.play(); // plays the animation for the tip button to move
								} else {
									hintButton.setDisable(false); // the hint button is activated again
									myTimer.cancel();
								}
							}
						});
					}
				}, 0, 500); // takes 0.5 seconds to run each segment of the above code

			} else { // the program now knows that the button has already been pressed meaning that
						// its time for the tip menu to go back
				Timer myTimer = new Timer();
				myTimer.scheduleAtFixedRate(new TimerTask() {
					boolean hasrun = false;

					@Override
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
								if (!hasrun) {
									hintButton.setDisable(true); // sets the hint button disable again
									clicked++;
									reverse.play(); // plays the animation that takes the hint page back again
									hasrun = true;
								} else {
									root2.getChildren().remove(chipValueTip);
									hintButton.setDisable(false);
									myTimer.cancel();
								}
							}
						});
					}
				}, 0, 500);

			}
		});

		// creates a new Chip object with a value of 5 and Image
		whiteChip = new Chip(5, new ImageView(new Image("application/white.png")));
		whiteChip.getImage().setFitHeight(100);
		whiteChip.getImage().setFitWidth(99);
		whiteChip.getImage().setTranslateY(480);
		whiteChip.getImage().setTranslateX(150);
		// adds the chip's image
		root2.getChildren().add(whiteChip.getImage());
		// adds the shadows for the white chip
		setFX(whiteChip.getImage());
		// when the chip is pressed, it adds its value to the total bet amount and
		// subtracts it from bank
		whiteChip.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (bank >= whiteChip.getValue()) { // the player has enough money to make the bet
				betChecking = true;
				betTotal += whiteChip.getValue();
				bank -= whiteChip.getValue();
				bankAmountShower(); // updates bank amount
				betAmountShower(); // updates bet amount
			} else {
				betFailError(); // if player does not have enough money to make the bet
			}
		});
		// same as above for the red chip
		redChip = new Chip(20, new ImageView(new Image("application/red.png")));
		redChip.getImage().setFitHeight(100);
		redChip.getImage().setFitWidth(99);
		redChip.getImage().setTranslateY(480);
		redChip.getImage().setTranslateX(260);
		setFX(redChip.getImage());
		root2.getChildren().add(redChip.getImage());
		redChip.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (bank >= redChip.getValue()) {
				betChecking = true;
				betTotal += redChip.getValue();
				bank -= redChip.getValue();
				bankAmountShower();
				betAmountShower();
			} else {
				betFailError();
			}
		});
		// same as above for the blue chip
		blueChip = new Chip(50, new ImageView(new Image("application/blue.png")));
		blueChip.getImage().setFitHeight(100);
		blueChip.getImage().setFitWidth(99);
		blueChip.getImage().setTranslateY(480);
		blueChip.getImage().setTranslateX(370);
		setFX(blueChip.getImage());
		root2.getChildren().add(blueChip.getImage());
		blueChip.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (bank >= blueChip.getValue()) {
				betChecking = true;
				betTotal += blueChip.getValue();
				bank -= blueChip.getValue();
				bankAmountShower();
				betAmountShower();
			} else {
				betFailError();
			}
		});
		// same as above for the green chip
		greenChip = new Chip(100, new ImageView(new Image("application/green.png")));
		greenChip.getImage().setFitHeight(100);
		greenChip.getImage().setFitWidth(99);
		greenChip.getImage().setTranslateY(480);
		greenChip.getImage().setTranslateX(480);
		setFX(greenChip.getImage());
		root2.getChildren().add(greenChip.getImage());
		greenChip.getImage().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (bank >= greenChip.getValue()) {
				betChecking = true;
				betTotal += greenChip.getValue();
				bank -= greenChip.getValue();
				bankAmountShower();
				betAmountShower();
			} else {
				betFailError();
			}
		});
	}

	// this method is responsible for the pause button in the game
	public void pauseButtonMethod() {
		// make the graphics for the pause button itself
		pauseButton = new ImageView(new Image("application/pauseButton.png"));
		pauseButton.setFitHeight(70);
		pauseButton.setFitWidth(70);
		pauseButton.setTranslateY(30);
		pauseButton.setTranslateX(367);
		// add the shadows to the pause button
		setFX(pauseButton);
		// the menu that opens when the pause button is pressed
		pauseMenueButton = new ImageView(new Image("application/pauseMenue.png"));
		pauseMenueButton.setFitHeight(300);
		pauseMenueButton.setFitWidth(600);
		pauseMenueButton.setTranslateY(150);
		pauseMenueButton.setTranslateX(100);
		// makes the exit button (cash-out) that closes the game when clicked on
		exitButton = new ImageView(new Image("application/cashout.png"));
		exitButton.setFitHeight(50);
		exitButton.setFitWidth(250);
		exitButton.setTranslateY(230);
		exitButton.setTranslateX(267);
		// add the shadows to the exit button
		setFX(exitButton);
		exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// closes the game
			Platform.exit();
			System.exit(0);
		});
		// makes the instruction button
		instructionButton = new ImageView(new Image("application/instructionButton.png"));
		instructionButton.setFitHeight(50);
		instructionButton.setFitWidth(150);
		instructionButton.setTranslateY(300);
		instructionButton.setTranslateX(320);
		// adds the shadows to the instruction button
		setFX(instructionButton);
		instructionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// open Instruction Menu:
			instructionPage();
		});
		// adds the back to game button
		backToGameButton = new ImageView(new Image("application/backToGameButton.png"));
		backToGameButton.setFitHeight(50);
		backToGameButton.setFitWidth(300);
		backToGameButton.setTranslateY(370);
		backToGameButton.setTranslateX(240);
		// adds the shadows to the button
		setFX(backToGameButton);
		// adds the event handler for the back to game button
		backToGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// Take the player back to the game
			// removes all elements of the pause menu
			root2.getChildren().removeAll(pauseMenueButton);
			root2.getChildren().removeAll(exitButton);
			root2.getChildren().removeAll(backToGameButton);
			root2.getChildren().removeAll(instructionButton);
			root2.getChildren().removeAll(volumeonicon);
			root2.getChildren().removeAll(volumeofficon);
			// re-adds all the elements of the game
			root2.getChildren().addAll(dealButton);
			root2.getChildren().addAll(hintButton);
			root2.getChildren().addAll(redChip.getImage());
			root2.getChildren().addAll(whiteChip.getImage());
			root2.getChildren().addAll(greenChip.getImage());
			root2.getChildren().addAll(blueChip.getImage());
			root2.getChildren().addAll(pauseButton);

		});

		root2.getChildren().addAll(pauseButton);
		// adds the event handler for the pause button
		pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// removes all current elements that are on the screen
			root2.getChildren().removeAll(dealButton);
			root2.getChildren().removeAll(hintButton);
			root2.getChildren().removeAll(redChip.getImage());
			root2.getChildren().removeAll(whiteChip.getImage());
			root2.getChildren().removeAll(greenChip.getImage());
			root2.getChildren().removeAll(blueChip.getImage());
			root2.getChildren().removeAll(pauseButton);
			root2.getChildren().removeAll(chipValueTip);
			// adds all of the button that are in the pause menu
			root2.getChildren().add(pauseMenueButton);
			root2.getChildren().addAll(exitButton);
			root2.getChildren().addAll(backToGameButton);
			// adds the volume button in the pause menue
			volumebutton();
			if (volumeon) {
				root2.getChildren().add(volumeonicon);
			} else {
				root2.getChildren().add(volumeofficon);
			}
			root2.getChildren().addAll(instructionButton);
		});
	}

	public void bankgraphic() {
		// adds the chips
		chipGrapic();
		// adds the pause button
		pauseButtonMethod();
		root2.getChildren().removeAll(brokeGraphic);
		root2.getChildren().removeAll(dealButton);
		// check to make sure that the player has more than 5$ to bet with
		if (bank < 5) {
			tester = 1;
			// opens the tip menu
			brokeGraphic.setFitHeight(330);
			brokeGraphic.setFitWidth(630);
			brokeGraphic.setTranslateY(135);
			brokeGraphic.setTranslateX(100);
			root2.getChildren().removeAll(dealButton);
			root2.getChildren().removeAll(hintButton);
			root2.getChildren().removeAll(redChip.getImage());
			root2.getChildren().removeAll(whiteChip.getImage());
			root2.getChildren().removeAll(greenChip.getImage());
			root2.getChildren().removeAll(blueChip.getImage());
			root2.getChildren().removeAll(chipValueTip);
			root2.getChildren().removeAll(pauseButton);
			root2.getChildren().addAll(brokeGraphic);

			// adds the close button
			closeBroke = new Button("X");
			closeBroke.setMinWidth(30);
			closeBroke.setMinHeight(30);
			closeBroke.setTranslateX(690);
			closeBroke.setTranslateY(130);
			closeBroke.setStyle("-fx-font-size: 20; -fx-base: #806000;");
			root2.getChildren().addAll(closeBroke);
			closeBroke.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
				tester = 0;
				bank = 1000;
				bankAmountShower();
				root2.getChildren().removeAll(brokeGraphic);
				root2.getChildren().removeAll(closeBroke);
				root2.getChildren().addAll(hintButton);
				root2.getChildren().addAll(redChip.getImage());
				root2.getChildren().addAll(whiteChip.getImage());
				root2.getChildren().addAll(greenChip.getImage());
				root2.getChildren().addAll(blueChip.getImage());
				root2.getChildren().addAll(chipValueTip);
				root2.getChildren().addAll(pauseButton);
				root2.getChildren().addAll(dealButton);
			});
		}

		// deal Button
		dealButton = new ImageView(new Image("application/dealButton.png"));
		dealButton.setFitHeight(50);
		dealButton.setFitWidth(150);
		dealButton.setTranslateY(513);
		dealButton.setTranslateX(627);

		setFX(dealButton);

		// adds the event handler to the deal button
		dealButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (betChecking == true) {
				root2.getChildren().removeAll(pauseButton);
				root2.getChildren().removeAll(hintButton);
				root2.getChildren().removeAll(redChip.getImage());
				root2.getChildren().removeAll(whiteChip.getImage());
				root2.getChildren().removeAll(greenChip.getImage());
				root2.getChildren().removeAll(blueChip.getImage());
				root2.getChildren().removeAll(chipValueTip);
				alreadywon = false;
				root2.getChildren().remove(dealButton);
				dealing();
				// only adds the buttons if you have not already won
				if (!alreadywon) {
					hit();
					stand();
				}

			} else {
				// show graphic saying that you need to bet before hitting
				root2.getChildren().removeAll(dealButton);
				betTip = new ImageView(new Image("application/chipTip.png"));
				betTip.setFitHeight(300);
				betTip.setFitWidth(600);
				betTip.setTranslateY(150);
				betTip.setTranslateX(100);
				root2.getChildren().removeAll(dealButton);
				root2.getChildren().removeAll(hintButton);
				root2.getChildren().removeAll(redChip.getImage());
				root2.getChildren().removeAll(whiteChip.getImage());
				root2.getChildren().removeAll(greenChip.getImage());
				root2.getChildren().removeAll(blueChip.getImage());
				root2.getChildren().removeAll(chipValueTip);
				root2.getChildren().removeAll(pauseButton);
				root2.getChildren().add(betTip);
				// remove box if X is pressed:
				closeChipTip = new Button("X");
				closeChipTip.setMinWidth(30);
				closeChipTip.setMinHeight(30);
				closeChipTip.setTranslateX(670);
				closeChipTip.setTranslateY(150);
				closeChipTip.setStyle("-fx-font-size: 20; -fx-base: #806000;");
				root2.getChildren().addAll(closeChipTip);
				closeChipTip.addEventHandler(MouseEvent.MOUSE_CLICKED, l -> {
					root2.getChildren().removeAll(closeChipTip);
					root2.getChildren().removeAll(betTip);
					root2.getChildren().removeAll(hintButton);
					root2.getChildren().removeAll(redChip.getImage());
					root2.getChildren().removeAll(whiteChip.getImage());
					root2.getChildren().removeAll(greenChip.getImage());
					root2.getChildren().removeAll(blueChip.getImage());
					bankgraphic();
				});
			}
		});
		if (tester == 0)
			root2.getChildren().addAll(dealButton);
	}

	public void gamebackground() {
		// URL: https://www.arkadium.com/cn/games/blackjack
		// adds game background

		Image gamebackground = new Image("application/background.jpg");
		ImageView gamebackground$ = new ImageView(gamebackground);
		gamebackground$.setFitHeight(650);
		gamebackground$.setFitWidth(850);
		gamebackground$.setTranslateY(-50);
		gamebackground$.setTranslateX(-30);
		root2.getChildren().add(gamebackground$);
	}

	// creates shuffling graphic and after 2.5 seconds, it disappears and starts the
	// game
	public void shufflinggraphic() {
		Timer myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask() {
			boolean hasrun = false;

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (!hasrun) {
							shuffling = new ImageView(new Image("application/shuffling.png"));
							shuffling.setFitHeight(150);
							shuffling.setFitWidth(495);
							shuffling.setTranslateY(225);
							shuffling.setTranslateX(155);
							root2.getChildren().add(shuffling);
							hasrun = true;
						} else {

							root2.getChildren().remove(shuffling);
							myTimer.cancel();
							runningGame();
						}

					}
				});
			}
		}, 0, 2500);

	}

	public int sumofhand(Hand hand) {
		// sum starts as zero
		int sum = 0;

		// loop iterates through every card in the hand and the sum is the summation of
		// all of the values of the cards
		for (int i = 0; i < hand.getHand().size(); i++) {
			sum += hand.getHand().get(i).getValue();
		}
		return sum;
	}

	public void dealing() {
		// shuffles the deck before each deal
		deck.shuffle();
		// the player's hand is dealt two cards (at indices 0 and 1)
		playerhand = deck.deal(cardsdealt++, cardsdealt++);
		// adds an image using the getFilename call
		pcards.add(new ImageView(new Image((playerhand.getHand().get(nmbplayercards).getFilename()))));
		pcards.get(nmbplayercards).setFitHeight(150);
		pcards.get(nmbplayercards).setFitWidth(100);
		pcards.get(nmbplayercards).setTranslateY(-100);
		pcards.get(nmbplayercards).setTranslateX(850);
		root2.getChildren().add(pcards.get(nmbplayercards));
		// assigns the card a value
		playerhand.getHand().get(nmbplayercards).assignValue();
		// if the card is an ace, its index is added to an ArrayList that keeps track of
		// the aces in the player's hand
		if (playerhand.getHand().get(nmbplayercards).getRank().getName().equals("Ace")) {
			playeraces.add(nmbplayercards);
		}
		// adds a transition to an arrayList of transitions
		pcardmove.add(new TranslateTransition(Duration.millis(600), pcards.get(nmbplayercards)));
		pcardmove.get(nmbplayercards).setByX(-520);
		pcardmove.get(nmbplayercards).setByY(500);
		// keeps track of the number of player cards
		nmbplayercards++;

		// same as above for the second card
		pcards.add(new ImageView(new Image(playerhand.getHand().get(nmbplayercards).getFilename())));
		pcards.get(nmbplayercards).setFitHeight(150);
		pcards.get(nmbplayercards).setFitWidth(100);
		pcards.get(nmbplayercards).setTranslateY(-100);
		pcards.get(nmbplayercards).setTranslateX(850);
		root2.getChildren().add(pcards.get(nmbplayercards));
		playerhand.getHand().get(nmbplayercards).assignValue();
		if (playerhand.getHand().get(nmbplayercards).getRank().getName().equals("Ace")) {
			playeraces.add(nmbplayercards);
		}
		pcardmove.add(new TranslateTransition(Duration.millis(600), pcards.get(nmbplayercards)));
		pcardmove.get(nmbplayercards).setByX(-495);
		pcardmove.get(nmbplayercards).setByY(500);
		nmbplayercards++;

		// same thing as above, but with the dealer's cards
		dealerhand = deck.deal(cardsdealt++, cardsdealt++); // indices 2 and 3
		dcards.add(new ImageView(new Image(dealerhand.getHand().get(nmbdealercards).getFilename())));
		dcards.get(nmbdealercards).setFitHeight(150);
		dcards.get(nmbdealercards).setFitWidth(100);
		dcards.get(nmbdealercards).setTranslateY(100);
		dcards.get(nmbdealercards).setTranslateX(330);
		backofcard.setFitHeight(215);
		backofcard.setFitWidth(150);
		backofcard.setTranslateY(-100);
		backofcard.setTranslateX(850);
		root2.getChildren().add(backofcard);
		dealerhand.getHand().get(nmbdealercards).assignValue();
		if (dealerhand.getHand().get(nmbdealercards).getRank().getName().equals("Ace")) {
			dealeraces.add(nmbdealercards);
		}
		dcardmove.add(new TranslateTransition(Duration.millis(600), backofcard));
		dcardmove.get(nmbdealercards).setByX(-543);
		dcardmove.get(nmbdealercards).setByY(179.5);
		nmbdealercards++;

		dcards.add(new ImageView(new Image(dealerhand.getHand().get(nmbdealercards).getFilename())));
		dcards.get(nmbdealercards).setFitHeight(150);
		dcards.get(nmbdealercards).setFitWidth(100);
		dcards.get(nmbdealercards).setTranslateY(-100);
		dcards.get(nmbdealercards).setTranslateX(850);
		root2.getChildren().add(dcards.get(nmbdealercards));
		dealerhand.getHand().get(nmbdealercards).assignValue();
		if (dealerhand.getHand().get(nmbdealercards).getRank().getName().equals("Ace")) {
			dealeraces.add(nmbdealercards);
		}
		dcardmove.add(new TranslateTransition(Duration.millis(600), dcards.get(nmbdealercards)));
		dcardmove.get(nmbdealercards).setByX(-495);
		dcardmove.get(nmbdealercards).setByY(200);
		nmbdealercards++;

		// shows the cards being dealt and checks if you won
		dealinggraphic();

		// if the sum of the player's hand is more than 21 (two aces), set one of the
		// aces value to 1 and remove it from the "playeraces" arrayList (a tracker)

		if (sumofhand(playerhand) > 21) {
			playerhand.getHand().get(playeraces.get(0)).setValue();
			playeraces.remove(0);
		}

		// same as above for the dealer

		if (sumofhand(dealerhand) > 21) {
			dealerhand.getHand().get(dealeraces.get(0)).setValue();
			dealeraces.remove(0);
		}

	}

	public void dealinggraphic() {
		Timer myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask() {
			// booleans to segment the code so it runs at certain time intervals
			boolean[] hasrun = { false, false, false, false, false };

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (!hasrun[0]) {
							pcardmove.get(0).play();
							hasrun[0] = true;
						} else {
							if (!hasrun[1]) {
								dcardmove.get(0).play();
								hasrun[1] = true;
							} else {
								if (!hasrun[2]) {
									pcardmove.get(1).play();
									hasrun[2] = true;
								} else {
									if (!hasrun[3]) {
										dcardmove.get(1).play();
										hasrun[3] = true;

									} else {
										if (!hasrun[4]) {
											// adds the sums and buttons
											root2.getChildren().addAll(hitButton);
											root2.getChildren().addAll(standButton);
											phandSumShower();
											dhandSumShower();
											hasrun[4] = true;
										} else {
											// checks to see if the player has already won
											if (sumofhand(playerhand) == 21) {
												alreadywon = true;
												// disables the buttons from being pressed if the player has won
												standButton.setDisable(true);
												hitButton.setDisable(true);
												firsthandwon();

											}
											myTimer.cancel();
										}

									}
								}
							}
						}
					}
				});
			}

		}, 0, 550);

	}

	public void dealerhit() {
		// the dealerhand object hits and a new card is added both in code and
		// graphically
		dealerhand.hit();
		Hand.hits++;
		cardsdealt++;
		dcards.add(new ImageView(new Image(dealerhand.getHand().get(nmbdealercards).getFilename())));
		dcards.get(nmbdealercards).setFitHeight(150);
		dcards.get(nmbdealercards).setFitWidth(100);
		dcards.get(nmbdealercards).setTranslateY(-100);
		dcards.get(nmbdealercards).setTranslateX(850);
		root2.getChildren().add(dcards.get(nmbdealercards));
		dealerhand.getHand().get(nmbdealercards).assignValue();
		if (dealerhand.getHand().get(nmbdealercards).getRank().getName().equals("Ace")) {
			dealeraces.add(nmbdealercards);
		}

		// checks to see if there is an ace and the sum is greater than 21 .. 
		// if so, one ace is set to have a value of 1
		if (sumofhand(dealerhand) > 21 && dealeraces.size() > 0) {
			dealerhand.getHand().get(dealeraces.get(0)).setValue();
			dealeraces.remove(0);
		}

		dcardmove.add(new TranslateTransition(Duration.millis(600), dcards.get(nmbdealercards)));
		// the position of the card depends on how many dealer cards there are 
		dcardmove.get(nmbdealercards).setByX(-495 + 25 * (nmbdealercards - 1));
		dcardmove.get(nmbdealercards).setByY(200);

		dcardmove.get(nmbdealercards).play();

		nmbdealercards++;

	}

	public void hit() {

		hitButton = new ImageView(new Image("application/hitButton.png"));
		hitButton.setFitHeight(50);
		hitButton.setFitWidth(150);
		hitButton.setTranslateY(250);
		hitButton.setTranslateX(620);

		setFX(hitButton);

		hitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			// the player hand hits and the card is added both graphically and in code
			playerhand.hit();
			Hand.hits++;
			cardsdealt++;
			pcards.add(new ImageView(new Image(playerhand.getHand().get(nmbplayercards).getFilename())));
			pcards.get(nmbplayercards).setFitHeight(150);
			pcards.get(nmbplayercards).setFitWidth(100);
			pcards.get(nmbplayercards).setTranslateY(-100);
			pcards.get(nmbplayercards).setTranslateX(850);
			root2.getChildren().add(pcards.get(nmbplayercards));
			playerhand.getHand().get(nmbplayercards).assignValue();
			if (playerhand.getHand().get(nmbplayercards).getRank().getName().equals("Ace")) {
				playeraces.add(nmbplayercards);
			}

			pcardmove.add(new TranslateTransition(Duration.millis(600), pcards.get(nmbplayercards)));
			pcardmove.get(nmbplayercards).setByX(-495 + 25 * (nmbplayercards - 1));
			pcardmove.get(nmbplayercards).setByY(500);

			// checks to see if there is an ace and the sum is greater than 21 .. 
			// if so, one ace is set to have a value of 1
			if (sumofhand(playerhand) > 21 && playeraces.size() > 0) {
				playerhand.getHand().get(playeraces.get(0)).setValue();
				playeraces.remove(0);
			}
			phandSumShower();

			//if sum of the hand is 21, a graphic is called and the buttons disabled
			if (sumofhand(playerhand) == 21) {

				standButton.setDisable(true);
				hitButton.setDisable(true);
				dealerhitgraphic1(nmbplayercards);

			}

			else {
				// if sum is greater than 21, a different graphic is called
				if (sumofhand(playerhand) > 21) {
					standButton.setDisable(true);
					hitButton.setDisable(true);
					dealerhitgraphic1(nmbplayercards);
				} else {
					//if neither, the card is just dealt on screen
					pcardmove.get(nmbplayercards).play();
				}
			}
			nmbplayercards++;
		});
	}

	// if you win, a graphic is called, bank is updated, and betTotal is reset
	public void win() {
		root2.getChildren().removeAll(betAmount);
		root2.getChildren().removeAll(betAmountText);
		bank += betTotal * 2;
		betTotal = 0;
		winGraphic.setFitHeight(225);
		winGraphic.setFitWidth(400);
		winGraphic.setTranslateY(160);
		winGraphic.setTranslateX(190);
		root2.getChildren().addAll(winGraphic);
		bankAmountShower();

	}

	// if you tie, a graphic is called, bank is updated, and betTotal is reset
	public void push() {
		root2.getChildren().removeAll(betAmount);
		root2.getChildren().removeAll(betAmountText);
		bank += betTotal;
		betTotal = 0;
		pushGraphic.setFitHeight(225);
		pushGraphic.setFitWidth(300);
		pushGraphic.setTranslateY(160);
		pushGraphic.setTranslateX(245);
		root2.getChildren().addAll(pushGraphic);
		bankAmountShower();
	}

	// if you lose, a graphic is called and betTotal is reset
	public void lose() {
		root2.getChildren().removeAll(betAmount);
		root2.getChildren().removeAll(betAmountText);
		betTotal = 0;
		loseGraphic.setFitHeight(200);
		loseGraphic.setFitWidth(300);
		loseGraphic.setTranslateY(200);
		loseGraphic.setTranslateX(250);
		root2.getChildren().addAll(loseGraphic);
		bankAmountShower();
	}

	public void dealerhitgraphic1(int i) {
		Timer myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask() {
			// these booleans are used so the code is executed in chunks at certain times
			// (in this case 1000 milliseconds)
			boolean hasrun = false;
			boolean wait = false;
			boolean hasflip = false;

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (wait) {
							// runs last... removes all cards and resets everything for a new hand
							for (int i = 0; i < pcards.size(); i++) {
								root2.getChildren().removeAll(pcards.get(i));
								pcardmove.get(i).stop();
							}
							for (int i = 0; i < dcards.size(); i++) {
								root2.getChildren().removeAll(dcards.get(i));
								dcardmove.get(i).stop();
							}
							hitButton.setDisable(false);
							standButton.setDisable(false);
							root2.getChildren().removeAll(hitButton);
							root2.getChildren().removeAll(standButton);
							playeraces.removeAll(playeraces);
							dealeraces.removeAll(dealeraces);
							pcardmove.removeAll(pcardmove);
							dcardmove.removeAll(dcardmove);
							pcards.removeAll(pcards);
							dcards.removeAll(dcards);
							nmbplayercards = 0;
							nmbdealercards = 0;
							Hand.hits = 0;
							root2.getChildren().removeAll(playersumborder);
							root2.getChildren().removeAll(playerHandSum);
							root2.getChildren().removeAll(dealersumborder);
							root2.getChildren().removeAll(dealerHandSum);

							// calls for the play again button, which also checks to see who won the hand
							playAgainB();
							myTimer.cancel();

						}
						// runs the graphic for the card to enter the pane
						if (!hasrun) {
							pcardmove.get(i).play();
							hasrun = true;
						}

						else {
							if (!hasflip) {
								// shows the two dealer cards
								root2.getChildren().remove(backofcard);
								root2.getChildren().removeAll(dcards.get(1));
								root2.getChildren().add(dcards.get(0));
								root2.getChildren().add(dcards.get(1));
								// hits is = 1 so that the sum will update (check comments in dhandSumShower())
								Hand.hits = 1;
								dhandSumShower();
								hasflip = true;
							} else {
								// adds dealer cards until its sum is greater than or equal to 17
								if (sumofhand(dealerhand) < 17) {

									dealerhit();
									dhandSumShower();

								} else {
									wait = true;

								}
							}
						}
					}
				});
			}
		}, 0, 1000);

	}

	public void dealerhitgraphic2() {
		Timer myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask() {
			boolean wait = false;
			boolean hasflip = false;

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						if (wait) {
							for (int i = 0; i < pcards.size(); i++) {
								root2.getChildren().removeAll(pcards.get(i));
								pcardmove.get(i).stop();
							}
							for (int i = 0; i < dcards.size(); i++) {
								root2.getChildren().removeAll(dcards.get(i));
								dcardmove.get(i).stop();
							}
							standButton.setDisable(false);
							hitButton.setDisable(false);
							root2.getChildren().removeAll(hitButton);
							root2.getChildren().removeAll(standButton);
							playeraces.removeAll(playeraces);
							dealeraces.removeAll(dealeraces);
							pcardmove.removeAll(pcardmove);
							dcardmove.removeAll(dcardmove);
							pcards.removeAll(pcards);
							dcards.removeAll(dcards);
							nmbplayercards = 0;
							nmbdealercards = 0;
							Hand.hits = 0;
							root2.getChildren().removeAll(playersumborder);
							root2.getChildren().removeAll(playerHandSum);
							root2.getChildren().removeAll(dealersumborder);
							root2.getChildren().removeAll(dealerHandSum);
							// play again button
							playAgainB();
							myTimer.cancel();

						}
						if (!hasflip) {

							root2.getChildren().remove(backofcard);
							root2.getChildren().removeAll(dcards.get(1));
							root2.getChildren().add(dcards.get(0));
							root2.getChildren().add(dcards.get(1));
							Hand.hits = 1;
							dhandSumShower();
							hasflip = true;
						} else {

							if (sumofhand(dealerhand) < 17) {

								dealerhit();
								dhandSumShower();

							} else {
								wait = true;

							}
						}

					}
				});
			}
		}, 0, 1000);

	}

	public void firsthandwon() {
		Timer myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask() {
			boolean hasflip = false;
			boolean wait = false;

			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {

						if (!hasflip) {
							root2.getChildren().removeAll(backofcard);
							root2.getChildren().removeAll(dcards.get(0));
							root2.getChildren().removeAll(dcards.get(1));
							root2.getChildren().add(dcards.get(0));
							root2.getChildren().add(dcards.get(1));
							Hand.hits = 1;
							dhandSumShower();
							hasflip = true;
						} else {
							if (sumofhand(dealerhand) < 17) {

								dealerhit();
								dhandSumShower();

							}

							else {
								wait = true;

							}
						}
						if (wait) {
							for (int i = 0; i < pcards.size(); i++) {
								root2.getChildren().removeAll(pcards.get(i));
								pcardmove.get(i).stop();
							}
							for (int i = 0; i < dcards.size(); i++) {
								root2.getChildren().removeAll(dcards.get(i));
								dcardmove.get(i).stop();
							}
							standButton.setDisable(false);
							hitButton.setDisable(false);
							root2.getChildren().removeAll(hitButton);
							root2.getChildren().removeAll(standButton);
							playeraces.removeAll(playeraces);
							dealeraces.removeAll(dealeraces);
							pcardmove.removeAll(pcardmove);
							dcardmove.removeAll(dcardmove);
							pcards.removeAll(pcards);
							dcards.removeAll(dcards);

							nmbplayercards = 0;
							nmbdealercards = 0;
							Hand.hits = 0;

							root2.getChildren().removeAll(playersumborder);
							root2.getChildren().removeAll(playerHandSum);
							root2.getChildren().removeAll(dealersumborder);
							root2.getChildren().removeAll(dealerHandSum);

							playAgainB();
							myTimer.cancel();

						}
					}
				});
			}
		}, 0, 1000);

	}

	public void stand() {
		standButton = new ImageView(new Image("application/standButton.png"));
		standButton.setFitHeight(50);
		standButton.setFitWidth(150);
		standButton.setTranslateY(350);
		standButton.setTranslateX(620);

		setFX(standButton);

		// if stand is pressed, it disables both the hit and stand button from being
		// pressed again
		// and a graphic is played for the dealer's hits
		standButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

			standButton.setDisable(true);
			hitButton.setDisable(true);
			dealerhitgraphic2();
		});
	}

	public void phandSumShower() {
		// removes the old sum to be updated
		root2.getChildren().removeAll(playersumborder);
		root2.getChildren().removeAll(playerHandSum);

		playersumborder = new ImageView(new Image("application/playerborder.png"));
		playersumborder.setFitHeight(100);
		playersumborder.setFitWidth(200);
		playersumborder.setTranslateY(450);
		playersumborder.setTranslateX(575);
		root2.getChildren().add(playersumborder);

		playerHandSum.setText(sumofhand(playerhand) + "");
		playerHandSum.setFont(Font.font("Verdana", 40));
		playerHandSum.setFill(Color.WHITE);
		// this centers the sum depending on whether it is one or two digits
		if (sumofhand(playerhand) >= 10) {
			playerHandSum.setX(600);
		} else {
			playerHandSum.setX(612);
		}
		playerHandSum.setY(515);
		root2.getChildren().add(playerHandSum);
	}

	// shows the dealer hand's sum
	public void dhandSumShower() {
		// removes the old sum as to be updated
		root2.getChildren().removeAll(dealersumborder);
		root2.getChildren().removeAll(dealerHandSum);

		dealersumborder = new ImageView(new Image("application/dealerborder.png"));
		dealersumborder.setFitHeight(100);
		dealersumborder.setFitWidth(220);
		dealersumborder.setTranslateY(128);
		dealersumborder.setTranslateX(573);
		root2.getChildren().add(dealersumborder);

		// if player has not hit, the sum should only be the value of the card that is
		// visible
		if (Hand.hits > 0) {
			dealerHandSum.setText(sumofhand(dealerhand) + "");
			dealerHandSum.setFont(Font.font("Verdana", 40));
			dealerHandSum.setFill(Color.WHITE);
			if (sumofhand(dealerhand) >= 10) {
				dealerHandSum.setX(600);
			} else {
				dealerHandSum.setX(612);
			}
			dealerHandSum.setY(192);
			root2.getChildren().add(dealerHandSum);
		} else {
			dealerHandSum.setText(dealerhand.getHand().get(1).getValue() + "");
			dealerHandSum.setFont(Font.font("Verdana", 40));
			dealerHandSum.setFill(Color.WHITE);
			if (dealerhand.getHand().get(1).getValue() >= 10) {
				dealerHandSum.setX(600);
			} else {
				dealerHandSum.setX(612);
			}
			dealerHandSum.setY(192);
			root2.getChildren().add(dealerHandSum);
		}

	}

	// adds a button that allows the player to play another hand
	public void playAgainB() {
		playAgainButton = new ImageView(new Image("application/playAgainButton.png"));
		playAgainButton.setFitWidth(300);
		playAgainButton.setFitHeight(75);
		playAgainButton.setTranslateX(250);
		playAgainButton.setTranslateY(400);

		setFX(playAgainButton);

		// checks to see if the player's hand won, lost, or it was a push (tie)
		if (winChecker(sumofhand(playerhand), sumofhand(dealerhand)) == 0) {
			lose();
		} else if (winChecker(sumofhand(playerhand), sumofhand(dealerhand)) == 1) {
			push();
		} else if (winChecker(sumofhand(playerhand), sumofhand(dealerhand)) == 2) {
			win();
		}

		root2.getChildren().addAll(playAgainButton);
		// if play again button is pressed, it goes back to chip menu where you can bet
		playAgainButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			runningGame();
		});
	}

	// method that adds effects to an image (buttons, chips, etc.)
	public void setFX(ImageView image) {
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			image.setEffect(shadow);

		});
		image.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			image.setEffect(null);

		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
