/*
 * UCD CSI Playing Card System.
 * $Id: PokerHand.java,v 1.2 2005/08/24 23:04:23 jkiniry Exp $
 */

package thady;
import java.util.*;
/**
 * A five card hand for poker.
 *
 * @author Joseph Kiniry <joseph.kiniry@ucd.ie>
 * @see http://secure.ucd.ie/~kiniry/part4.txt
 */

public class PokerHand {

  public static final byte ROYAL_FLUSH = -1, STRAIGHT_FLUSH = -2,
    FOUR_OF_A_KIND= -3, FULL_HOUSE = -4, FLUSH = -5, STRAIGHT=-6, THREE_OF_A_KIND= -7,
    TWO_PAIRS=-8, ONE_PAIR=-9, HIGH_CARD=-10;

  private PokerCard[] handArray = new PokerCard[0];

  // use these variables for comparing hands after evaluation
  private byte hand_Ranking = 0;
  private byte hand_RankingSuite = -1;
  private byte hand_HighestCard = 0;
  /**
   * Creates an empty poker hand
   *
   */
  public PokerHand() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


  /**
   * Add the given card to the PokerHand
   * @param c PokerCard
   */
  public void addCard(Card c) {

    PokerCard[] tmp = new PokerCard[handArray.length+1];
     System.arraycopy( handArray, 0, tmp, 0,handArray.length );
     tmp[tmp.length-1] = (PokerCard)c;
     handArray = tmp;


  }

  public void removeCard(Card c) {
  }

  public byte count() {
    return (byte)handArray.length ;
  }

  /**
   * Is this hand of higher value than the hand 'h'.
   *
   * Recall that in the rules of poker, the order of hand values is as
   * follows, highest to lowest:
   * <ol>
   *
   * <li> royal flush </li>, the five highest cards, namely
   * ace-king-queen-jack-ten of any one of the four suites.  The
   * suites have equal rank.  Royal flushes are of equal value.
   *
   * <li> straight flush </li> any five cards of the same suite in
   * numerical sequence, such as the ten-nine-eight-seven-six of
   * spades.  This flush is called "ten high".  Two flushes are
   * compared by comparing their top cards, e.g., a "ten high" beats a
   * "nine high".  If the top cards are the same, the hands are of
   * equal value.
   *
   * <li> four of a kind </li> any four cards of the same
   * demonination.
   *
   * <li> full house </li> three of one kind and two of another.  In
   * evaluating two full houses, the hand with the highest three of a
   * kind wins.
   *
   * <li> flush </li> any five cards of the same suite, but not in
   * sequence.  In evaluating two flushes, the winner is determined by
   * the rank of the highest card in the hand.  If the highest card is
   * the same, the next highest cards are compared, etc.  If all ranks
   * are equal, the hands are of equal value.
   *
   * <li> straight </li> five cards in consecutive sequence but not of
   * the same suite.  In competing straights, the winner is decided by
   * the rank of the highest card.  Straights of the same denomination
   * are equal and tie.
   *
   * <li> three of a kind </li> three cards of the same numerical
   * value plus two different and irrelevant cards.  The hand having
   * the highest three of a kind wins, regardless of the value of the
   * unmatched cards.
   *
   * <li> two pair </li> two different pairs of cards plus one odd
   * card.  In evaluating two two-pair hands, the winner is the hand
   * with the highest pair.  If the highest pairs are tied, the rank
   * of the second pair in the hands determines the winner.  If the
   * second pair is also tied, the higher card of the odd cards
   * determines the winner.  If all cards of the competing hands are
   * of matching value, the hands are tied.
   *
   * <li> one pair </li> two cards of the same denomination plus three
   * indifferent unmatched cards.  The hand with the higher pair wins.
   * If the pairs have the same value, the highest indifferent card
   * wins, etc.  If all cards match, the hands tie.
   *
   * <li> high card </li> a hand which contains five indifferent cards
   * not of the same suite, not in sequence, and falling into none of
   * the above combinations.  Between two high card hands, the highest
   * card determines the winner, etc.  If all cards match, the hands
   * tie.
   *
   * </ol>
   *
   * If two hands are of the same type, then the hand with the highest
   * rank wins.
   */
public byte evaluate(){

      // this method returns the byte value representing what poker hand
      // we reckon this is
      Arrays.sort(handArray);

      if(isRoyalFlush()){
          //System.out.println("Royal Flush");
          return ROYAL_FLUSH;
      }
      else if(isStraightFlush())
      {
          //System.out.println("Straight Flush - " + hand_HighestCard + " high");
          return STRAIGHT_FLUSH ;
      }
      else if(isFourOfAKind())
      {
          //System.out.println("Four of a Kind - " + hand_HighestCard + " high");
          return FOUR_OF_A_KIND;
      }
      else if(isFullHouse())
    {
        //System.out.println("Full House - " + hand_HighestCard + " high");
        return FULL_HOUSE;
    }

      else if(isFlush()){
          //System.out.println("Flush - " + hand_HighestCard + " high");
          return FLUSH;
      }
      else if(isStraight()){
          //System.out.println("Straight - " + hand_HighestCard + " high");
          return STRAIGHT;
      }
      else if(isThreeOfAKind())
      {
          //System.out.println("Three of a Kind - " + hand_HighestCard + " high");
          return THREE_OF_A_KIND;
      }
      else if(isTwoPair())
      {
         //System.out.println("Two Pair - " + hand_HighestCard + " high");
         return TWO_PAIRS;
      }
      else if(isPair())
      {
         //System.out.println("One Pair - " + hand_HighestCard + " high");
         return ONE_PAIR;
      }
      else if(isHighCard())
      {
          //System.out.println("High Card Only - " + hand_HighestCard + " high");
          return HIGH_CARD;
      }
      // else nothing - this shouldn't really happen
      return 0;
  }
  /**
   * Test to see if the hand is a royal flush
   * @return boolean
   */
  private boolean isRoyalFlush(){

      // is this a royal flush?
      //Arrays.sort(handArray);

      if(sameSuite() ){
         if ((handArray[0].value() == -13)
                && (handArray[1].value() == -12)
                   && (handArray[2].value() == -11)
                      && (handArray[3].value() == -10)
                         &&(handArray[4].value() == -1)) {
             hand_Ranking = ROYAL_FLUSH;
             hand_HighestCard = (byte)-1;
             hand_RankingSuite = handArray[0].suite();
             return true;
         }
     }
     return false;

  }
  /**
 * Test to see if the hand is a straight flush
 * @return boolean
 */

