
public class CardGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deck deck = new Deck();
		
		deck.shuffle();
		
		/*
		 *  creating an array and filling it with 5 cards
		 */
		Card[] cardHand = new Card[5];
		cardHand = deck.dealCards(cardHand);
		
		/*
		 *  printing out elements of array for my own tests
		 */
		
		for (int i = 0; i <5; i++){
			System.out.println(cardHand[i]);
		}
		
	}	
}
