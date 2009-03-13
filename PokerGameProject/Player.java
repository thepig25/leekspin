
public class Player extends PokerHand {

	int decision;
	int bet=0;
	int currentMoney;
	Card[] thisHand = new Card[7];
	int cardCount=0;

	
	public player(String Name, int Chips, Card[] currentHand){
		Chips = currentMoney;
	}
	
	// this takes ah array of dealt cards then adds them to the
	// players hand. using cardCount to make sure its in the 
	// position
	public Card[] receiveCards(Card[] received){
		Card[] temp = received;
		for(int i = 0,i <temp.length,i++){
			thisHand[cardCound] = temp[i];
			cardCount++;
		}
	}
	
	
	public void printHand(){
	
		for(int i = 0, i < thisHand.length, i++){
			System.out.println(thisHand[i].getValueAsString()+ " of " + thisHand[i].getSuitAsString());

		}
	}
	
	
	public void fold(){
		//When a player folds, they lose the current hand, and their money in the
		//pot goes to the winner of the hand.
	}
	
	public void raise(float amount_raised){
		//A player can raise by any amount, on their turn pre-flop, after the flop,
		//after the turn, or after the river, whereby the the other players must call,
		//raise or fold.
	}
	
	public void check(){
		//A player can check on their turn without betting if no bet has been placed
		//yet.
	}
	
	public void call(float amount_checked){
		//A player calls by matching the amount raised by the previous player.
	}
	
	
// Don't think these methods are necessary?!
/*	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision // 0=fold,1=check/call, 2=raise
	}
	
	public int getBet(){
		if(decision!=0){
			return bet;
		}
		
	}
	
	private makeDecision(thisHand){ // where we decide what to do - keep private!
		
	}
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet;
	}*/
}
