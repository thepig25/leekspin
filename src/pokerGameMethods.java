import java.util.Random;


public class pokerGameMethods extends Pack {

	
public int chooseDealer(int amtPlayers){
		
		Random rand = new Random();
		int min=0;
		int max=amtPlayers;

		
		int randomNum = rand.nextInt(max - min ) + min;

		
		return randomNum;
		
	}
	
}

