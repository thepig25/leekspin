
public class Player extends Pack {

	int decision;
	int bet=0;
	int currentMoney;
	String name;
	Card[] thisHand = new Card[7];
	Card[] playerHand = new Card[2];
	Card[] communityCards = new Card[3];
	int cardCount=0;
	int cardPocketCount=0;
	PokerHand bestPokerHand;
	CombinationGenerator x;
	int[] elements;
	Card [] poolCards;
	int allCombosLength;
	int highestFirstPair=2;
	int highestSecondPair=2;
	int bestBoolean=20;
	int highCard=2;
	int highestLowest=0;
	
	

	
	public Player(String Name, int Chips, Card[] currentHand){ // constructor for player
		currentMoney=Chips; // initialise currentMoney to the starting amount
		name = Name;
	}
	
	public String getName(){
		return name;
	}
	
	// this takes an array of dealt cards then adds them to the
	// players hand. using cardCount to make sure its in the 
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
	
	
	public void printPocketHand(){ // prints pocket pair
	
		for(int i = 0; i < 2; i++){
			System.out.println(playerHand[i].getValueAsString()+ " of " + playerHand[i].getSuitAsString());

		}
	}
	
	public void printCommunity(){ // prints the community cards
		
		for(int i = 0; i < communityCards.length; i++){
			System.out.println("community Cards are :" +communityCards[i].getValueAsString()+ " of " + communityCards[i].getSuitAsString());

		}
	}
	
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision; // 0=fold,1=check/call, 2=raise
	}
	
	public int getBet(){
		if(decision!=0){
			return bet;
		}
		return bet;
		
	}
	
	//private makeDecision(thisHand){ // where we decide what to do - keep private!
		
//	}
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet;
	}
	
	public int checkMoney(){ // returns player's current money
		return currentMoney;
		
	}
	
	public  Card [] getBestHand(){
		//poolCards = new Card[communityCards.length+playerHand.length];

				
		if(playerHand.length+communityCards.length==5){ // just return the 5 cards available
			poolCards = new Card[communityCards.length+playerHand.length];
			System.arraycopy(playerHand, 0, poolCards, 0, playerHand.length);
			System.arraycopy(communityCards, 0, poolCards, 2, communityCards.length);
			PokerHand testHand=new PokerHand(poolCards);
			bestBoolean=testHand.testBooleans();
			
			return poolCards;
			
		}
		
		if(communityCards.length+playerHand.length>5){
			
			poolCards = new Card[communityCards.length+playerHand.length];
			System.arraycopy(playerHand, 0, poolCards, 0, playerHand.length); // copy from player's hand into pool Card
			System.arraycopy(communityCards, 0, poolCards, 2, communityCards.length); // copy from community cards into pool cards starting after the player's first two cards
			
			if(poolCards.length==6){
				
				elements = new int[]  {0, 1, 2, 3, 4, 5};
				x = new CombinationGenerator (elements.length, 5);
				allCombosLength=6; // six possible combinations of five cards
				
				
			}
			else if(poolCards.length==7){
				
				elements = new int[] {0, 1, 2, 3, 4, 5, 6};
				x = new CombinationGenerator (elements.length, 5);
				allCombosLength=21; // 21 possible combinations of five cards
	
			}
			else{
				return null;
			}
		}
		Card [] testCard = new Card [5];
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
						}
					allCombos[count] = genCards;
					
					count++;
				}
				
				
			
			
				for (int i = 0; i < allCombosLength; i++) {
					for (int k=0;k<genCards.length;k++){
						int temp =allCombos[i][k];
						testCard[k]=poolCards[temp];
					}
					
					
				}
				PokerHand testHand=new PokerHand(testCard);
				if(testHand.testBooleans()==7){ // special case for two pair
					if(testHand.testBooleans()<=bestBoolean&&testHand.highestSecondPair>highestSecondPair&&testHand.firstMatch>highestFirstPair){
						highestSecondPair=testHand.highestSecondPair;
						highestFirstPair=testHand.firstMatch;
						bestPokerHand = testHand;
						if(testHand.testBooleans()<bestBoolean){
							bestBoolean=testHand.testBooleans();
						}
					}
				}
					
				if(testHand.testBooleans()<=bestBoolean&&testHand.getHighCard().getValue()>highCard&&testHand.getLowestHighCard().getValue()>highestLowest){
						bestBoolean=testHand.testBooleans();
						System.out.println(testHand.testBooleans());
						highCard=testHand.getHighCard().getValue();
						bestPokerHand = testHand;
						System.out.println("Current best 5 are: ");
						Card bestCard = bestPokerHand.getHighCard();
						
					}
			
			}
			
			
			
			return bestPokerHand.getCard();
		}
	
	public  PokerHand getBestPokerHand(){
		getBestHand();
		return bestPokerHand;
	}
		
		
		   // need to add the community and pocket card arrays together here!
			
			// then go through the combos and select the best card
			// a lot of the combos are going to have the same hand value so we need to search by highest card
	
	
	
	public  void showBestHand(){
		Card [] showCards = getBestHand();
		System.out.println("Player's best hand is: ");
		//System.out.println("showCards length: "+showCards.length);
		for (int i=0;i<showCards.length;i++){
			
			System.out.println(showCards[i].getValueAsString()+" of "+showCards[i].getSuitAsString());
		}
	}

}
