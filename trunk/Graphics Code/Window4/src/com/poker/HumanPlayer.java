package com.poker;

import android.os.Message;

public class HumanPlayer extends Player {
	int askCount=0;
	int bet=0;
	private int decision;
	public boolean alreadyBet = false;
	private int localCurrentBet;
	
	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
	
		isHuman=true;
	
	}
	
	public void receiveCommunityCards(Card[] received){ // method for receiving community cards. 
		//initially set array to size 3 and then copy to make it the full size of 5
		
		if( cardCount >= 2){
			Card[] tempCopy = new Card[(communityCards.length)];
			System.arraycopy(communityCards, 0, tempCopy, 0, tempCopy.length);
			communityCards = new Card[(communityCards.length+1)];
			System.arraycopy(tempCopy, 0,communityCards , 0, tempCopy.length);
			
		}
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			communityCards[cardCount] = temp[i];
			cardCount++;
		}
			
	}
	
	
	
	/** This takes an array of dealt cards then adds them to the
	the private variable which holds the player's hand.
	This is just for the first two pocket cards a player gets. */
	public void receivePocketCards(Card[] received){
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			playerHand[cardPocketCount] = temp[i];
			cardPocketCount++;
		}
		System.out.println("cardPocketCount is"+cardPocketCount);
		/*if(cardPocketCount==2){
			Message m = new Message();
	        m.what = GameWindow.DRAWPLAYERCARDS;
	        m.obj = (Card[]) (playerHand);
	        GameWindow.myViewUpdateHandler.sendMessage(m);
		}	*/
	}
	
	public void makeDecision(){
		
		//decision = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter decision here. Fold=1"));
		
		Message m1 = new Message();
        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
        m1.obj = (String) ("\nPlayer has raised to "+localCurrentBet+"Press raise or call to continue\n");
        GameWindow.myViewUpdateHandler.sendMessage(m1);
		while(GameWindow.getAnyPressed() != true){
			// wait
		}
		
		decision = GameWindow.command;
		GameWindow.any_pressed = false;
		
		switch (decision) {
        	case 0: // Raise
        		bet = GameWindow.getBet();
        		//askCount++;
        		//alreadyBet=true;
        		// send new bet somewhere...
        		break;
            
        	case 1: // Fold
        		Message m = new Message();
                m.what = GameWindow.DRAWBLANKCARDS;
                GameWindow.myViewUpdateHandler.sendMessage(m);
                GameWindow.p1Folded = true;
        		break;
            
        	case 2: // Call
        		// ?
        		break;
        		
        	case 3: // Check
        		
        		break;
		}
		GameWindow.any_pressed=false;
	}
	
	public int getDecision(){
		
		return decision;
	}
	
	public int getBet(int currentBet){
		
		localCurrentBet=currentBet;
		
		if(askCount>0&&bet==currentBet){
			return bet;
		}
			
		
		
		/*if (decision==0&&alreadyBet==true){
			alreadyBet=false;
			return bet;
		}*/
		if(decision==2&&bet==currentBet){
			return currentBet;
		}
		if(decision==2&&currentBet>bet){
			Message m1 = new Message();
	        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m1.obj = (String) ("Player has raised to "+currentBet+". Press raise or call to continue\n");
	        GameWindow.myViewUpdateHandler.sendMessage(m1);
			makeDecision();
			if(decision==2){
				return currentBet;
			}
		}
		
		else{
		
			Message m1 = new Message();
	        m1.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m1.obj = (String) ("Player has raised to "+currentBet+". Press raise to continue\n");
	        GameWindow.myViewUpdateHandler.sendMessage(m1);
	        while(GameWindow.raised_done != true){
	        	// wait
	        }
				
		GameWindow.raised_done = false;
		
		System.out.println(currentBet);
		String as,bs,cs;
		as = Integer.toString(currentBet);
		bs = Integer.toString(currentBet * 2);
		cs = Integer.toString(currentBet * 3);
		
		String[] bets = new String[3];
		bets[0] = as;
		bets[1] = bs;
		bets[2] = cs;
		
		Message m2 = new Message();
        m2.what = GameWindow.SETBET;
        m2.obj = (String[]) (bets);
        GameWindow.myViewUpdateHandler.sendMessage(m2);
		
		
			//int oldBet= bet;
			Message m3 = new Message();
	        m3.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m3.obj = (String) (" >> Press Raise to bet. <<\n");
	        GameWindow.myViewUpdateHandler.sendMessage(m3);
			//while(GameWindow.getAnyPressed() != true){
				// wait
			//}
	        int oldBet=bet;
			bet = GameWindow.getBet();
			//bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Current Bet is: "+currentBet+" Enter amount here"));
			
			
			finaliseBet(bet);
			int counter=0;
			
			GameWindow.any_pressed=false;
			askCount++;
		
		}

		

		return bet;
				
	}
	
	public void finaliseCallBet(int currentBet){
		currentMoney = currentMoney - (bet);
	}
	
	public void finaliseBet(int currentBet){
		currentMoney = currentMoney - (bet);
	}
	
	public void resetCounters(){
		bet=20;
		askCount=0;
	}
	
}
