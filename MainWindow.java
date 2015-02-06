import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MainWindow extends JFrame {
	private String wordToGuess;
	private int difficulty;
	/*	1)10 guesses-iterate by 6
	 *	2)6 guesses-iterate by 10 
	 *	3)4 guesses-iterate by 15
	 *	4)1 guess-iterate by 60
	 */
	private String guesses;
	private int remainingGuess;
	private int wrongIterator;
	private String wordProg;
	
	private int gameMode;
	/*	default)Enter own word/random
	 * 	1)Holidays
	 * 	2)Food
	 * 	3)Colors
	 *	4)Locations
	 */
	
	private boolean twoPlayer;
	
	
	
	public MainWindow(String word, int difficulty, boolean twoPlayer, int gameMode){
		
		this.twoPlayer = twoPlayer;
		this.gameMode = gameMode;
		
		Random r = new Random();
		
		if(!twoPlayer){//1 player mode-no option to enter own word
			if(this.gameMode == 1){//holidays
				String[] words = new String[]{"JOLLY", "BELLS", "JOYFUL", "TREE", 
												"CHRISTMAS", "HALLOWEEN", "FIREWORKS", "WALNUT", "COAL", 
												"PRESENT", "STUFFING", "TURKEY"};
				word = words[r.nextInt(words.length) - 1];
			
				
				
			}else if(this.gameMode == 2){//food
				String[] words = new String[]{"HAMBURGER", "CHEESEBURGER", "BURRITO", "PIZZA", 
												"BAGEL", "APPLE", "BREAD", "BUTTER", "CARROT", 	
												"COKE", "COOKIE", "COFFEE", "PRETZEL", "SPAGHETTI",
												"SHRIMP", "RAISIN", "TWINKIE", "FAJITA", "MEATLOAF", 
												"CHEESE", "POPCORN", "JAMBALAYA", "SANDWICH"};
				word = words[r.nextInt(words.length) - 1];
			}else if(this.gameMode == 3){//color
				String[] words = new String[]{"BLUE", "CYAN", "BLACK", "WHITE", 
												"ORANGE", "PURPLE", "INDIGO", "MAROON", 
												"AUBURN", "TURQUOISE", "MAGENTA"};
				word = words[r.nextInt(words.length) - 1];
			}else if(this.gameMode == 4){//location
				String[] words = new String[]{"PARIS", "MADRID", "AFRICA", "ENGLAND",
												"EUROPE", "ANTARCTICA", "AUSTRALIA", "CANADA", 
												"MEXICO", "FLORIDA", "DENVER", "TEXAS", "CALIFORNIA", 
												"MAINE", "KENTUCKY", "CHINA", "PHILIPPINES", 
												"CARIBBEAN", "CHILE", "BAHAMAS", "LONDON"};
				word = words[r.nextInt(words.length) - 1];
			}else{//random word
				String[] words = new String[]{"TOASTER", "SPATULA", "GLASSES", "CURTAINS", "COUCH",
												"COMPUTER", "TOILET", "PENCIL", "SPONGE", "LIGHT", 
												"QUILT", "LAMP", "REVERENCE", "SUBMISSIVE", "SUPERFICIAL", 
												"EXEMPLARY", "DILIGENT", "DIGRESSION", "FRUGAL", "HEDONIST", 
												"HYPOTHESIS", "IMPETUOUS", "RECONCILIATION", "RENOVATION", 
												"SAGACITY", "WARY", "TACIT", "TACTFUL", "TWERK", "EMOJI", 
												"THROWBACK", "HASHTAG", "FETCH", "INSTAGRAM", "FACEBOOK", 
												"TWITTER", "SNAPCHAT", "POTATO", "PENDULUM", "DERIVATIVE", 
												"CIRCUIT", "SERVER", "PHYSICS", "CALCULUS", "ANSARI", "DRAKE"};
				word = words[r.nextInt((words.length) - 1)];
				

			}
		}else{//2 player-> only enter word
			//enter word or random
				if(word == null){//no word->pick random
					String[] words = new String[]{"TOASTER", "SPATULA", "GLASSES", "CURTAINS", "COUCH",
							"COMPUTER", "TOILET", "PENCIL", "SPONGE", "LIGHT", 
							"QUILT", "LAMP", "REVERENCE", "SUBMISSIVE", "SUPERFICIAL", 
							"EXEMPLARY", "DILIGENT", "DIGRESSION", "FRUGAL", "HEDONIST", 
							"HYPOTHESIS", "IMPETUOUS", "RECONCILIATION", "RENOVATION", 
							"SAGACITY", "WARY", "TACIT", "TACTFUL", "TWERK", "EMOJI", 
							"THROWBACK", "HASHTAG", "FETCH", "INSTAGRAM", "FACEBOOK", 
							"TWITTER", "SNAPCHAT", "POTATO", "PENDULUM", "DERIVATIVE", 
							"CIRCUIT", "SERVER", "PHYSICS", "CALCULUS", "ANSARI", "DRAKE"};
					word = words[r.nextInt((words.length) - 1)];
				}else{
					wordToGuess = word;
				}
			}
		
		
		
		
		
		
		this.difficulty = difficulty;
		remainingGuess = 60;
		
		if(difficulty == 2){//medium
			wrongIterator = 10;
		}else if(difficulty == 3){//hard
			wrongIterator = 15;
		}else if(difficulty == 4){//insane
			wrongIterator = 60;
			word = "PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIOSIS";
		}else{//easy or invalid input
			wrongIterator = 6;
		}
		
		guesses = "";
		wordProg = "";
		for(int i = 0; i < wordToGuess.length(); i++){
			wordProg += "_ ";
		}
		
		JPanel corePanel = new JPanel();
		corePanel.setLayout(new BorderLayout());
		
		final JLabel status = new JLabel("You have "+remainingGuesses+" remaining", SwingConstants.CENTER);
		final JLabel wrong = new JLabel("Wrong guesses so far: "+wrongGuesses);
		final JLabel visibleLabel = new JLabel(wordProg, SwingConstants.CENTER);
		final JTextField input = new JTextField(); 
		
		JPanel southPanel = new JPanel(new GridLayout(4, 1));
		southPanel.add(status);
		southPanel.add(visibleLabel);
		southPanel.add(input);
		southPanel.add(wrong);
		
		corePanel.add(southPanel, BorderLayout.SOUTH);
		
		final HangmanFigure hf = new HangmanFigure();
		corePanel.add(hf, BorderLayout.CENTER);

		this.add(corePanel, BorderLayout.CENTER);
		
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String text = input.getText();
				
				if(text.length()  == 1 && text.matches("[a-z]")) {
					
					boolean guessFound = false;
					
					for(int i = 0; i < word.length(); ++i) {
						if(text.charAt(0) == word.charAt(i)) {
							guessFound = true;
							
							String newVisible = "";
							for(int j = 0; j < wordProg.length(); ++j) {
								if(j == (i*2)) {
									newVisible += word.charAt(i);
								}
								else {
									newVisible += wordProg.charAt(j);
								}
							}
							visible = newVisible;
							visibleLabel.setText(visible);
						}
					}
					
					if(!guessFound) {
						if(--remainingGuesses >= 0) {
							status.setText("You have "+remainingGuesses+" guesses remaining");
							wrongGuesses += text+" ";
							wrong.setText("Wrong guesses so far: "+wrongGuesses);
							hf.set();
						}
						else {
							status.setText("You lost: the word was "+word);
							input.setEnabled(false);
						}
					}
					else {
						String actualVisible = "";
						for(int i = 0; i < wordProg.length(); i+=2) {
							actualVisible += wordProg.charAt(i);
						}
						
						if(actualVisible.equals(word)) {
							status.setText("Congratulations, you have won!");
							input.setEnabled(false);
						}
					}
					
				}
				else {
					System.out.println("Invalid input!");
				}
				
				input.setText("");
			}
			
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


		
		
	}
	
	public String getWord(){
		return wordToGuess;
	}
	
	public int getDiff(){
		return difficulty;
	}
	
	public boolean getPlayer(){
		return twoPlayer;
	}
	
	public int getGameMode(){
		return gameMode;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Two player?");
		boolean twoPlayer = s.nextBoolean();
		MainWindow m;
		if(twoPlayer){
			System.out.print("Difficulty: ");
			System.out.print("\n");
			int diff = s.nextInt();
			System.out.println("Enter a word: ");
			String word = s.next();
			m = new MainWindow(word, diff, twoPlayer, 0);
		}else{
			System.out.println("Difficulty: ");
			int diff = s.nextInt();
			System.out.println("Enter game mode: ");
			int gm = s.nextInt();
			m = new MainWindow(null, diff, false, gm);
		}
		
		//new MainWindow(word, 1, false, 0);
		//ask 2 player, difficulty, word
		
		System.out.println("Word:" + m.getWord());
		System.out.println("Difficulty: " + m.getDiff());
		System.out.println("Two Player? " + m.getPlayer());
		System.out.println("Game Mode: " + m.getGameMode());
		
	}

}
