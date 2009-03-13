
public class pokerGame extends Pack {
	
	int gameOver;
	private int pot;
	
	public pokerGame(int NOplayer, int startAmount, int bigBlind){
		Player [] positions = new Player[NOplayer];
		// code for deciding dealer goes here
		gameOver = NOplayer;
		positions[0] = new HumanPlayer("Hugh Man",startAmount,null);
		positions[1] = new AI_Player ("Megatron",startAmount,null);
		
		
		
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
			
			
			Card[] deal = myPack.dealOneCardArray(); // this part deals the pocket pair to each player in the correct manner
			positions[0].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[0].receivePocketCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receivePocketCards(deal);
			
			pot = positions[0].getBet(); // first round of betting
			pot = pot+ positions[1].getBet();
			
			
			deal = myPack.dealThreeCardArray(); // deal 3 community cards
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
			
			pot = pot+ positions[0].getBet(); // 2nd round of betting
			pot = pot+ positions[1].getBet(); // 2nd round of betting
			
			deal = myPack.dealOneCardArray(); // deal 1 community card
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
			
			pot = pot+ positions[0].getBet(); // 3rd round of betting
			pot = pot+ positions[1].getBet(); // 3rd round of betting
			
			deal = myPack.dealOneCardArray(); // deal final community card
			positions[0].receiveCommunityCards(deal); // each player gets same cards
			positions[1].receiveCommunityCards(deal); // each player gets same cards
			
			pot = pot+ positions[0].getBet(); // final round of betting
			pot = pot+ positions[1].getBet(); // final round of betting
			
			
			positions[0].printPocketHand();
			positions[1].printPocketHand();
			
			positions[0].printHand();
			positions[1].printHand();
			System.out.println("Pot is: " +pot);
			
			System.out.println (positions[0].checkMoney());
			System.out.println (positions[1].checkMoney());
		}
	}
	

