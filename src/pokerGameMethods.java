import java.util.Random;


public class pokerGameMethods extends Pack {

    int bestBoolean=10;
    int currentBet=0;
    private int potTotal;
    private Player [] involvedPlayers;
    private int [] intInvolvedPlayers;
    private int BlindLevel=1;
    int currentMoney;
    int HighCard=0;
    int winningPlayer=0;
    int firstPair=0;
    int secondPair=0;
    int temp;
    int handCounter=0;
   
   
    public void dealPocketCard(Pack myPack, Player [] positions){
        Card[] deal;
        /*for(int i=0;i<positions.length;i++){
            deal = myPack.dealOneCardArray();
            positions[i].receivePocketCards(deal);
        }
        for(int i=0;i<positions.length;i++){
            deal = myPack.dealOneCardArray();
            positions[i].receivePocketCards(deal);
        }*/
        deal = myPack.getFirstPocketSpecial();
        positions[0].receivePocketCards(deal);
        deal = myPack.getSecondPocketSpecial();
        positions[1].receivePocketCards(deal);
        deal = myPack.getThirdPocketSpecial();
        positions[0].receivePocketCards(deal);
        deal = myPack.getFourthPocketSpecial();
        positions[1].receivePocketCards(deal);
       
    }
   
    public void dealFlopCommunityCards(Pack myPack, Player [] positions){
        //Card[] deal = myPack.dealThreeCardArray();
    	Card[] deal = myPack.getCommmunitySpecial();
        for(int i=0;i<positions.length;i++){
            positions[i].receiveCommunityCards(deal);
        }
    }
   
    public void dealTurn(Pack myPack, Player [] positions){
        //Card[] deal = myPack.dealOneCardArray();
    	Card[] deal = myPack.getTurnSpecial();
        int printCount=0;
        for(int i=0;i<positions.length;i++){
           
            positions[i].receiveCommunityCards(deal);
            if(printCount<1){
                System.out.println("Turn card is a "+deal[i].getValueAsString()+" of "+deal[i].getSuitAsString());
                printCount++;
            }
        }
       
    }
   
