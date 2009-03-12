
public class Player extends PokerHand {

	int decision;
	int bet=0;
	int currentMoney;
	Card[] thisHand = new Card[7];
	int cardCount=0;

	
	public Player(String Name, int Chips, Card[] currentHand){
		
	}
	
	// this takes ah array of dealt cards then adds them to the
	// players hand. using cardCount to make sure its in the 
	// position
	public void receiveCards(Card[] received){
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			thisHand[cardCount] = temp[i];
			cardCount++;
		}
	}
	
	
	public void printHand(){
	
		for(int i = 0; i < thisHand.length; i++){
			System.out.println(thisHand[i].getValueAsString()+ " of " + thisHand[i].getSuitAsString());

		}
	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  decision; // 0=fold,1=check/call, 2=raise
	}
	
	public int getBet(){
		if(decision!=0){
			return bet;
		}
		return bet;
		
	}
	
	//private makeDecision(thisHand){ // where we decide what to do - keep private!
		
//	}
	
	private int makeBet(){ // where we decide what to bet(if any) - keep private!
		return bet;
	}
}
