
public class pokerGame extends Pack {
	
	int gameOver;
	
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
			
			
			Card[] deal = myPack.dealOneCardArray();
			positions[0].receiveCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receiveCards(deal);
			deal = myPack.dealOneCardArray();
			positions[0].receiveCards(deal);
			deal = myPack.dealOneCardArray();
			positions[1].receiveCards(deal);
			positions[0].printHand();
			System.out.println("Finished pos[0]");
			positions[1].printHand();
			
		}
	}
	

