import java.util.Random;


public class pokerGameMethods extends Pack {

    int bestBoolean=10;
    int currentBet=0;
    private int potTotal;
    private Player [] involvedPlayers;
    private int [] intInvolvedPlayers;
    private int BlindLevel=1;
    private int HandLevel=1;
    int currentMoney;
    int HighCard=0;
    int winningPlayer=0;
    int firstPair=0;
    int secondPair=0;
    int temp;
   
   
    public void dealPocketCard(Pack myPack, Player [] positions){
        Card[] deal;
        for(int i=0;i<positions.length;i++){
            deal = myPack.dealOneCardArray();
            positions[i].receivePocketCards(deal);
        }
        for(int i=0;i<positions.length;i++){
            deal = myPack.dealOneCardArray();
            positions[i].receivePocketCards(deal);
        }
       
    }
   
    public void dealFlopCommunityCards(Pack myPack, Player [] positions){
        Card[] deal = myPack.dealThreeCardArray();
        for(int i=0;i<positions.length;i++){
            positions[i].receiveCommunityCards(deal);
        }
    }
   
    public void dealTurn(Pack myPack, Player [] positions){
        Card[] deal = myPack.dealOneCardArray();
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
        Card[] deal = myPack.dealOneCardArray();
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
public Player [] winner(Player [] involvedPlayers, PokerHand [] possibleWinners){
   
    PokerHand[] localpossibleWinners = possibleWinners;
   
    System.out.println("Cards passed for player 1 in are: "); // print statements
    for(int i=0;i<localpossibleWinners[0].mySortedCards.length;i++){// print statements
        System.out.println(localpossibleWinners[0].mySortedCards[i].getValueAsString()+" "+localpossibleWinners[0].mySortedCards[i].getSuitAsString());// print statements
    }
   
    System.out.println("Cards passed for player 2 in are: "); // print statements
    for(int i=0;i<localpossibleWinners[1].mySortedCards.length;i++){// print statements
        System.out.println(localpossibleWinners[1].mySortedCards[i].getValueAsString()+" "+localpossibleWinners[1].mySortedCards[i].getSuitAsString());// print statements
    }
   
   
   
    for(int i=0;i<localpossibleWinners.length;i++){
        if(localpossibleWinners[i].testBooleans()<bestBoolean){ //go through pokerCard array and find best hand
            bestBoolean=localpossibleWinners[i].testBooleans();
            System.out.println("Best boolean is:"+bestBoolean);
        }
    }
   
    //PokerHand[] finalists = new PokerHand[involvedPlayers.length];

   
    int [] intWinners = new int[0];
   
   
    for(int i=0;i<localpossibleWinners.length;i++){ // now need to check for any other players with the same hand. Then use the highest card to determine the winner
       
        // case for pairs
        /*if(localpossibleWinners[i].testBooleans()==bestBoolean&&bestBoolean==8&&(localpossibleWinners[i].firstMatch>finalistsHighCardCount||localpossibleWinners[i].firstMatch==0)){
            System.out.println("in 8 code");
            if(localpossibleWinners[i].firstMatch==0){
                finalistsHighCardCount=13;
            }
            else{
                finalistsHighCardCount = localpossibleWinners[i].firstMatch;
            }
        }*/
       
        if(localpossibleWinners[i].testBooleans()==bestBoolean){
           
            if(bestBoolean==9){
                if(localpossibleWinners[i].getHighCard().getValue()>HighCard||localpossibleWinners[i].getHighCard().getValue()==0){
                    if(localpossibleWinners[i].getHighCard().getValue()==0){
                        HighCard = 30; // ace is highest so can't touch it
                    }
                    else{
                        HighCard = localpossibleWinners[i].getHighCard().getValue();
                    }
                }
            }
           
            if(bestBoolean==8){
                if(localpossibleWinners[i].firstMatch>firstPair||localpossibleWinners[i].firstMatch==0){
                    if(localpossibleWinners[i].firstMatch==0){
                        firstPair = 30;
                    }
                    else{
                        firstPair = localpossibleWinners[i].firstMatch;
                    }
                   
                }
            }
           
            if(bestBoolean==7){
                if((localpossibleWinners[i].firstMatch>firstPair||localpossibleWinners[i].firstMatch==0)&&(localpossibleWinners[i].highestSecondPair>secondPair)){
                    if(localpossibleWinners[i].firstMatch==0){
                        firstPair=30;
                        secondPair=localpossibleWinners[i].highestSecondPair;
                    }
                    else{
                        firstPair=localpossibleWinners[i].firstMatch;
                        secondPair=localpossibleWinners[i].highestSecondPair;
                    }
                }
            }
           
           
           
        }
       
       
       
/*        if(localpossibleWinners[i].testBooleans()==bestBoolean && localpossibleWinners[i].testBooleans()!=8 && (localpossibleWinners[i].getHighCard().getValue()>=finalistsHighCardCount||localpossibleWinners[i].getHighCard().getValue()==0)){
            if(localpossibleWinners[i].getHighCard().getValue()==0){
                finalistsHighCardCount=13;
            }
            else{
                finalistsHighCardCount = localpossibleWinners[i].getHighCard().getValue();
            }
           
           
        }*/
    }
    int count=0;
   
    for(int i=0;i<localpossibleWinners.length;i++){ // need to check for split pots
       
        //pair
       
        if(localpossibleWinners[i].testBooleans()==bestBoolean){
           
            int[] tempCopy = new int[(intWinners.length)];
            System.arraycopy(intWinners, 0, tempCopy, 0, tempCopy.length);
            intWinners = new int[(intWinners.length+1)];
            System.arraycopy(tempCopy, 0,intWinners , 0, tempCopy.length);
           
           
           
           
            if(bestBoolean==9&&localpossibleWinners[i].getHighCard().getValue()==HighCard){
                temp =i;
                intWinners[i]=temp;
            }
           
            if(bestBoolean==8&&localpossibleWinners[i].firstMatch==firstPair){
                temp =i;
                intWinners[i]=temp;
            }
           
            if(bestBoolean==7&&localpossibleWinners[i].firstMatch==firstPair&&localpossibleWinners[i].highestSecondPair==secondPair){
                temp=i;
                intWinners[i]=temp;
            }

        }

    }
    System.out.println("length"+intWinners.length);
    Player [] playerWinners = new Player[intWinners.length];
    for(int i=0;i<intWinners.length;i++){
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
public int dealBlinds(int currentDealer, int pokerGame, int takeMoney, int HandLevel, Player [] positions){
	 
	//increase hands
	for(int i = 0, i < HandLevel, i++){
		if(inti ==10){
			blindLevel++;
		}
	}
	//if it is 10 hands
	//increase blindlevel
	
	//int smallBlind = currentMoney +
	positions[(currentDealer + 1) % 2].removeBlinds(BlindLevel * 10);
	//int bigBlind = currentMoney + 
	positions[(currentDealer + 2) % 2]. removeBlinds(BlindLevel * 20);
	

	//	for(int i = 0, i < dealBlinds, i++){
//		if(int i ==10){
//			BlindLevel++;
//			}						//counter for 10rounds so that the blinds will increse
//	
//		
//		}
	return smallBlind+bigBlind;
	}
}