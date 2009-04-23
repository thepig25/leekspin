package com.poker;

import android.os.Message;

//import javax.swing.JOptionPane;

public class HumanPlayer extends Player {
	int askCount=0;
	int bet=20;
	
	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
	}
	
	public void printPocketHand(){
	for(int i = 0; i < 2; i++){
		Message m = new Message();
        m.what = GameWindow.DRAWPLAYERCARDS;
        m.obj = (Card[]) (playerHand);
        GameWindow.myViewUpdateHandler.sendMessage(m);
//		GameWindow.setConsoleText(playerHand[i].getValueAsString()+ " of " + playerHand[i].getSuitAsString() + ", ");
	}
	}
	public int getBet(int currentBet) {
		
		if(askCount<1){
			
			int a,b,c;
			a = currentBet;
			b = currentBet * 2;
			c = currentBet * 3;
			
			String as,bs,cs;
			as = Integer.toString(a);
			bs = Integer.toString(b);
			cs = Integer.toString(c);
			
			String[] bets = new String[3];
			bets[0] = as;
			bets[1] = bs;
			bets[2] = cs;
			
			Message m1 = new Message();
            m1.what = GameWindow.SETBET;
            m1.obj = (String[]) (bets);
            GameWindow.myViewUpdateHandler.sendMessage(m1);
            
            //while(GameWindow.getRaisedPressed() != true){
				// Do nothing;
			//}
			
			bet = GameWindow.getBet();
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
	public void foldPlayer(){
		//Message m = new Message();
		//m.what = GameWindow.DRAWBLANKCARDS;
		//GameWindow.myViewUpdateHandler.sendMessage(m);
	}
	
}
