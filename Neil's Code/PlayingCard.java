
public class PlayingCard {

	   public final static int HEARTS = 1;
	   public final static int DIAMONDS = 2;
	   public final static int SPADES = 3;       // Integer values for the four suits.
	   public final static int CLUBS = 4;
	   
	   public final static int ACE = 1;			 // Values for the non-numbered cards.
	   public final static int JACK = 11;        
	   public final static int QUEEN = 12;       
	   public final static int KING = 13;
	   
	   public final int suit; 
	   public final int value;
	   
	
	   public PlayingCard(int cardValue, int cardSuit) {
	      //Creates a card.
	      value = cardValue;
	      suit = cardSuit;
	   }

	   public int getSuit() {
	      return suit;
	   }
	   
	   public int getValue() {
	      return value;
	   }
	   
}

