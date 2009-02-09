
public class Card {

	
	public final static int Spades = 0;   
	public final static int Hearts = 1;
	public final static int Diamonds = 2;
	public final static int Clubs = 3;
	   
	   
	public final static int Ace = 1;      // Codes for the non-numeric cards.
	public final static int Jack = 11;    //   Cards 2 through 10 have their 
	public final static int Queen = 12;   //   numerical values for their codes.
	public final static int King = 13;

	/*
	 * the cards suit must be one of the following spades, hearts, diamonds, clubs
	 */
	
	private final int suit;
	
	/*
	 * The cards value
	 */
	private final int value;
	
	
	public Card (int theValue, int theSuit) {
		if (theSuit != Spades && theSuit != Hearts && theSuit != Diamonds && 
	            theSuit != Clubs)
	         throw new IllegalArgumentException("Illegal playing card suit");
	      if (theValue < 1 || theValue > 13)
	         throw new IllegalArgumentException("Illegal playing card value");
	      
	      value = theValue;
	      suit = theSuit;
	}
	
	
	public int getSuit(){
		return suit;
	}
	
	
	public int getValue(){
		return value;
	}
	
	
	public String getSuitAsString() {
	      switch ( suit ) {
	      case Spades:   return "Spades";
	      case Hearts:   return "Hearts";
	      case Diamonds: return "Diamonds";
	      case Clubs:    return "Clubs";
	      default:       return "Joker";
	      }
	   }
	
	
	public String getValueAsString() {
	         switch ( value ) {
	         case 1:   return "Ace";
	         case 2:   return "2";
	         case 3:   return "3";
	         case 4:   return "4";
	         case 5:   return "5";
	         case 6:   return "6";
	         case 7:   return "7";
	         case 8:   return "8";
	         case 9:   return "9";
	         case 10:  return "10";
	         case 11:  return "Jack";
	         case 12:  return "Queen";
	         default:  return "King";
	         }
	      
	 }
	
	
	public String toString() {
	      return getValueAsString() + " of " + getSuitAsString();
	}

}
