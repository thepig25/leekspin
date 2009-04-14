import javax.swing.JOptionPane;


public class HumanPlayer extends Player {
	int askCount=0;
	int bet=0;

	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
	}
	
	public int getBet(int currentBet){
		if(askCount<1){
			bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Current Bet is: "+currentBet+" Enter amount here"));
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
