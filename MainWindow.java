
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
	
	public int difficulty = 1;
		/*
		 * 1) 10 guesses - iterates by 6 - easy
		 * 2) 6 guesses - iterates by 10 - medium
		 * 3) 4 guesses - iterates by 15 - hard
		 * 4) 1 guess - iterates by 60 - insane
		 */
	public int gameMode = 1;
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
	public String visibleWord = "";
	
	private String longWord = "PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIOSIS";
	
	
	public MainWindow(){
		super("Hangman");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		//BUTTONS
		//ONE OR TWO PLAYER
		final JButton onePlayerButton = new JButton("One Player");
		final JButton twoPlayerButton = new JButton("twoPlayer");
		
		//DIFFICULTY
		final JButton easy = new JButton("Easy");
		final JButton medium = new JButton("Medium");
		final JButton hard = new JButton("Hard");
		final JButton insane = new JButton("Insane");
		
		//GAMEMODE
		final JButton randomWord = new JButton("Random Word");
		final JButton holiday = new JButton("Holiday");
		final JButton food = new JButton("Food");
		final JButton color = new JButton("Color");
		final JButton location = new JButton("Location");
		
		/*
		 * DEFAULT OPTIONS
		 */
		
		onePlayerButton.setEnabled(false);
		easy.setEnabled(false);
		randomWord.setEnabled(false);
		
		/*
		 * NUMBER OF PLAYERS
		 */
				
		onePlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
					twoPlayer = false;
					onePlayerButton.setEnabled(false);
					twoPlayerButton.setEnabled(true);
					
					randomWord.setEnabled(true);
					holiday.setEnabled(true);
					food.setEnabled(true);
					color.setEnabled(true);
					location.setEnabled(true);
					
					System.out.println("twoPlayer? " + twoPlayer);
					
			}
		}); 
		
		twoPlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
					twoPlayer = true;
					onePlayerButton.setEnabled(true);
					twoPlayerButton.setEnabled(false);
					
					randomWord.setEnabled(false);
					holiday.setEnabled(false);
					food.setEnabled(false);
					color.setEnabled(false);
					location.setEnabled(false);
					
					System.out.println("twoPlayer? " + twoPlayer);
			}
		});
		
		add(onePlayerButton);
		add(twoPlayerButton);
		
		//IF TWO PLAYER, DISABLE OTHER OPTIONS
		
		/*
		 * DIFFICULTY
		 */
		
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 1;	
				guessIterator = 6;
				easy.setEnabled(false);
				medium.setEnabled(true);
				hard.setEnabled(true);
				insane.setEnabled(true);
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 2;	
				guessIterator = 10;
				easy.setEnabled(true);
				medium.setEnabled(false);
				hard.setEnabled(true);
				insane.setEnabled(true);
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 3;
				guessIterator = 15;
				easy.setEnabled(true);
				medium.setEnabled(true);
				hard.setEnabled(false);
				insane.setEnabled(true);
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		insane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 4;
				guessIterator = 60;
				easy.setEnabled(true);
				medium.setEnabled(true);
				hard.setEnabled(true);
				insane.setEnabled(false);
				System.out.println("Difficulty: " + difficulty);
				System.out.println("Guesses: " + (remainingGuesses / guessIterator));
			}
		});
		
		add(easy);
		add(medium);
		add(hard);
		add(insane);
		
		/*
		 * GAME MODE
		 */
		
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
				randomWord.setEnabled(false);
				holiday.setEnabled(true);
				food.setEnabled(true);
				color.setEnabled(true);
				location.setEnabled(true);
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		holiday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 2;
				String words[] = new String[]{"JOLLY", "BELLS", "JOYFUL", "TREE", 
						"CHRISTMAS", "HALLOWEEN", "FIREWORKS", "WALNUT", "COAL", 
						"PRESENT", "STUFFING"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				randomWord.setEnabled(true);
				holiday.setEnabled(false);
				food.setEnabled(true);
				color.setEnabled(true);
				location.setEnabled(true);
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
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
				randomWord.setEnabled(true);
				holiday.setEnabled(true);
				food.setEnabled(false);
				color.setEnabled(true);
				location.setEnabled(true);
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameMode = 4;
				String words[] = new String[]{"BLUE", "CYAN", "BLACK", "WHITE", 
						"ORANGE", "PURPLE", "INDIGO", "MAROON", 
						"AUBURN", "TURQUOISE", "MAGENTA"};
				Random r = new Random();
				wordToGuess = (difficulty!=4)? words[r.nextInt(words.length) - 1]:longWord;
				randomWord.setEnabled(true);
				holiday.setEnabled(true);
				food.setEnabled(true);
				color.setEnabled(false);
				location.setEnabled(true);
				System.out.println("GameMode: " + gameMode);
				System.out.println("Word: " + wordToGuess);	
			}
		});
		
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
				randomWord.setEnabled(true);
				holiday.setEnabled(true);
				food.setEnabled(true);
				color.setEnabled(true);
				location.setEnabled(false);
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
		/*
		for(int i = 0; i < wordToGuess.length(); i++){
			visibleWord += "_ ";
		}
		*/
	}
	
	
	
	public static void main(String[] args){
		new MainWindow().setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
