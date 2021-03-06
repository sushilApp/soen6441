package eclipse_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * LakeTiles are created according to number of players.
 *
 */
public class LakeTiles  {
	
	public String leftColor, rightColor, upColor, downColor;
	public int left, right, up, down;
	public int id;
	public boolean platform;
	public LakeTiles lakeTiles[];
	public Stack<LakeTiles> globalLakeTiles = new Stack<LakeTiles>();

	/**
	 * Default constructor
	 */
	public LakeTiles() {
		super();
	}

	/**
	 * Parameterized constructor for loading the existing lake tiles form the passed value of the game state from the file
	 * @param leftColor
	 * @param rightColor
	 * @param upColor
	 * @param downColor
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 * @param id
	 * @param platform
	 * @param lakeTiles 
	 * @param globalLakeTiles
	 */
	public LakeTiles(String leftColor, String rightColor, String upColor,
			String downColor, int left, int right, int up, int down, int id,
			boolean platform, LakeTiles[] lakeTiles,
			Stack<LakeTiles> globalLakeTiles) {
		
		this.leftColor = leftColor;
		this.rightColor = rightColor;
		this.upColor = upColor;
		this.downColor = downColor;
		
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		
		//
		this.id = id;
		this.platform = platform;
		
		this.lakeTiles = lakeTiles;
		
		this.globalLakeTiles = globalLakeTiles;
	}

	/**
	 * method to generate lake tiles with according to number of players where
	 * some of the tiles will have platform
	 * 
	 * @param numberOfPlayers
	 *            total number of players
	 */
	public void initializeLakeTiles(int numberOfPlayers) {
		switch (numberOfPlayers) {
		case 1:
			System.out
					.println("rightColor now you can't play with computer.Sorry for the inconveience");
			break;
		case 2:
			lakeTiles = new LakeTiles[23];
			// 16 tiles on stack + 6 tiles for players + start Tile
			numberOfLakeTiles(lakeTiles);
			
			/*for (int i = 0; i < lakeTiles.length; i++) {
				lakeTiles[i] = new LakeTiles();
				lakeTiles[i].leftColor = lakeTiles[i].randomValues();
				lakeTiles[i].rightColor = lakeTiles[i].randomValues();
				lakeTiles[i].upColor = lakeTiles[i].randomValues();
				lakeTiles[i].downColor = lakeTiles[i].randomValues();
				lakeTiles[i].id = i + 1;
				if (i % 2 != 0)
					lakeTiles[i].platform = true;
			}*/

			break;
		case 3:
			// 18 tiles on stack + 9 tiles for players + start Tile
			lakeTiles = new LakeTiles[28];
			numberOfLakeTiles(lakeTiles);
			
			/*for (int i = 0; i < lakeTiles.length; i++) {
				lakeTiles[i] = new LakeTiles();
				lakeTiles[i].leftColor = lakeTiles[i].randomValues();
				lakeTiles[i].rightColor = lakeTiles[i].randomValues();
				lakeTiles[i].upColor = lakeTiles[i].randomValues();
				lakeTiles[i].downColor = lakeTiles[i].randomValues();
				lakeTiles[i].id = i + 1;
				if (i % 2 != 0)
					lakeTiles[i].platform = true;
			}*/

			break;
		case 4:
			// 20 tiles on stack + 12 tiles for players + start Tile
			lakeTiles = new LakeTiles[33];
			numberOfLakeTiles(lakeTiles);
			
			/*for (int i = 0; i < lakeTiles.length; i++) {
				lakeTiles[i] = new LakeTiles();
				lakeTiles[i].leftColor = lakeTiles[i].randomValues();
				lakeTiles[i].rightColor = lakeTiles[i].randomValues();
				lakeTiles[i].upColor = lakeTiles[i].randomValues();
				lakeTiles[i].downColor = lakeTiles[i].randomValues();
				lakeTiles[i].id = i + 1;
				if (i % 2 != 0)
					lakeTiles[i].platform = true;
			}*/
			
			break;
		}
	}

	/**
	 * method to create lake tile object and set values to the attributes of a lake tile   
	 * 
	 * 
	 * @param lakeTiles
	 *            Array of Lake Tile object
	 */
	
	public void numberOfLakeTiles(LakeTiles lakeTiles[]){
		
		for (int i = 0; i < lakeTiles.length; i++) {
			lakeTiles[i] = new LakeTiles();
			lakeTiles[i].leftColor = lakeTiles[i].randomValues();
			lakeTiles[i].rightColor = lakeTiles[i].randomValues();
			lakeTiles[i].upColor = lakeTiles[i].randomValues();
			lakeTiles[i].downColor = lakeTiles[i].randomValues();
			lakeTiles[i].id = i + 1;
			lakeTiles[i].left=-1;
			lakeTiles[i].right=-1;
			lakeTiles[i].down=-1;
			lakeTiles[i].up=-1;
			if (i % 2 != 0)
				lakeTiles[i].platform = true;
		}
		
	}

