package com.poker;

import android.os.Message;

public class Player extends Pack {

	private int decision;
	private boolean first = true;
	int bet=0;
	int currentMoney;
	String name;
	Card[] thisHand = new Card[7];
	public Card[] playerHand = new Card[2];
	Card[] communityCards = new Card[3];
	int cardCount=0;
	int cardPocketCount=0;
	Card [] bestPokerHand;
	CombinationGenerator x;
	int[] elements;
	Card [] poolCards;
	int allCombosLength;
	int highestFirstPair=-1;
	int highestSecondPair=-1;
	int bestBoolean=20;
	int highCard=2;
	int highestLowest=0;
	int highestGeneralPair=0;
	int highStraightCard=0;
	int playerID;
	int nonPair;
	int nonPairForEight;
	int threeCard=0;
	int firstNonThreeCard=0;
	int secondNonThreeCard=0;
	int bigBlind;
	
	
	public boolean isHuman=false;
	
	

	
	public Player(String Name, int Chips, Card[] currentHand,int tempPlayerID){ // constructor for player
		currentMoney=Chips; // initialise currentMoney to the starting amount
		name = Name;
		playerID = tempPlayerID;
	}
	
	public void rewardPlayer(int amount){ // methods for adding to player's total if they win
		System.out.println("amount is "+amount);
		currentMoney=currentMoney+amount;
	}
	public void removeBlinds(int amount){ // removes from the player's total
		currentMoney = currentMoney-amount;
	}
	public void incrementDealer(){
		
	}
	public void resetCounters(){
		
	}
	
public void resetAllCounters(){
	
	first = true;
	bet=0;
	thisHand = new Card[7];
	playerHand = new Card[2];
	communityCards = new Card[3];
	cardCount=0;
	cardPocketCount=0;


highestFirstPair=-1;
highestSecondPair=-1;
bestBoolean=20;
highCard=2;
highestLowest=0;
highestGeneralPair=0;
highStraightCard=0;
threeCard=0;
firstNonThreeCard=0;
secondNonThreeCard=0;
bigBlind=0;
	} 
	
	public String getName(){
		return name;
	}
	
	// this takes an array of dealt cards then adds them to the
	// players hand. using cardCount to make sure its in the right
	// position
	public void receiveCommunityCards(Card[] received){ // method for receiving community cards. 
		//initially set array to size 3 and then copy to make it the full size of 5
		
		if( cardCount >= 2){
			Card[] tempCopy = new Card[(communityCards.length)];
			System.arraycopy(communityCards, 0, tempCopy, 0, tempCopy.length);
			communityCards = new Card[(communityCards.length+1)];
			System.arraycopy(tempCopy, 0,communityCards , 0, tempCopy.length);
			
		}
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			communityCards[cardCount] = temp[i];
			cardCount++;
		}
		
