
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String wordToGuess = "";
	
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
		//ONE OR TWO PLAYER - Change to JRadioButton
		final JRadioButton onePlayerButton = new JRadioButton("One Player");
		final JRadioButton twoPlayerButton = new JRadioButton("twoPlayer");
		
		//DIFFICULTY - Change to JRadioButton
		final JRadioButton easy = new JRadioButton("Easy");
		final JRadioButton medium = new JRadioButton("Medium");
		final JRadioButton hard = new JRadioButton("Hard");
		final JRadioButton insane = new JRadioButton("Insane");
		
		//GAMEMODE - change to JRadioButton
		final JRadioButton randomWord = new JRadioButton("Random Word");
		final JRadioButton holiday = new JRadioButton("Holiday");
		final JRadioButton food = new JRadioButton("Food");
		final JRadioButton color = new JRadioButton("Color");
		final JRadioButton location = new JRadioButton("Location");
		
		final JButton startGame = new JButton("Start");//closes all options except new game and reveal
		final JButton newGame = new JButton("New Game?");//opens all options up again
		final JButton showWord = new JButton("Reveal Word");//shows word; only option is to quit or new game
		
		JTextField inputWord = new JTextField();
		JTextField guessBox = new JTextField(1);
		JTextField wrongLetters = new JTextField();
		JTextField wordToGuessReveal = new JTextField();
		
		final JButton guessLetter = new JButton("Guess Letter");
		final JButton submitWord = new JButton("Submit Word");
		
		/*
		 * DEFAULT OPTIONS
		 */
		
		onePlayerButton.setSelected(true);
		easy.setSelected(true);
		randomWord.setSelected(true);
		
		newGame.setEnabled(false);
		showWord.setEnabled(false);
		
		
		
		showConditions();
		
		/*
		 * NUMBER OF PLAYERS
		 */
				
		onePlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				twoPlayer = false;
	
				onePlayerButton.setSelected(true);
				twoPlayerButton.setSelected(false);
					
				randomWord.setEnabled(true);
				holiday.setEnabled(true);
				food.setEnabled(true);
				color.setEnabled(true);
				location.setEnabled(true);
				
				randomWord.setSelected(true);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(false);
				
				easy.setSelected(true);
				medium.setSelected(false);
				hard.setSelected(false);
				insane.setSelected(false);
				
				difficulty = 1;
				gameMode = 1;
				
				showConditions();
			}
		}); 
		
		twoPlayerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				twoPlayer = true;
				onePlayerButton.setSelected(false);
				twoPlayerButton.setSelected(true);
					
				randomWord.setEnabled(false);
				holiday.setEnabled(false);
				food.setEnabled(false);
				color.setEnabled(false);
				location.setEnabled(false);
					
				randomWord.setSelected(false);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(false);
					
				easy.setSelected(true);
				medium.setSelected(false);
				hard.setSelected(false);
				insane.setSelected(false);
					
				difficulty = 1;
					
				showConditions();
				//enable box to type word
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
				easy.setSelected(true);
				medium.setSelected(false);
				hard.setSelected(false);
				insane.setSelected(false);
				
				showConditions();
			}
		});
		
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 2;	
				guessIterator = 10;
				easy.setSelected(false);
				medium.setSelected(true);
				hard.setSelected(false);
				insane.setSelected(false);
				
				showConditions();
			}
		});
		
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 3;
				guessIterator = 15;
				easy.setSelected(false);
				medium.setSelected(false);
				hard.setSelected(true);
				insane.setSelected(false);
				
				showConditions();
			}
		});
		
		insane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				difficulty = 4;
				guessIterator = 60;
				easy.setSelected(false);
				medium.setSelected(false);
				hard.setSelected(false);
				insane.setSelected(true);
				
				showConditions();
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
				randomWord.setSelected(true);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(false);
				
				showConditions();
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
				randomWord.setSelected(false);
				holiday.setSelected(true);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(false);
				
				showConditions();
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
				randomWord.setSelected(false);
				holiday.setSelected(false);
				food.setSelected(true);
				color.setSelected(false);
				location.setSelected(false);
				
				showConditions();
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
				randomWord.setSelected(false);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(true);
				location.setSelected(false);
				
				showConditions();
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
				randomWord.setSelected(false);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(true);	
				
				showConditions();
			}
		});
		
		add(randomWord);
		add(holiday);
		add(food);
		add(color);
		add(location);
		
		//start game
		startGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//disable everything except new game and show word
				//enable box to guess letters
				//enable drawing canvas
				
				onePlayerButton.setEnabled(false);
				twoPlayerButton.setEnabled(false);
				
				randomWord.setEnabled(false);
				holiday.setEnabled(false);
				food.setEnabled(false);
				color.setEnabled(false);
				location.setEnabled(false);
				
				easy.setEnabled(false);
				medium.setEnabled(false);
				hard.setEnabled(false);
				insane.setEnabled(false);
				
				startGame.setEnabled(false);
				newGame.setEnabled(true);
				showWord.setEnabled(true);
				
				showConditions();
			}
		});
		
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//reset to default settings
				twoPlayer = false;
				
				onePlayerButton.setSelected(true);
				twoPlayerButton.setSelected(false);
				
				onePlayerButton.setEnabled(true);
				twoPlayerButton.setEnabled(true);
				
				randomWord.setEnabled(true);
				holiday.setEnabled(true);
				food.setEnabled(true);
				color.setEnabled(true);
				location.setEnabled(true);
				
				randomWord.setSelected(true);
				holiday.setSelected(false);
				food.setSelected(false);
				color.setSelected(false);
				location.setSelected(false);
				
				easy.setEnabled(true);
				medium.setEnabled(true);
				hard.setEnabled(true);
				insane.setEnabled(true);
				
				easy.setSelected(true);
				medium.setSelected(false);
				hard.setSelected(false);
				insane.setSelected(false);
				
				startGame.setEnabled(true);
				newGame.setEnabled(false);
				showWord.setEnabled(false);
				
				difficulty = 1;
				gameMode = 1;
				
				showConditions();
			}
		});
		
		showWord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//disable all except new game
				onePlayerButton.setEnabled(false);
				twoPlayerButton.setEnabled(false);
				
				randomWord.setEnabled(false);
				holiday.setEnabled(false);
				food.setEnabled(false);
				color.setEnabled(false);
				location.setEnabled(false);
				
				easy.setEnabled(false);
				medium.setEnabled(false);
				hard.setEnabled(false);
				insane.setEnabled(false);
				
				startGame.setEnabled(false);
				showWord.setEnabled(false);
				
				showConditions();
			}
		});
		
		add(startGame);
		add(newGame);
		add(showWord);
	}
	
	public void updateConditions(){
		
	}
	
	public void showConditions(){
		System.out.println("\nWord: " + wordToGuess);
		System.out.println("Two player? " + twoPlayer);
		System.out.println("Difficulty: " + difficulty);
		System.out.println("Game Mode: " + gameMode);
		System.out.println("Guess iterator:" + guessIterator);
		System.out.println("Visible word: " + visibleWord + "\n");
		
	}
	
	
	public static void main(String[] args){
		new MainWindow().setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