	/**
	 * method to assign 3 LakeTiles to each Player
	 * 
	 * @param numberOfPlayers
	 *            total number of players
	 * @return list of lakeTiles
	 */
	public ArrayList<LakeTiles> assignLakeTiles(int numberOfPlayers) {
		ArrayList<LakeTiles> assignedLakeTiles = new ArrayList<LakeTiles>();
		
		if(numberOfPlayers==2 || numberOfPlayers==3 || numberOfPlayers==4){
			for (int i = 1; i < numberOfPlayers * 3 + 1; i++)
				assignedLakeTiles.add(lakeTiles[i]);
			for (int i = numberOfPlayers * 3 + 1; i < lakeTiles.length; i++) {
				globalLakeTiles.push(lakeTiles[i]);
			}
		}
		
		/*switch (numberOfPlayers) {
		case 2:
			for (int i = 1; i < numberOfPlayers * 3 + 1; i++)
				assignedLakeTiles.add(lakeTiles[i]);
			for (int i = numberOfPlayers * 3 + 1; i < lakeTiles.length; i++) {
				globalLakeTiles.push(lakeTiles[i]);
			}
			break;
		case 3:
			for (int i = 1; i < numberOfPlayers * 3 + 1; i++)
				assignedLakeTiles.add(lakeTiles[i]);
			for (int i = numberOfPlayers * 3 + 1; i < lakeTiles.length; i++) {
				globalLakeTiles.push(lakeTiles[i]);
			}
			break;
		case 4:
			for (int i = 1; i < numberOfPlayers * 3 + 1; i++)
				assignedLakeTiles.add(lakeTiles[i]);
			for (int i = numberOfPlayers * 3 + 1; i < lakeTiles.length; i++) {
				globalLakeTiles.push(lakeTiles[i]);
			}
			break;
		}*/
		
		return assignedLakeTiles;
	}

	/**
	 * method to get a single LakeTile from Stack of LakeTiles
	 * 
	 * @return LakeTile
	 */
	public LakeTiles getLakeTile() {
		LakeTiles top = globalLakeTiles.pop();
		return top;
	}

	/**
	 * return true of if not empty
	 * 
	 * @return boolean
	 */
	public boolean hasLakeTile() {
		return !globalLakeTiles.isEmpty();
	}
	
	/**
	 * method to place a new Tile on board
	 * 
	 * @param x
	 *            coordinate of position where user want to place a tile
	 * @param y
	 *            coordinate of position where user want to place a tile
	 * @param Board
	 * @param LakeTile
	 *            to be placed on board
	 * @return true if the tile is placed successfully
	 */
	public boolean placeTile(int col, int row, Board gameBoard,
			LakeTiles tileToPlace) {
		boolean flag = false;
		//System.out.println("col i m getting"+col);
		//System.out.println("y i m getting"+row);
		if (gameBoard.board[row][col] == -1) {
			gameBoard.board[row][col] = tileToPlace.id;
			gameBoard.tilesOnBoard.add(tileToPlace);
			if (gameBoard.board[row+ 1][col] != -1) {
				tileToPlace.down = gameBoard.board[row + 1][col];
				for (int i = 0; i < gameBoard.tilesOnBoard.size(); i++) {
					if (gameBoard.tilesOnBoard.get(i).id == gameBoard.board[row + 1][col])
						gameBoard.tilesOnBoard.get(i).up = tileToPlace.id;
				}
			}
			if (gameBoard.board[row][col + 1] != -1) {
				tileToPlace.right = gameBoard.board[row][col + 1];
				for (int i = 0; i < gameBoard.tilesOnBoard.size(); i++) {
					if (gameBoard.tilesOnBoard.get(i).id == gameBoard.board[row][col + 1])
						gameBoard.tilesOnBoard.get(i).left = tileToPlace.id;
				}
			}
			if (gameBoard.board[row - 1][col] != -1) {
				tileToPlace.up = gameBoard.board[row - 1][col];
				for (int i = 0; i < gameBoard.tilesOnBoard.size(); i++) {
					if (gameBoard.tilesOnBoard.get(i).id == gameBoard.board[row - 1][col])
						gameBoard.tilesOnBoard.get(i).down = tileToPlace.id;
				}
			}
			if (gameBoard.board[row][col - 1] != -1) {
				tileToPlace.left = gameBoard.board[row][col - 1];
				for (int i = 0; i < gameBoard.tilesOnBoard.size(); i++) {
					if (gameBoard.tilesOnBoard.get(i).id == gameBoard.board[row][col - 1])
						gameBoard.tilesOnBoard.get(i).right = tileToPlace.id;
				}
			}
			
			
			flag = true;
		} else
			System.out
					.println("LakeTile is already present on that location.Please choose another loacation");
		return flag;
	}

