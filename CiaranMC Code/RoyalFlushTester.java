package cards;

public class RoyalFlushTester {
	
	public static DeckOfCards deck = new DeckOfCards();
	public static PokerHand ph;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ph = new PokerHand(deck);
		if(ph.isRoyalFlush()==true){
			System.out.print("RoyalFlush: 1!");
			System.exit(0);
		}
		deck = new DeckOfCards();
		int j = 0;
		while(ph.isRoyalFlush()==false){
			if(j==10){
				deck = new DeckOfCards();
				j=0;
			}
			j++;
			ph = new PokerHand(deck);
			if(ph.isRoyalFlush()==true){
				long endTime = System.currentTimeMillis();
				long time = (endTime - startTime)/1000;
				System.out.print("RoyalFlush! in only "+time+" seconds.");
				System.exit(0);
			}	
		}
	}
}
