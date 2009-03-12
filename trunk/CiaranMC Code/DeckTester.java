package cards;

/**
 * Ciaran McElhatton - 05365791
 */
public class DeckTester {

	public static void main(String[] args) {

		DeckOfCards deck = new DeckOfCards();
		PlayingCard[] p = new PlayingCard[52];
		p = deck.dealCards(p);
		for(int i=0; i<52; i++){
			System.out.println(p[i].getSimpleName() + " of " + p[i].getSuit() + ".");
		}
		deck.shuffle();
		p = deck.dealCards(p);
		System.out.println();
		for(int i=0; i<52; i++){
			System.out.println(p[i].getSimpleName() + " of " + p[i].getSuit() + ".");
		}
	}

}
