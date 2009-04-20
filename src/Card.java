
public class Card {

	public final static int HEARTS = 1; 
	public final static int SPADES = 0;   
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;
	   
	public final static int ACE = 1;      // Codes for the non-numeric cards.
	public final static int JACK = 11;    //   Cards 2 through 10 have their 
	public final static int QUEEN = 12;   //   numerical values for their codes.
	public final static int KING = 13;
	
	private final int suit;
	private final int value;
	
	public Card(int theValue, int theSuit) {
	value = theValue;
	suit = theSuit;
	
	}

	public int getSuit() {
	      return suit;
	   }

	public int getValue() {
	      return value;
	   }

	public String getSuitAsString() {
	      switch ( suit ) {
	      case SPADES:   return "Spades";
	      case HEARTS:   return "Hearts";
	      case DIAMONDS: return "Diamonds";
	      case CLUBS:    return "Clubs";
	      default:       return "Card Not Found";
	      }
	   }
	public String getValueAsString() {
	      
	         switch ( value ) {
	         //case 0:   return "Ace";
	         case 0:   return "2";
	         case 1:   return "3";
	         case 2:   return "4";
	         case 3:   return "5";
	         case 4:   return "6";
	         case 5:   return "7";
	         case 6:   return "8";
	         case 7:   return "9";
	         case 8:  return "10";
	         case 9:  return "Jack";
	         case 10:  return "Queen";
	         case 11:  return "King";
	         case 12:  return "Ace";
	         default:  return "Card Not Found";
	         
	      }
	   }

	
	public String toString() {
	      
	         return getValueAsString() + " of " + getSuitAsString();
	   }

	
}
