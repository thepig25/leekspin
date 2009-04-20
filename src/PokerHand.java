

public class PokerHand extends Pack { // Not really working for this weeks assignment - Doesn't pick up on improved hands for anything more than 2 pair




	Card[] theseCards;
	Card[] mySortedCards;
	
	int[] matchedCards;
	double Pair=0;
	double twoPair=0;
	double Threes=0;
	double Fours=0;
	double fullHouse=0;
	double Straight=0;
	double Flush=0;
	double SF=0;
	double SRoyalFlush=0;
	int testAmount = 1;
	int threeCard;
	int fourCard;
	int matchCardCount=0;
	int firstMatch=0;
	int secondMatch;
	int flushCount=0;
	int flushSuit;
	boolean almostFlush=false;
	boolean printedBoolean=false;
	int[][] cardStats = new int[10][3];
	int highestSecondPair;
	



		public PokerHand(Card[] Hand) {
		// TODO Auto-generated constructor stub
			Card[] theseCards = Hand; 	
			mySortedCards = sortedCards(theseCards);
			//System.out.println("PokerHand created is:");
			//for(int i=0;i<Hand.length;i++){
				
				//System.out.println(Hand[i].getValueAsString()+" "+Hand[i].getSuitAsString());
			//}
			
		}
		
	public int testBooleans(){
		//tests for different winning hands.
		if(isRoyalFlush()){
			if(printedBoolean==false){
				//System.out.println("Royal Flush!!!!");
				SRoyalFlush++;
				printedBoolean=true;
			}


			return 0;
		}

		if(isStraightFlush()){
			if(printedBoolean==false){
				System.out.println("Straight flush");
				printedBoolean=true;
				SF++;
			}


			return 1;
		}

		if(isFourOfAKind()){
			if(printedBoolean==false){
				//System.out.println("Found four of a kind");
				Fours++;
				printedBoolean=true;
			}

			return 2;
		}

		if(isFullHouse()){
			if(printedBoolean==false){
				//System.out.println("Found a full house");
				fullHouse++;	
				printedBoolean=true;
			}

			return 3;
		}
		if(isFlush()){
			if(printedBoolean==false){
				//System.out.println("Found a flush");
				Flush++;
				printedBoolean=true;
			}

			return 4;
		}
		if(isStraight()){
			if(printedBoolean==false){
				//System.out.println("Found a straight");
				Straight++;	
				printedBoolean=true;
			}

			return 5;
		}
		if(isThreeOfAKind()){
			if(printedBoolean==false){
				//System.out.println("Found three of a kind");
				Threes++;
				printedBoolean=true;
			}

			return 6;
		}
		if(isTwoPair()){
			if(printedBoolean==false){
				//System.out.println("Found two pair");
				twoPair++;
				printedBoolean=true;
			}

			return 7;
		}

		if(isOnePair()){
			if(printedBoolean==false){
				//System.out.println("Found a pair");
				Pair++;
				printedBoolean=true;
			}

			return 8;
		}

		if(!isStraight()&&!isFullHouse()&&!isFourOfAKind()&&!isThreeOfAKind()
				&&!isTwoPair()&&!isOnePair()&&!isFlush()){
			if(printedBoolean==false){
				//System.out.println("Found nothing");
				printedBoolean=true;
			}

			return 9;
		}
		else{
			return 9;
		}


	}



	public Card[]  sortedCards(Card[] hand){ // sorts cards 
		boolean doMore = true;
		int n = hand.length;
		while (doMore) {
			n--;
			doMore = false; 


			for (int i=0; i<n; i++) {
				if(((hand[i]).getValue())>((hand[i+1]).getValue())){
					/*System.out.println(hand[i].getValue()+" "+hand[i].getSuitAsString());
					System.out.println(hand[i+1].getValue()+" "+hand[i+1].getSuitAsString());
					System.out.println(" ");*/
					Card big = hand[i];
					hand[i] = hand[i+1];
					hand[i+1] = big;
					doMore=true;
					/*System.out.println(hand[i].getValue()+" "+hand[i].getSuitAsString());
					System.out.println(hand[i+1].getValue()+" "+hand[i+1].getSuitAsString());
					System.out.println(" ");*/
				}
				
			}
			/*for(int j=0;j<hand.length;j++){
				System.out.println(hand[j].getValueAsString());*/
				
			/*}
			System.out.println(" ");*/
		}
		/*System.out.println("Sorted:");
		for(int i=0;i<hand.length;i++){
			System.out.println(hand[i].getValueAsString()+ " of " + hand[i].getSuitAsString());
		}*/

		return hand;
	}

