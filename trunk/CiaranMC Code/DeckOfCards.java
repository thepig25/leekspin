package cards;
import java.util.Random;

/**
 * Ciaran McElhatton - 05365791
 */
public class DeckOfCards {

	PlayingCard deck[] = new PlayingCard[52];
	public int next;
	PlayingCard[] temp = new PlayingCard[52];
	
	public DeckOfCards(){
		next = 0;
		newSuit(0,"clubs");
		newSuit(13,"diamonds");
		newSuit(26,"hearts");
		newSuit(39,"spades");
		shuffle();
	}
	
	public void newSuit(int no, String s){
		int i = no;
		String suit = s;
		deck[i] = new PlayingCard(suit,"ace",1);
		deck[i+1] = new PlayingCard(suit,"two",2);
		deck[i+2] = new PlayingCard(suit,"three",3);
		deck[i+3] = new PlayingCard(suit,"four",4);
		deck[i+4] = new PlayingCard(suit,"five",5);
		deck[i+5] = new PlayingCard(suit,"six",6);
		deck[i+6] = new PlayingCard(suit,"seven",7);
		deck[i+7] = new PlayingCard(suit,"eight",8);
		deck[i+8] = new PlayingCard(suit,"nine",9);
		deck[i+9] = new PlayingCard(suit,"ten",10);
		deck[i+10] = new PlayingCard(suit,"jack",10);
		deck[i+11] = new PlayingCard(suit,"queen",10);
		deck[i+12] = new PlayingCard(suit,"king",10);
	}
	
	public PlayingCard dealCard(){
		return deck[next++];
	}
	
	public PlayingCard[] dealCards(PlayingCard[] p){
		for(int i=0; i<p.length; i++){
			p[i] = dealCard();
		}
		return p;
	}
	
	public void shuffle(){
		for(int i=0; i<52; i++){
			temp[i] = new PlayingCard("","",0);
		}
		for(int i=0; i<52; i++){
			temp[getFreeSlot()] = deck[i];
		}
		for(int i=0; i<52; i++){
			deck[i] = temp[i];
		}
		next = 0;
	}
	
	public int getFreeSlot(){
		Random r = new Random();
		int y = r.nextInt(52);
		while(temp[y].getValue() != 0){
			y = r.nextInt(52);
		}
		return y;
	}
}
