package com.poker;

public class AI_Player extends Player {
	
	int askCount=0;
	int bet=0;
	private int decision;

	public AI_Player(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
		
	}
	
	public int getBet(int highestCurrentBet){
		
		if(askCount<1){
		bet = 20;
		if (highestCurrentBet>bet){ // <<<-------- this is where the AI coding needs to go
			bet = highestCurrentBet*2;
		}
		currentMoney = currentMoney - bet;
		askCount++;
		}
		return bet;
		
	}
	
	public void makeDecision(){
		decision=0;
	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		return  0; // 0=fold,1=check/call, 2=raise
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
}
