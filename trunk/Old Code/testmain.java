package thady;



/**
 * <p>Title: SoftwareProjectReassessment</p>
 *
 * <p>Description: SoftwareProjectReassessment</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Thady Lynch
 * @version 1.0
 */import java.util.*;
public class testmain{
    public testmain() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public byte ACE = -1, TWO = -2, THREE = -3, FOUR = -4,
            FIVE = -5, SIX = -6, SEVEN = -7, EIGHT = -8, NINE = -9, TEN = -10,
             JACK = -11, QUEEN = -12, KING = -13;

   public  byte CLUB = 0, DIAMOND = 1, HEART = 2, SPADE = 3;
   public byte ROYAL_FLUSH = -1, STRAIGHT_FLUSH = -2,
               FOUR_OF_A_KIND= -3, FULL_HOUSE = -4, FLUSH = -5, STRAIGHT=-6, THREE_OF_A_KIND= -7,
               TWO_PAIRS=-8, ONE_PAIR=-9, HIGH_CARD=-10;
       /**
                *
                * Select the test option we require by changing the value of bOPTION
                *<br>
                * Option ONE_OF_EACH_HAND tests the evaluate Method of PokerHand for each hand type<br>
                * Option COMPARE_HANDS lets us compare some predifined hands - used for testing<br>
                * Option RANDOM_HANDS draws 2 random hands from the deck and compares them
          */
     public static void main(String[] args){
         byte bOPTION = 0;
         byte ONE_OF_EACH_HAND = 1,COMPARE_HANDS=2,RANDOM_HANDS=3;

         /**
          * Update this variable for the different modes
          */
         bOPTION = 3;


    // define values for testing card combinations
     byte ROYAL_FLUSH = -1, STRAIGHT_FLUSH = -2,
                       FOUR_OF_A_KIND= -3, FULL_HOUSE = -4, FLUSH = -5, STRAIGHT=-6, THREE_OF_A_KIND= -7,
             TWO_PAIRS=-8, ONE_PAIR=-9, HIGH_CARD=-10;
      byte CLUB = 0, DIAMOND = 1, HEART = 2, SPADE = 3;

       byte ACE = -1, TWO = -2, THREE = -3, FOUR = -4,
                 FIVE = -5, SIX = -6, SEVEN = -7, EIGHT = -8, NINE = -9, TEN = -10,
             JACK = -11, QUEEN = -12, KING = -13;
     /**
      * First this does a quick test to evaluate one of each type of hand
      */
     ///        PokerDeck myDeck = new PokerDeck();
     if(bOPTION == ONE_OF_EACH_HAND){
        PokerHand PokerHand_1;
        PokerHand_1 = getSampleHand(ROYAL_FLUSH);
        System.out.println("Value is " + PokerHand_1.evaluate() );

        PokerHand_1 = getSampleHand(STRAIGHT_FLUSH);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(FOUR_OF_A_KIND);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(FULL_HOUSE);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(FLUSH);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(STRAIGHT);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(THREE_OF_A_KIND);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(TWO_PAIRS);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(ONE_PAIR);
        System.out.println("Value is " + PokerHand_1.evaluate() );
        PokerHand_1 = getSampleHand(HIGH_CARD);
        System.out.println("Value is " + PokerHand_1.evaluate() );
     }
     else if(bOPTION == COMPARE_HANDS)
     {
           PokerHand PokerHand_1,PokerHand_2;
           PokerHand_1= new PokerHand();
           PokerHand_1.addCard(new PokerCard(EIGHT,HEART));
           PokerHand_1.addCard(new PokerCard(EIGHT,DIAMOND));
           PokerHand_1.addCard(new PokerCard(SEVEN,HEART));
           PokerHand_1.addCard(new PokerCard(SIX,HEART));
           PokerHand_1.addCard(new PokerCard(FIVE,HEART));

           System.out.println("Hand 1---------------------" + PokerHand_1.toString() );
           PokerHand_2= getSampleHand(ONE_PAIR);
           System.out.println("Hand 2---------------------" + PokerHand_2.toString() );
           System.out.println("------------------------");
         if(!PokerHand_1.equal(PokerHand_2))
         {
             if (PokerHand_1.compare(PokerHand_2)) {
                 System.out.println("hand 1 beats hand 2");
             } else {
                 System.out.println("hand 1 loses to hand 2");
             }
         }
         else
         {
             System.out.println("Hands are equal");

         }

     }
     else if(bOPTION == RANDOM_HANDS)
     {
         PokerDeck myDeck = new PokerDeck();
         myDeck.shuffle();

         //System.out.println(myDeck.toString());

         PokerHand PokerHand_1 = new PokerHand();
         PokerHand PokerHand_2 = new PokerHand();

         // now draw every other card
         for(int i = 0;i<5;i++){
             PokerHand_1.addCard(myDeck.getCard());
             PokerHand_2.addCard(myDeck.getCard());
         }
         System.out.println("\nHand 1 - " + PokerHand_1.toString() );
         PokerHand_2= getSampleHand(ONE_PAIR);
         System.out.println("\nHand 2 - " + PokerHand_2.toString() );
         System.out.println("------------------------");
         if(!PokerHand_1.equal(PokerHand_2))
               {
                   if (PokerHand_1.compare(PokerHand_2)) {
                       System.out.println("hand 1 beats hand 2");
                   } else {
                       System.out.println("hand 1 loses to hand 2");
                   }
               }
               else
               {
                   System.out.println("Hands are equal");
               }
         }

   }

