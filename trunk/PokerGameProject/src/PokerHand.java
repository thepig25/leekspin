
public class PokerHand {


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

	public static boolean IsOnePair(Card[] x){
		boolean IsOnePair = false;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue())){
				
				System.out.println(" One pair found. " + x[i] +" and " +x[i+1]);
				IsOnePair = true;
				//return true;
			}
			else{
				//return false;
			}
		}
		if(IsOnePair == false){
		System.out.println(" No pair found.");
		}
		//return false;	
		return IsOnePair;
	} 
	
	
	
	public static boolean IsTwoPair(Card[] x){
		int count = 0 ;
		boolean IsTwoPair = false;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue())){
				
				//System.out.println(" One pair found. " + x[i] +" and " +x[i+1]);
				count++;
			}
			
		}
		if(count == 2){
			System.out.println("Two pairs found. ");
			return IsTwoPair = true;
					}
		//return false;	
		return IsTwoPair;
	}
	
	
	public static boolean IsThreeOfAKind(Card[] x){
		boolean IsThreeOfAKind = false;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue()) && (x[i+1].getValue()) == (x[i+2].getValue())){
				
				System.out.println(" Trips found of " + x[i].getValue());
				return IsThreeOfAKind = true;
			}
			
		}
		//return false;	
		return IsThreeOfAKind;
	}
	
	
	public static boolean IsFourOfAKind(Card[] x){
		boolean IsfourOfAKind = false;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue()) && (x[i+1].getValue()) == (x[i+2].getValue()) && (x[i+2].getValue()) == (x[i+3].getValue())){
				
				System.out.println(" Quaps found of " + x[i].getValue());
				return IsfourOfAKind = true;
			}
			
		}
		//return false;	
		return IsfourOfAKind;
		
	}
	
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
		int j =(x[i].getValue() -1);
		if( (x[i].getValue())   == (x[i+1].getValue()) && 
			(x[i+1].getValue()) == (x[i+2].getValue()) && 
			(x[i+2].getValue()) == (x[i+3].getValue()) && 
			(x[i+3].getValue()) == (x[i+4].getValue()) ){
			
			System.out.println(" Straight found");
			return isStraight = true;
		}
		return isStraight;
	}
	
	public static void main(String args[]){
	Deck deck = new Deck();
	deck.shuffle();
	
	Card[] cardHand = new Card[5];
	cardHand = deck.dealCards(cardHand);
	
	cardHand[0] = new Card(6, 0);
	cardHand[4] = new Card(2, 3);
	cardHand[1] = new Card(4, 1);
	cardHand[2] = new Card(5, 2);
	cardHand[3] = new Card(3, 3);
	
	bubbleSort(cardHand);
	
		for (int i = 0; i <5; i++){
			
			System.out.println(cardHand[i]);
		}
	
		IsStraight(cardHand);
		//if (IsOnePair(cardHand) == true){
		//	System.out.println(" One pair found.");
		//}
	}
	
	
}