  private boolean isStraightFlush(){
      //Arrays.sort(handArray);
      if(sameSuite() ){
           if ((handArray[0].value() == handArray[1].value() - 1)
               && (handArray[1].value() == handArray[2].value() - 1)
               && (handArray[2].value() == handArray[3].value() - 1)
               && (handArray[3].value() == handArray[4].value() - 1)) {

               hand_Ranking = STRAIGHT_FLUSH;
               hand_HighestCard = handArray[0].value();
               hand_RankingSuite = handArray[0].suite();

               return true;
           }
       }
      return false;
  }
  /**
   * Test to see if the hand is a Poker
   * @return boolean
   */

  private boolean isFourOfAKind(){

      byte tmpLastValue = 0;
      byte count_same = 1;
      byte four_of_a_kind_value = 0;
      for(int i =0;i<handArray.length;i++){
          if(tmpLastValue != handArray[i].value()){
              count_same = 1;
              tmpLastValue = handArray[i].value();
          }
          else{
             count_same ++;
             four_of_a_kind_value =  handArray[i].value();
             if(count_same==4)break;
          }
      }
      if(count_same==4){
          hand_HighestCard = four_of_a_kind_value;
          hand_Ranking = FOUR_OF_A_KIND;
          return true;
      }
      return false;
  }
  /**
 * Test to see if the hand is a full house
 * @return boolean
 */

  private boolean isFullHouse(){

      byte tmpLastValue = 0;
      byte count_same = 1;
      byte three_of_a_kind_value = 0;
      for(int i =0;i<handArray.length;i++){
          if(tmpLastValue != handArray[i].value()){
              count_same = 1;
              tmpLastValue = handArray[i].value();
          }
          else{
             count_same ++;
             three_of_a_kind_value =  handArray[i].value();
             if(count_same==3)break;
          }
      }
      if(count_same==3){
          // this is at least a 3 of a kind - if there are only 2 distinct values
          // in the hand then it is a full house
          if(distinctValues() == 2){
              hand_HighestCard = three_of_a_kind_value;
              hand_Ranking = FULL_HOUSE;
              return true;
          }
      }
      return false;
  }


  /**
 * Test to see if the hand is a straight
 * @return boolean
 */

  private boolean isStraight(){
     //Arrays.sort(handArray);

          if ((handArray[0].value() == handArray[1].value() - 1)
              && (handArray[1].value() == handArray[2].value() - 1)
              && (handArray[2].value() == handArray[3].value() - 1)
              && (handArray[3].value() == handArray[4].value() - 1)) {

              hand_Ranking = STRAIGHT ;
              hand_HighestCard = handArray[0].value();
              hand_RankingSuite = handArray[0].suite();

              return true;
          }

     return false;
 }
 /**
 * Test to see if the hand is a  flush
 * @return boolean
 */

  private boolean isFlush(){
     // Arrays.sort(handArray);
      if (sameSuite()) {
          hand_Ranking = FLUSH;
          hand_HighestCard = handArray[0].value();
          hand_RankingSuite = handArray[0].suite();
          return true;
      }
      return false;
  }
  /**
 * Test to see if the hand is a Three of A Kind
 * @return boolean
 */

