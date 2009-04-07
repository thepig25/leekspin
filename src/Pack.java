import java.util.Random;


public class Pack {

	Card[] thisPack;
	Card[] temp1;
	int CardsDealt=0;
	
	public void createPack(){
		
		thisPack = new Card[52];
	
		
		int i=0;
		int cardCount=0;
		
	while (i<thisPack.length){
		for(int suitCount=0;suitCount<4;suitCount++){
					
					for (;cardCount<13;cardCount++){
					Card temp = new Card(cardCount,suitCount);
					//System.out.println(suitCount + " " + cardCount);
					//System.out.println(temp.getSuitAsString());
					//System.out.println(temp.getValueAsString());
					thisPack[i] = temp;
					i++;
		
					}
		cardCount=0;
	}
	}
}
	
	public void Shuffle(){
		Random rnd = new Random();
//		System.out.println("Shuffling");
//		System.out.println("..");
//		System.out.println("..");
//		System.out.println("..");
//		System.out.println("..");
//		System.out.println("..");
		for (int i = thisPack.length - 1; i > 0; i--)
		{
			int swapWithPos = rnd.nextInt(i + 1);
			Card tmp = thisPack[i];
			thisPack[i] = thisPack[swapWithPos];
			thisPack[swapWithPos] = tmp;
		}
		
	}
	
	public Card[] getPack(){
		return thisPack;
	}
	
	public Card[] getSpecial(){ // for testing out with particular hands
		Card [] special = new Card [5];
		special[0] = thisPack[0];
		special[1] = thisPack[13];
		special[2] = thisPack[26];
		special[3] = thisPack[11];
		special[4] = thisPack[31];
		return special;
		
	}
	
	public Card[] getFirstPocketSpecial(){
		Card [] special = new Card [2];
		special[0] = thisPack[4];
		special[1] = thisPack[17];
		return special;
	}
	
	public Card[] getSecondPocketSpecial(){
		Card [] special = new Card [2];
		special[0] = thisPack[3];
		special[1] = thisPack[12];
		return special;
	}

	public Card[] getCommmunitySpecial(){
		Card [] special = new Card [3];
		special[0] = thisPack[18];
		special[1] = thisPack[5];
		special[2] = thisPack[0];
	return special;
	}
	
	public Card[] getTurnSpecial(){
		Card [] special = new Card [1];
		special[0] = thisPack[34];
		return special;
	}
	
	public Card[] getRiverSpecial(){
		Card [] special = new Card [1];
		special[0] = thisPack[13];
		return special;
	}
	
	
	public void printPack(){
		
		
		for (int i = thisPack.length - 1; i >= 0; i--){ 
			//Card temp = thisPack[i];
			
			System.out.println(thisPack[i].getValueAsString()+ " of " + thisPack[i].getSuitAsString());
			
		}
	}
	public Card[] getHand(){
		Card [] Hand = dealCards(thisPack);
		return Hand;
	}
	
	public Card getOneCard(){
		Card  []Hand = dealOneCard(thisPack);
		Card oneCard= Hand[0];
		return oneCard;
	}
	
	public Card[] dealOneCardArray(){
		Card[] temp = new Card[1];
		temp[0]=getOneCard();
		return temp;
	}
	public Card[] dealThreeCardArray(){
		Card[] temp = new Card[3];
		temp[0]=getOneCard();
		temp[1]=getOneCard();
		temp[2]=getOneCard();
		return temp;
	}
	
	
	
	public void printHand(){
		Card[] Hand = dealCards(thisPack);
		
		
			
		if(CardsDealt<50){
		System.out.println("Cards dealt are: ");
		}
		for (int i =Hand.length - 1; i >= 0; i--){ 
			if(CardsDealt>=50){
				break;
			}
			System.out.println(Hand[i].getValueAsString()+ " of " + Hand[i].getSuitAsString());
	}
		
	}
		
		
		public Card[] dealCards(Card[] temp1){
			Card[] temp2;
			
			
			
				temp2 = new Card[5];	
			
			
			for(int i=0;i<temp2.length;i++){
			if (CardsDealt>=50){
				//System.out.println("You have requested another hand but there are not enough cards left to make a Hand!! - dummy");
				//Card secondLast = temp1[50];
				//Card Last = temp1[51];
				//temp2[i] = temp1[CardsDealt];
				//System.out.println("In case you're wondering, the last two cards left are a " + secondLast.getValueAsString() + " of " + secondLast.getSuitAsString()+" and a " +Last.getValueAsString()+" of "+Last.getSuitAsString()); 
				//break;
				//createPack();
				//Shuffle();
				CardsDealt=0;
			}
			
				temp2[i] = temp1[CardsDealt];
				CardsDealt++;
			
		}
			if(CardsDealt<50){
				System.out.println("Number of cards left in deck after this deal is: " + (temp1.length-CardsDealt));
			}
			
			
		
		return temp2;
		
	}
		
		public Card[] dealOneCard(Card[] temp1){
			Card[] temp2;
			
			
			
				temp2 = new Card[1];	
			
			
			for(int i=0;i<temp2.length;i++){
			if (CardsDealt>=50){
				//System.out.println("You have requested another hand but there are not enough cards left to make a Hand!! - dummy");
				//Card secondLast = temp1[50];
				//Card Last = temp1[51];
				//temp2[i] = temp1[CardsDealt];
				//System.out.println("In case you're wondering, the last two cards left are a " + secondLast.getValueAsString() + " of " + secondLast.getSuitAsString()+" and a " +Last.getValueAsString()+" of "+Last.getSuitAsString()); 
				//break;
				//createPack();
				//Shuffle();
				CardsDealt=0;
			}
			
				temp2[i] = temp1[CardsDealt];
				CardsDealt++;
			
		}
			if(CardsDealt<50){
				System.out.println("Number of cards left in deck after this deal is: " + (temp1.length-CardsDealt));
			}
			
			
		
		return temp2;
		
	}
		
		
}