	public boolean isOnePair(){

		for(int i=0;i<mySortedCards.length-1;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())){
				
				firstMatch = mySortedCards[i].getValue();
				//System.out.println("Pair is:"+firstMatch);
				return true;
			}

		}

		return false;

	}
	public boolean isTwoPair(){
		isOnePair();
	
	
	  for(int i=0;i<mySortedCards.length-1;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())){
				if((mySortedCards[i]).getValue()!=firstMatch&&mySortedCards[i+1].getValue()!=firstMatch){
					secondMatch=mySortedCards[i].getValue();
					
					highestSecondPair=secondMatch;
					return true;
				}

			}
		}
		return false;

	}
	public boolean isThreeOfAKind(){

		for(int i=0;i<3;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())&&(mySortedCards[i+1]).getValue()==(mySortedCards[i+2].getValue())){
				threeCard = mySortedCards[i].getValue();
				return true;

			}

		}

		return false;

	}
	public boolean isFourOfAKind(){
		for(int i=0;i<2;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())&&(mySortedCards[i+1]).getValue()==(mySortedCards[i+2].getValue())&&(mySortedCards[i+2]).getValue()==(mySortedCards[i+3].getValue())){
				fourCard = mySortedCards[i].getValue();
				return true;

			}

		}

		return false;

	}
	public boolean isFullHouse(){
		for(int i=0;i<mySortedCards.length-1;i++){
			if(mySortedCards[i].getValue()==mySortedCards[i+1].getValue()&&mySortedCards[i].getValue()!=threeCard&&isThreeOfAKind()){
				return true;
			}
		}

		return false;
	}
	public boolean isStraight(){
		boolean smallStraight=false;
		//Card[] theseSortedCards = sortedCards(theseCards);
		

		if(mySortedCards[0].getValue()==0&&mySortedCards[1].getValue()==1&&mySortedCards[2].getValue()==2
				&& mySortedCards[3].getValue()==3 && mySortedCards[4].getValue()==12){
			smallStraight=true;
			return smallStraight;
		}
		else{
			for(int i=0;i<mySortedCards.length-1;i++){
				if(((mySortedCards[i+1]).getValue())-((mySortedCards[i]).getValue())==1){
					smallStraight=true; // holds true for every iteration of for loop if value is always greater than the next
					//System.out.println("Testing for straight");
				}

				else{
					smallStraight=false;
					break; // no point in keeping checking if it breaks down once
				}
		}
		
		}

		return smallStraight;


	}
	public boolean isFlush(){


		boolean noFlush=false;
		for(int i=0;i<mySortedCards.length-1;i++){
			if(((mySortedCards[i]).getSuit()) == ((mySortedCards[i+1]).getSuit())){
				flushCount++;
				//System.out.println("Current Flush count: "+flushCount);
				flushSuit = mySortedCards[i].getSuit();
				//smallFlush=true; // holds true for every iteration of for loop if suit is always the same as the next
			}
			else{
				noFlush=true;
				//break;
			}
		}
		if(flushCount>=2){
			almostFlush=true;
		}
		if(noFlush==true){
			flushCount=0;
			return false;
		}
		flushCount=0;
		return true;

	}

	public boolean isStraightFlush(){
		if(isFlush()&&isStraight()){
			return true;
		}

		return false;
	}

	public boolean isRoyalFlush(){
		if(mySortedCards[0].getValue()==8&&mySortedCards[1].getValue()==9&&mySortedCards[2].getValue()==10
				&& mySortedCards[3].getValue()==11 && mySortedCards[4].getValue()==12&&isFlush()){
			return true;
		}
		return false;
	}

	public int shouldDiscard(int cardPosition) {
		int discard;
		if (testBooleans()==0){ // royal flush
			return discard = 0;
		}
		if (testBooleans()==1){ //both very good hands - no need to change - straight flush
			return discard = 0;
		}
		if (testBooleans()==2){//four of a kind
			if (mySortedCards[cardPosition].getValue()!=fourCard){ //four of a kind
				return discard=50;
			}
			else{
				return discard=0;
			}
		}
		if (testBooleans()==3){// full house
			if (mySortedCards[cardPosition].getValue()!=threeCard){ //three of a kind
				return discard=5;
			}
			else{
				return discard=0;
			}
		}
		if (testBooleans()==4){//flush - don't want to change
			return discard=0;
		}
		if (testBooleans()==5){//straight - don't want to change
			return discard=0;
		}
		if (testBooleans()==6){//three of a kind -
			if (mySortedCards[cardPosition].getValue()!=threeCard){
				return discard=50;
			}
			else{
				return discard=0;
			}
		}
		if (testBooleans()==7){//two pair - want to discard single card

			if(mySortedCards[cardPosition].getValue()!=secondMatch&&mySortedCards[cardPosition].getValue()!=firstMatch){
				return discard=75;

			}
			return discard=0;
		}
		if (testBooleans()==8){//pair - want to discard other cards

			if(mySortedCards[cardPosition].getValue()!=firstMatch){
				return discard=75;

			}
			return discard=0;
		}

		if(almostFlush==true&&mySortedCards[cardPosition].getSuit()!=flushSuit&&testBooleans()==9){ // almost flush - doesn't quite work
			return discard=85;
		}

		if(mySortedCards[cardPosition].getValue()==0&&testBooleans()==9){ // got nothing going on except an ace so we'll keep that
			return discard=35;
		}
		else{
			return discard=80;
		}


	}

	public Card getHighCard(){
		
			return mySortedCards[4];
		
	}
	
	public Card getLowestHighCard()  {
		
		if(mySortedCards[0].getValue() == 0){
			for(int i = 0; i < 5 ; i++){
				if(mySortedCards[i].getValue() != 0){
					return mySortedCards[i];
				}
			}
		}
		else {
			return mySortedCards[0];
		}
		System.out.println("I'm throwing an error in the getLowestHighCard method!!");
		return null;
	}
		
		
	
	public Card [] getCard(){
		return mySortedCards;
	}
	
	public void printPokerHand(){
		for (int i=0;i<mySortedCards.length;i++){
			System.out.println(mySortedCards[i].getValueAsString()+" "+mySortedCards[i].getSuitAsString());
		}
	}
	public Card[] firstTwo(){
		Card [] myCard = new Card[2];
		myCard [0] =mySortedCards[0];
		myCard [1] =mySortedCards[1];
		return myCard;
		
	}
	
	public int getLastCardValue(){
		return mySortedCards[4].getValue();
	}
	
	public int getNonPair(){
		if(isTwoPair()){
			for (int i=0;i<mySortedCards.length;i++){
				if(mySortedCards[i].getValue()!= firstMatch&&mySortedCards[i].getValue()!= highestSecondPair){
					return mySortedCards[i].getValue();
				}
			}
		}
		
		return 0;
	}
	
	public int getFirstPair(){
		return firstMatch;
	}
}
