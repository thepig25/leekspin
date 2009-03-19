import java.util.Random;


public class pokerGameMethods extends Pack {

	
public int chooseFirstDealer(int amtPlayers){
		
		Random rand = new Random();
		int min=0;
		int max=amtPlayers;

		
		int randomNum = rand.nextInt(max - min ) + min;

		
		return randomNum;
		
	}

public int chooseNextDealer(int amtPlayers, int currentDealer){
	
	if(currentDealer==amtPlayers-1){ //if current dealer is last person in player array, go back to the first person
		return 0;
	}
	else{
		return currentDealer+1; // otherwise dealer is previous dealer +1
	}
}



	
}

