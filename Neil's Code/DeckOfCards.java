

public class DeckOfCards {

    private PlayingCard[] deck;   // A deck is represented by an array.
    private int dealtCards; 	  // The number of cards that have been dealt from the deck.
    
    public DeckOfCards() {
           // Makes a deck of Cards
       deck = new PlayingCard[52];
       int cardsMade = 0;
       for (int suit = 1; suit <= 4; suit++) {
          for ( int value = 1; value <= 13; value++) {
             deck[cardsMade] = new PlayingCard(value,suit);
             cardsMade ++;
          }
       }
       dealtCards = 0;
    }
    
    public void shuffle() {
          // Shuffles the deck.
        for ( int i = 51; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            PlayingCard temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        dealtCards = 0;
    }
    
    public PlayingCard dealCard() {
          // Deals a card from the deck
        if(dealtCards == 52){
        	shuffle();
        }
    	dealtCards++;
        return deck[dealtCards - 1];
    }

    public void dealCards(PlayingCard[] hand){
    	 // Deals a hand of five cards
    	for(int i=0; i<hand.length; i++){
    		hand[i]=dealCard();
    	}
    }
}