   public static PokerHand getSampleHand(byte bTypeOfHand) {
       PokerHand hand_1 = new PokerHand();
       // define sample cards
       PokerCard card1, card2, card3, card4, card5;
       //- test royal flush
       byte ROYAL_FLUSH = -1, STRAIGHT_FLUSH = -2,
                       FOUR_OF_A_KIND= -3, FULL_HOUSE = -4, FLUSH = -5, STRAIGHT=-6, THREE_OF_A_KIND= -7,
             TWO_PAIRS=-8, ONE_PAIR=-9, HIGH_CARD=-10;
      byte ACE = -1, TWO = -2, THREE = -3, FOUR = -4,
              FIVE = -5, SIX = -6, SEVEN = -7, EIGHT = -8, NINE = -9, TEN = -10,
               JACK = -11, QUEEN = -12, KING = -13;

        byte CLUB = 0, DIAMOND = 1, HEART = 2, SPADE = 3;

       if (bTypeOfHand == ROYAL_FLUSH) {
           card1 = new PokerCard(KING, HEART);
           card2 = new PokerCard(TEN, HEART);
           card3 = new PokerCard(JACK, HEART);
           card4 = new PokerCard(QUEEN, HEART);
           card5 = new PokerCard(ACE, HEART);
       } else if (bTypeOfHand == STRAIGHT_FLUSH) {
           card1 = new PokerCard(KING, HEART);
           card2 = new PokerCard(TEN, HEART);
           card3 = new PokerCard(JACK, HEART);
           card4 = new PokerCard(QUEEN, HEART);
           card5 = new PokerCard(NINE, HEART);
       } else if (bTypeOfHand == STRAIGHT) {

           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(TEN, DIAMOND);
           card3 = new PokerCard(JACK, HEART);
           card4 = new PokerCard(SEVEN, HEART);
           card5 = new PokerCard(NINE, HEART);
       } else if (bTypeOfHand == FLUSH) {

           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(ACE, HEART);
           card3 = new PokerCard(JACK, HEART);
           card4 = new PokerCard(SEVEN, HEART);
           card5 = new PokerCard(NINE, HEART);
       } else if (bTypeOfHand == FOUR_OF_A_KIND) {
           // -- test four of a kind
           card1 = new PokerCard(SIX, HEART);
           card2 = new PokerCard(SEVEN, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(SEVEN, CLUB);
           card5 = new PokerCard(SEVEN, HEART);
       } else if (bTypeOfHand == THREE_OF_A_KIND) {
           // test 3 of kind

           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(TWO, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(SEVEN, CLUB);
           card5 = new PokerCard(SEVEN, HEART);
       } else if (bTypeOfHand == FULL_HOUSE) {

//-- test full house
           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(EIGHT, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(SEVEN, CLUB);
           card5 = new PokerCard(SEVEN, HEART);
       } else if (bTypeOfHand == TWO_PAIRS) {

           // test two pairs
           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(FIVE, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(SEVEN, CLUB);
           card5 = new PokerCard(FIVE, HEART);
       } else if (bTypeOfHand == ONE_PAIR) {
           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(ACE, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(SEVEN, CLUB);
           card5 = new PokerCard(THREE, HEART);
       } else {

           card1 = new PokerCard(EIGHT, HEART);
           card2 = new PokerCard(ACE, DIAMOND);
           card3 = new PokerCard(SEVEN, SPADE);
           card4 = new PokerCard(NINE, CLUB);
           card5 = new PokerCard(THREE, HEART);
       }

       hand_1.addCard(card1);
       hand_1.addCard(card2);
       hand_1.addCard(card3);
       hand_1.addCard(card4);
       hand_1.addCard(card5);
       return hand_1;
   }

    private void jbInit() throws Exception {
    }


}


