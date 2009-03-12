
public class PokerHand {
	
	public PlayingCard[] pokerHand;
	private DeckOfCards deck;

	public PokerHand(){
		
		deck = new DeckOfCards();
		deck.shuffle();
		pokerHand= new PlayingCard[5];
		deck.dealCards(pokerHand);
		
		int out, in;
		PlayingCard temp;
		
		for(out=pokerHand.length-1; out>1; out--){  // outer loop
		  for(in=0; in<out; in++){    // inner loop
			 if(pokerHand[in].getValue() > pokerHand[in+1].getValue()){
				 temp = pokerHand[in];
				 pokerHand[in] = pokerHand[in+1];
				 pokerHand[in+1] = temp;
			 }
	      }
		}
	}

	public boolean isOnePair(){
		
		for(int i = 0; i<pokerHand.length-1; i++){
			if(pokerHand[i].getValue() == pokerHand[i+1].getValue()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isTwoPair(){

		for(int i = 0; i<1; i++){
			if((pokerHand[i].getValue() == pokerHand[i+1].getValue() &&
			    pokerHand[i+2].getValue() == pokerHand[i+3].getValue()) ||
			   (pokerHand[i].getValue() == pokerHand[i+1].getValue() &&
				pokerHand[i+3].getValue() == pokerHand[i+4].getValue()) ||
			   (pokerHand[i+1].getValue() == pokerHand[i+2].getValue() &&
			    pokerHand[i+3].getValue() == pokerHand[i+4].getValue())){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isThreeOfAKind(){
		
		for(int i = 0; i<3; i++){
			if(pokerHand[i].getValue() == pokerHand[i+1].getValue() && 
			   pokerHand[i+1].getValue() == pokerHand[i+2].getValue()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isFourOfAKind(){
		
		for(int i = 0; i<2; i++){
			if(pokerHand[i].getValue() == pokerHand[i+1].getValue() && 
			   pokerHand[i+1].getValue() == pokerHand[i+2].getValue() &&
			   pokerHand[i+2].getValue() == pokerHand[i+3].getValue()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isFullHouse(){
		
		for(int i = 0; i<1; i++){
			if((pokerHand[i].getValue() == pokerHand[i+1].getValue() && 
			    pokerHand[i+2].getValue() == pokerHand[i+3].getValue() &&
			    pokerHand[i+3].getValue() == pokerHand[i+4].getValue()) ||
			   (pokerHand[i].getValue() == pokerHand[i+1].getValue() &&
				pokerHand[i+1].getValue() == pokerHand[i+2].getValue() &&
				pokerHand[i+3].getValue() == pokerHand[i+4].getValue())){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isStraight(){
		
		for(int i = 0; i<1; i++){
			if((pokerHand[i].getValue()+1) == pokerHand[i+1].getValue() && 
			   (pokerHand[i+1].getValue()+1) == pokerHand[i+2].getValue() &&
			   (pokerHand[i+2].getValue()+1) == pokerHand[i+3].getValue() &&
			   (pokerHand[i+3].getValue()+1) == pokerHand[i+4].getValue()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isFlush(){

		for(int i = 0; i<1; i++){
			if(pokerHand[i].getSuit() == pokerHand[i+1].getSuit() && 
			   pokerHand[i+1].getSuit() == pokerHand[i+2].getSuit() &&
			   pokerHand[i+2].getSuit() == pokerHand[i+3].getSuit() &&
			   pokerHand[i+3].getSuit() == pokerHand[i+4].getSuit()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isStraightFlush(){
		
		for(int i = 0; i<1; i++){
			if(pokerHand[i].getSuit() == pokerHand[i+1].getSuit() && 
			   pokerHand[i+1].getSuit() == pokerHand[i+2].getSuit() &&
			   pokerHand[i+2].getSuit() == pokerHand[i+3].getSuit() &&
			   pokerHand[i+3].getSuit() == pokerHand[i+4].getSuit() &&
			  (pokerHand[i].getValue()+1) == pokerHand[i+1].getValue() && 
			  (pokerHand[i+1].getValue()+1) == pokerHand[i+2].getValue() &&
			  (pokerHand[i+2].getValue()+1) == pokerHand[i+3].getValue() &&
			  (pokerHand[i+3].getValue()+1) == pokerHand[i+4].getValue()){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public boolean isRoyalFlush(){
		
		for(int i = 0; i<1; i++){
			if(pokerHand[i].getSuit() == pokerHand[i+1].getSuit() && 
			   pokerHand[i+1].getSuit() == pokerHand[i+2].getSuit() &&
			   pokerHand[i+2].getSuit() == pokerHand[i+3].getSuit() &&
			   pokerHand[i+3].getSuit() == pokerHand[i+4].getSuit() &&
			  (pokerHand[i].getValue()==1) && (pokerHand[i+1].getValue()==10) &&
			  (pokerHand[i+2].getValue()==11) && (pokerHand[i+3].getValue()==12) &&
			  (pokerHand[i+4].getValue()==13)){
				return true;
			}else{return false;}
		}
		return false;
	}
	
	public int shouldDiscard(int cardPosition){
		//Returns an int that represents the probability that you should keep
		//or discard a certain card from your hand with 0 meaning definitely
		//keep and 100 meaning definitely discard.
		
		//Most of the if statements rely on the fact that if the cards are sorted, they can 
		//only be in certain positions of the array eg. for a four-of-a-kind, the four same
		//value cards must be situated side by side, and so the only elements of the
		//array that the odd card could be in are the first and last.
		
		if (cardPosition < 0 || cardPosition > 4)
			throw new IllegalArgumentException("Illegal card position");
		
		//Strong hands that rely on all five cards, where you will wish to discard
		//nothing.
		if(isFlush() || isStraight() || isStraightFlush() || isRoyalFlush() ){
			return 0;
		}
		
		//If the first card or last card in your hand is busting a straight then it 
		//should definitely be discarded.
		if((cardPosition == 0 && 
		   (pokerHand[cardPosition].getValue()+1) != pokerHand[cardPosition+1].getValue() && 
		   (pokerHand[cardPosition+1].getValue()+1) == pokerHand[cardPosition+2].getValue() &&
		   (pokerHand[cardPosition+2].getValue()+1) == pokerHand[cardPosition+3].getValue() &&
		   (pokerHand[cardPosition+3].getValue()+1) == pokerHand[cardPosition+4].getValue()) ||
		   (cardPosition == 4 && 
		   (pokerHand[cardPosition].getValue()-1) != pokerHand[cardPosition-1].getValue() && 
		   (pokerHand[cardPosition-1].getValue()-1) == pokerHand[cardPosition-2].getValue() &&
		   (pokerHand[cardPosition-2].getValue()-1) == pokerHand[cardPosition-3].getValue() &&
		   (pokerHand[cardPosition-3].getValue()-1) == pokerHand[cardPosition-4].getValue())
		   ){
			return 100;
		}
		
		//If one of the cards in your hand is busting a flush then it should definitely be
		//discarded.
		if((cardPosition == 0 && 
		   pokerHand[cardPosition].getSuit() != pokerHand[cardPosition+1].getSuit() && 
		   pokerHand[cardPosition+1].getSuit() == pokerHand[cardPosition+2].getSuit() &&
		   pokerHand[cardPosition+2].getSuit() == pokerHand[cardPosition+3].getSuit() &&
		   pokerHand[cardPosition+3].getSuit() == pokerHand[cardPosition+4].getSuit()) ||
		   (cardPosition == 1 && 
		   pokerHand[cardPosition].getSuit() != pokerHand[cardPosition-1].getSuit() && 
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition+1].getSuit() &&
		   pokerHand[cardPosition+1].getSuit() == pokerHand[cardPosition+2].getSuit() &&
		   pokerHand[cardPosition+2].getSuit() == pokerHand[cardPosition+3].getSuit()) ||
		   (cardPosition == 2 && 
		   pokerHand[cardPosition].getSuit() != pokerHand[cardPosition-1].getSuit() && 
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition-2].getSuit() &&
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition+1].getSuit() &&
		   pokerHand[cardPosition+1].getSuit() == pokerHand[cardPosition+2].getSuit()) ||
		   (cardPosition == 3 && 
		   pokerHand[cardPosition].getSuit() != pokerHand[cardPosition-1].getSuit() && 
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition-2].getSuit() &&
		   pokerHand[cardPosition-2].getSuit() == pokerHand[cardPosition-3].getSuit() &&
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition+1].getSuit()) ||
		   (cardPosition == 4 && 
		   pokerHand[cardPosition].getSuit() != pokerHand[cardPosition-1].getSuit() && 
		   pokerHand[cardPosition-1].getSuit() == pokerHand[cardPosition-2].getSuit() &&
		   pokerHand[cardPosition-2].getSuit() == pokerHand[cardPosition-3].getSuit() &&
		   pokerHand[cardPosition-3].getSuit() == pokerHand[cardPosition-4].getSuit())
		   ){
			return 100;
		}
		
		//For a full house, one of the two cards making up the pair would rarely be discarded.
		if((cardPosition == 0 && isFullHouse() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition+2].getValue()) || (cardPosition == 1 && isFullHouse() && 
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition+1].getValue()) || 
			(cardPosition == 3 && isFullHouse() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition-1].getValue()) || (cardPosition == 4 && isFullHouse() && 
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition-2].getValue())
		    ){
			 return 10;
		}
		
		//The odd card out in a four-of-a-kind can be kept or discarded to confuse other
		//players.
		if((cardPosition == 0 && isFourOfAKind() && pokerHand[cardPosition].getValue() 
		    != pokerHand[cardPosition+1].getValue()) || (cardPosition == 4 && isFourOfAKind() && 
		    pokerHand[cardPosition].getValue() != pokerHand[cardPosition-1].getValue())){
			return 50;
		}
		
		//One of the odd cards out in a three-of-a-kind can be discarded for something better
		//most of the time.
		if((cardPosition == 0 && isThreeOfAKind() && pokerHand[cardPosition].getValue() 
			!= pokerHand[cardPosition+1].getValue()) || (cardPosition == 1 && isThreeOfAKind() && 
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition+1].getValue()) || 
			(cardPosition == 3 && isThreeOfAKind() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition-1].getValue()) || (cardPosition == 4 && isThreeOfAKind() && 
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition-1].getValue())){
			return 80;
			}
		
		//The odd card out in a two-pair can be can be discarded the majority of the time
		//for something better such as a full-house.
		if((cardPosition == 0 && isTwoPair() && pokerHand[cardPosition].getValue() 
			!= pokerHand[cardPosition+1].getValue()) || (cardPosition == 2 && isTwoPair() && 
			(pokerHand[cardPosition].getValue() != pokerHand[cardPosition+1].getValue() &&
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition-1].getValue())) || 
			(cardPosition == 4 && isTwoPair() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition-1].getValue())){
			return 90;
			}
		
		//If the card is not part of the pair in a one-pair hand then it should be discarded
		//for something better most of the time.
		if((cardPosition == 0 && isOnePair() && pokerHand[cardPosition].getValue() 
			!= pokerHand[cardPosition+1].getValue()) || (cardPosition == 1 && isOnePair() && 
			(pokerHand[cardPosition].getValue() != pokerHand[cardPosition+1].getValue() &&
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition-1].getValue())) || 
			(cardPosition == 2 && isOnePair() && (pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition+1].getValue() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition-1].getValue())) || (cardPosition == 3 && isOnePair() && 
			(pokerHand[cardPosition].getValue() != pokerHand[cardPosition+1].getValue() &&
			pokerHand[cardPosition].getValue() != pokerHand[cardPosition-1].getValue())) ||
			(cardPosition == 0 && isOnePair() && pokerHand[cardPosition].getValue() != 
			pokerHand[cardPosition-1].getValue())) {
			return 90;
			}
		
		//If you have nothing in your hand then the card should definitely be discarded for
		//something better.
		if(!isOnePair() && !isTwoPair() && !isThreeOfAKind() && !isFourOfAKind() && 
		   !isFullHouse() && !isStraight() && !isFlush() && !isStraightFlush() &&
		   !isRoyalFlush()){
			return 100;
		}
		
		//If the card is an ace, you may wish to keep it some of the time.
		if(pokerHand[cardPosition].getValue()==1){
			return 50;
		}
		
		return 0;
	}
}
