package cards;

/**
 * Ciaran McElhatton - 05365791
 */
public class PlayingCard {

	private String suit;
	private int value;
	private String simpleName;
	
	public PlayingCard(String s, String name, int v){
		suit = s;
		simpleName = name;
		value = v;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public String getSimpleName(){
		return simpleName;
	}
	
	public int getRoyalValue(){
		if(simpleName.equalsIgnoreCase("jack")){
			return 1;
		}
		if(simpleName.equalsIgnoreCase("queen")){
			return 2;
		}
		if(simpleName.equalsIgnoreCase("king")){
			return 3;
		}
		return 0;
	}
}
