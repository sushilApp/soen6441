package eclipse_project;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

/**
 * Main application container - loads the new game or existing game based on the
 * user input
 */
public class LanternsApplication {

	private static GameEngine game;
	private static int numOfPlayers;
	private static int playerTurn;
	private static int tokensCount;
	private static int redCount, blueCount, greenCount, whiteCount,
			purpleCount, blackCount, orangeCount;

	private static boolean isLoad = false;

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerException, Exception {

		int resp = 10;// sentinel value
		String regex = "\\d+";
		String validFileRegEx = "^[^*&%\\s]+$";
		String fileName = "";
		int M = 0, endOption = 0, userInput_N = 0;

		System.out.println("Choose an option:");
		System.out.println("Press 1 for New Game");
		System.out.println("Press 2 for Existing Game");
		System.out.println("Press 0 to Exit Game");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		if (!input.matches(regex))
			System.out.println("Input is invalid");
		else
			resp = Integer.parseInt(input, 10);

		do {

			if (resp == 1) {

				boolean check = true;
				while (check) {
					System.out.println("Enter number of players");
					in = new Scanner(System.in);
					input = in.nextLine();
					if (!input.matches(regex)) {
						System.out.println("Number of players invalid input");
						check = true;
					} else {
						numOfPlayers = Integer.parseInt(input, 10);
						if (numOfPlayers < 2 || numOfPlayers > 4) {
							System.out
									.println("Number of players invalid input");
						} else {
							check = false;
							isLoad = true;
							//loadNewGame();
						}
					}

				}//while: Number of Players

				if (numOfPlayers == 2)
					M = 23 / numOfPlayers;
				else if (numOfPlayers == 3)
					M = 28 / numOfPlayers;
				else if (numOfPlayers == 4)
					M = 33 / numOfPlayers;

				check = true;
				input = "";

				while (check) {
					System.out.println("Select the ending of the game.\n");
					System.out.println("1. Proper End.\n");
					System.out.println("2. N lake tiles ending.\n");
					System.out.println("3. One player enough dedication.\n");
					in = new Scanner(System.in);
					input = in.nextLine();
					if (!input.matches(regex)) {
						System.out.println("invalid option. Enter again!");
						check = true;
					} else {
						endOption = Integer.parseInt(input, 10);
						if (endOption == 1 || endOption == 2 || endOption == 3) {
							
							boolean innerCheck = true;
							if (endOption == 1) {
								innerCheck = false;
								check  = false;
							} else if (endOption == 2) {
								System.out
										.println("Enter the number of lake tiles between 2 & "+M+".");
							} else if (endOption == 3) {
								M = 155/numOfPlayers;
								System.out
										.println("Enter the number of dedication tokens between 4 & "+M+".");
							}
							input = "";
							while (innerCheck) {

								in = new Scanner(System.in);
								input = in.nextLine();
								if (!input.matches(regex)) {
									System.out.println("invalid option. Enter again!");
									
								} else {
									userInput_N = Integer.parseInt(input, 10);
									if (endOption == 2) {
										if (userInput_N < 2 || userInput_N > M) {
											System.out
													.println("N lake tile is invalid input. Enter agqin!");
										} else {
											innerCheck = false;
											check  = false;
										}
									}
									else if (endOption == 3) {
										M = 155/numOfPlayers;
										if (userInput_N < 4 || userInput_N > M) {
											System.out
													.println("N dedication tokens is invalid input. Enter agqin!");
										} else {
											innerCheck = false;
											check  = false;
										}
									}
									else System.out.println("invalid option. Enter again!");
								}

							}//END - innerWhile
						} else {
							System.out.println("invalid option. Enter again!");
						}
					}

				}
				
				loadNewGame(numOfPlayers, userInput_N, endOption);
			} else if (resp == 2) {

				while (true) {
					System.out.println("Enter name of file with .xml extension.");
					in = new Scanner(System.in);
					fileName = in.nextLine();
					fileName = fileName.trim();
					if (!fileName.matches(validFileRegEx)) {
						System.out.println("invalid file name");
						isLoad = false;
					} else {
						isLoad = true;
						break;
					}
				}
				if (isLoad)
					loadExistingGame(fileName);

			} else if (resp == 0) {
				if (isLoad) {
					while (true) {
						System.out
								.println("Enter the name of file with .xml extension to save the game & quit.");
						in = new Scanner(System.in);
						fileName = in.nextLine();
						fileName = fileName.trim();
						if (!fileName.matches(validFileRegEx)) {
							System.out.println("invalid file name");
						} else {
							saveTheGame(fileName, game);
							System.out.println("Game saved successfully!");
							break;
						}
					}
				}
				break;
			}

			//
			System.out.println('\n');
			System.out.println("Choose an option");
			System.out.println("Press 1 for New Game");
			System.out.println("Press 2 for Existing Game");
			System.out.println("Press 0 to Exit Game");
			in = new Scanner(System.in);
			input = in.nextLine();
			if (!input.matches(regex)) {
				System.out.println("Input is invalid");
				resp = 10;
			} else
				resp = Integer.parseInt(input, 10);

		} while (true);

		System.out.println("Game is ended!");
	}

	/**
	 * load the new based on the user input
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	public static void loadNewGame(int numOfPlayers, int N, int endingOption) throws IOException, Exception {

		game = new GameEngine(numOfPlayers, N, endingOption);
		game.startNewGame();
	}

	/**
	 * load the existing game from file name provided by the user
	 * 
	 * @param  fileName
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void loadExistingGame(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		game = new GameEngine();

		File file = new File(fileName);
		if (!file.exists()) {
			isLoad = false;
			System.out.println("file: " + fileName + " doesn't exist.");
		} else
			game.loadExistingGame(fileName);
	}

	/**
	 * save the state of the game to the file
	 * 
	 * @param fileName
	 * @param game
	 *            - Current Game Instance
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void saveTheGame(String fileName, GameEngine game)
			throws ParserConfigurationException, TransformerException {
		game.saveGame(fileName, game);
	}

}
