import java.util.Random;


public class pokerGameMethods extends Pack {

	int bestBoolean=10;
	int currentBet=0;
	private int potTotal;
	private Player [] involvedPlayers;
	private int [] intInvolvedPlayers;
	
public int chooseFirstDealer(int amtPlayers){
		
		Random rand = new Random();
		int min=0;
		int max=amtPlayers;

		
		int randomNum = rand.nextInt(max - min ) + min;

		
		return randomNum;
		
	}

public int chooseNextDealer(int amtPlayers, int currentDealer){
	
	if(currentDealer==amtPlayers-1){ //if current dealer is last person in player array, go back to the first person
		return 0;
	}
	else{
		return currentDealer+1; // otherwise dealer is previous dealer +1
	}
}

public int returnPot(){
	return potTotal;
}

public Player [] winner(Player [] involvedPlayers, PokerHand [] possibleWinners){ // this takes in an array of pokerHands (which are the best 5 cards of a player) and determines the winning player
	
	PokerHand[] localpossibleWinners = possibleWinners;
	
	System.out.println("Cards passed for player 1 in are: ");
	for(int i=0;i<localpossibleWinners[0].mySortedCards.length;i++){
		System.out.println(localpossibleWinners[0].mySortedCards[i].getValueAsString()+" "+localpossibleWinners[0].mySortedCards[i].getSuitAsString());
	}
	
	System.out.println("Cards passed for player 2 in are: ");
	for(int i=0;i<localpossibleWinners[1].mySortedCards.length;i++){
		System.out.println(localpossibleWinners[1].mySortedCards[i].getValueAsString()+" "+localpossibleWinners[1].mySortedCards[i].getSuitAsString());
	}
	
	
	
	for(int i=0;i<localpossibleWinners.length;i++){
		if(localpossibleWinners[i].testBooleans()<bestBoolean){ //go through pokerCard array and find best hand
			bestBoolean=localpossibleWinners[i].testBooleans();
			
		}
	}
	
	//PokerHand[] finalists = new PokerHand[involvedPlayers.length];
	int finalistsHighCardCount=0;
	int winningPlayer=0;
	
	int [] intWinners = new int[involvedPlayers.length];
	Player [] playerWinners = new Player[involvedPlayers.length];
	
	for(int i=0;i<localpossibleWinners.length;i++){ // now need to check for any other players with the same hand and use the highest card to determine the winner
		if(localpossibleWinners[i].testBooleans()==bestBoolean && (localpossibleWinners[i].getHighCard().getValue()>=finalistsHighCardCount||localpossibleWinners[i].getHighCard().getValue()==0)){
			finalistsHighCardCount = localpossibleWinners[i].getHighCard().getValue();
			
		}
	}
	
	for(int i=0;i<localpossibleWinners.length;i++){ // need to check for split pots
		if(localpossibleWinners[i].testBooleans()==bestBoolean && localpossibleWinners[i].getHighCard().getValue()==finalistsHighCardCount){
			int temp =i;
			intWinners[i]=temp;
			
		}
	}
	
	for(int i=0;i<localpossibleWinners.length;i++){
		int temp2=intWinners[i];
		playerWinners[i]=involvedPlayers[temp2];
	}
	
	
	return playerWinners;
	
}

public void bettingRound(Player [] tempPlayers){
	setPlayers(tempPlayers);
	getHighestBet();
	setPot();
}


public void setPlayers(Player [] players){
	involvedPlayers = new Player[players.length];
	intInvolvedPlayers = new int[players.length];
	involvedPlayers=players;
}
public Player [] getInvolvedPlayers(){
	Player [] tempInvolvedPlayers = new Player[intInvolvedPlayers.length];
	int tempInvolvedPlayersCount =0;
	for(int i=0;i<involvedPlayers.length;i++){
		for(int j=0;j<involvedPlayers.length;j++){
			if(intInvolvedPlayers[i]==involvedPlayers[j].playerID){
				tempInvolvedPlayers[tempInvolvedPlayersCount]=involvedPlayers[j];
				tempInvolvedPlayersCount++;
		}
		
			
		}
	}
	return tempInvolvedPlayers;
}

public void getBetAndPot(){
	getHighestBet();
	setPot();
}

public int getHighestBet(){
	
	//currentBet=involvedPlayers[0].getBet();
	//addToPot(currentBet);
	
	for(int i=0;i<involvedPlayers.length;i++){
		if(involvedPlayers[i].getBet(currentBet)>currentBet){ // go back to first player if bet has increased
			currentBet=involvedPlayers[i].getBet(currentBet);
			getHighestBet();
		}
	}
	return currentBet;
}

private void setPot(){
	//int [] tempInvolvedPlayers = new int[involvedPlayers.length];
	int tempInvolvedPlayersCount=0;
	for(int i=0;i<involvedPlayers.length;i++){
		if(involvedPlayers[i].getBet(currentBet)==currentBet){ // find players who are matching the current bet and get their ID
			int temp=involvedPlayers[i].playerID;
			System.out.println(temp);
			intInvolvedPlayers[tempInvolvedPlayersCount] = temp;
			tempInvolvedPlayersCount++;
			addToPot(involvedPlayers[i].getBet(currentBet));
		}
	}
	
}

private void addToPot(int playerBet){
	potTotal = potTotal + playerBet;
}
}

