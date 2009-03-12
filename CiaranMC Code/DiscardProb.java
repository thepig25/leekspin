package cards;

/**
 * Ciaran McElhatton - 05365791
 */
public class DiscardProb{

	public static PokerHand ph;
	public static DeckOfCards deck = new DeckOfCards();
	
	public static int royalFlush = 9;
	public static int straightFlush = 8;
	public static int fourOfAKind = 7;
	public static int fullHouse = 6;
	public static int flush = 5;
	public static int straight = 4;
	public static int threeOfAKind = 3;
	public static int twoPair = 2;
	public static int pair = 1;
	
	/*
	 * first three numbers are the number of times this hand is improved
	 * first: one card swapped
	 * second: two cards swapped
	 * third: three cards swapped
	 * fourth number is the no. of times this hand is delt.
	 * prob = (one of the first three no.s)/(fourth number).
	 */
	public static int[] _royalFlush = {0,0,0,0};
	public static int[] _straightFlush = {0,0,0,0};
	public static int[] _fourOfAKind = {0,0,0,0};
	public static int[] _fullHouse = {0,0,0,0};
	public static int[] _flush = {0,0,0,0};
	public static int[] _straight = {0,0,0,0};
	public static int[] _threeOfAKind = {0,0,0,0};
	public static int[] _twoPair = {0,0,0,0};
	public static int[] _pair = {0,0,0,0};
	
	public static void main(String[] args) {
		for(int i=0,j=0; i<100; i++,j++){
			// To deal a new deck before previous deck becomes empty.
			if(j==5){	// set to 5 to allow for the extra cards being delt (exchanged).
				deck = new DeckOfCards();
				j=0;
			}
			int cur = 0;
			int next = 0;
			ph = new PokerHand(deck);
			if(ph.isRoyalFlush()==true){
				cur = royalFlush;
				_royalFlush[0] = 7;
			}else{
				if(ph.isStraightFlush()==true){
					cur = straightFlush;
				}else{
					if(ph.isFourOfAKind()==true){
						cur = fourOfAKind;
					}else{
						if(ph.isFullHouse()==true){
							cur = fullHouse;
						}else{
							if(ph.isFlush()==true){
								cur = flush;
							}else{
								if(ph.isStraight()==true){
									cur = straight;
								}else{
									if(ph.isThreeOfAKind()==true){
										cur = threeOfAKind;
									}else{
										if(ph.isTwoPair()==true){
											cur = twoPair;
										}else{
											if(ph.isOnePair()==true){
												cur = pair;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			for(int x=0; x<3; x++){
				int discard = 0;
				for(int k=0; k<5; k++){
					if(ph.shouldDiscard(k)>discard){
						discard = k;
					}
				}
				ph.hand[discard] = deck.dealCard();
				if(ph.isRoyalFlush()==true){
					next = royalFlush;
				}else{
					if(ph.isStraightFlush()==true){
						next = straightFlush;
					}else{
						if(ph.isFourOfAKind()==true){
							next = fourOfAKind;
						}else{
							if(ph.isFullHouse()==true){
								next = fullHouse;
							}else{
								if(ph.isFlush()==true){
									next = flush;
								}else{
									if(ph.isStraight()==true){
										next = straight;
									}else{
										if(ph.isThreeOfAKind()==true){
											next = threeOfAKind;
										}else{
											if(ph.isTwoPair()==true){
												next = twoPair;
											}else{
												if(ph.isOnePair()==true){
													next = pair;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(next>cur){
					
				}
			}
		}
	}
}
