
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
		for(int i; i < x.length; i++){
			if()
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
	
	}
	
	
}
