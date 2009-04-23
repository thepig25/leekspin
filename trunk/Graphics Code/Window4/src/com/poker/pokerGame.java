package com.poker;

import android.os.Handler;

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
		positions[0] = new HumanPlayer("Hugh Man",startAmount,null,0);
		positions[1] = new AI_Player ("Megatron",startAmount,null,1);

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
//			GameWindow.setConsoleText("Dealer is player: "+Dealer+"\n");
			dealBlinds(Dealer, positions);
			System.out.println(positions[0].checkMoney());
			System.out.println(positions[1].checkMoney());
			dealPocketCard(myPack, positions);
			for(int i=0;i<positions.length;i++){
//				GameWindow.setConsoleText("Player "+positions[i].getName()+" has been dealt a ");
				positions[i].printPocketHand();
			}
			
			bettingRound(positions); // 1st betting round
			reducedPositions = getInvolvedPlayers();
			
			
//			GameWindow.setConsoleText(positions[0].getName()+"\n");
//			GameWindow.setConsoleText(positions[1].getName()+"\n");
//			GameWindow.setConsoleText(returnPot()+"\n");
			
	
			
			dealFlopCommunityCards(myPack, reducedPositions);
			
			
			reducedPositions[0].printCommunity();
			
//			GameWindow.setConsoleText("Player "+reducedPositions[0].getName()+"'s best hand is:\n");
			
			reducedPositions[0].showBestHand();
			
//			GameWindow.setConsoleText("Player "+reducedPositions[1].getName()+"'s best hand is:\n");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 2nd betting round
			reducedPositions = getInvolvedPlayers();
			resetCounters(reducedPositions);
		
			dealTurn(myPack, reducedPositions);			
//			GameWindow.setConsoleText("Player "+reducedPositions[0].getName()+"'s best hand is: ");
			reducedPositions[0].showBestHand();
			
//			GameWindow.setConsoleText("Player "+reducedPositions[1].getName()+"'s best hand is: ");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 3rd betting round
			reducedPositions = getInvolvedPlayers();
			dealRiver(myPack, reducedPositions);
			resetCounters(reducedPositions);
			
			
//			GameWindow.setConsoleText("Player "+reducedPositions[0].getName()+"'s best hand is: ");
			reducedPositions[0].showBestHand();
			
//			GameWindow.setConsoleText("Player "+reducedPositions[1].getName()+"'s best hand is: ");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 4th betting round
			reducedPositions = getInvolvedPlayers();
			resetCounters(reducedPositions);
			
//			GameWindow.setConsoleText("Pot is: " +returnPot()+" ");
			
			
			PokerHand [] winningCards = {reducedPositions[0].getBestPokerHand(),reducedPositions[1].getBestPokerHand()};
			
			
			Player [] winner = winner(reducedPositions);
			
			if(winner.length>=2){
//				GameWindow.setConsoleText("Split pot between ");
				
			}
			else{
//				GameWindow.setConsoleText("Winner is: ");
			}
			
			for(int i=0;i<winner.length;i++){
				
//				GameWindow.setConsoleText(winner[i].getName()+" ");
			}
			
			rewardWinners(winner,returnPot());
			resetCounters(positions);
			int temp = incrementDealer(Dealer, positions);
			Dealer=temp;
//		}
	}
}
	