  private boolean isThreeOfAKind(){

    byte tmpLastValue = 0;
    byte count_same = 1;
    byte three_of_a_kind_value = 0;
    for(int i =0;i<handArray.length;i++){
        if(tmpLastValue != handArray[i].value()){
            count_same = 1;
            tmpLastValue = handArray[i].value();
        }
        else{
           count_same ++;
           three_of_a_kind_value =  handArray[i].value();
           if(count_same==3)break;
        }
    }
    if(count_same==3){
        hand_HighestCard = three_of_a_kind_value;
        hand_Ranking = THREE_OF_A_KIND;
        return true;
    }
    return false;
 }
 /**
* Test to see if the hand is a Two Pair
* @return boolean
*/

 private boolean isTwoPair(){

    byte tmpLastValue = 0;
    byte count_same = 1;
    byte pair_value = 0;
    for(int i =0;i<handArray.length;i++){
        if(tmpLastValue != handArray[i].value()){
            count_same = 1;
            tmpLastValue = handArray[i].value();
        }
        else{
           count_same ++;
           pair_value =  handArray[i].value();
           if(count_same==2)break;
        }
    }
    if(count_same==2){
        // this is at least a pair - if there are only 3 distinct values
        // in the hand then it is a full house
        if(distinctValues() == 3){
            hand_HighestCard = pair_value;
            hand_Ranking = TWO_PAIRS;
            return true;
        }
    }
    return false;
}
/**
* Test to see if the hand is a Single Pair
* @return boolean
 */
 private boolean isPair() {

     byte tmpLastValue = 0;
     byte count_same = 1;
     byte pair_value = 0;
     for (int i = 0; i < handArray.length; i++) {
         if (tmpLastValue != handArray[i].value()) {
             count_same = 1;
             tmpLastValue = handArray[i].value();
         } else {
             count_same++;
             pair_value = handArray[i].value();
             if (count_same == 2)break;
         }
     }
     if (count_same == 2) {
         hand_HighestCard = pair_value;
         hand_Ranking = ONE_PAIR;
         return true;
     }
     return false;
 }
 /**
* Test to see if the hand is a simply a high card
* @return boolean
*/

 private boolean isHighCard() {
     if(!sameSuite() && distinctValues() == 5){
         hand_HighestCard = handArray[0].value();
         hand_Ranking = HIGH_CARD;
         return true;
     }
     else{
         return false;
     }
 }



  private boolean sameSuite() {
      // helper function to determine if all the cards are of the
      // same suite
      byte iCounter = 1;
      byte lastvalue = handArray[0].suite() ;
      for(int i=0;i<handArray.length;i++){
          if (handArray[i].suite() != lastvalue) {
              lastvalue = handArray[i].suite();
              iCounter++;
          }
      }

       if(iCounter > 1)
           return false;

      return true;

  }

  private byte distinctValues(){

      byte iCounter = 1;
      byte lastvalue = handArray[0].value();
      for(int i=0;i<handArray.length;i++){
          if(handArray[i].value() != lastvalue){
              lastvalue = handArray[i].value();
              iCounter++;
          }
      }


      return iCounter;
  }
  /**
   * This method compares the current hand to the passed hand
   * returns true if the current hand is higher
   * @param h PokerHand
   * @return boolean
   */
  public boolean compare(PokerHand h) {

      if(this.evaluate() > h.evaluate()){
          return true;
      }
      else if(this.evaluate() < h.evaluate()){
          return false;
      }
      else
      {
          // there are the same hand but different high card
          if(this.hand_HighestCard < h.hand_HighestCard)
              return true;
          else
              return false;
      }
  }

  /**
   * @see #higherValueThan(PokerHand)
   */
  public boolean equal(PokerHand h) {
     if(this.evaluate() == h.evaluate() && this.hand_HighestCard == h.hand_HighestCard ){
         return true;
     }
     else{
         return false;
     }
  }

  public String toString() {

  int iValue = 0;
  String strValue = "";
  iValue = (int)this.evaluate();

  if(iValue == -1)
      strValue = "ROYAL_FLUSH";
  else if(iValue== -2)
      strValue = "STRAIGHT_FLUSH";
  else if(iValue== -3)
      strValue = "FOUR_OF_A_KIND";
  else if(iValue== -4)
      strValue = "FULL_HOUSE";
  else if(iValue== -5)
      strValue = "FLUSH";
  else if(iValue== -6)
      strValue = "STRAIGHT";
  else if(iValue== -7)
      strValue = "THREE_OF_A_KIND";
  else if(iValue== -8)
      strValue = "TWO_PAIRS";
  else if(iValue== -9)
        strValue = "ONE_PAIR";
  else
     strValue = " HIGH_CARD";





   String strRet = "Hand is " + strValue + " " + hand_HighestCard + " high \n";
     for(int i=0;i<handArray.length;i++){
         strRet += "\nCard " + i + " " + handArray[i].toString();
     }
     return strRet;
  }

  public int hashCode() {
      return 0;
  }

  public boolean equals(Object o) {
        return true;
  }

    private void jbInit() throws Exception {
    }

}
