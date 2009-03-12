package cards;


/**
 * Ciaran McElhatton - 05365791
 */
public class PokerHand {
	
	public PlayingCard[] hand;
	
	public PokerHand(DeckOfCards d){
		DeckOfCards deck = d;
		hand = new PlayingCard[5];
		hand = deck.dealCards(hand);
		sort();
	}
	
	public boolean isOnePair(){
		if( hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) ||
			hand[1].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) ||
			hand[2].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ||
			hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
				return true;
		}
		return false;
	}
	
	public boolean isTwoPair(){
		if( (hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) &&
				hand[2].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName())) ||
			(hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) && 
				hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName())) ||
			(hand[1].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) &&
				hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName())) ){
					return true;
		}
		return false;
	}
	
	public boolean isThreeOfAKind(){
		if( hand[0].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) ||
			hand[1].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ||
			hand[2].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
				return true;
		}
		return false;
	}
	
	public boolean isFourOfAKind(){
		if( hand[0].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ||
			hand[1].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
				return true;
		}
		return false;
	}
	
	public boolean isFullHouse(){
		if( (hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) &&
			hand[2].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName())) ||
			(hand[0].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) &&
			hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName())) ){
					return true;
			}
		return false;
	}
	
	public boolean isStraight(){
		if( (hand[0].getRoyalValue()+1 == hand[1].getRoyalValue()) &&
			(hand[1].getRoyalValue()+1 == hand[2].getRoyalValue()) &&
			(hand[2].getRoyalValue()+1 == hand[3].getRoyalValue()) &&
			(hand[3].getRoyalValue()+1 == hand[4].getRoyalValue()) ){
				return true;
		}
		return false;
	}
	
	public boolean isFlush(){
		String suit = hand[0].getSuit();
		for(int i=1; i<5; i++){
			if( !(hand[i].getSuit().equals(suit)) ){
				return false;
			}
		}
		return true;
	}
	
	public boolean isStraightFlush(){
		if(isFlush()==true && isStraight()==true){
			return true;
		}
		return false;
	}
	
	public boolean isRoyalFlush(){
		if( isStraight()==true && hand[0].getSimpleName().equalsIgnoreCase("ace") &&
				hand[1].getSimpleName().equalsIgnoreCase("ten") &&
				hand[2].getSimpleName().equalsIgnoreCase("jack") &&
				hand[3].getSimpleName().equalsIgnoreCase("queen") &&
				hand[4].getSimpleName().equalsIgnoreCase("king") ){
			return true;
		}
		return false;
	}
	
	public void sort(){
		// To sort by PlayingCard.value
	    boolean cont = true;
	    while (cont) {
	        cont = false;
	        for (int i=0; i<hand.length-1; i++) {
	            if (hand[i].getValue() > hand[i+1].getValue()){
	               PlayingCard temp = hand[i];
	               hand[i] = hand[i+1];
	               hand[i+1] = temp;
	               cont = true;
	            }
	        }
	    }
	    // To sort jacks, queen and kings.
	    if(hand[hand.length-2].getValue()==10){
	    	cont = true;
	    	while (cont) {
	    		int i=hand.length-2;
		        cont = false;
		        while(i>=0 && hand[i].getValue()==10){
		    		if( hand[i].getRoyalValue() > hand[i+1].getRoyalValue() ){PlayingCard temp = hand[i];
		    			hand[i] = hand[i+1];
			            hand[i+1] = temp;
			            cont = true;
		    		}
		    		i--;
		    	}
	    	}
	    }
	}
	
	public int shouldDiscard(int cardPosition){
		// If hand is a royal-flush never loose a card. (Return 0)
		if(isRoyalFlush()){
			return 0;
		}else{
			// If hand is a straight-flush never loose a card. (Return 0)
			if(isStraightFlush()){
				return 0;
			}else{
				// Returns 0 for any card part of the four-of-a-kind
				// and 50 for the other card. (to confuse other players)
				if(isFourOfAKind()){
					if( (cardPosition==0) && 
						!(hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName())) ){
						return 50;
					}
					if( (cardPosition==4) && 
						!(hand[4].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName())) ){
						return 50;
					}
					return 0;
				}else{
					// Returns 0 for the three-of-a-kind and 10 for the pair.
					if(isFullHouse()){
						if(hand[1].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName())){
							if(cardPosition<3){
								return 0;
							}else{
								return 10;
							}
						}else{
							if(cardPosition>1){
								return 0;
							}else{
								return 10;
							}
						}
					}else{
						// If hand is a flush never loose a card. (Return 0)
						if(isFlush()){
							return 0;
						}else{
							// If hand is a straight never loose a card. (Return 0)
							if(isStraight()){
								return 0;
							}else{
								// Returns a 0 for any of the three-of-a-kind and 75 for each of
								// the other two. so that sometimes only one will be discarded.
								if(isThreeOfAKind()){
									if( hand[0].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) ){
										if(cardPosition<3){
											return 0;
										}else{
											return 75;
										}
									}else{
										if( hand[1].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ){
											if(cardPosition>0 || cardPosition<4){
												return 0;
											}else{
												return 75;
											}
										}else{
											if( hand[2].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
												if(cardPosition>1){
													return 0;
												}else{
													return 75;
												}
											}else{
												return 0; // Used to keep the compiler happy. (actual code never reached)
											}
										}
									}
								}else{
									// Returns 0 for the odd card and 10 for the two pairs.
									if(isTwoPair()){
										if( hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) &&
											hand[2].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ){
											if(cardPosition==4){
												return 100;
											}else{
												return 10;
											}
										}
										if( hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) && 
											hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
											if(cardPosition==2){
												return 100;
											}else{
												return 10;
											}
										}
										if( hand[1].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) &&
											hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
											if(cardPosition==0){
												return 100;
											}else{
												return 10;
											}
										}
										return 0; // Used to keep the compiler happy. (actual code never reached)
									}else{
										// Returns 0 for the pair and 100 for the others.
										if(isOnePair()){
											if( hand[0].getSimpleName().equalsIgnoreCase(hand[1].getSimpleName()) ){
												if(cardPosition<2){
													return 0;
												}else{
													return 100;
												}
											}
											if(	hand[1].getSimpleName().equalsIgnoreCase(hand[2].getSimpleName()) ){
												if(cardPosition==1 || cardPosition==2){
													return 0;
												}else{
													return 100;
												}
											}
											if(	hand[2].getSimpleName().equalsIgnoreCase(hand[3].getSimpleName()) ){
												if(cardPosition==2 || cardPosition==3){
													return 0;
												}else{
													return 100;
												}
											}
											if(	hand[3].getSimpleName().equalsIgnoreCase(hand[4].getSimpleName()) ){
												if(cardPosition==3 || cardPosition==4){
													return 0;
												}else{
													return 100;
												}
											}
											return 0; // Used to keep the compiler happy. (actual code never reached)
										}else{
											// If there is no good hand in the deck, keep highest card.
											// Always keeps aces.
											if( (cardPosition<4) && (hand[cardPosition].getValue()!=1) ){
												return 100;
											}else{
												return 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/*
	 * Test Method
	 */
	public static void main(String args[]){
		// Generates a random 5-card hand.
		DeckOfCards myDeck = new DeckOfCards();
		PokerHand ph = new PokerHand(myDeck);
		
		// Prints the highest possible value of the hand using the above rules.
		if(ph.isRoyalFlush()==true){
			System.out.println("> Royal Flush!");
		}else{
			if(ph.isStraightFlush()==true){
				System.out.println("> Straight Flush.");
			}else{
				if(ph.isFourOfAKind()==true){
					System.out.println("> Four of a kind.");
				}else{
					if(ph.isFullHouse()==true){
						System.out.println("> Full House.");
					}else{
						if(ph.isFlush()==true){
							System.out.println("> Flush.");
						}else{
							if(ph.isStraight()==true){
								System.out.println("> Straight.");
							}else{
								if(ph.isThreeOfAKind()==true){
									System.out.println("> Three of a kind.");
								}else{
									if(ph.isTwoPair()==true){
										System.out.println("> Two Pair.");
									}else{
										if(ph.isOnePair()==true){
											System.out.println("> One Pair.");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for(int i=0; i<ph.hand.length; i++){
			System.out.println(ph.hand[i].getSimpleName()+" of "+ph.hand[i].getSuit()+". "+ph.shouldDiscard(i));
		}
	}
}
