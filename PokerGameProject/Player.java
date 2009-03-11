
public class Player extends PokerHand {

	int decision;
	int bet=0;
	int currentMoney;
	Card[] thisHand;

	
	public player(String Name, int Chips, Card[] currentHand){
		
	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision // 0=fold,1=check/call, 2=raise
	}
	
	public int getBet(){
		if(decision!=0){
			return bet;
		}
		
	}
	
	private makeDecision(thisHand){ // where we decide what to do - keep private!
		
	}
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet;
	}
}
