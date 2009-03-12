package cards;

/**
 * Ciaran McElhatton - 05365791
 */
public class ProbabilityTester {

	public static PokerHand ph;
	public static DeckOfCards deck = new DeckOfCards();

	public static int RoyalFlush = 0;
	public static int StraightFlush = 0;
	public static int FourOfAKind = 0;
	public static int FullHouse = 0;
	public static int Flush = 0;
	public static int Straight = 0;
	public static int ThreeOfAKind = 0;
	public static int TwoPair = 0;
	public static int OnePair = 0;
	
	public static void main(String[] args) {
		for(int i=0,j=0; i<10000; i++,j++){
			// To deal a new deck before previous deck becomes empty.
			if(j==10){
				deck = new DeckOfCards();
				j=0;
			}
			ph = new PokerHand(deck);
			if(ph.isRoyalFlush()==true){
				RoyalFlush++;
			}else{
				if(ph.isStraightFlush()==true){
					StraightFlush++;
				}else{
					if(ph.isFourOfAKind()==true){
						FourOfAKind++;
					}else{
						if(ph.isFullHouse()==true){
							FullHouse++;
						}else{
							if(ph.isFlush()==true){
								Flush++;
							}else{
								if(ph.isStraight()==true){
									Straight++;
								}else{
									if(ph.isThreeOfAKind()==true){
										ThreeOfAKind++;
									}else{
										if(ph.isTwoPair()==true){
											TwoPair++;
										}else{
											if(ph.isOnePair()==true){
												OnePair++;
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
		System.out.println("RoyalFlush: ("+RoyalFlush+"/10000)");
		System.out.println("StraightFlush: ("+StraightFlush+"/10000)");
		System.out.println("FourOfAKind: ("+FourOfAKind+"/10000)");
		System.out.println("FullHouse: ("+FullHouse+"/10000)");
		System.out.println("Flush: ("+Flush+"/10000)");
		System.out.println("Straight: ("+Straight+"/10000)");
		System.out.println("ThreeOfAKind: ("+ThreeOfAKind+"/10000)");
		System.out.println("TwoPair: ("+TwoPair+"/10000)");
		System.out.println("OnePair: ("+OnePair+"/10000)");
	}
}
	
