package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HumanPlayer implements PlayerStrategy {

	String regex = "\\d+";
	
	public HumanPlayer(String name) {
		//super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * @see eclipse_project.PlayerStrategy#play(eclipse_project.GameEngine, eclipse_project.Player)
	 */
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		// Exchange lantern cards
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Type 1 if you want to exchange lantern cards, any other number to skip: ");
		
		int choice=0;
		boolean check = true;
		Scanner in;
		String input = "";
		
		try {

			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 0) {
						System.out.println("invalid input. Enter again!");
					} else {
						check = false;
					}
				}

			}//
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		if(choice == 1)
		{
			try {
				exchangeLanternCards(gameEngine, player, br);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Make dedication
		System.out.print("Type 1 if you want to make a dedication, any other number to skip: ");
		try {
			check = true;
			while (check) {
				in = new Scanner(System.in);
				input = in.nextLine();
				if (!input.matches(regex)) {
					System.out.println("invalid input. Enter again!");
				} else {
					choice = Integer.parseInt(input, 10);
					if (choice < 0) {
						System.out.println("invalid input. Enter again!");
					} else {
						check = false;
					}
				}

			}//
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(choice == 1)
		{
			try {
				makeDedication(gameEngine, player, br);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		//place lake tile 
		try {
			placeLakeTile(gameEngine, player, br);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(gameEngine.lakeTiles.hasLakeTile())
		{// Composury pick lake tile
			player.pickLakeTileFromStack(gameEngine.lakeTiles.getLakeTile());
			System.out.println();
			player.displayPlayersLakeTile(player);
		}else
		{
			System.out.println("No lake tiles left on board to pick");
		}
	}
	
	/**
	 * Method called when human player needs to exchange cards.
	 * @param gameEngine - all the info of the game. State of the game
	 * @param player - all the info of the player.
	 * @throws IOException
	 */
	protected void exchangeLanternCards(GameEngine gameEngine, Player player, BufferedReader br) throws IOException
	{
		int lanternCard = 0;
		String returnLCard = "";
		String pickLCard = "";
		String regex = "\\d+";
		Scanner in = new Scanner(System.in);
		//
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		
		// Print lanterns
		System.out.println("--Lantern cards you currently have:--");
		System.out.println(player.getLanternCards());
		System.out.println("----------------------------");
		
		//
		System.out.println("Select the lantern card you want to return.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		boolean check = true;
		while (check) {
			in = new Scanner(System.in);
			returnLCard = in.nextLine();
			returnLCard.trim();
			if (!returnLCard.matches(regex)) {
				System.out.println("Invalid lantern card option");
				check = true;
			} else {
				lanternCard = Integer.valueOf(returnLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid lantern card option");
				} else {
					check = false;
				}
			}

		}
		switch(lanternCard)
		{
			case 1: returnLCard = "redCard";break;
			case 2: returnLCard = "greenCard";break;
			case 3: returnLCard = "purpleCard";break;
			case 4: returnLCard = "orangeCard";break;
			case 5: returnLCard = "whiteCard";break;
			case 6: returnLCard = "blackCard";break;
			case 7: returnLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		lanternCard = 0;
		System.out.println("Select the lantern card you want to pick.\n"
				+ "1.Red Card\n"
				+ "2.Green Card\n"
				+ "3.Purple Card\n"
				+ "4.Orange Card\n"
				+ "5.White Card\n"
				+ "6.Black Card\n"
				+ "7.Blue Card\n");
		
		check = true;
		while (check) {
			in = new Scanner(System.in);
			pickLCard = in.nextLine();
			pickLCard.trim();
			if (!pickLCard.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				lanternCard = Integer.valueOf(pickLCard);
				if (lanternCard < 1 || lanternCard > 7) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		//
		switch(lanternCard)
		{
			case 1: pickLCard = "redCard";break;
			case 2: pickLCard = "greenCard";break;
			case 3: pickLCard = "purpleCard";break;
			case 4: pickLCard = "orangeCard";break;
			case 5: pickLCard = "whiteCard";break;
			case 6: pickLCard = "blackCard";break;
			case 7: pickLCard = "blueCard";break;
			default: System.out.println("Invalid option");break;
		}
		
		boolean moveState = player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards,
						returnLCard, pickLCard);
		if (moveState)
			System.out.print("Successful Exchange");
		else
			System.out.println("Unsuccessful Exchange: make sure you have \n the needed cards for the exchange are available or you have enough tokens");
	}
	/**
	 * method called when human player want to make a dedication.
	 * 
	 * @param gameEngine - all info of the game. (State of the game)
	 * @param player - the current player. (Current player state).
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	
	protected void makeDedication(GameEngine gameEngine, Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		int move = 0;
		String regex = "\\d+";
		String option = "";
		Scanner in = new Scanner(System.in);	
		//
		System.out.println("This is the amount of tokens you have: "+ player.favorTokenScore);
		System.out.println("----------------------------");
		System.out.println("--Lantern cards you currently have:--");

		// Prints the number of black Cards the player has.
		System.out.println(player.getLanternCards());

		System.out.println("----------------------------");
		// TODO needs validation to be done before submitting
		// the code
		System.out.println("What Move do you want to make? Enter its corresponding integer");

		System.out.println("1:Three Pair 2:Four of a kind 3: Seven Unique");
	
		boolean check = true;
	
		while (check) {
			in = new Scanner(System.in);
			option = in.nextLine();
			option.trim();
			if (!option.matches(regex)) {
				System.out.println("Invalid option. Enter again!");
				check = true;
			} else {
				move = Integer.valueOf(option);
				if (move < 1 || move > 3) {
					System.out.println("Invalid option. Enter again!");
				} else {
					check = false;
				}
			}

		}
		
		String moveString = null;
		LanternCards returnedLanternCards = null;
		boolean state = false;
		if (move == 1) {
			moveString = "threePair";
			System.out.println("What Lantern cards do you want to return? Enter "+ "the three cards.");
			System.out.println("1: redCard 2: blueCard 3: greenCard 4: "+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

			int card = Integer.valueOf(br.readLine());
			int card2 = Integer.valueOf(br.readLine());
			int card3 = Integer.valueOf(br.readLine());

			if (player.getLanternCards().CardCount(card) >= 2
						&& player.getLanternCards().CardCount(card2) >= 2
						&& player.getLanternCards().CardCount(card3) >= 2) {
					
				//System.out.println("This means that you succeded to "+ "get through the card check point");

					CardToReturn cardToReturn = new CardToReturn(
							card, card2, card3);

					returnedLanternCards = cardToReturn
							.returnStackThreeOfKind();
					System.out
							.println("these are the cards to be returned 3:"
									+ returnedLanternCards
											.CardCount(card3));
					System.out
							.println("these are the cards to be returned 2:"
									+ returnedLanternCards
											.CardCount(card2));

					System.out
							.println("these are the cards to be returned 1:"
									+ returnedLanternCards
											.CardCount(card));

					// take 2 cards with the first color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());

					// take 2 cards with the second color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor2());
					player.getLanternCards()
							.getCard(cardToReturn.getColor2());

					// take 2 cards with the third color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor3());
					player.getLanternCards()
							.getCard(cardToReturn.getColor3());
				} else {
					System.out
							.println("You do not have enough cards to make 'Three pair' move");
				}

			} else if (move == 2) {
				moveString = "FourOfKind";
				System.out
						.println("What Lantern cards do you want to return?"
								+ " Enter the card");
				System.out
						.println("1: redCard 2: blueCard 3: greenCard 4: "
								+ "whiteCard 5: purpleCard 6: blackCard 7: orangeCard ");

				int card = Integer.valueOf(br.readLine());
				if (player.getLanternCards()
						.CardCount(card) >= 4) {

					CardToReturn cardToReturn = new CardToReturn(
							card);
					returnedLanternCards = cardToReturn
							.returnStackFourOfKind();

					// take 4 cards with the third color inserted
					// from player
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
					player.getLanternCards()
							.getCard(cardToReturn.getColor());
				} else {
					System.out
							.println("You do not have enough cards to make 'Four of a kind' move");
				}

			} else if (move == 3) {
				moveString = "sevenUnique";

				CardToReturn cardToReturn = new CardToReturn();
				if (cardToReturn.SevenUniqueState(player)) {
					returnedLanternCards = cardToReturn
							.returnSeveUnique();
					//get cards from player
					player.getLanternCards()
					.getCard("blackCard");
					player.getLanternCards()
					.getCard("blueCard");
					player.getLanternCards()
					.getCard("greenCard");
					player.getLanternCards()
					.getCard("orangeCard");
					player.getLanternCards()
					.getCard("purpleCard");
					player.getLanternCards()
					.getCard("whiteCard");
					player.getLanternCards()
					.getCard("redCard");

				} else {
					System.out
							.println("You do not have enough cards to make 'Seven Unique' move");
				}
			}
			else if(move >= 4)
			{
				System.out.println("Invalid option");
			}
			if (returnedLanternCards != null) {
				state = player.pickDedicationToken(moveString,
								returnedLanternCards, gameEngine.lanternCards,
								gameEngine.dedicationTokens);
			}
			if (state) {
				System.out.println("Picked!");
				System.out.println("Score: four of a kind "
								+ player.playerScore_fourKind);
				System.out
						.println("Score: three of a kind "
								+ player.playerScore_threePair);
				System.out
						.println("Score: Seven unique "
								+ player.playerScore_sevenUnique);
			} else
				System.out
						.println(" Please revisit the game rules!");
	}
	/**
	 * Method called for the human player to place the laketile on board
	 * @param gameEngine
	 * @param player
	 * @param br
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("No lake tile available to play");
			return;
		}
		
		int degreeOfRotation = 0,indexOption =0;
		String option = "";
		String regex = "\\d+";
		boolean check = true;
		
		Scanner in = new Scanner(System.in);
		
		// board
		gameEngine.board.displayBoard();
		System.out.println();
		
		// player
		player.displayPlayersLakeTile(player);
		System.out.println();
		
		//
		System.out.println("Enter the index of laketiles you want to put on board:");
		
		check = true;
		while (check) {
			in = new Scanner(System.in);
			option = in.nextLine();
			option.trim();
			if (!option.matches(regex)) {
				System.out.println("Invalid index. Enter again!");
				check = true;
			} else {
				indexOption = Integer.valueOf(option);
				if (indexOption < 0 || indexOption > 2) {
					System.out.println("Invalid index. Enter again!");
				} else {
					check = false;
				}
			}

		}
		
		LakeTiles currentTileToPlace = player.placeLakeTile(indexOption);

		//
		boolean flag = true;
		boolean placeLakeTile = false;
	
		//
		while (flag) {
			System.out
			.println("Enter the id of the adjacent tile (on board) where you want to place your LakeTile");
			//validate
			int id = validateIndex(gameEngine);
			//end-validate
			System.out
			.println("Enter the adjacent position (right, left, up, down)");
			boolean loop = true;
			String AdjacentPosition = "";
			while(loop){
				
				AdjacentPosition = br.readLine();
				AdjacentPosition = AdjacentPosition.trim();
				if(AdjacentPosition.equalsIgnoreCase("right"))
					loop = false;
				else if(AdjacentPosition.equalsIgnoreCase("left"))
					loop = false;
				else if(AdjacentPosition.equalsIgnoreCase("up"))
					loop = false;
				else if(AdjacentPosition.equalsIgnoreCase("down"))
					loop = false;
				else System.out.println("invalid input. Enter the direction as right, left, up, down!");
					
			}

			
			int GetColumn=gameEngine.lakeTiles.getColumn(gameEngine.board,id,AdjacentPosition);
			int GetRow=gameEngine.lakeTiles.getRow(gameEngine.board,id,AdjacentPosition);
			//System.out.println("MyColumn "+GetColumn+" row "+GetRow);
	
			System.out
					.println("Enter the degree of roatation for the tile you want to place on board");
			System.out.println("Available options 0 90 180 270");
			
			check = true;
			while (check) {
				in = new Scanner(System.in);
				option = in.nextLine();
				option.trim();
				if (!option.matches(regex)) {
					System.out.println("Invalid degree. Enter again!");
					check = true;
				} else {
					degreeOfRotation = Integer.valueOf(option);
					if (degreeOfRotation == 0 || degreeOfRotation==90 || degreeOfRotation==180 || degreeOfRotation==270 ) {
						check = false;
					} else {
						System.out.println("Invalid degree. Enter again!");
					}
				}

			}
			
			currentTileToPlace = gameEngine.lakeTiles.rotateLakeTile(
					currentTileToPlace, degreeOfRotation);

			placeLakeTile = gameEngine.lakeTiles.placeTile(GetColumn,
					GetRow, gameEngine.board, currentTileToPlace);
			if (placeLakeTile) {
				gameEngine.lanternCards.assignLanternCardsToPlayers(
						gameEngine.numOfPlayer, gameEngine.board,
						GetColumn,GetRow, currentTileToPlace,
							gameEngine.PlayerList, gameEngine.lanternCards, gameEngine.favorTokens);
								gameEngine.board.displayBoard();
				player.displayPlayersLakeTile(player);
				System.out.println();
				System.out
						.println("Number of FavorTokens:"
								+ player.favorTokenScore);
				System.out.println();
				System.out
						.println("Details of the LanternCards Assigned to Each Player After Placing the LakeTile");
				for (int i = 0; i < gameEngine.PlayerList.size(); i++) {
					System.out
							.println("Player" + (i + 1) + ":");
					System.out
							.println(gameEngine.PlayerList.get(i).playerLCStack);
					System.out.println();
				}
				
				flag = false;
			} else
				player.pickLakeTileFromStack(currentTileToPlace);
			
			
			System.out.println();
			System.out.println("Player Pick up the new LakeTile from the Stack after placing one");
		}
	}
	/**
	 * Validate the index for the placement of the laketile.
	 * @param gameEngine - state of the game.
	 * @return - valid id of laketile
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	
	public int validateIndex(GameEngine gameEngine) throws NumberFormatException, IOException{

		String regex = "\\d+";
		Scanner in;
		int answer=0;
		boolean check = true;
		String input = "";
		int id = 0;
		while(check){		
			in = new Scanner(System.in);
			input = in.nextLine();
			if (!input.matches(regex)) {
				System.out.println("invalid input. Enter again!");
			} else {
				id = Integer.parseInt(input, 10);
				boolean innerCheck = true;
				if (id < 0) {
					System.out.println("invalid input. Enter again!");
				} else {
					for(int i =0;i<gameEngine.board.tilesOnBoard.size();i++)
					{
						if(gameEngine.board.tilesOnBoard.get(i).id==id)
							innerCheck = false;
					}
				}
								
				if (innerCheck) {
					System.out.println("invalid input. Enter again!");
				} else {
					check = false;
				}
			}

		}
		return id;
	}



}
