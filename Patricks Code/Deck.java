
public class Deck {

	private Card[] deck;
	private int cardsUsed;
	Card[] temp1;
	
	public Deck(){	
	   deck = new Card[52];
	      int cardCount = 0; 
	      for ( int suit = 0; suit <= 3; suit++ ) {
	         for ( int value = 1; value <= 13; value++ ) {
	            deck[cardCount] = new Card(value,suit);
	            cardCount++;
	         }
	      }
		}   
	

	public void shuffle() {
		for ( int i = deck.length-1; i > 0; i-- ) {
			int random = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[random];
			deck[random] = temp;
		}
	}
	
	
	
	public void printcards(){
		for ( int i = deck.length-1; i > 0; i-- ) {
			System.out.println( );
			
		}
	}
	
	public Card dealCard() {
	      if (cardsUsed == deck.length)
	         throw new IllegalStateException("No cards are left in the deck.");
	      cardsUsed++;
	      return deck[cardsUsed - 1];
	      
	   }

	
	/*
	 *  takes an array temp1 gets the arrays lenght and fills each space in array with dealCard()
	 */
	
	public Card[] dealCards(Card[] temp1){
			
		for(int i = 0; i <temp1.length ; i++){
		temp1[i] = dealCard();
		}
		return temp1;
		
	}
	
}
