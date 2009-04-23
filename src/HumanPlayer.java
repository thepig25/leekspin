import javax.swing.JOptionPane;


public class HumanPlayer extends Player {
	int askCount=0;
	int bet=0;
	private int decision;

	public HumanPlayer(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub
	}
	
	public void makeDecision(){
		
		decision = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter decision here. Fold=1"));
		System.out.println(decision);
		
	}
public int getDecision(){
		
		return decision;
	}
	
	
	public int getBet(int currentBet){
		if(askCount<1){
			bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Current Bet is: "+currentBet+" Enter amount here"));
			
			askCount++;
		}

		

		return bet;
				
	}
	
	public void finaliseBet(int currentBet){
		currentMoney = currentMoney - bet;
	}
	
	public void resetCounters(){
		bet=20;
		askCount=0;
	}
	
}
