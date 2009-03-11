/*
 * UCD CSI Playing Card System.
 * $Id: PokerCard.java,v 1.1.1.1 2005/08/24 23:02:37 jkiniry Exp $
 */

package thady;

/**
 * A standard playing card.
 *
 * @author Joseph Kiniry <joseph.kiniry@ucd.ie>
 */

public class PokerCard implements Card ,Comparable{
    // we want to implement the comparable interface in order
    // to use Arrays.sort on arrays of PokerCards

    byte[] validCardValueArray = { ACE , TWO, THREE , FOUR ,FIVE , SIX , SEVEN, EIGHT , NINE , TEN , JACK , QUEEN , KING };
    byte[] validCardSuiteArray = {CLUB, DIAMOND, HEART, SPADE};

    public PokerCard() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  // two private values for storing the value and suite of the cards
  private byte cardsuite;
  private byte cardvalue;

  public PokerCard(byte value, byte suite) {
      cardsuite = suite;
      cardvalue = value;
  }
  /**
   *
   * we want to output a string that's in plain english
  */
  public String toString() {

          String suite = "",value="";
      if(this.suite() == 0)
          suite = "CLUB";
      else if(this.suite() == 1)
          suite = "DIAMOND";
      else if(this.suite() == 2)
          suite = "HEART";
      else if(this.suite() == 3)
          suite = "SPADE";

      if(this.value() == -1)
          value = "ACE";
      else if(this.value() == -2)
          value = "TWO";
      else if(this.value() == -3)
          value = "THREE";
      else if(this.value() == -4)
          value = "FOUR";
      else if(this.value() == -5)
          value = "FIVE";
      else if(this.value() == -6)
          value = "SIX";
      else if(this.value() == -7)
          value = "SEVEN";
      else if(this.value() == -8)
          value = "EIGHT";
      else if(this.value() == -9)
          value = "NINE";
      else if(this.value() == -10)
          value = "TEN";
      else if(this.value() == -11)
          value = "JACK";
      else if(this.value() == -12)
          value = "QUEEN";
      else if(this.value() == -13)
          value = "KING";


    return " Value = " + value +" " +suite ;
  }
  /**
   *  this is suite of the card
   */

  public byte suite(){
        return cardsuite;
  }


  public boolean validSuite(byte s){
      for(byte i=0;i< validCardSuiteArray.length;i++){
         if(cardsuite == validCardSuiteArray[i]){
             return true;
         }
     }
     return false;

  }
  public boolean equals(Card o) {

      if ( this == o ) return true;
       if ( !(o instanceof Card) ) return false;

       Card _o = (Card)o;
       return  ( this.value() == _o.value() ) &&  ( this.value() == _o.suite() ) ;
  }


  /** The face-value of this card */
  public byte value(){
      return cardvalue;

  }
  public boolean validValue(byte v){
      // card value must exist in the validcardvaluearray
      for(byte i=0;i< validCardValueArray.length;i++){
          if(cardvalue == validCardValueArray[i]){
              return true;
          }
      }
      return false;
  }

  /**
   * @param a_card the card to compare
   * @return true iff the suite of this card is identical to the suite
   *         of <code>a_card</code>
   */
  public boolean sameSuiteAs(Card a_card){
      if(a_card.suite() == cardsuite)
          return true;
      else
          return false;
  }

  /**
   * @param a_card the card to compare
   * @return true iff the face value of this card is identical to the
   *         face value of <code>a_card</code>
   */
  public boolean sameFaceValueAs(Card a_card){
      if(a_card.value() == cardvalue)
         return true;
     else
         return false;

  }

  /**
   * @param a_card the card to compare
   * @return true iff the face value of this card is strictly greater than
   *         the face value of <code>a_card</code>
   */
  public boolean greaterFaceValueThan(Card a_card){
      // logic is backwards cos we're using negative numbers for the cards
      if(cardvalue < a_card.value())
        return true;
    else
        return false;

  }

  /**
   * Whether this card has a greater value than <code>a_card</code> is
   * determined by a given card game's rules.
   *
   * @param a_card the card to compare
   * @return true iff this card has a great value than <code>a_card</code>
   */
  public boolean greaterValueThan(Card a_card){
  // not sure of the difference between greatValueThan and greaterFaceValue
  //      logic is reversed due to use of negative numbers
      if(cardvalue < a_card.value()){
          return true;
      }
      else
          return false;
  }

  /**
   * Two cards are equivalent if they are indistinguishable in a given
   * card game's rules.
   *
   * @param a_card the card to compare
   * @return true iff this card and <code>a_card</code> are indistinguishable
   */

  public boolean equivalentTo(Card a_card){
      if(cardvalue == a_card.value())
          return true;
      else
          return false;
  }
  /**
   * We want to implement the compareTo method in order to be able to
   * sort arrays of cards effectively
   */
  public int compareTo(Object a_card){
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;
    PokerCard a__card = new PokerCard();
    a__card = (PokerCard)a_card;
    if ( this == a_card ) return EQUAL;

    // put highest card values first
    if(this.value() < a__card.value()) return BEFORE;
    if(this.value() > a__card.value()) return AFTER;

    // values are equal suites are not
    // we'll put lowest suite values first

    if(this.suite() > a__card.suite())return BEFORE;
    if(this.suite() < a__card.suite())return AFTER;

     // cards are appparently equal

     return EQUAL;
  }

  private void jbInit() throws Exception {
    }
}
