
public class Player extends Pack {

	int decision;
	int bet=0;
	int currentMoney;
	Card[] thisHand = new Card[7];
	Card[] playerHand = new Card[2];
	Card[] communityCards = new Card[3];
	int cardCount=0;
	int cardPocketCount=0;
	

	
	public Player(String Name, int Chips, Card[] currentHand){
		
	}
	
	// this takes ah array of dealt cards then adds them to the
	// players hand. using cardCount to make sure its in the 
	// position
	public void receiveCommunityCards(Card[] received){
		
		if( cardCount >= 3){
			Card[] tempCopy = new Card[(communityCards.length)];
			System.arraycopy(communityCards, 0, tempCopy, 0, tempCopy.length);
			communityCards = new Card[(communityCards.length+1)];
			System.arraycopy(tempCopy, 0,communityCards , 0, communityCards.length);
		}
		
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			communityCards[cardCount] = temp[i];
			cardCount++;
		}
		
		
	}
	
	// this takes ah array of dealt cards then adds them to the
	// players hand.
	// This is just for the first two pocket cards a player gets
	public void receivePocketCards(Card[] received){
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			playerHand[cardCount] = temp[i];
			cardPocketCount++;
		}
	}
	
	
	public void printPocketHand(){
	
		for(int i = 0; i < 2; i++){
			System.out.println(playerHand[i].getValueAsString()+ " of " + playerHand[i].getSuitAsString());

		}
	}
	
	public void printHand(){
		
		for(int i = 0; i < 5; i++){
			System.out.println("community Cards are :" +communityCards[i].getValueAsString()+ " of " + communityCards[i].getSuitAsString());

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
	
	public int checkMoney(){
		return currentMoney;
		
	}
	
}
