
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;

public class MainWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String wordToGuess;
	
	public boolean twoPlayer = false;
	
	public int difficulty;
		/*
		 * 1) 10 guesses - iterates by 6 - easy
		 * 2) 6 guesses - iterates by 10 - medium
		 * 3) 4 guesses - iterates by 15 - hard
		 * 4) 1 guess - iterates by 60 - insane
		 */
	public int gameMode;
		/*
		 * 1)Enter own word/random
		 * 2)Holidays
		 * 3)Food
		 * 4)Colors
		 * 5)Locations
		 */
	
	private int remainingGuesses = 60;//not really 60. use 60 because it can be
										//divided by all numbers from difficulty
	public int guessIterator;//depends based on difficulty
	
	public String wrongGuesses = "";
	public String visibleWord;
	
	private String longWord = "PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIOSIS";
	
	
	public MainWindow(){
		super("Hangman");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		
		/*
		 * NUMBER OF PLAYERS
		 */
		JButton onePlayerButton = new JButton("One Player");
		onePlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
					twoPlayer = false;
					System.out.println("twoPlayer? " + twoPlayer);
					
			}
		}); 
		
		JButton twoPlayerButton = new JButton("twoPlayer");
		twoPlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
					
					twoPlayer = true;
					System.out.println("twoPlayer? " + twoPlayer);
			}
		});
		
		add(onePlayerButton);
		add(twoPlayerButton);
		
		//REMOVE BUTTONS - WAIT FUNCTION OR SOMETHING
		//IF TWO PLAYER SKIP THE NEXT STEPS AND ASK FOR USER INPUT
		
		/*
		 * DIFFICULTY
		 */
		
		JButton easy = new JButton("Easy");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 1;	
				guessIterator = 6;
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		JButton medium = new JButton("Medium");
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 2;	
				guessIterator = 10;
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		JButton hard = new JButton("Hard");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 3;
				guessIterator = 15;
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		JButton insane = new JButton("Insane");
		insane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 4;
				guessIterator = 60;
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		add(easy);
		add(medium);
		add(hard);
		add(insane);
		
		//REMOVE BUTTONS - WAIT FUNCTION OR SOMETHING
		
		/*
		 * GAME MODE
		 */
		
		JButton randomWord = new JButton("Random Word");
		randomWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 1;
				String words[] = new String[]{"TOASTER", "SPATULA", "GLASSES", "CURTAINS", "COUCH",
						"COMPUTER", "TOILET", "PENCIL", "SPONGE", "LIGHT", 
						"QUILT", "LAMP", "REVERENCE", "SUBMISSIVE", "SUPERFICIAL", 
						"EXEMPLARY", "DILIGENT", "DIGRESSION", "FRUGAL", "HEDONIST", 
						"HYPOTHESIS", "IMPETUOUS", "RECONCILIATION", "RENOVATION", 
						"SAGACITY", "WARY", "TACIT", "TACTFUL", "TWERK", "EMOJI", 
						"THROWBACK", "HASHTAG", "FETCH", "INSTAGRAM", "FACEBOOK", 
						"TWITTER", "SNAPCHAT", "POTATO", "PENDULUM", "DERIVATIVE", 
						"CIRCUIT", "SERVER", "PHYSICS", "CALCULUS", "ANSARI", "DRAKE"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		JButton holiday = new JButton("Holiday");
		holiday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 2;
				String words[] = new String[]{"JOLLY", "BELLS", "JOYFUL", "TREE", 
						"CHRISTMAS", "HALLOWEEN", "FIREWORKS", "WALNUT", "COAL", 
						"PRESENT", "STUFFING"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		JButton food = new JButton("Food");
		food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 3;
				String words[] = new String[]{"HAMBURGER", "CHEESEBURGER", "BURRITO", "PIZZA", 
						"BAGEL", "APPLE", "BREAD", "BUTTER", "CARROT", 	
						"COKE", "COOKIE", "COFFEE", "PRETZEL", "SPAGHETTI",
						"SHRIMP", "RAISIN", "TWINKIE", "FAJITA", "MEATLOAF", 
						"CHEESE", "POPCORN", "JAMBALAYA", "SANDWICH"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		JButton color = new JButton("Color");
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 4;
				String words[] = new String[]{"BLUE", "CYAN", "BLACK", "WHITE", 
						"ORANGE", "PURPLE", "INDIGO", "MAROON", 
						"AUBURN", "TURQUOISE", "MAGENTA"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		JButton location = new JButton("Location");
		location.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 5;
				String words[] = new String[]{"PARIS", "MADRID", "AFRICA", "ENGLAND",
						"EUROPE", "ANTARCTICA", "AUSTRALIA", "CANADA", 
						"MEXICO", "FLORIDA", "DENVER", "TEXAS", "CALIFORNIA", 
						"MAINE", "KENTUCKY", "CHINA", "PHILIPPINES", 
						"CARIBBEAN", "CHILE", "BAHAMAS", "LONDON"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);		
			}
		});
		
		add(randomWord);
		add(holiday);
		add(food);
		add(color);
		add(location);
		
		//start game
		
		
	}
	
	
	
	public static void main(String[] args){
		new MainWindow().setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
