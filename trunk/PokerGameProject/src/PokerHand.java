
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
		Card match;
		for(int i = 0; i < x.length -1; i++){
			if( (x[i].getValue()) == (x[i+1].getValue())){
				
				System.out.println(" One pair found. " + x[i] +" and " +x[i+1]);
				IsOnePair = true;
				match = x[i+1];
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
	
	
	
	public static void IsTwoPair(Card[] x){
		if(IsOnePair(x) == true && IsOnePair(x) == true){
			IsOnePair.match
			System.out.println(" two pair found. " );
		}
	}
	
	public static void main(String args[]){
	Deck deck = new Deck();
	deck.shuffle();
	
	Card[] cardHand = new Card[5];
	cardHand = deck.dealCards(cardHand);
	bubbleSort(cardHand);

		for (int i = 0; i <5; i++){
			System.out.println(cardHand[i]);
		}
	
		IsTwoPair(cardHand);
		//if (IsOnePair(cardHand) == true){
		//	System.out.println(" One pair found.");
		//}
	}
	
	
}
