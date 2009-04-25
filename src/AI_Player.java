
public class AI_Player extends Player {
	
	int askCount=0;
	int bet=0;
	private int decision;

	public AI_Player(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
		
	}
	
	
	public int getBet(int highestCurrentBet){
		
		if(askCount<1){
		bet = 20;
		if (highestCurrentBet>bet){ // <<<-------- this is where the AI coding needs to go
			bet = highestCurrentBet;
		}
		currentMoney = currentMoney - bet;
		askCount++;
		}
		return bet;
		
	}
	
	public void makeDecision(){
		decision=0;
	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  0; // 0=fold,1=check/call, 2=raise
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
	
	/*
	 * Fold Call Raise decison
	 * takes in three int which is the percentage of chance of making a FCR decision
	 * then coose a random decision between these and decides to fold call or raise
	 * should add up to 10.0 or 100%
	 */
	
	public int getFCR(int call, int raise, int fold){
		
		int total = call +raise+fold;
		
		int FCR = total %10; 
		
		//uncomplete
		
		return FCR;
	}
	
	/*
	 * this method will just check the players pocket cards
	 */
	public void getPocketCardDecision(){
		
		int highHoleCard = getPlayerHighHoleCard();
		int lowHoleCard = getPlayerHighHoleCard();
		
		int pocketPair = 0;
		boolean havePocketPair = false;
		boolean flush = false;		// check to see if a flush would be possible
		int distance = 3;	// check to see if you can get a straight
		int limit = 13;
		int distanceMultiplier = 0;

		
		Card firstPocketCard = playerHand[0];
		Card secondPocketCard = playerHand[1];
		
		int firstCardValue = firstPocketCard.getValue();
		int secondCardValue = secondPocketCard.getValue();
		
		if((highHoleCard - lowHoleCard) < distance){
			
		}
		
		if(firstCardValue == secondCardValue){
			pocketPair = firstPocketCard.getValue();
			havePocketPair = true;
			
		}
		
		if(firstPocketCard.getSuit() == secondPocketCard.getSuit()){
			flush = true;
		}
		
		
		if((firstCardValue + secondCardValue)> limit){
			
			
		}
		
		
		
		
	}
	
	
	
	}
