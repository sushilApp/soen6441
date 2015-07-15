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
			SAXException, IOException {

		int resp = '\0';

		do {

			System.out.println("Choose an option:");
			System.out.println("Press 1 for New Game");
			System.out.println("Press 2 for Existing Game");
			System.out.println("Press ZERO to Exit Game");
			Scanner in = new Scanner(System.in);
			resp = in.nextInt();

			if (resp == 1) {
				System.out.println("Enter number of players");
				in = new Scanner(System.in);
				numOfPlayers = in.nextInt();
				isLoad = true;
				loadNewGame();

			} else if (resp == 2) {

				System.out.println("Enter name of file");
				in = new Scanner(System.in);
				String fileName = in.nextLine();
				isLoad = true;
				loadExistingGame(fileName);

			}

			if (resp == 0) {
				if (isLoad) {
					System.out
							.println("Enter the name of file to save the game & quit.");
					in = new Scanner(System.in);
					String fileName = in.nextLine();
					fileName = fileName.trim();
					saveTheGame(fileName,game);
					System.out.println("Game saved successfully!");
				}
				break;
			}
			
			System.out.println("press 9 to continue and ZERO to quit!");
			in = new Scanner(System.in);
			resp = in.nextInt();


		} while (resp != 0);

		System.out.println("Game is ended!");
	}

	public static void loadNewGame() {

		game = new GameEngine(numOfPlayers);
		game.startNewGame();
	}

	public static void loadExistingGame(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		game = new GameEngine();
		
		File file = new File(fileName);
		if(!file.exists())
			System.out.println("file: "+fileName+" doesn't exist.");
		else
			game.loadExistingGame(fileName);
	}

	public static void saveTheGame(String fileName,GameEngine game) throws ParserConfigurationException {
		game.saveGame(fileName, game);
	}

}
