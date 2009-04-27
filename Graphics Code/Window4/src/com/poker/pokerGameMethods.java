package com.poker;
import java.util.Random;

import android.os.Message;


public class pokerGameMethods extends Pack {

    int bestBoolean=10;
    int currentBet=20;
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
    
   public Player[] returnPlayersHack(){
	   return involvedPlayers;
   }
   
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
        /*deal = myPack.getFirstPocketSpecial();
        //receivePocketCards(deal);
        positions[0].receivePocketCards(deal); 					// Temp fix.
        deal = myPack.getSecondPocketSpecial();
        positions[1].receivePocketCards(deal);
        deal = myPack.getThirdPocketSpecial();
        positions[0].receivePocketCards(deal);
        deal = myPack.getFourthPocketSpecial();
        positions[1].receivePocketCards(deal);*/
        
        long startTime1 = System.currentTimeMillis();
        while(System.currentTimeMillis()<startTime1+600){
        	// waits for 600 milliseconds.
        }
       
        Message m = new Message();
        m.what = GameWindow.DRAWPLAYERCARDS;
        m.obj = (Card) (positions[0].playerHand[0]);
        m.arg1 = 1;
        GameWindow.myViewUpdateHandler.sendMessage(m);

        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis()<startTime+600){
        	// waits for 600 milliseconds.
        }
        
        Message m1 = new Message();
        m1.what = GameWindow.DRAWPLAYERCARDS;
        m1.obj = (Card) (positions[0].playerHand[1]);
        m1.arg1 = 2;
        GameWindow.myViewUpdateHandler.sendMessage(m1);

        positions[0].makeDecision();
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
   
	for(int i=0;i<involvedPlayers.length;i++){
		if(involvedPlayers[i].isHuman==false){
			int id = involvedPlayers[i].playerID;
			Message m = new Message();
		    m.what = GameWindow.DRAWOPPONENTCARDS;
		    m.arg1 = id;
		    m.obj = (Card[]) (involvedPlayers[i].playerHand);
		    GameWindow.myViewUpdateHandler.sendMessage(m);
		    Message m1 = new Message();
		    m1.what = GameWindow.DRAWOPPONENTNAME;
		    m1.arg1 = id;
		    m1.obj = (String) (involvedPlayers[i].name);
		    GameWindow.myViewUpdateHandler.sendMessage(m1);
		}
	}
	
    // set cards to flip over here
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
   
   int count =0;
    for(int i=0;i<involvedPlayers.length;i++){ // now need to check for any other players with the same hand. Then use the highest card to determine the winner
    	
        if(involvedPlayers[i].bestBoolean==bestBoolean){
        	Player[] tempCopy = new Player[(finalWinners.length)];
            System.arraycopy(finalWinners, 0, tempCopy, 0, tempCopy.length);
            finalWinners = new Player[(finalWinners.length+1)];
            System.arraycopy(tempCopy, 0,finalWinners , 0, tempCopy.length);
        	finalWinners[count]=involvedPlayers[i];
        	count++;
        }
        
    }
    count =0;
    involvedPlayers=finalWinners;
    if(involvedPlayers.length==1){
    	return involvedPlayers;
    }
    else{
    if(bestBoolean==9){
    	
    	
    	Player[] highLowPlayers = new Player[0];
        int playerPosition = 0;
        count = 0;
        int highestHoleCard = 0;
        int lowestHoleCard = involvedPlayers[0].getPlayerLowHoleCard();
       
        for(int i = 0;i< involvedPlayers.length;i++){
            if (involvedPlayers[i].getPlayerHighHoleCard() > highestHoleCard){
                highestHoleCard = involvedPlayers[i].getPlayerHighHoleCard();
                playerPosition = i;
            }
            else if (involvedPlayers[i].getPlayerHighHoleCard() == highestHoleCard){
                   
                    if (involvedPlayers[i].getPlayerLowHoleCard() > lowestHoleCard){
                        lowestHoleCard =involvedPlayers[i].getPlayerLowHoleCard();
                        playerPosition = i;
                    }
                    else if (involvedPlayers[i].getPlayerLowHoleCard() == lowestHoleCard){
                    //spilt pot
                    	Player[] tempCopy = new Player[(highLowPlayers.length)];
                        System.arraycopy(highLowPlayers, 0, tempCopy, 0, tempCopy.length);
                        highLowPlayers = new Player[(highLowPlayers.length+2)];
                        System.arraycopy(tempCopy, 0,highLowPlayers , 0, tempCopy.length);
                        highLowPlayers[count] = involvedPlayers[i];
                        count ++;
                        highLowPlayers[count] = involvedPlayers[playerPosition];
                        playerPosition = i;
                        count ++;
                    }
                }
            if(count == 0){
                highLowPlayers[count] = involvedPlayers[playerPosition];
            }
        }
        return highLowPlayers;
    	
    	// paddy's code goes here
    	
    }
    
    if(bestBoolean==8){
    	
    	Player []tempWinners=new Player[0];
    	for(int j=0;j<involvedPlayers.length;j++){
    		
    		if(involvedPlayers[j].highestFirstPair>firstPair){
    			
    			firstPair = involvedPlayers[j].highestFirstPair; // establish highest pair
    			
    		}
    	}
    	for(int j=0;j<involvedPlayers.length;j++){ // find winners with pair
    		if(involvedPlayers[j].highestFirstPair==firstPair){
    			Player[] tempCopy = new Player[(tempWinners.length)];
                System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
                tempWinners = new Player[(tempWinners.length+1)];
                System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                tempWinners[count]=involvedPlayers[j];
            	count++;
    		}
    		
    	}
    	if(tempWinners.length>1){ // if players have same pair, then get their highest non Pair card
    		Player [] finalFinalWinners = new Player[0];
    		int nonPair=0;
    		count =0;
    		for(int j=0;j<tempWinners.length;j++){
    			
    			if(tempWinners[j].nonPair>nonPair){
    				nonPair=tempWinners[j].nonPair;
    			}
    			
    		}
    		for(int j=0;j<tempWinners.length;j++){
    			if(tempWinners[j].nonPair==nonPair){
    				
    				Player[] tempCopy = new Player[(finalFinalWinners.length)];
    				System.out.println("tempcopy length:"+tempCopy.length);
                    System.arraycopy(finalFinalWinners, 0, tempCopy, 0, tempCopy.length);
                    finalFinalWinners = new Player[(finalFinalWinners.length+1)];
                    System.arraycopy(tempCopy, 0,finalFinalWinners , 0, tempCopy.length);
                    finalFinalWinners[count]=tempWinners[j];
                    count++;
                    //System.out.println("finalFinalWinners length:"+finalFinalWinners.length);
    			}
    		}
    		//System.out.println(finalFinalWinners[1].getName());
    		return finalFinalWinners;
    	}
    	else{
    		return tempWinners;
    	}
    	
    }
    
    if(bestBoolean==7){
    	int secondPair=0;
    	count =0;
    	Player []tempWinners=new Player[0];
    	for(int j=0;j<involvedPlayers.length;j++){ // establish highest pair card (get playrs 2nd pair becuase will always be higher than 1st pair
    		System.out.println(involvedPlayers[j].highestSecondPair);
    		if(involvedPlayers[j].highestSecondPair>secondPair){
    			
    			
    			secondPair = involvedPlayers[j].highestSecondPair; // establish highest pair
    			
    		}
    	}
    	for(int j=0;j<involvedPlayers.length;j++){// go though players and find players with that high second pair
    		if(involvedPlayers[j].highestSecondPair==secondPair){
    			Player[] tempCopy = new Player[(tempWinners.length)];
    			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
    			tempWinners = new Player[(tempWinners.length+1)];
                System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                tempWinners[count]=involvedPlayers[j];
                count++;
    		}
    	}
    	
    	
    	
    	if(tempWinners.length>1){ // got players still in contention so now need to check their 1st pair
		    		involvedPlayers=tempWinners;
		    		tempWinners=new Player[0];
		    		count=0;
		    		int firstPair=0;
		    		for(int j=0;j<involvedPlayers.length;j++){ // establish highest pair card
		        		if(involvedPlayers[j].highestFirstPair>firstPair){
		        			firstPair = involvedPlayers[j].highestFirstPair; 
		        		}
		        	}
		    		tempWinners=new Player[0];
		    		for(int j=0;j<involvedPlayers.length;j++){
		    			if(involvedPlayers[j].highestFirstPair==firstPair){
		    				Player[] tempCopy = new Player[(tempWinners.length)];
		        			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
		        			tempWinners = new Player[(tempWinners.length+1)];
		                    System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
		                    tempWinners[count]=involvedPlayers[j];
		                    count++;
		    			}
		    		}
    	
    	
    	}
    	else{
    		return tempWinners;
    	}
    	
    	if(tempWinners.length>1){ // if array has still got 2 players in it, then try and get the non pair card to decide it.
    		involvedPlayers=tempWinners;
    		tempWinners=new Player[0];
    		count=0;
    		int highNonPair=0;
    		for(int j=0;j<involvedPlayers.length;j++){ // establish highest pair card
        		if(involvedPlayers[j].nonPair>highNonPair){
        			highNonPair = involvedPlayers[j].nonPair; 
        		}
        	}
    		tempWinners=new Player[0];
    		for(int j=0;j<involvedPlayers.length;j++){
    			if(involvedPlayers[j].nonPair==highNonPair){
    				Player[] tempCopy = new Player[(tempWinners.length)];
        			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
        			tempWinners = new Player[(tempWinners.length+1)];
                    System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                    tempWinners[count]=involvedPlayers[j];
                    count++;
    			}
    		}
    		return tempWinners;
    		
    	}
    	return tempWinners;
    	
    }
    
    if(bestBoolean==6){
    	int threeCard=0;
    	count =0;
    	Player []tempWinners=new Player[0];
    	for(int j=0;j<involvedPlayers.length;j++){ // establish highest 3 of a kind
    		
    		if(involvedPlayers[j].threeCard>threeCard){
    			
    			threeCard = involvedPlayers[j].threeCard; // establish highest pair
    			
    		}
    	}
    	for(int j=0;j<involvedPlayers.length;j++){// go though players and find players with that high second pair
    		if(involvedPlayers[j].threeCard==threeCard){
    			Player[] tempCopy = new Player[(tempWinners.length)];
    			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
    			tempWinners = new Player[(tempWinners.length+1)];
                System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                tempWinners[count]=involvedPlayers[j];
                count++;
    		}
    	}
    	
    	
    	
    	if(tempWinners.length>1){
    		involvedPlayers=tempWinners;
    		tempWinners=new Player[0];
    		count =0;
    		int secondNonThreeCard=0;
    		
    		for(int j=0;j<involvedPlayers.length;j++){ // establish highest pair card (get playrs 2nd pair becuase will always be higher than 1st pair
        		
        		if(involvedPlayers[j].secondNonThreeCard>secondNonThreeCard){
        			
        			secondNonThreeCard = involvedPlayers[j].secondNonThreeCard; // establish highest pair
        			
        		}
        	}
        	for(int j=0;j<involvedPlayers.length;j++){// go though players and find players with that high second pair
        		if(involvedPlayers[j].secondNonThreeCard==secondNonThreeCard){
        			Player[] tempCopy = new Player[(tempWinners.length)];
        			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
        			tempWinners = new Player[(tempWinners.length+1)];
                    System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                    tempWinners[count]=involvedPlayers[j];
                    count++;
        		}
        	}
        	
        	if(tempWinners.length>1){
        		involvedPlayers=tempWinners;
        		tempWinners=new Player[0];
        		count =0;
        		int firstNonThreeCard=0;
        		
        		for(int j=0;j<involvedPlayers.length;j++){ // establish highest pair card (get playrs 2nd pair becuase will always be higher than 1st pair
            		
            		if(involvedPlayers[j].firstNonThreeCard>firstNonThreeCard){
            			
            			firstNonThreeCard = involvedPlayers[j].firstNonThreeCard; // establish highest pair
            			
            		}
            	}
            	for(int j=0;j<involvedPlayers.length;j++){// go though players and find players with that high second pair
            		if(involvedPlayers[j].firstNonThreeCard==firstNonThreeCard){
            			Player[] tempCopy = new Player[(tempWinners.length)];
            			System.arraycopy(tempWinners, 0, tempCopy, 0, tempCopy.length);
            			tempWinners = new Player[(tempWinners.length+1)];
                        System.arraycopy(tempCopy, 0,tempWinners , 0, tempCopy.length);
                        tempWinners[count]=involvedPlayers[j];
                        count++;
            		}
            	}
            	return tempWinners;
        	}
        	else{
        		return tempWinners;
        	}
    		
    	}
    	else{
    		return tempWinners;
    	}
    }
    
    if(bestBoolean<6){ // don't have time to code for further cases
    	return involvedPlayers; 
    }
    
    
    
    }
    return involvedPlayers;
   
}