		for(int i=0; i<communityCards.length; i++){
			Message m = new Message();
	        m.what = GameWindow.DRAWCOMMUNITYCARDS;
	        m.obj = (Card) (communityCards[i]);
	        m.arg1 = i+1;
	        GameWindow.myViewUpdateHandler.sendMessage(m);
	        
	        // First time we draw comm. cards, pause between each one.
	        if(first){
	        	long startTime = System.currentTimeMillis();
		        while(System.currentTimeMillis()<startTime+600){
		        	// waits for 600 milliseconds.
		        }
	        }
		}
		first = false;
	}
	
	
	
	// this takes an array of dealt cards then adds them to the
	// the private variable which holds the player's hand.
	// This is just for the first two pocket cards a player gets
	public void receivePocketCards(Card[] received){
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			playerHand[cardPocketCount] = temp[i];
			cardPocketCount++;
		}
	}
	
	public int getPlayerHighCard(){
		Card [] temp = playerHand;
		
		if(temp [0].getValue()>temp [1].getValue()){
			return temp [0].getValue();
		}
		else{
			return temp [1].getValue();
		}
	}
	
	public void printPocketHand(){ // prints pocket pair
	
		for(int i = 0; i < 2; i++){
			System.out.println(playerHand[i].getValueAsString()+ " of " + playerHand[i].getSuitAsString());
			// Only for testing.
			/*Message m = new Message();
	        m.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m.obj = (String) (playerHand[i].getValueAsString()+ " of " + playerHand[i].getSuitAsString()+ " ");
	        GameWindow.myViewUpdateHandler.sendMessage(m);*/
		}
	}
	
	public void printCommunity(){ // prints the community cards
		
		for(int i = 0; i < communityCards.length; i++){
			System.out.println("community Cards are :" +communityCards[i].getValueAsString()+ " of " + communityCards[i].getSuitAsString());

		}
	}
	
	
	/*public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision; // 0=fold,1=check/call, 2=raise
	}*/
	
	public int getBet(int currentBet, int tempbigBlind){ // implemented differently for AI and Human Players
		/*if(decision!=0){ 
			return bet;
		}*/
		return bet;
		
	}
	
	/*public int makeDecision(){ // where we decide what to do 
	
		return decision;
		
	}*/
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet;
	}
	
	public int checkMoney(){ // returns player's current money
		
		Message m = new Message(); // updates UI
        m.what = GameWindow.SETPLAYERMONEY;
        m.arg1 = this.playerID;
        m.obj = (String) (Integer.toString(currentMoney));
        GameWindow.myViewUpdateHandler.sendMessage(m);
        
		return currentMoney;
	}
	
	public  Card [] getBestHand(){ // returns a card array of 5
		//poolCards = new Card[communityCards.length+playerHand.length];

				
		if(playerHand.length+communityCards.length==5){ // just return the 5 cards available as that's the only combination available
			poolCards = new Card[communityCards.length+playerHand.length];
			System.arraycopy(playerHand, 0, poolCards, 0, playerHand.length);
			System.arraycopy(communityCards, 0, poolCards, 2, communityCards.length);
			PokerHand testHand=new PokerHand(poolCards);
			bestBoolean=testHand.testBooleans();
			PokerHand test1Hand=new PokerHand(poolCards);
			bestPokerHand=test1Hand.getCard();
			
			return poolCards;
			
		}
		
		if(communityCards.length+playerHand.length>5){
			
			poolCards = new Card[communityCards.length+playerHand.length];
			System.arraycopy(playerHand, 0, poolCards, 0, playerHand.length); // copy from player's hand into pool Card
			System.arraycopy(communityCards, 0, poolCards, 2, communityCards.length); // copy from community cards into pool cards starting after the player's first two cards
			
			if(poolCards.length==6){
				
				elements = new int[]  {0, 1, 2, 3, 4, 5}; 
				x = new CombinationGenerator (elements.length, 5); // call combination generator with a 6 choose 5 argument
				allCombosLength=6; // six possible combinations of five cards
				
				
			}
			else if(poolCards.length==7){
				
				elements = new int[] {0, 1, 2, 3, 4, 5, 6};
				x = new CombinationGenerator (elements.length, 5);// call combination generator with a 7 choose 5 argument
				allCombosLength=21; // 21 possible combinations of five cards
	
			}
			else{
				return null;
			}
		}
		Card [][] testCard = new Card [allCombosLength][5];
			int[] indices;
			int [] genCards = new int [5];
			int [][] allCombos = new int[allCombosLength][5];// going to have an array of x combos of 5 cards
			int count=0;
			
			StringBuffer combination;
			
			
			
			
			for(int j=0;j<allCombosLength;j++){
				while (x.hasMore ()) {
					combination = new StringBuffer ();
					indices = x.getNext ();
						for (int i = 0; i < indices.length; i++) { 
							combination.append (elements[indices[i]]);
							genCards[i]= elements[indices[i]];
							allCombos[count][i] = genCards[i];
						}
					
					
					count++;
				}
				
				
				// read in the combination string and create an array of 5 cards from that
			
				for (int i = 0; i < allCombosLength; i++) {
					for (int k=0;k<genCards.length;k++){
						int temp =allCombos[i][k];
						testCard[i][k]=poolCards[temp];
						//System.out.println(testCard[i][k].getValueAsString()+" "+ testCard[i][k].getSuitAsString());
					}
					//System.out.println("Next Hand is");
					
				}
				
				PokerHand testHand=new PokerHand(testCard[j]);
				
				
				if(testHand.testBooleans()<bestBoolean){
					bestBoolean=testHand.testBooleans();
					bestPokerHand = testHand.getCard();
				}
				
				
				
				if(testHand.testBooleans()==8){ // pair
					if(testHand.testBooleans()<bestBoolean){
						bestBoolean=testHand.testBooleans();
					}
					if(testHand.firstMatch>highestFirstPair&&bestBoolean==8){
						highestFirstPair=testHand.firstMatch;
					}
					if(testHand.getNonPair()>nonPairForEight){
						nonPairForEight=testHand.getNonPair();
					}
				}
				
				
				
				
				
				if(testHand.testBooleans()==7){ // special case for two pair
					if(testHand.testBooleans()<bestBoolean){//&&testHand.highestSecondPair>highestSecondPair&&testHand.firstMatch>highestFirstPair){
						bestBoolean=testHand.testBooleans();
						//System.out.println("upper 2 pair code");
						//System.out.println(testHand.firstMatch);
						
						
						
					}
					testHand.printPokerHand();	
					Card [] tempy = testHand.firstTwo();
						nonPair=testHand.getNonPair();
						

					if((testHand.firstMatch>highestFirstPair&&testHand.highestSecondPair>highestSecondPair)){
							//if(testHand.getLastCardValue()>highCard&&bestBoolean==7){
						highestSecondPair=testHand.highestSecondPair;
						highestFirstPair=testHand.firstMatch;
						bestPokerHand = testHand.getCard();
						
						if (testHand.getNonPair()>nonPair) {
								nonPair=testHand.getNonPair();
								highestSecondPair=testHand.highestSecondPair;
								System.out.println("2nd pair is: "+highestSecondPair);
								highestFirstPair=testHand.firstMatch;
								bestPokerHand = testHand.getCard();
								//highCard=testHand.getHighCard().getValue();
								
								//System.out.println("In "+bestBoolean+ "best hand is ");
								testHand.printPokerHand();
								//bestPokerHand;
								System.out.println(" ");
							}
							
						
						//System.out.println("outside 2nd if loop");
							//bestPokerHand.printPokerHand();
							//System.out.println(" ");
							//}
							
							}
					
						
					}
				
				
				if(testHand.testBooleans()==3){ // full house
					if(testHand.testBooleans()<bestBoolean){
						bestBoolean=testHand.testBooleans();
					}
					if(testHand.firstMatch>highestGeneralPair){
						highestGeneralPair=testHand.firstMatch;
						bestPokerHand = testHand.getCard();
					}
					if(testHand.highestSecondPair>highestGeneralPair){
						highestGeneralPair=testHand.highestSecondPair;
						bestPokerHand = testHand.getCard();
						
					}
				}
				
				if(testHand.testBooleans()==5||testHand.testBooleans()==1){//straight and straight flush
					System.out.println("in straight code");
					if(testHand.testBooleans()<bestBoolean){//&&testHand.highestSecondPair>highestSecondPair&&testHand.firstMatch>highestFirstPair){
						bestBoolean=testHand.testBooleans();
					}
					
					if(testHand.getLastCardValue()>highStraightCard&&testHand.testBooleans()==bestBoolean){
						bestPokerHand = testHand.getCard();
						highStraightCard=testHand.getLastCardValue();
					}
					
					
				}
				
				if(testHand.testBooleans()==6){ // three of a kind
					if(testHand.testBooleans()<bestBoolean){//&&testHand.highestSecondPair>highestSecondPair&&testHand.firstMatch>highestFirstPair){
						bestBoolean=testHand.testBooleans();
					}
					if(testHand.threeCard>threeCard&&testHand.firstNon>firstNonThreeCard&&testHand.secondNon>secondNonThreeCard&&testHand.testBooleans()==bestBoolean){
						threeCard=testHand.threeCard;
						firstNonThreeCard=testHand.firstNon;
						secondNonThreeCard=testHand.secondNon;
						bestPokerHand= testHand.getCard();
					}
				}
				
				
				//System.out.println("Best Boolean is ");
					
				

				
				
				
				// all other hands should go into this
				if(testHand.testBooleans()==bestBoolean&&(testHand.getHighCard().getValue()>highCard)&&testHand.getLowestHighCard().getValue()>highestLowest&&testHand.testBooleans()!=8&&testHand.testBooleans()!=7&&testHand.testBooleans()!=3&&testHand.testBooleans()!=5&&testHand.testBooleans()!=1){
						
						System.out.println("above if "+highCard);
												
						
						
							highCard=testHand.getHighCard().getValue();	
						
						
						bestPokerHand = testHand.getCard();
						//System.out.println("Current best 5 are: ");
						//Card bestCard = bestPokerHand[5];
						
						
				}
				
				
			
			}
			
			
			//System.out.println("printing @ end of getBestHand");
			//bestPokerHand.printPokerHand();
			return bestPokerHand;
		}
	
	public  PokerHand getBestPokerHand(){
		getBestHand();
		//System.out.println("printing @ getBestPokerHand");
		//bestPokerHand.printPokerHand();
		PokerHand tempPokerHand = new PokerHand(bestPokerHand);
		return tempPokerHand;
	}
			
	public  void showBestHand(){ // this updates the UI with the best hand
		Card [] showCards = getBestHand();
		PokerHand newCard = new PokerHand(showCards);
		showCards=newCard.getCard();
		//System.out.println("Player's best hand is: ");
		//System.out.println("showCards length: "+showCards.length);
		/*for (int i=0;i<showCards.length;i++){
			System.out.println(showCards[i].getValueAsString()+" of "+showCards[i].getSuitAsString());
			Message m = new Message();
	        m.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m.obj = (String) (showCards[i].getValueAsString()+" of "+showCards[i].getSuitAsString()+", ");
	        GameWindow.myViewUpdateHandler.sendMessage(m);
		}*/
		
		
	}

	public int getDecision() { // AI and human player handle this differently
		// TODO Auto-generated method stub
		return decision;
	}
	
	public void makeDecision(){
		
	}
	
	// used when determining the winner in a high card situation
	// where the lower pocket card is used
	public int getPlayerHighHoleCard(){ 
        Card [] temp = playerHand;
       
        if(temp [0].getValue()>temp [1].getValue()){
            return temp [0].getValue();
        }
        else{
            return temp [1].getValue();
        }
	}
	
	// used when determining the winner in a high card situation
	// where the lower pocket card is used
	public int getPlayerLowHoleCard(){ 
		   Card [] temp = playerHand;
		 
		   if(temp [0].getValue()<temp [1].getValue()){
		       return temp [0].getValue();
		   }
		   else{
		       return temp [1].getValue();
		   }
		}

}