    public void dealRiver(Pack myPack, Player [] positions){
    	Card[] deal = myPack.getRiverSpecial();
    	//Card[] deal = myPack.dealOneCardArray();
        int printCount=0;
        for(int i=0;i<positions.length;i++){
           
            positions[i].receiveCommunityCards(deal);
            if(printCount<1){
                System.out.println("River card is a "+deal[i].getValueAsString()+" of "+deal[i].getSuitAsString());
                printCount++;
            }
        }
    }
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

//this takes in an array of pokerHands (which are the best 5 cards of a player) and determines the winning player
public Player [] winner(Player [] involvedPlayers){
   
   
    System.out.println("Cards passed for player"+ involvedPlayers[0].getName()+" are: "); // print statements
    involvedPlayers[0].showBestHand();
   
    System.out.println("Cards passed for player"+ involvedPlayers[1].getName()+" are: "); // print statements
    involvedPlayers[1].showBestHand();
   
   
   
    for(int i=0;i<involvedPlayers.length;i++){ //go through pokerCard array and find best hand type

        if(involvedPlayers[i].getBestPokerHand().testBooleans()<bestBoolean){ 
            bestBoolean=involvedPlayers[i].getBestPokerHand().testBooleans();
            System.out.println("Best boolean is:"+bestBoolean);
        }
    }
    Player [] finalWinners = new Player[0];
   
   
    for(int i=0;i<involvedPlayers.length;i++){ // now need to check for any other players with the same hand. Then use the highest card to determine the winner
    	
        
        if(involvedPlayers[i].getBestPokerHand().testBooleans()==bestBoolean){
        	
        	
            if(bestBoolean==9){
                if(involvedPlayers[i].getBestPokerHand().getHighCard().getValue()>HighCard){
                    
                        HighCard = involvedPlayers[i].getBestPokerHand().getHighCard().getValue();
                    
                }
            }
           
            if(bestBoolean==8){
            	
                if(involvedPlayers[i].highestFirstPair>firstPair){

                        firstPair = involvedPlayers[i].highestFirstPair;
                }
            }
           
            if(bestBoolean==7){
                if(involvedPlayers[i].highestFirstPair>firstPair){
                	firstPair=involvedPlayers[i].getBestPokerHand().firstMatch;
                	
                	if((involvedPlayers[i].highestSecondPair>secondPair)){
                		secondPair=involvedPlayers[i].getBestPokerHand().highestSecondPair;
                	}
                        
                        
                }
            }
           
           
           
        }
    }
    int count=0;
   
    for(int i=0;i<involvedPlayers.length;i++){ // need to check for split pots
       
        //pair
       
        if(involvedPlayers[i].getBestPokerHand().testBooleans()==bestBoolean){
           
            if((bestBoolean==9&&involvedPlayers[i].getBestPokerHand().getHighCard().getValue()==HighCard)){
            	Player[] tempCopy = new Player[(finalWinners.length)];
                System.arraycopy(finalWinners, 0, tempCopy, 0, tempCopy.length);
                finalWinners = new Player[(finalWinners.length+1)];
                System.arraycopy(tempCopy, 0,finalWinners , 0, tempCopy.length);
            	finalWinners[count]=involvedPlayers[i];
            	 count++;
            }
            
           
            if((bestBoolean==8&&involvedPlayers[i].highestFirstPair==firstPair)){
            	Player[] tempCopy = new Player[(finalWinners.length)];
                System.arraycopy(finalWinners, 0, tempCopy, 0, tempCopy.length);
                finalWinners = new Player[(finalWinners.length+1)];
                System.arraycopy(tempCopy, 0,finalWinners , 0, tempCopy.length);
            	finalWinners[count]=involvedPlayers[i];
            	 count++;
            }
            
           
            if(bestBoolean==7&&involvedPlayers[i].highestFirstPair==firstPair&&involvedPlayers[i].getBestPokerHand().highestSecondPair==secondPair){
            
            		Player[] tempCopy = new Player[(finalWinners.length)];
                    System.arraycopy(finalWinners, 0, tempCopy, 0, tempCopy.length);
                    finalWinners = new Player[(finalWinners.length+1)];
                    System.arraycopy(tempCopy, 0,finalWinners , 0, tempCopy.length);
                	finalWinners[count]=involvedPlayers[i];
                    count++;
            	
            	
            }
            
            if (bestBoolean<7){
            	Player[] tempCopy = new Player[(finalWinners.length)];
                System.arraycopy(finalWinners, 0, tempCopy, 0, tempCopy.length);
                finalWinners = new Player[(finalWinners.length+1)];
                System.arraycopy(tempCopy, 0,finalWinners , 0, tempCopy.length);
            	finalWinners[count]=involvedPlayers[i];
            	 count++;
            }
           
        }

    }
    
    if(finalWinners.length>1){ // now need to check when there is a split pot, can we determine 1 winner
    
    }
    
    
    System.out.println("length"+finalWinners.length);
   
   
    return finalWinners;
   
}

/*private Player getWinnerRecursively(Player [] splitPotPlayers){

	int downCounter=4;
	
	if(bestBoolean==9){
		if(splitPotPlayers.length==4){
			
		if(splitPotPlayers[i].getBestHand()[downCounter].getValue()>
	}
	
	
	}
}
*/
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

public void checkPlayerMoney(Player [] positions){
    for (int i=0;i<positions.length;i++){
        System.out.println(positions[i].checkMoney());
    }
   
}

private void addToPot(int playerBet){
    potTotal = potTotal + playerBet;
}

public void rewardWinners(Player [] winners, int total){
    int individualAmount = total/winners.length;
    for (int i=0;i<winners.length;i++){
        winners[i].rewardPlayer(individualAmount);
        System.out.println("Player "+winners[i].getName()+" now has "+winners[i].checkMoney());
    }
}

public void resetCounters(Player [] players){
    for (int i=0;i<players.length;i++){
        players[i].resetCounters();
    }
}


public void dealBlinds(int currentDealer, Player [] positions){

		if(handCounter ==10){
			BlindLevel++;
			handCounter=0;
		}
 
	positions[(currentDealer + 1) % positions.length].removeBlinds(BlindLevel * 10);
	addToPot(BlindLevel * 10);

	positions[(currentDealer + 2) % positions.length].removeBlinds(BlindLevel * 20);
	addToPot(BlindLevel * 20);
	

	handCounter++;
	}
public int incrementDealer(int currentDealer, Player [] positions){
	
	
		return (currentDealer + 1) % positions.length;

	
}

}