
public class pokerGame extends pokerGameMethods { // this is test code for dealing - not a final implementation. 
	//Want to be able to deal with more than 2 players in future. for now we'll just use 2
	
	int gameOver;
	private int pot;
	
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
			myPack.Shuffle();
			
			int Dealer = chooseFirstDealer(positions.length); // method to choose Dealer
			System.out.println("Dealer is player: "+Dealer);
			dealBlinds(Dealer, positions);
			System.out.println(positions[0].checkMoney());
			System.out.println(positions[1].checkMoney());
			dealPocketCard(myPack, positions);
			
			for(int i=0;i<positions.length;i++){
				System.out.print("Player "+positions[i].getName()+" has been dealt a ");
				positions[i].printPocketHand();
			}
			
			bettingRound(positions); // 1st betting round
			reducedPositions = getInvolvedPlayers();
			resetCounters(reducedPositions);
			
			System.out.println(positions[0].getName());
			System.out.println(positions[1].getName());
			System.out.println(returnPot());
			
	
			
			dealFlopCommunityCards(myPack, reducedPositions);
			
			
			reducedPositions[0].printCommunity();
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 2nd betting round
			reducedPositions = getInvolvedPlayers();
			resetCounters(reducedPositions);
		
			dealTurn(myPack, reducedPositions);			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 3rd betting round
			reducedPositions = getInvolvedPlayers();
			dealRiver(myPack, reducedPositions);
			resetCounters(reducedPositions);
			
			
			System.out.println("Player "+reducedPositions[0].getName()+"'s best hand is:");
			reducedPositions[0].showBestHand();
			
			System.out.println("Player "+reducedPositions[1].getName()+"'s best hand is:");
			reducedPositions[1].showBestHand();
			
			bettingRound(reducedPositions); // 4th betting round
			reducedPositions = getInvolvedPlayers();
			resetCounters(reducedPositions);
			
			System.out.println("Pot is: " +returnPot());
			
			
			PokerHand [] winningCards = {reducedPositions[0].getBestPokerHand(),reducedPositions[1].getBestPokerHand()};
			
			
			Player [] winner = winner(reducedPositions);
			
			if(winner.length>=2){
				System.out.print ("Split pot between ");
				
			}
			else{
				System.out.println ("Winner is: ");
			}
			
			for(int i=0;i<winner.length;i++){
				
				System.out.print (winner[i].getName()+" ");
			}
			
			rewardWinners(winner,returnPot());
			resetCounters(positions);
		}
	}
	