	/**
	 * method to generate random values within range 5 to 11 where each number
	 * represents particular color for example 5:red 6:green
	 * 
	 * @return String with one of the given colors
	 */
	public String randomValues() {
		String answer = null;
		// String leftColor,rightColor,upColor,downColorColor;
		int x;

		Random ran = new Random();
		x = ran.nextInt(7) + 5;
		// System.out.println(x);
		switch (x) {
		case 5:
			answer = "red";
			break;
		case 6:
			answer = "green";
			break;
		case 7:
			answer = "blue";
			break;
		case 8:
			answer = "white";
			break;
		case 9:
			answer = "black";
			break;
		case 10:
			answer = "orange";
			break;
		case 11:
			answer = "purple";
			break;
		}
		return answer;
	}
	
	public String toString()
	{
		return ("id: " + this.id + " " + "leftColor: " + this.leftColor + " "
				+ "rightColor: " + this.rightColor + " " + "upColor: " + this.upColor + " "+ 
				"downColor: " + this.downColor + " " + "platForm: "+ this.platform);
	}
	
	/**
	 * This method rotates the lakeTile
	 * @param lakeTile LakeTile to be rotated
	 * @param degreeOfRotation Degree of rotation
	 * @return lakeTile Rotated lakeTile
	 */
	public LakeTiles rotateLakeTile(LakeTiles lakeTile,int degreeOfRotation)
	{
		String left=lakeTile.leftColor;
		String right=lakeTile.rightColor;
		String up=lakeTile.upColor;
		String down=lakeTile.downColor;
		switch(degreeOfRotation)
		{
		case 90:
			lakeTile.leftColor=down;
			lakeTile.upColor=left;
			lakeTile.rightColor=up;
			lakeTile.downColor=right;
			break;
		case 180:
			lakeTile.leftColor=right;
			lakeTile.upColor=down;
			lakeTile.rightColor=left;
			lakeTile.downColor=up;
			break;
		case 270:
			lakeTile.leftColor=down;
			lakeTile.upColor=left;
			lakeTile.rightColor=up;
			lakeTile.downColor=right;
			break;
			default://System.out.println();
		}
		return lakeTile;
	}

	/**
	 * Method to get Column of the board where player wants to place a LakeTile
	 * @param board GameBoard
	 * @param id2 Id of the adjacent LakeTile
	 * @param adjacentPosition Position can be left,right,down,up
	 * @return Column 
	 */
	public int getColumn(Board board, int id2, String adjacentPosition) {
		// TODO Auto-generated method stub
		int Column=-2;
		/*for(int i=0;i<board.tilesOnBoard.size();i++)
		{
			if(board.tilesOnBoard.get(i).id==id2)
			{
				
			}
		}*/
		for(int i=0;i<board.board.length;i++)
		{
			for(int j=0;j<board.board.length;j++)
			{
				if(board.board[i][j]==id2)
				{
					Column=j;
					//System.out.println(board.board[i][j]==id2);
					//System.out.println("INLoop"+Column);
				}
					
			}
		}
		if(adjacentPosition.equalsIgnoreCase("left"))
			Column--;
		if(adjacentPosition.equalsIgnoreCase("right"))
			Column++;
		return Column;
	}
	
	/**
	 * Method to get Row of the board where player wants to place a LakeTile
	 * @param board GameBoard
	 * @param id2 Id of the adjacent LakeTile
	 * @param adjacentPosition Position can be left,right,down,up
	 * @return Row
	 */
	public int getRow(Board board, int id2, String adjacentPosition) {
		// TODO Auto-generated method stub
		int Row=-2;
		/*for(int i=0;i<board.tilesOnBoard.size();i++)
		{
			if(board.tilesOnBoard.get(i).id==id2)
			{
				
			}
		}*/
		for(int i=0;i<board.board.length;i++)
		{
			for(int j=0;j<board.board.length;j++)
			{
				if(board.board[i][j]==id2)
					Row=i;
			}
		}
		if(adjacentPosition.equalsIgnoreCase("down"))
			Row++;
		if(adjacentPosition.equalsIgnoreCase("up"))
			Row--;
		return Row;
	}
	
	/**
	 * What is the COLOR of the position rotated rotated by angle?
	 * @param position (left, right up, down) 
	 * @param angle (0, 90, 180, 270) 
	 */
	public String positionRotated(String position, int angle)
	{
		//
		String[] positions = new String[] {"left", "down", "right", "up"};
		
		//
		int current  = Arrays.asList(positions).indexOf(position);
		int rotated = current + angle/90;
		rotated = rotated % 4;
		
		//
		String newPosition = positions[rotated];
		
		//
		if(newPosition.equals("left"))
		{
			return this.leftColor;
		}else if(newPosition.equals("right"))
		{
			return this.rightColor;
		}else if(newPosition.equals("down"))
		{
			return this.downColor;
		}else
		{
			return this.upColor;
		}
		
	}
}