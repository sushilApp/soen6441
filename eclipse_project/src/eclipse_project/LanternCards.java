package eclipse_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LanternCards {
	// stacks  index by color
	private HashMap<String, Integer> stacks = new HashMap<String, Integer>();

	private int numOfPlayers;

	/**
	 * Create an empty stack of seven colors.
	 */
	public LanternCards() {
		// push the specified number of cards in each stack
		this.stacks.put("redCard", 0);
		this.stacks.put("blueCard", 0);
		this.stacks.put("greenCard", 0);
		this.stacks.put("whiteCard", 0);
		this.stacks.put("purpleCard", 0);
		this.stacks.put("blackCard", 0);
		this.stacks.put("orangeCard", 0);
	}

	/**
	 * Constructor to initialize the number of lanterns cards that depends on
	 * the number of players.
	 * 
	 * @param numOfPlayers
	 *            - Number of players playing game.
	 */
	public LanternCards(int numOfPlayers) {
		this();

		this.numOfPlayers = numOfPlayers;

		int cards = 0;

		// Determine how many cards to be push based on the number of players
		if (this.numOfPlayers == 2)
			cards = 5;
		else if (this.numOfPlayers == 3)
			cards = 7;
		else if (this.numOfPlayers == 4)
			cards = 8;
		else
			System.out.println("The number of players is invalid");
		
		//
		for(Map.Entry<String, Integer> entry : this.stacks.entrySet())
		{
			entry.setValue(cards);
		}
	}

	/**
	 * Constructor to initialize the number of lantern cards with some existing
	 * values - Existing Game Resume
	 * 
	 * @param numOfPlayers
	 *            - Number of Players playing the game.
	 * @param redCount
	 *            - Number of Red Cards.
	 * @param blueCount
	 *            - Number of Blue Cards.
	 * @param greenCount
	 *            - Number of Green Cards.
	 * @param whiteCount
	 *            - Number of White Cards.
	 * @param purpleCount
	 *            - Number of Purple Cards.
	 * @param blackCount
	 *            - Number of Black Cards.
	 * @param orangeCount
	 *            - Number of Orange Cards.
	 */
	public LanternCards(int numOfPlayers, int redCount, int blueCount,
			int greenCount, int whiteCount, int purpleCount, int blackCount,
			int orangeCount) {

		this.numOfPlayers = numOfPlayers;

		//
		this.stacks.put("redCard", redCount);
		//
		this.stacks.put("blueCard", blueCount);
		//
		this.stacks.put("greenCard", greenCount);
		//
		this.stacks.put("whiteCard", whiteCount);
		//
		this.stacks.put("purpleCard", purpleCount);
		//
		this.stacks.put("blackCard", blackCount);
		//
		this.stacks.put("orangeCard", orangeCount);
	}

	/**
	 * This method gives the red card to the player
	 * 
	 * @return true or false
	 */
	public boolean getRedCard() {
		return this.getCard("redCard");
	}

	/**
	 * This method add the red card to the stack
	 */
	public void addRedcard() {
		this.addCard("redCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int redCardCount() {
		return this.stacks.get("redCard");
	}

	/**
	 * This method gives the blue card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlueCard() {
		return this.getCard("blueCard");
	}

	/**
	 * This method add the blue card to the stack
	 */
	public void addBluecard() {
		this.addCard("blueCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int blueCardCount() {
		return this.stacks.get("blueCard");
	}

	/**
	 * This method gives the green card to the player
	 * 
	 * @return true or false
	 */
	public boolean getGreenCard() {
		return this.getCard("greenCard");
	}

	/**
	 * This method add the green card to the stack
	 */
	public void addGreencard() {
		this.addCard("greenCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int greenCardCount() {
		return this.stacks.get("greenCard");
	}

	/**
	 * This method gives the white card to the player
	 * 
	 * @return true or false
	 */
	public boolean getWhiteCard() {
		return this.getCard("whiteCard");
	}

	/**
	 * This method add the white card to the stack
	 */
	public void addWhitecard() {
		this.addCard("whiteCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int whiteCardCount() {
		return this.stacks.get("whiteCard");
	}

	/**
	 * This method gives the purple card to the player
	 * 
	 * @return true or false
	 */
	public boolean getPurpleCard() {
		return this.getCard("purpleCard");
	}

	/**
	 * This method add the purple card to the stack
	 */
	public void addPurplecard() {
		this.addCard("purpleCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int purpleCardCount() {
		return this.stacks.get("purpleCard");
	}

	/**
	 * This method gives the black card to the player
	 * 
	 * @return true or false
	 */
	public boolean getBlackCard() {
		return this.getCard("blackCard");
	}

	/**
	 * This method add the black card to the stack
	 */
	public void addBlackcard() {
		this.addCard("blackCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int blackCardCount() {
		return this.stacks.get("blackCard");
	}

	/**
	 * This method gives the orange card to the player
	 * 
	 * @return true or false
	 */
	public boolean getOrangeCard() {
		return this.getCard("orangeCard");
	}

	/**
	 * This method add the orange card to the stack
	 */
	public void addOrangecard() {
		this.addCard("orangeCard");
	}

	/**
	 * 
	 * @return count of orange cards
	 */
	public int orangeCardCount() {
		return this.stacks.get("orangeCard");
	}
	
	/**
	 * @param card the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public boolean hasCard(String card) {
		return this.stacks.containsKey(card) && this.stacks.get(card) >= 1;
	}
	
	/**
	 * @param card the color of the card to check
	 * @return true if this object contains the cards, else false.
	 */
	public void addCard(String card) {
		this.stacks.put(card, this.stacks.get(card)+1);
	}
	
	/**
	 * @param card the color of the card to with draw
	 * @return true if the card was Succesfully withdrawn
	 */
	public boolean getCard(String card) {
		if(!this.stacks.containsKey(card) || this.stacks.get(card) == 0)
		{
			
			return false;
		}
		
		//
		this.stacks.put(card, this.stacks.get(card)-1);
		//
		return true;
	}
	
	/**
	 * this lantern cards has either 0 or quantity of each kind.
	 */
	public int numberOfColorsOfWithQuantity(int quantity) {
		int result = 0;
	
		//
		for(Map.Entry<String, Integer> entry : this.stacks.entrySet())
		{
			if(entry.getValue() == quantity)
			{
				result += 1;
			}
		}
		
		return result;
	}
	
	/**
	 * This method assigns lanternCards to each Player according to LakeTile and players position
	 * @param numOfPlayers - Number of Players
	 * @param playerList - Arraylist of Players
	 * @param startTile - LakeTile
	 * @param lanternCards - Object of lanternCard class
	 */
	public void assignLanternCards(int numOfPlayers,ArrayList<Player> playerList,LakeTiles startTile,LanternCards lanternCards)
	{
		String lanternCardtoAdd;
		switch(numOfPlayers)
		{		
		case 2:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;
		case 3:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.upColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;
		case 4:
			lanternCardtoAdd =startTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.upColor+"Card";
			playerList.get(1).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.rightColor+"Card";
			playerList.get(2).pickLanternCard(lanternCardtoAdd,lanternCards);
			lanternCardtoAdd =startTile.downColor+"Card";
			playerList.get(3).pickLanternCard(lanternCardtoAdd,lanternCards);
			break;

		}
	}
	
	/**
	 * This method assigns lanternCards to each Player according to LakeTile and players position
	 * @param numOfPlayers - Number of Players
	 * @param board - GameBoard
	 * @param x - X coordinate of lakeTile
	 * @param y - Y coordinate of lakeTile
	 * @param lakeTile - LakeTile
	 * @param playerList - Arraylist of Players
	 * @param lanternCard - Available Lantern Cards
	 */
	public void assignLanternCardsToPlayers(int numOfPlayers ,Board board,int x,int y,LakeTiles lakeTile,ArrayList<Player> playerList,LanternCards lanternCard)
	{
		String card;
		switch(numOfPlayers)
		{
		case 2:
			card=lakeTile.leftColor+"Card";
			System.out.println("InitialCard"+card);
			playerList.get(0).pickLanternCard(card,lanternCard);
			card=lakeTile.rightColor+"Card";
			System.out.println("InitialCard"+card);
			playerList.get(1).pickLanternCard(card,lanternCard);
			break;
		case 3:
			card=lakeTile.leftColor+"Card";
			System.out.println("InitialCard"+card);
			playerList.get(0).pickLanternCard(card,lanternCard);
			card=lakeTile.upColor+"Card";
			System.out.println("InitialCard"+card);
			playerList.get(1).pickLanternCard(card,lanternCard);
			card=lakeTile.rightColor+"Card";
			System.out.println("InitialCard"+card);
			playerList.get(2).pickLanternCard(card,lanternCard);
			break;
		case 4:
			card=lakeTile.leftColor+"Card";
			playerList.get(0).pickLanternCard(card,lanternCard);
			card=lakeTile.upColor+"Card";
			playerList.get(1).pickLanternCard(card,lanternCard);
			card=lakeTile.rightColor+"Card";
			playerList.get(2).pickLanternCard(card,lanternCard);
			card=lakeTile.downColor+"Card";
			playerList.get(3).pickLanternCard(card,lanternCard);
			break;
		}
		for(int currentPlayer=0;currentPlayer<playerList.size();currentPlayer++)
		{
			String cardColor="";
			if(playerList.get(currentPlayer).current)
			{
				if(board.board[x-1][y]!=-1)
				{
					for(int i=0;i<board.tilesOnBoard.size();i++)
					{
						if(board.tilesOnBoard.get(i).id==board.board[x-1][y] && board.tilesOnBoard.get(i).rightColor.equalsIgnoreCase(lakeTile.leftColor))
						{
							card=board.tilesOnBoard.get(i).rightColor+"Card";
							System.out.println("Card"+card);
							playerList.get(currentPlayer).pickLanternCard(cardColor, lanternCard);
						}
					}
				}
				
				if(board.board[x+1][y]!=-1)
				{
					for(int i=0;i<board.tilesOnBoard.size();i++)
					{
						if(board.tilesOnBoard.get(i).id==board.board[x+1][y] && board.tilesOnBoard.get(i).leftColor.equalsIgnoreCase(lakeTile.rightColor))
						{
							card=board.tilesOnBoard.get(i).leftColor+"Card";
							System.out.println("Card"+card);
							playerList.get(currentPlayer).pickLanternCard(cardColor, lanternCard);
						}
					}
				}
				
				if(board.board[x][y+1]!=-1)
				{
					for(int i=0;i<board.tilesOnBoard.size();i++)
					{
						if(board.tilesOnBoard.get(i).id==board.board[x][y+1] && board.tilesOnBoard.get(i).upColor.equalsIgnoreCase(lakeTile.downColor))
						{
							card=board.tilesOnBoard.get(i).upColor+"Card";
							System.out.println("Card"+card);
							playerList.get(currentPlayer).pickLanternCard(cardColor, lanternCard);
						}
					}
				}
				
				if(board.board[x][y-1]!=-1)
				{
					for(int i=0;i<board.tilesOnBoard.size();i++)
					{
						
						if(board.tilesOnBoard.get(i).id==board.board[x][y-1] && board.tilesOnBoard.get(i).downColor.equalsIgnoreCase(lakeTile.upColor))
						{
							card=board.tilesOnBoard.get(i).downColor+"Card";
							System.out.println("Card"+card);
							playerList.get(currentPlayer).pickLanternCard(cardColor, lanternCard);
						}
					}
				}
				
			}
		}
	}
}
