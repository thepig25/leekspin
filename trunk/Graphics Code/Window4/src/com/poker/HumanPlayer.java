package com.poker;

import android.os.Message;


public class HumanPlayer extends Player {
	int askCount=0;
	int bet=0;
	private int decision;
	boolean alreadyBet = false;

	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
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
		Message m = new Message();
        m.what = GameWindow.DRAWCOMMUNITYCARDS;
        m.obj = (Card[]) (communityCards);
        GameWindow.myViewUpdateHandler.sendMessage(m);
					
	}
	
	
	
	// this takes an array of dealt cards then adds them to the
	// the private variable which holds the player's hand.
	// This is just for the first two pocket cards a player gets
	public void receivePocketCards(Card[] received){
		Card[] temp = received;
		for(int i = 0;i <temp.length;i++){
			playerHand[cardPocketCount] = temp[i];
			cardPocketCount++;
		}
		System.out.println("cardPocketCount is"+cardPocketCount);
		if(cardPocketCount==2){
			Message m = new Message();
	        m.what = GameWindow.DRAWPLAYERCARDS;
	        m.obj = (Card[]) (playerHand);
	        GameWindow.myViewUpdateHandler.sendMessage(m);
		}
		
	}
	
	
	
	
	
	
	
	public void makeDecision(){
		
		//decision = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter decision here. Fold=1"));
		while(GameWindow.getAnyPressed() != true){
			// wait
		}
		
		decision = GameWindow.command;
		
		switch (decision) {
        	case 0:
        		//
        		break;
             
        	case 1:
        		//
        		break;
             
        	case 2:
        		//
        		break;
        		
        	case 3:
        		//
        		break;
		}
		
	}
	
	public int getDecision(){
		
		return decision;
	}
	
	
	public int getBet(int currentBet){
		
		
		System.out.println(currentBet);
		String as,bs,cs;
		as = Integer.toString(currentBet);
		bs = Integer.toString(currentBet * 2);
		cs = Integer.toString(currentBet * 3);
		
		String[] bets = new String[3];
		bets[0] = as;
		bets[1] = bs;
		bets[2] = cs;
		
		Message m1 = new Message();
        m1.what = GameWindow.SETBET;
        m1.obj = (String[]) (bets);
        GameWindow.myViewUpdateHandler.sendMessage(m1);
		
		if(currentBet>bet){
			//int oldBet= bet;
			Message m2 = new Message();
	        m2.what = GameWindow.GUIUPDATEIDENTIFIER;
	        m2.obj = (String) (" >> Press Raise to bet. <<\n");
	        GameWindow.myViewUpdateHandler.sendMessage(m2);
			while(GameWindow.getAnyPressed() != true){
				// wait
			}
			bet = GameWindow.getBet();
			//bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Current Bet is: "+currentBet+" Enter amount here"));
			
			
			finaliseBet(bet);
			int counter=0;
			
			GameWindow.any_pressed=false;
			askCount++;
		
		}

		

		return bet;
				
	}
	
	public void finaliseBet(int currentBet){
		currentMoney = currentMoney - (bet);
	}
	
	public void resetCounters(){
		bet=20;
		askCount=0;
	}
	
}
