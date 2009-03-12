
public class test {

	public static void main(String[] args){
		
		DeckOfCards deck = new DeckOfCards();
		deck.shuffle();
		
		for(int i = 0; i < 1; i++){
			PokerHand poker = new PokerHand();
			if(!poker.isFlush()){System.out.println("hello");}
			System.out.println(poker.pokerHand[i].getValue());
		}
		
	}
}
