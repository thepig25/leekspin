
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
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision; // 0=fold,1=check/call, 2=raise
	}
	//jhvhgd
	
	
	}
