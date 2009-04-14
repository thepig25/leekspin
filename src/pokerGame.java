
public class pokerGame extends pokerGameMethods { // this is test code for dealing - not a final implementation. 
	//Want to be able to deal with more than 2 players in future. for now we'll just use 2
	
	int gameOver;
	private int pot;
	
	public pokerGame(int NOplayer, int startAmount, int bigBlind){
		Player [] positions = new Player[NOplayer];
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
			
			int Dealer = chooseFirstDealer(positions.length); // method to choose Dealer
			System.out.println("Dealer is player: "+Dealer);
			
			Card[] deal = myPack.getFirstPocketSpecial();
			positions[0].receivePocketCards(deal);
			deal = myPack.getSecondPocketSpecial();
			positions[1].receivePocketCards(deal);
			
			
			/*Card[] deal = myPack.dealOneCardArray(); // this part deals the pocket pair to each player in the correct manner
			positions[0].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[0].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receivePocketCards(deal);*/
			
			
			System.out.print("Player "+positions[0].getName()+" has been dealt a ");
			positions[0].printPocketHand();
			System.out.print("Player "+positions[1].getName()+" has been dealt a ");
			positions[1].printPocketHand();
			
			bettingRound(positions);
			positions = getInvolvedPlayers();
			
			System.out.println(positions[0].getName());
			System.out.println(positions[1].getName());
			System.out.println(returnPot());
			
			
			//pot = positions[0].getBet(); // first round of betting
			//pot = pot+ positions[1].getBet();
			
			deal = myPack.getCommmunitySpecial();
			positions[0].receiveCommunityCards(deal); 
			positions[1].receiveCommunityCards(deal); 
			
			
			/*deal = myPack.dealThreeCardArray(); // deal 3 community cards
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
*/			
			
			positions[0].printCommunity();
			
			System.out.println("Player "+positions[0].getName()+"'s best hand is:");
			
			positions[0].showBestHand();
			
			System.out.println("Player "+positions[1].getName()+"'s best hand is:");
			positions[1].showBestHand();
			
		
			
			
			
			//pot = pot+ positions[0].getBet(); // 2nd round of betting
			//pot = pot+ positions[1].getBet(); // 2nd round of betting
			
			//deal = myPack.dealOneCardArray(); // deal 1 community card
			
			deal = myPack.getTurnSpecial();
			
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
			
			System.out.println("Turn card is a "+deal[0].getValueAsString()+" of "+deal[0].getSuitAsString());
			
			System.out.println("Player "+positions[0].getName()+"'s best hand is:");
			positions[0].showBestHand();
			
			System.out.println("Player "+positions[1].getName()+"'s best hand is:");
			positions[1].showBestHand();
			
			
			
			//pot = pot+ positions[0].getBet(); // 3rd round of betting
			//pot = pot+ positions[1].getBet(); // 3rd round of betting
			
			//deal = myPack.dealOneCardArray(); // deal final community card
			
			deal = myPack.getRiverSpecial();
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
			
			System.out.println("Turn card is a "+deal[0].getValueAsString()+" of "+deal[0].getSuitAsString());
			
			System.out.println("Player "+positions[0].getName()+"'s best hand is:");
			positions[0].showBestHand();
			
			System.out.println("Player "+positions[1].getName()+"'s best hand is:");
			positions[1].showBestHand();
			
			//pot = pot+ positions[0].getBet(); // final round of betting
			//pot = pot+ positions[1].getBet(); // final round of betting

			
			System.out.println("Pot is: " +pot);
			
			System.out.println (positions[0].checkMoney());
			System.out.println (positions[1].checkMoney());
			
			PokerHand [] winningCards = {positions[0].getBestPokerHand(),positions[1].getBestPokerHand()};
			
			
			Player [] winner = winner(positions,winningCards);
			
			if(winner.length>=2){
				System.out.print ("Split pot between ");
			}
			else{
				System.out.println ("Winner is: ");
			}
			
			for(int i=0;i<winner.length;i++){
				
				System.out.print (winner[i].getName()+" ");
			}
			
			
		}
	}
	

