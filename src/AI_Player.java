
public class AI_Player extends Player {

	public AI_Player(String Name, int Chips, Card[] currentHand) {
		super(Name, Chips, currentHand);
		// TODO Auto-generated constructor stub
		
	}
	
	
	public int getBet(){
		
		bet = 20;
		currentMoney = currentMoney - bet;
		return bet;
		
	}
	
	
	}
