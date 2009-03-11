
public class pokerGame {
	
	int gameOver;
	
	public pokerGame(int NOplayer, int startAmount, int bigBlind){
		Player [] positions = new Player[NOplayer];
		// code for deciding dealer goes here
		gameOver = NOplayer;
		positions[0] = new HumanPlayer("Hugh Man",startAmount,null,true);
		positions[1] = new AI_Player ("Megatron",startAmount,null,true);
		
		
		
		while(gameOver>1){ // loop which keeps game going
			for (int i=0;i<positions.length;i++{ // checks at start of hand if any players ar out of chips
				if(positions[i].canPlay==false){ 
					positon[i]=null;// kicks player if they're out
					gameOver--; // one closer to a game over situation
				}
			}
			
			
		}
	}
	
}
