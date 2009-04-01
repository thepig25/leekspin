import java.util.Random;


public class pokerGameMethods extends Pack {

	
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


public Player winner(Player [] involvedPlayers, PokerHand [] possibleWinners){ // this takes in an array of pokerHands (which are the best 5 cards of a player) and determines the winning player
	
	PokerHand[] localpossibleWinners = possibleWinners;
	
	int bestBoolean=10;
	for(int i=0;i<localpossibleWinners.length;i++){
		if(localpossibleWinners[i].testBooleans()<bestBoolean){ //go through pokerCard array and find best hand
			bestBoolean=localpossibleWinners[i].testBooleans();
		}
	}
	
	//PokerHand[] finalists = new PokerHand[involvedPlayers.length];
	int finalistsHighCardCount=0;
	int winningPlayer=0;
	
	for(int i=0;i<localpossibleWinners.length;i++){ // now need to check for any other players with the same hand and use the highest card to determine the winner
		if(localpossibleWinners[i].testBooleans()==bestBoolean && localpossibleWinners[i].getHighCard().getValue()>finalistsHighCardCount){
			finalistsHighCardCount = localpossibleWinners[i].getHighCard().getValue();
			winningPlayer=i;
		}
	}
	
	return involvedPlayers[winningPlayer];
	
}


	
}

