
public class Player {

	private Ship [] all_ship;
	Ship firstShip;
	Ship secondShip;
	Ship thirdShip;
	Ship fourthShip;
	private Board Ship_Board;
	private Board Guess_Board;
	private int score;
	
	public Player(String whois)
	{
		Ship_Board = new Board("SHIP BOARD",7,3);		
		Guess_Board = new Board("GUESS BOARD",30,3);
		all_ship = new Ship[6];
		setScore(0);
		
		if(whois.equals("User"))
		{			
			Ship_Board.draw();
			Guess_Board.draw();
			firstShip = new Ship(Ship_Board);
			Ship_Board.draw();
			secondShip = new Ship(Ship_Board);
			Ship_Board.draw();
			thirdShip = new Ship(Ship_Board);
			Ship_Board.draw();
			fourthShip = new Ship(Ship_Board);
			Ship_Board.draw();
		}
		else if(whois.equals("Computer"))
		{
			firstShip = new Ship(Ship_Board,2);
			secondShip = new Ship(Ship_Board,3);
			thirdShip = new Ship(Ship_Board,4);
			fourthShip = new Ship(Ship_Board,5);
		}
	
		
		
		for(int i=2;i<6;i++)
		{
			if(firstShip.size == i)
			{
				all_ship[i] = firstShip;
			}
			if(secondShip.size == i)
			{
				all_ship[i] = secondShip;
			}
			if(thirdShip.size == i)
			{
				all_ship[i] = thirdShip;
			}
			if(fourthShip.size == i)
			{
				all_ship[i] = fourthShip;
			}		
		}
		
		
	}

	// Getters and Setters
	
	public Board getShip_Board() {
		return Ship_Board;
	}

	public void setShip_Board(Board ship_Board) {
		Ship_Board = ship_Board;
	}

	public Board getGuess_Board() {
		return Guess_Board;
	}

	public void setGuess_Board(Board guess_Board) {
		Guess_Board = guess_Board;
	}

	public Ship [] getAll_ship() {
		return all_ship;
	}

	public void setAll_ship(Ship [] all_ship) {
		this.all_ship = all_ship;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
