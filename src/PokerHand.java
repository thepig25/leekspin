
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
	int firstMatch;
	int secondMatch;
	int flushCount=0;
	int flushSuit;
	boolean almostFlush=false;
	boolean printedBoolean=false;
	int[][] cardStats = new int[10][3];



	public PokerHand(Pack Cardies) {
		// TODO Auto-generated constructor stub

		Cardies.createPack();
		Cardies.Shuffle();// for normal use
		//cardStats = new int[10][3];
		//theseCards = Cardies.getSpecial(); // used for testing. creates hands with certain cards. At the moment it is drawing cards which form a straight flush.
		for(int j1=0;j1<testAmount;j1++){//run this x many times
			theseCards = Cardies.getHand(); // for normal use
			
			//				for(int i=0;i<theseCards.length;i++){
			//					System.out.println(theseCards[i].getValue());
			//					System.out.println(theseCards[i].getValueAsString()+ " of " + theseCards[i].getSuitAsString());
			//				}
			mySortedCards = sortedCards(theseCards);			
			int bestBoolean=testBooleans();
			for(int j=0;j<3;j++){

				matchedCards = new int[5];
				int bestDiscard=0;
				String bestCardValue="";
				String bestCardSuit="";
				int bestCardi=0;

				for(int i=0;i<mySortedCards.length;i++){
					System.out.println(mySortedCards[i].getValue());
					System.out.println(mySortedCards[i].getValueAsString()+ " of " + mySortedCards[i].getSuitAsString());
				}


				for(int i=0;i<mySortedCards.length;i++){
					if(shouldDiscard(i)>bestDiscard){
						bestDiscard = shouldDiscard(i);
						bestCardValue = mySortedCards[i].getValueAsString();
						bestCardSuit = mySortedCards[i].getSuitAsString();
						bestCardi=i;
					}

				}
				System.out.println("flush count "+flushCount);
				System.out.println("Most likely card to discard is " +bestCardValue+" of "+bestCardSuit+" with a discard value of "+bestDiscard);

				System.out.println("Discarding card....");

				mySortedCards[bestCardi]=Cardies.getOneCard();
				Card[]temp = mySortedCards;
				printedBoolean=false;
				mySortedCards = sortedCards(temp);

				if(bestBoolean>testBooleans()){ // if the previous hand before discarding was worse, do this
					int tempStat = cardStats[bestBoolean][j];
					System.out.println("Improvement count "+tempStat + "Best Boolean : " + bestBoolean);
					tempStat++;
					cardStats[bestBoolean][j]=tempStat;
					
				}
				

			}
			bestBoolean=0;
			
		}
		//		System.out.println("Pair " + Pair + "Percentage: " + (Pair/testAmount)*100);
		//		System.out.println("twoPair " + twoPair + "Percentage: " + (twoPair/testAmount)*100);
		//		System.out.println("Threes " + Threes + "Percentage: " + (Threes/testAmount)*100);
		//		System.out.println("Fours " + Fours + "Percentage: " + (Fours/testAmount)*100);
		//		System.out.println("fullHouse "+ fullHouse + "Percentage: " + (fullHouse/testAmount)*100);
		//		System.out.println("Straight "+ Straight+ "Percentage: " + (Straight/testAmount)*100);
		//		
		//		System.out.println("SF " + SF+ "Percentage: " + (SF/testAmount)*100);
		//		System.out.println("SRoyalFlush " + SRoyalFlush+ "Percentage: " + (SRoyalFlush/testAmount)*100);

		System.out.println("Hand		Card 1	Card 2	Card 3	Count");
		System.out.println("Threes		" + ((cardStats[6][2])));
		System.out.println("RoyalFlush	" + ((cardStats[0][0])*100/testAmount)+"	"+((cardStats[0][1])*100/testAmount)+"	"+((cardStats[0][2])*100/testAmount));
		System.out.println("SF		" +  ((cardStats[1][0])*100/testAmount)+"	"+((cardStats[1][1])*100/testAmount)+"	"+((cardStats[1][2])*100/testAmount));
		System.out.println("Four of a Kind	" +  ((cardStats[2][0])*100/testAmount)+"	"+((cardStats[2][1])*100/testAmount)+"	"+((cardStats[2][2])*100/testAmount));
		System.out.println("fullHouse	"+  ((cardStats[3][0])*100/testAmount)+"	"+((cardStats[3][1])*100/testAmount)+"	"+((cardStats[3][2])*100/testAmount));
		System.out.println("Flush		" +  ((cardStats[4][0])*100/testAmount)+"	"+((cardStats[4][1])*100/testAmount)+"	"+((cardStats[4][2])*100/testAmount));
		System.out.println("Straight	"+ ((cardStats[5][0])*100/testAmount)+"	"+((cardStats[5][1])*100/testAmount)+"	"+((cardStats[5][2])*100/testAmount));
		System.out.println("Threes		" + ((cardStats[6][0])*100/testAmount)+"	"+((cardStats[6][1])*100/testAmount)+"	"+((cardStats[6][2])*100/testAmount)+"	"+Threes);
		System.out.println("twoPair		" + ((cardStats[7][0])*100/testAmount)+"	"+((cardStats[7][1])*100/testAmount)+"	"+((cardStats[7][2])*100/testAmount)+"	"+twoPair);
		System.out.println("Pair		" + ((cardStats[8][0])*100/testAmount)+"	"+((cardStats[8][1])*100/testAmount)+"	"+((cardStats[8][2])*100/testAmount));
		System.out.println("Nothing		" + ((cardStats[9][0])*100/testAmount)+"	"+((cardStats[9][1])*100/testAmount)+"	"+((cardStats[9][2])*100/testAmount));




	}

	public int testBooleans(){
		//tests for different winning hands.
		if(isRoyalFlush()){
			if(printedBoolean==false){
				System.out.println("Royal Flush!!!!");
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
				System.out.println("Found four of a kind");
				Fours++;
				printedBoolean=true;
			}

			return 2;
		}

		if(isFullHouse()){
			if(printedBoolean==false){
				System.out.println("Found a full house");
				fullHouse++;	
				printedBoolean=true;
			}

			return 3;
		}
		if(isFlush()){
			if(printedBoolean==false){
				System.out.println("Found a flush");
				Flush++;
				printedBoolean=true;
			}

			return 4;
		}
		if(isStraight()){
			if(printedBoolean==false){
				System.out.println("Found a straight");
				Straight++;	
				printedBoolean=true;
			}

			return 5;
		}
		if(isThreeOfAKind()){
			if(printedBoolean==false){
				System.out.println("Found three of a kind");
				Threes++;
				printedBoolean=true;
			}

			return 6;
		}
		if(isTwoPair()){
			if(printedBoolean==false){
				System.out.println("Found two pair");
				twoPair++;
				printedBoolean=true;
			}

			return 7;
		}

		if(isOnePair()){
			if(printedBoolean==false){
				System.out.println("Found a pair");
				Pair++;
				printedBoolean=true;
			}

			return 8;
		}

		if(!isStraight()&&!isFullHouse()&&!isFourOfAKind()&&!isThreeOfAKind()
				&&!isTwoPair()&&!isOnePair()&&!isFlush()){
			if(printedBoolean==false){
				System.out.println("Found nothing");
				printedBoolean=true;
			}

			return 9;
		}
		else{
			return 9;
		}


	}



	public Card[]  sortedCards(Card[] hand){ // sorts cards to test for a straight
		boolean doMore = true;
		int n = hand.length;
		while (doMore) {
			n--;
			doMore = false; 


			for (int i=0; i<n; i++) {
				if(((hand[i]).getValue())>((hand[i+1]).getValue())){
					Card big = hand[i];
					hand[i] = hand[i+1];
					hand[i+1] = big;
					doMore=true;
				}
			}
		}
		System.out.println("Sorted:");
		for(int i=0;i<hand.length;i++){
			System.out.println(hand[i].getValueAsString()+ " of " + hand[i].getSuitAsString());
		}

		return hand;
	}

	public boolean isOnePair(){

		for(int i=0;i<mySortedCards.length-1;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())){
				firstMatch = theseCards[i].getValue();
				return true;
			}

		}

		return false;

	}
	public boolean isTwoPair(){

		for(int i=0;i<mySortedCards.length-1;i++){

			if((mySortedCards[i]).getValue()==(mySortedCards[i+1].getValue())&&isOnePair()){
				if((mySortedCards[i]).getValue()!=firstMatch){
					secondMatch =mySortedCards[i].getValue();
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


		if(mySortedCards[0].getValue()==0&&mySortedCards[1].getValue()==9&&mySortedCards[2].getValue()==10
				&& mySortedCards[3].getValue()==11 && mySortedCards[4].getValue()==12){
			smallStraight=true;
			return smallStraight;
		}
		for(int i=0;i<mySortedCards.length-1;i++){
			if(((mySortedCards[i+1]).getValue())-((mySortedCards[i]).getValue())==1){
				smallStraight=true; // holds true for every iteration of for loop if value is always greater than the next
			}

			else{
				smallStraight=false;
				break; // no point in keeping checking if it breaks down once
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
		if(mySortedCards[0].getValue()==0&&mySortedCards[1].getValue()==9&&mySortedCards[2].getValue()==10
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


}
