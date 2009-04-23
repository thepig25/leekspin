package com.poker;

import android.os.Message;

public class pokerGame extends pokerGameMethods { // this is test code for dealing - not a final implementation. 
	//Want to be able to deal with more than 2 players in future. for now we'll just use 2
	
	int gameOver;
	private int pot;
	int Dealer;
	
	public pokerGame(int NOplayer, int startAmount, int bigBlind){
		Player [] positions = new Player[NOplayer];
		Player [] reducedPositions;
		// code for deciding dealer goes here
		gameOver = NOplayer;
		positions[0] = new HumanPlayer("Hugh Man",startAmount,null,1);
		positions[1] = new AI_Player ("Megatron",startAmount,null,2);

//		while(gameOver>1){ // loop which keeps game going
//			for (int i=0;i<positions.length;i++{ // checks at start of hand if any players are out of chips
//				if(positions[i].canPlay==false){ 
//					positon[i]=null;// kicks player if they're out
//					gameOver--; // one closer to a game over situation
//				}
//			}
		
			Pack myPack = new Pack();
			myPack.createPack();
			//myPack.Shuffle();
			
			Dealer = chooseFirstDealer(positions.length); // method to choose Dealer
			
			System.out.println("Dealer is player: "+Dealer);
			Message m = new Message();
	        m.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m.obj = (String) ("Dealer is player: "+Dealer+"\n");
	        GameWindow.myViewUpdateHandler.sendMessage(m);
	        
			dealBlinds(Dealer, positions);
			System.out.println(positions[0].checkMoney());
			System.out.println(positions[1].checkMoney());
			
			dealPocketCard(myPack, positions);
			
			for(int i=0;i<positions.length;i++){
				System.out.print("Player "+positions[i].getName()+" has been dealt a ");
				Message m1 = new Message();
		        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m1.obj = (String) ("Player "+positions[i].getName()+" has been dealt a ");
		        GameWindow.myViewUpdateHandler.sendMessage(m1);
		        
				positions[i].printPocketHand();
			}
			
			bettingRound(positions); // 1st betting round
			reducedPositions = returnPlayersHack();
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
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is: ");
			Message m1 = new Message();
	        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m1.obj = (String) ("Player "+reducedPositions[0].getName()+"'s best hand is: ");
	        GameWindow.myViewUpdateHandler.sendMessage(m1);
			
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			Message m2 = new Message();
	        m2.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m2.obj = (String) ("Player "+reducedPositions[1].getName()+"'s best hand is: ");
	        GameWindow.myViewUpdateHandler.sendMessage(m2);
	        
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 2nd betting round
			reducedPositions = returnPlayersHack();
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
		
			dealTurn(myPack, reducedPositions);			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 3rd betting round
			reducedPositions = returnPlayersHack();
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
			dealRiver(myPack, reducedPositions);
			
			
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 4th betting round
			reducedPositions = returnPlayersHack();
			System.out.println("reduced"+reducedPositions.length);
			if(reducedPositions.length==1){
				
				System.out.println("Pot is "+returnPot());
				rewardWinners(reducedPositions,returnPot());
				
			}
			else{
			
			resetCounters(reducedPositions);
			
			
			System.out.println("Pot is: " +returnPot());
			
			
			PokerHand [] winningCards = {reducedPositions[0].getBestPokerHand(),reducedPositions[1].getBestPokerHand()};
			
			
			Player [] winner = winner(reducedPositions);
			
			if(winner.length>=2){
				System.out.print ("Split pot between ");
				Message m5 = new Message();
		        m5.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m5.obj = (String) ("Split pot between ");
		        GameWindow.myViewUpdateHandler.sendMessage(m5);
			}
			else{
				System.out.println ("Winner is: ");
				Message m3 = new Message();
		        m3.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m3.obj = (String) ("Winner is: ");
		        GameWindow.myViewUpdateHandler.sendMessage(m3);
			}
			System.out.println(winner.length);
			for(int i=0;i<winner.length;i++){
				System.out.print (winner[i].getName()+" ");
				Message m4 = new Message();
		        m4.what = GameWindow.GUIUPDATEIDENTIFIER;
		        m4.obj = (String) (winner[i].getName()+" ");
		        GameWindow.myViewUpdateHandler.sendMessage(m4);
			}
			
			rewardWinners(winner,returnPot());
			resetCounters(positions);
			int temp = incrementDealer(Dealer, positions);
			Dealer=temp;
			}
			}
			}
			}
	
	}
	
	}
	

