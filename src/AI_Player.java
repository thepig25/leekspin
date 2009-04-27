import java.util.Random;

public class AI_Player extends Player {
	
	int askCount=0;
	int bet=0;
	private int decision;

	public AI_Player(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
		
	}
	
	
	public int getBet(int highestCurrentBet, int tempbigBlind){
		bigBlind = tempbigBlind;
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
		
		return getPocketCardDecision(); // raise = 0,call/check = 2 ,fold = 1
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
	
	/*
	 *  
	 * Fold Call Raise decison
	 * takes in three int which is the percentage of chance of making a FCR decision
	 * then coose a random decision between these and decides to fold call or raise
	 * should add up to 10.0 or 100%
	 * 
	 * @param	Call	the percentage related to how likely the computer will call
	 * @param	Raise	the percentage related to how likely the computer will raise
	 * @param	Fold	the percentage related to how likely the computer will fold
	 * @return			the decision made by the computer call = 2, raise = 0,fold = 1, default is to call
	*/
	
	public int getFCR(int call, int raise, int fold){
		
		int total = call +raise+fold;
		
		//int FCR = total %10; 
		Random randomGenerator = new Random();
		int FCR = randomGenerator.nextInt(10);
		
		if(FCR < call){
			//call = 2
			//check = 3
			System.out.println("Computers decison is call");
			return 2;
		}
		
		if(FCR > call&& FCR <(call+raise)){
			//raise = 0
			System.out.println("Computers decison is raise");
			return 0;
		}
		
		if(FCR >(call+raise) && FCR<(call+raise+ fold)){
			//fold = 1
			System.out.println("Computers decison is fold");
			return 1;
		}
		
		System.out.println("Computers decison is deafault call");
		return 2;
	}
	
	/*
	 * this method will just check the players pocket cards.
	 * check if they are a pair.
	 * check if the distance between the cards is low or high that could be
	 * used in a straight.
	 * check to see if the cards are the same suit
	 * 
	 * Adds the values of cards and if it passes a threshold then a decison can be made.
	 */
	public int getPocketCardDecision(){
		
		int highHoleCard = getPlayerHighHoleCard();
		int lowHoleCard = getPlayerLowHoleCard();
		int pocketPair = 0;
		boolean havePocketPair = false;
		boolean flush = false;				// check to see if a flush would be possible
		int distance = 3;					// check to see if you can get a straight	
		int distanceMultiplier = 0;
		int flushMultiplier =0;
		int pocketPairMultiplier = 0;
		int threshold = 13;					//computer will call if over this point
		int thresholdLowerLimit = 5;		// computer will call or raise
		int thresholdHigherLimit = 10;		//computer will raise most likely
		
		Card firstPocketCard = playerHand[0];
		Card secondPocketCard = playerHand[1];
		
		int firstCardValue = firstPocketCard.getValue();
		int secondCardValue = secondPocketCard.getValue();
		
		//check for pocket pairs
		if(firstCardValue == secondCardValue){
			pocketPair = firstPocketCard.getValue();
			havePocketPair = true;
			pocketPairMultiplier = 7;
			
		}
		
		//check for cards that are close
		if(((highHoleCard - lowHoleCard) < distance) && havePocketPair != true){
			distanceMultiplier = 3;
		}
		
		//checks for cards are same suit
		if(firstPocketCard.getSuit() == secondPocketCard.getSuit()){
			flush = true;
			flushMultiplier = 3;
		}
		
		// adds the multipliers together
		int Multipliers = distanceMultiplier + flushMultiplier + pocketPairMultiplier;
		
		/*
		 * This adds the card values together and multipliers and if the total passes
		 * the threshold then the computer knows it has good pocket cards
		 */
		// it should just call
		if((firstCardValue + secondCardValue + Multipliers)>= threshold){
			
			// the total surpasses the theshold by a bit so it should call or maybe raise
			if((firstCardValue + secondCardValue + Multipliers)>= (threshold+ thresholdLowerLimit) ){
				
				// the total far surpasses the theshold so it should raise
				if((firstCardValue + secondCardValue + Multipliers)>= (threshold+ thresholdHigherLimit) ){
					getFCR(4,6,0);
					
				}

				getFCR(6,4,0);
			}
			
			getFCR(7,2,1);
		}
		
		
		else{
			getFCR(1,0,9);
		}
		return getFCR(7,2,1);
		
	}
	
	
	public void lowMoney(){
		
		if (bigBlind == (currentMoney *4)){
			
		}
	}
	
	
	/*
	 * Make decisions about the hand that you currently have.
	 * 
	 * if it is a pair get the value of the pair and see what the pot it and what the current highest bet.
	 */
	
	public void makeHandDecision(){
		
		if( bestBoolean == 9){
			
		}
		
		if( bestBoolean == 8){
			
		}
		if( bestBoolean == 7){
			
		}
		if( bestBoolean == 6){
			
		}
		if( bestBoolean == 5){
			
		}
		if( bestBoolean == 4){
			
		}
		if( bestBoolean == 3){
			
		}
		if( bestBoolean == 2){
			getFCR(20,80,0);	
		}
		if( bestBoolean == 1){
			getFCR(20,80,0);
		}
		if( bestBoolean == 0){
			getFCR(10,90,0);
		}
	
		
	}
	
	
}
