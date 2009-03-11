/*
 * UCD CSI Playing Card System.
 * $Id: PokerDeck.java,v 1.1.1.1 2005/08/24 23:02:37 jkiniry Exp $
 */

package thady;

import java.util.Arrays;

/**
 * A standard poker deck of 52 playing cards.
 *
 * @author Joseph Kiniry <joseph.kiniry@ucd.ie>
 */

public class PokerDeck {
  private PokerCard[] deckArray = new PokerCard[52];

  /**
  * Initialize the deck with one of each card combination
  */


  public PokerDeck() {
      byte position =0;
      for(byte suit = 0; suit < 4; suit++){
         for(byte name = -1; name > -14; name--){
            deckArray[position] = new PokerCard(name, suit);
            position ++;
         }
      }
  }


 /**
  *
  * Returns the current length of the deck array
  * @return byte
  */
  public byte count() {
      return (byte)deckArray.length ;
  }

  public PokerCard getCard() {

      PokerCard tmpCard = deckArray[deckArray.length-1];

      // remove the last card in the deck so it can't be dealt again
      // do this by reducing the array length by one
      PokerCard[] tmp = new PokerCard[deckArray.length-1];
      System.arraycopy( deckArray, 0, tmp, 0,deckArray.length-1 );

      deckArray = tmp;

   return tmpCard;

  }

  /**
   * Shuffles the deckArray
   * <br>this alogorithm is a modification of that described in lecture
   */
  public void shuffle() {

      for(int x = 0; x < deckArray.length; x++){
         int rand = (int) (Math.random()*deckArray.length);
          PokerCard temp = deckArray[rand];
          deckArray[rand] = deckArray[x];
          deckArray[x] = temp;


      }


  }

  public String toString() {
     String tmpString = "";

      for(byte i = 0; i< deckArray.length-1;i++){
          tmpString += "\n" + deckArray[i].toString();
      }
      return tmpString;
  }

  public int hashCode() {
      return 0;
  }

  public boolean equals(Object o) {
    return false;
  }

}
