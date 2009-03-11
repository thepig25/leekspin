
public class AI_Player extends PokerHand {
	
String decision;
int bet=0;
int currentMoney;
Card[] thisHand;

	public AI_plyr(String Name, int Chips, Card[] currentHand){
		
	}
	
	public String getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision
	}
	public int getBet(){
		return bet;
	}
	
	
	private makeDecision(thisHand){ // where we decide what to do - keep private!
		
	}
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet=0;
	}
}
