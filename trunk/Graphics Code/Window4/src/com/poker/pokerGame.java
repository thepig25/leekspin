package com.poker;

import android.os.Message;

// this class runs an actual poker game

public class pokerGame extends pokerGameMethods { 
	
	int gameOver;
	private int pot;
	int Dealer;
	
	public pokerGame(int NOplayer, int startAmount, int bigBlind, int[] money){
		Player [] positions = new Player[NOplayer];
		Player [] reducedPositions;
		// code for deciding dealer goes here
		gameOver = NOplayer;	
	//if(money==null){
			positions[0] = new HumanPlayer("Hugh Man",startAmount,null,1);
			positions[1] = new AI_Player ("Megatron",startAmount,null,2);
			positions[2] = new AI_Player ("Optimus Prime",startAmount,null,3);
			positions[3] = new AI_Player ("Roomba",startAmount,null,4);
		/*}else{
			positions[0] = new HumanPlayer("Hugh Man",money[0],null,1);
			positions[1] = new AI_Player ("Megatron",money[1],null,2);
			positions[2] = new AI_Player ("Optimus Prime",money[2],null,3);
			positions[3] = new AI_Player ("Roomba",money[3],null,4);
		}*/
		
		
		for(int i = 0; i < positions.length; i++){
			
	        Message m5 = new Message();
	        m5.what = GameWindow.DRAWOPPONENTNAME;
	        m5.arg1 = positions[i].playerID;
	        m5.obj = (String) (positions[i].getName());
	        GameWindow.myViewUpdateHandler.sendMessage(m5);
	        
		}
		
		int count =0;
		while(gameOver>1){ // loop which keeps game going
			Player[] tempPlayer = new Player[0];
		
			for (int i=0;i<positions.length;i++){ // checks at start of hand if any players are out of chips
				if(positions[i].checkMoney()<=0){ 
					gameOver--; // one closer to a game over situation
					if(positions[i].checkMoney()<=0&&positions[i].isHuman){
						// put loser screen here
						
						Message m5 = new Message();
				        m5.what = GameWindow.LOSER;
				        GameWindow.myViewUpdateHandler.sendMessage(m5);
					break; // V important to keep this!
					}
				}
				
				
				if(positions[i].checkMoney()>0){ 
					Player[] tempCopy = new Player[(tempPlayer.length)];
        			System.arraycopy(tempPlayer, 0, tempCopy, 0, tempCopy.length);
        			tempPlayer = new Player[(tempPlayer.length+1)];
                    System.arraycopy(tempCopy, 0,tempPlayer , 0, tempCopy.length);
                    tempPlayer[count]=positions[i];
                    count++;
					 
				}
			}
			positions=tempPlayer;
			if(positions.length==1&&positions[0].isHuman ){
				// display winner screen
				break;
			}
			count=0;
			
			
			Pack myPack = new Pack();
			myPack.createPack();
			myPack.Shuffle();
			
			Dealer = chooseFirstDealer(positions.length); // method to choose Dealer
			
			System.out.println("Dealer is player: "+Dealer);
			Message m = new Message();
	        m.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m.obj = (String) ("\nDealer is player: "+Dealer+"");
	        GameWindow.myViewUpdateHandler.sendMessage(m);
	        
			dealBlinds(Dealer, positions);
			
			checkPlayerMonies(positions);
			
			dealPocketCard(myPack, positions);
			
			for(int i=0;i<positions.length;i++){
				System.out.print("Player "+positions[i].getName()+" has been dealt a ");
				Message m1 = new Message();
		        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m1.obj = (String) ("\nPlayer "+positions[i].getName()+" has been dealt a ");
		        GameWindow.myViewUpdateHandler.sendMessage(m1);
		        
				positions[i].printPocketHand();
			}
			
			bettingRound(positions); // 1st betting round
			reducedPositions = returnPlayersHack();
			checkPlayerMonies(reducedPositions);
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			resetCounters(reducedPositions);
			
			System.out.println(positions[0].getName());
			System.out.println(positions[1].getName());
			System.out.println(returnPot());
			
	
			
			dealFlopCommunityCards(myPack, reducedPositions);
			
			
			reducedPositions[0].printCommunity();
	        
			for(int i=0; i<reducedPositions.length; i++ ){
				reducedPositions[i].showBestHand();
			}
			
			GameWindow.commCardsDealt = true;
			
			for(int i = 0; i < reducedPositions.length; i++){
				
				System.out.println("Player "+reducedPositions[i].getName()+"'s best hand is: ");
				
		        Message m5 = new Message();
		        m5.what = GameWindow.SETPLAYERSBESTHAND;
		        m5.arg1 = reducedPositions[i].playerID;
		        m5.obj = (Card[]) (reducedPositions[i].getBestHand());
		        GameWindow.myViewUpdateHandler.sendMessage(m5);
		        
			}
			
			bettingRound(reducedPositions); // 2nd betting round
			reducedPositions = returnPlayersHack();
			checkPlayerMonies(reducedPositions);
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
		
			dealTurn(myPack, reducedPositions);	// deal best card
			
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			for(int i = 0; i < reducedPositions.length; i++){
				
				System.out.println("Player "+reducedPositions[i].getName()+"'s best hand is: ");
				
		        Message m5 = new Message();
		        m5.what = GameWindow.SETPLAYERSBESTHAND;
		        m5.arg1 = reducedPositions[i].playerID;
		        m5.obj = (Card[]) (reducedPositions[i].getBestHand());
		        GameWindow.myViewUpdateHandler.sendMessage(m5);
		        
			}
			
			bettingRound(reducedPositions); // 3rd betting round
			reducedPositions = returnPlayersHack();
			checkPlayerMonies(reducedPositions);
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
			dealRiver(myPack, reducedPositions); // deal River
			
			
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			for(int i = 0; i < reducedPositions.length; i++){
				
				System.out.println("Player "+reducedPositions[i].getName()+"'s best hand is: ");
				
		        Message m5 = new Message();
		        m5.what = GameWindow.SETPLAYERSBESTHAND;
		        m5.arg1 = reducedPositions[i].playerID;
		        m5.obj = (Card[]) (reducedPositions[i].getBestHand());
		        GameWindow.myViewUpdateHandler.sendMessage(m5);
		        
			}
			
			bettingRound(reducedPositions); // 4th betting round
			reducedPositions = returnPlayersHack();
			checkPlayerMonies(reducedPositions);
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
			
			GameWindow.game_over = true;
			
			System.out.println("Pot is: " +returnPot());
				
			Player [] winner = winner(reducedPositions);
			
			
			if(winner.length>=2){
				System.out.print ("\nSplit pot between: ");
				Message m5 = new Message();
		        m5.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m5.obj = (String) ("Split pot between ");
		        GameWindow.myViewUpdateHandler.sendMessage(m5);
			}
			else{
				System.out.println ("\nWinner is: ");
				Message m3 = new Message();
		        m3.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m3.obj = (String) ("\nWinner is: ");
		        GameWindow.myViewUpdateHandler.sendMessage(m3);
			}
			System.out.println(winner.length);
			for(int i=0;i<winner.length;i++){
				System.out.print (winner[i].getName()+" ");
				Message m4 = new Message();
		        m4.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m4.obj = (String) (winner[i].getName()+", ");
		        GameWindow.myViewUpdateHandler.sendMessage(m4);
			}
			
			rewardWinners(winner,returnPot());
			checkPlayerMonies(reducedPositions);
			resetCounters(positions);
			int temp = incrementDealer(Dealer, positions);
			Dealer=temp;
			}
			}
			}
			}
	resetAllCounters(positions);
	
	Message m7 = new Message();
    m7.what = GameWindow.GUIUPDATEIDENTIFIER;
    m7.obj = (String) ("\nPress any Button to continue.");
    GameWindow.myViewUpdateHandler.sendMessage(m7);
	
	while(GameWindow.any_pressed != true){
    	// wait
    }
	
	GameWindow.any_pressed = false;
	
	Message m1 = new Message();
    m1.what = GameWindow.NEWROUND;
    GameWindow.myViewUpdateHandler.sendMessage(m1);
    for(int i=0; i<positions.length; i++){
    	positions[i].checkMoney();
    }
	}// end game loop
		System.exit(0);
	
	}
	
}
