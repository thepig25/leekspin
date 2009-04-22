package com.poker;
//import javax.swing.JOptionPane;

public class HumanPlayer extends Player {
	int askCount=0;
	int bet=0;
	
	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
	}
	
	public int getBet(int currentBet) {
		
		if(askCount<1){
			//try {
				GameWindow.setConsoleText("Current Bet is: "+currentBet+".   >> Press Raise to bet. <<\n");
			//} catch (Exception e) {
			//	e.getMessage();
			//}
			
			//GameWindow.com = "something";
			
			/**
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			while(GameWindow.getPressed() == false){
				try {
					wait(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			*/
			bet = 2;//GameWindow.getBet();
			//bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Current Bet is: "+currentBet+" Enter amount here"));
			currentMoney = currentMoney - bet;
			askCount++;
		}
		return bet;
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
}
