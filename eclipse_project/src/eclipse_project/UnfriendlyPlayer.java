package eclipse_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class UnfriendlyPlayer extends Player implements PlayerStrategy {

	public UnfriendlyPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(GameEngine gameEngine, Player player) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * The goal is if possible is to get dedication tokens so someone else can't get it
	 * @param gameEngine
	 * @param player
	 */
	protected void makeDedication(GameEngine gameEngine, Player player)
	{
		boolean played = false;
		//
		for(int i=0; i<gameEngine.PlayerList.size(); i++)
		{
			 if(player.playerLCStack.hasSevenUniques() && gameEngine.PlayerList.get(i).playerLCStack.hasSevenUniques() && gameEngine.dedicationTokens.sevenUniqueCount() == 1)
			 {
				 System.out.println("unfriendly: doing seven unique to prevent user: "+gameEngine.PlayerList.get(i).name+" from doing seven unique");
				 //player.pickDedicationToken("threePair", returnedLanternCards, globalLanternCards, dedicationToken)
				 played=true;
				 break;
			 }else if(player.playerLCStack.hasFourOfKinds() && gameEngine.PlayerList.get(i).playerLCStack.hasFourOfKinds() && gameEngine.dedicationTokens.fourOfKindCount() == 1)
			 {
				 System.out.println("unfriendly: doing four of kind to prevent user: "+gameEngine.PlayerList.get(i).name+" from four of kind");
				 
				 played=true;
				 break;
			 }else if(player.playerLCStack.numThreePairs()>0 && gameEngine.PlayerList.get(i).playerLCStack.numThreePairs()>0 && gameEngine.dedicationTokens.threePairCount() == 1)
			 {
				 System.out.println("unfriendly: doing three pair to prevent user: "+gameEngine.PlayerList.get(i).name+" from four of kind");
				 
				 played = true;
				 break;
			 }
		}
		
		if(!played)
		{
			System.out.println("unfriendly: no dedication possible that will prevent someone from making dedication");
		}
	}

	/**
	 * The goal is if possible  is to get a lantern card such that one of the players can't get a better score for the next round
	 * @param gameEngine contains the entire state of the game
	 * @param player current player
	 */
	protected void exchangeLanternCards(GameEngine gameEngine, Player player)
	{
		if(player.favorTokenScore<2)
		{
			//
			System.out.println("unfriendly: can't do exchange lantern cards, not enough tokens: "+player.favorTokenScore);
		}
		
		// Available colors
		LinkedList<String> playerAvailableColors = player.playerLCStack.colorsWithAtLeastQuantity(1);
		if(playerAvailableColors.size() >= 1)
		{
			//
			System.out.println("unfriendly: can't do exchange lantern cards, player has no lantern cards ");
		}
		
		// Some cards are unique on the board, maybe someone needs them to make a dedication
		LinkedList<String> boardUniqueColors = gameEngine.lanternCards.colorsWithQuantity(1);
		//
		if(boardUniqueColors.size() == 0)
		{
			//
			System.out.println("unfriendly: not lantern cards on the board is unique, attack any one");
			return;
		}
		
		
		//
		Player minPlayer = null;
		int minScoreGain = Integer.MAX_VALUE;
		String minCardReturn = null;
		String minCardGet = null;
		
		//
		for(int i=0; i<playerAvailableColors.size(); i++)
		{
			//
			for(int j=0; j<boardUniqueColors.size(); j++)
			{
				//
				for(int k=0; k<gameEngine.PlayerList.size(); k++)
				{
					//
					if(gameEngine.PlayerList.get(k) == player)
						continue;

					//
					int scoreGain = scoreGain(gameEngine, gameEngine.PlayerList.get(k), playerAvailableColors.get(i), boardUniqueColors.get(j));
					
					if(scoreGain < 0)
					{
						if(scoreGain<minScoreGain)
						{
							//
							minPlayer = gameEngine.PlayerList.get(k);
							minScoreGain = scoreGain;
							//
							minCardReturn = playerAvailableColors.get(i);
							minCardGet = boardUniqueColors.get(j);
						}
					}
				}
			}
		}
		
		if(minPlayer == null)
		{
			System.out.println("unfriendly: found no one who needed a single color to make dedication");
			
		}else
		{
			System.out.println("unfriendly: being unfriendly to "+minPlayer.name+" returning lantern card "+minCardReturn+" getting lantern card"+minCardGet);
			player.spendFavorTokens(gameEngine.favorTokens, gameEngine.lanternCards, minCardReturn, minCardGet);
		}
		
	}

	/**
	 * What possible score does the player gain if he gets lantern color2 instead of lantern color2
	 * @param player
	 * @param color1
	 * @param color2
	 * @return
	 */
	public int scoreGain(GameEngine gameEngine, Player player, String color1, String color2)
	{
		// current players lantern cards
		LanternCards lanternCardWithColor1 = player.getLanternCards().duplicate();
		lanternCardWithColor1.addCard(color1);
		
		// current players lantern cards
		LanternCards lanternCardWithColor2 = player.getLanternCards().duplicate();
		lanternCardWithColor2.addCard(color2);
		
		// 
		return lanternCardWithColor2.bestPossibleScore(gameEngine.dedicationTokens) - lanternCardWithColor2.bestPossibleScore(gameEngine.dedicationTokens);
	}


	/**
	 * The goal is to place a lake tiles such that it minimizes the possible score gain on any given player
	 * @param gameEngine
	 * @param player
	 * @param br
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void placeLakeTile(GameEngine gameEngine, Player player, BufferedReader br) throws NumberFormatException, IOException
	{
		//
		System.out.println("unfriendly: placing laketile for player "+player.name);
		
		//
		if(player.playerLTStack.size() == 0)
		{
			System.out.println("No lake tile available to play");
			return;
		}
		
		// The  lake tile position with the min case of a given player
		int[] minPosition = null; // the poosition leading to a min point for a given player
		int minRotation = 0;   // the rotation of the min point localtion
		int minScoreGain = Integer.MAX_VALUE; 	 	// The min points I can make any given player have
		int minId = 0; 			// An id of a lake tile adjacent to the min position
		Player minPlayer = null;
		
		// TODO replace this with board empty location
		ArrayList<int[]> emptyLocations = new ArrayList<int[]>();
		
		
		//foreach of the character laketiles
		ArrayList<LakeTiles> lakeTiles = player.getLakeTiles(); 
		for(int i=0; i<lakeTiles.size(); i++)
		{
			//
			LakeTiles lakeTile = lakeTiles.get(i);
		
			// for each of the possible locations
			for(int j=0; i<emptyLocations.size(); j++)
			{
				//
				int[] position = emptyLocations.get(j);
				
				// Go through the possible rotations
				int[] possibleRotations = new int[]{0, 90, 180, 270};
				for(int k=0; i<possibleRotations.length; k++)
				{
					//
					int rotation = possibleRotations[k];
					
					// for each of the other players
					for(int z=0; z<gameEngine.PlayerList.size(); z++)
					{
						//
						Player currentPlayer = gameEngine.PlayerList.get(z);
						
						//TODO test
						if(currentPlayer != player)
						{
							//
							int scoreGain_ = scoreGain(gameEngine, lakeTile, currentPlayer, position, rotation);
							
							// place lake tile such that minimizes the score gained
							if(minPosition == null || scoreGain_ < minScoreGain)
							{
								//
								minPosition = position;
								minRotation = rotation;
								minScoreGain = scoreGain_;
								minId = lakeTile.id;
								minPlayer = currentPlayer;
							}
						}
					}
				}
			}
		}
		
		//
		System.out.println("Unfriendly: placing laketile: "+minId+", min_player(victim): "+minPlayer.name+", min position: "+minPosition);

		
		// Place on the min
		// withdrawing the lake tile
		LakeTiles currentTileToPlace = player.placeLakeTile(minId);
		//
		gameEngine.lakeTiles.rotateLakeTile(currentTileToPlace, minRotation);
		//
		boolean placedLaketile = gameEngine.lakeTiles.placeTile(minPosition[1], minPosition[0], gameEngine.board, currentTileToPlace);
		
		//
		if(!placedLaketile)
		{
			System.out.println("unfriendly: Unexpected error placing lake tile");
		}

	}
	
	/**
	 * What possible score does the player gain if the tile is place at the position?
	 * @param gameEngine
	 * @param lakeTile
	 * @param player
	 * @param position
	 * @param rotation
	 * @return
	 */
	public int scoreGain(GameEngine gameEngine, LakeTiles lakeTile, Player player, int[] position, int rotation)
	{
		// current players lantern cards
		LanternCards lanternCards = player.getLanternCards();
		int currentScore = lanternCards.bestPossibleScore(gameEngine.dedicationTokens);
		
		// lantern cards of player after tile is placed
		LanternCards lanternAfterLakeTile = lanternCards.duplicate();
		//TODO if lake tile does not exist, real minmal
		
		//
		int scoreAfterLakeTile = lanternAfterLakeTile.bestPossibleScore(gameEngine.dedicationTokens);
		
		// 
		return scoreAfterLakeTile - currentScore;
	}

}