import javax.swing.JOptionPane;


public class HumanPlayer extends Player {

	public HumanPlayer(String Name, int Chips, Card[] currentHand) {
		super(Name, Chips, currentHand);
		// TODO Auto-generated constructor stub
	}
	
	public int getBet(){
		
		int bet = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter amount here"));
		currentMoney = currentMoney - bet;
		return bet;
				
	}
	
}