public void bettingRound(Player [] tempPlayers){
    setPlayers(tempPlayers);
    getPlayersWhoWantToPlay();
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


public void getPlayersWhoWantToPlay(){
	Player[] tempPlayer = new Player[0];
	int playerCount=0;
	for(int i=0;i<involvedPlayers.length;i++){
		involvedPlayers[i].makeDecision();
		//System.out.println(involvedPlayers[i].getName()+involvedPlayers[i].getDecision());
		
		/** What to do if the player doesn't fold. */
		if(involvedPlayers[i].getDecision()!=1){
			Player[] tempCopy = new Player[(tempPlayer.length)];
            System.arraycopy(tempPlayer, 0, tempCopy, 0, tempCopy.length);
            tempPlayer = new Player[(tempPlayer.length+1)];
            System.arraycopy(tempCopy, 0,tempPlayer , 0, tempCopy.length);
            tempPlayer[playerCount]=involvedPlayers[i];
            System.out.println("copied"+tempPlayer[playerCount].getName());
            playerCount++;
		}
	}
	involvedPlayers=tempPlayer;
	if(involvedPlayers.length==1){
		
	}
	else{
		getHighestBet();
		setPot();
		
	}
	
	//resetCounters();
}

public int getHighestBet(){
   
    //currentBet=involvedPlayers[0].getBet();
    //addToPot(currentBet);
   
    for(int i=0;i<involvedPlayers.length;i++){
    	// -<<<<<<<< updating highest current bet GUI code goes here
        if(involvedPlayers[i].getBet(currentBet,BlindLevel * 20)>currentBet){ // go back to first player if bet has increased
            currentBet=involvedPlayers[i].getBet(currentBet,BlindLevel * 20);
            getHighestBet();
        }
    }
    return currentBet;
}

private void setPot(){
    //int [] tempInvolvedPlayers = new int[involvedPlayers.length];
    int tempInvolvedPlayersCount=0;
    for(int i=0;i<involvedPlayers.length;i++){
        if(involvedPlayers[i].getBet(currentBet,BlindLevel * 20)==currentBet){ // find players who are matching the current bet and get their ID
            int temp=involvedPlayers[i].playerID;
            System.out.println(temp);
            intInvolvedPlayers[tempInvolvedPlayersCount] = temp;
            tempInvolvedPlayersCount++;
            addToPot(involvedPlayers[i].getBet(currentBet,BlindLevel * 20));
        }
    }
    String pot_s = Integer.toString(potTotal);
    Message m = new Message();
    m.what = GameWindow.SETPOTTEXT;
    m.obj = (String) (pot_s);
    GameWindow.myViewUpdateHandler.sendMessage(m);
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
        System.out.println("Winner is "+winners[i].getName());
        //System.out.println("Player "+winners[i].getName()+" now has "+winners[i].checkMoney());
    }
    potTotal=0;
}

public void resetCounters(Player [] players){
    for (int i=0;i<players.length;i++){
        players[i].resetCounters();
    }
}

public void resetAllCounters(Player [] players){
    
	
	for (int i=0;i<players.length;i++){
        players[i].resetAllCounters();
    }
bestBoolean=10;
 currentBet=20;
    BlindLevel=1;
HighCard=0;
 winningPlayer=0;
firstPair=0;
 secondPair=0;

 handCounter=0;
}


public void dealBlinds(int currentDealer, Player [] positions){

		if(handCounter ==10){
			BlindLevel++;
			handCounter=0;
		}
 
	positions[(currentDealer + 1) % 2].removeBlinds(BlindLevel * 10);
	addToPot(BlindLevel * 10);

	positions[(currentDealer + 2) % 2].removeBlinds(BlindLevel * 20);
	addToPot(BlindLevel * 20);
	

	handCounter++;
	}
public int incrementDealer(int currentDealer, Player [] positions){
	
	
		return (currentDealer + 1) % positions.length;

	
}

public void checkPlayerMonies(Player [] playerArg){
	for(int i=0;i<playerArg.length;i++){
		System.out.println(playerArg[i].getName()+"has got "+playerArg[i].checkMoney()+" left.");
	}
}

}