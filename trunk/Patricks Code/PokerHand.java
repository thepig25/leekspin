



public class PokerHand {

	static int onePairs = 0;

	public static void getCountsOfCard(Card[] x){
		
		if(IsOnePair(x) == true){
		onePairs++;
		}
	
	}
	
	public static void bubbleSort(Card[] x) {
	    int n = x.length;
	    for (int i=1; i < n; i++) {  // count how many times
	        // This next loop becomes shorter and shorter
	        for (int j=0; j < n-i; j++) {
	            if (x[j].getValue() > x[j+1].getValue()) {
	                // exchange elements
	                Card temp = x[j];
	                x[j] = x[j+1];
	                x[j+1] = temp;
	            }
	        }
	    }
	    
	}

	// goes through the array comparing two positions to each other since the array will be sorted they should match if there is a pair
	
	public static boolean IsOnePair(Card[] x){
		boolean IsOnePair = false;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue())){
				
				//System.out.println("One pair found. " + x[i] +" and " +x[i+1]);
				IsOnePair = true;
				//return true;
			}
		}
		//if(IsOnePair == false){
		//System.out.println("No pair found.");
		//}
		return IsOnePair;
	} 
	
	
	// goes through the array comparing two positions to each other since the array will be sorted they should match if there is a pair
	// BUT it adds a counter to it and sees if there is two pairs
	public static boolean IsTwoPair(Card[] x){
		// keeps track of how many pairs you have encountered
		int count = 0 ;		
		boolean IsTwoPair = false;
		
		for(int i  = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue())){
				count++;
				Card paired = x[i+1];	
			}	
		}
		
		//This is the check that if there are triple cards that a count-- takes place so that three of a kind don't get counted as two pairs
		
		if(x[0].getValue() == x[2].getValue() || x[1].getValue() == x[3].getValue() || x[2].getValue() == x[4].getValue()){
			count--;
		}
		
		if(count == 2){
			System.out.println("Two pairs found. ");
			return IsTwoPair = true;
		}
		return IsTwoPair;
	}
	
	// check to see if three cards in a row are the same value
	public static boolean IsThreeOfAKind(Card[] x){
		boolean IsThreeOfAKind = false;
		for(int i = 0; i < x.length -2; i++){
			if( (x[i].getValue()) == (x[i+1].getValue()) && (x[i+1].getValue()) == (x[i+2].getValue())){
				
				System.out.println("Trips found of " + x[i].getValue());
				return IsThreeOfAKind = true;
			}
			
		}
		//return false;	
		return IsThreeOfAKind;
	}
	
	// check to see if four cards in a row are the same value
	public static boolean IsFourOfAKind(Card[] x){
		boolean IsfourOfAKind = false;
		for(int i = 0; i < x.length -3; i++){
			if( (x[i].getValue()) == (x[i+1].getValue()) && (x[i+1].getValue()) == (x[i+2].getValue()) && (x[i+2].getValue()) == (x[i+3].getValue())){
				
				System.out.println(" Quaps found of " + x[i].getValue());
				return IsfourOfAKind = true;
			}
			
		}
		//return false;	
		return IsfourOfAKind;
		
	}
	// full house uses a count and then the isthreeofAkind and is pair and istwo pair to see if a full house
	public static boolean IsFullHouse(Card[] x){
		boolean isFullHouse = false;
		int count = 0;
		if(IsOnePair(x)){
			if(IsThreeOfAKind(x)){
				if(IsTwoPair(x)){
					count++;	
				}
				count++;
			}
			count++;
		}
		if(count == 2){
			System.out.println("Full House");
			return isFullHouse = true;
		}
		return false;
	}
	
	
	/*
	 * x will be equal to j-1
	 * for when x starts at 0 and j starts at 1
	 */
	public static boolean IsStraight(Card[] x){
		boolean isStraight = false;
		int i =0;
		int j =1;
		if( (x[i].getValue())   == (x[j].getValue() -1) && 
			(x[i+1].getValue()) == (x[j+1].getValue() -1) && 
			(x[i+2].getValue()) == (x[j+2].getValue() -1) && 
			(x[i+3].getValue()) == (x[j+3].getValue() -1)
			
			)
		
		{
			System.out.println("Straight found");
			return isStraight = true;
		}
		return isStraight;
	}
	
	
	// isflush counts the occurences of a cards suit showing up, using a switch statement and if equal to 5 there is a flush
	public static boolean IsFlush(){
		boolean isFlush = false;
		int spadeCount = 0;
		int heartCount = 0;
		int diamondCount = 0;
		int clubCount = 0;
		int defail = 0;
		
		for(int i = 0; i < x.length; i++){
			
			int suit = x[i].getSuit();	
			switch( suit ){
			case 0: spadeCount++; break;
			case 1: heartCount++; break;
			case 2: diamondCount++; break;
			case 3: clubCount++; break;
			default: defail++; break;
			}
		}
			if( spadeCount  == 5){
				System.out.println("Flush found of Spades");
				return isFlush = true;
			}
			if( heartCount == 5){
				System.out.println("Flush found of Hearts");
				return isFlush = true;
			}
			if( diamondCount == 5){
				System.out.println("Flush found of Diamonds");
				return isFlush = true;
			}
			if( clubCount == 5){
				System.out.println("Flush found of Clubs");
				return isFlush = true;
			}
			
		return isFlush;
	
	}
	
	public int shouldDiscard(int cardPosition){
		if (this.IsFlush() == true){
			System.out.println("You have a flush");
			return 0;
		}
		return 0;
	}
		
	/*
	public static boolean TestHands(Card[] x){
		
		if(IsFlush(x)&& IsStraight(x) == true){
			return true;
		}
		else if(IsFlush(x) == true){
			return true;
		}
		else if(IsStraight(x) == true){
			return true;
		}
		else if(IsFullHouse(x) == true){
			return true;
		}
		else if(IsFourOfAKind(x) == true){
			return true;
		}
		else if(IsThreeOfAKind(x) == true){
			return true;
		}
		else if(IsTwoPair(x) == true){
			return true;
		}
		else if(IsOnePair(x) == true){
			System.out.println("One Pair Found");
			return true;
		}
		else{
			return false;
		}
	}
	*/
	
	// my testing main method
	public static void main(String args[]){
	

	Deck deck = new Deck();
	deck.shuffle();
	
	Card[] cardHand = new Card[5];
	cardHand = deck.dealCards(cardHand);
		
	
	
	cardHand[0] = new Card(6, 2);
    cardHand[4] = new Card(2, 1);
    cardHand[1] = new Card(6, 2);
    cardHand[2] = new Card(6, 3);
    cardHand[3] = new Card(5, 2);

    bubbleSort(cardHand);
	
		for (int j = 0; j <5; j++){
			
			System.out.println(cardHand[j]);
		}
	
		//IsThreeOfAKind(cardHand);
		//TestHands(cardHand);
		cardHand.shouldDiscard(2);
	}
	
	
}
