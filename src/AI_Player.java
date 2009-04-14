
public class AI_Player extends Player {
	
	int askCount=0;
	int bet=0;

	public AI_Player(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
		
	}
	
	
	public int getBet(int highestCurrentBet){
		
		if(askCount<1){
		bet = 20;
		if (highestCurrentBet>bet){
			return highestCurrentBet;
		}
		currentMoney = currentMoney - bet;
		askCount++;
		}
		return bet;
		
	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision; // 0=fold,1=check/call, 2=raise
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
	}
