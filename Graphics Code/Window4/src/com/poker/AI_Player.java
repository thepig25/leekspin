package com.poker;

import android.os.Message;
import java.util.Random;

public class AI_Player extends Player {
	
	int askCount=0;
	int bet = 0;
	private int decision;
	int currentHighBet = 0;
	

	public AI_Player(String Name, int Chips, Card[] currentHand, int tempPlayerID) {
		super(Name, Chips, currentHand, tempPlayerID);
		// TODO Auto-generated constructor stub 
		
	}
	
	
	public int getBet(int highestCurrentBet, int tempbigBlind){
		bigBlind = tempbigBlind;
		currentHighBet = highestCurrentBet;
		if(askCount<1){
		//bet = 20;
		
		// want to reevaluate the computer hand if someone raises after you have made you're bet
		if (highestCurrentBet>bet){ 
			
			
			bet = (highestCurrentBet - bet);
		}
		currentMoney = currentMoney - bet;
		askCount++;
		}
		return bet;
		
	}
	
	public void setBet(int newbet){
		bet = newbet;
	}
	
//	public void makeDecision(){
//		decision=0;
//	}
	
	public int getDecision(){ // public methods for OOP to get our decision and amount to bet (if any)
		
		return getPocketCardDecision(); // raise = 0,call/check = 2 ,fold = 1
	}
	
	public void resetCounters(){
		bet=0;
		askCount=0;
	}
	
	
	/*
	 *  
	 * Fold Call Raise decison
	 * takes in three int which is the percentage of chance of making a FCR decision
	 * then coose a random decision between these and decides to fold call or raise
	 * should add up to 10.0 or 100%
	 * 
	 * The computer shouldn't be very predictable
	 * 
	 * @param	Call	the percentage related to how likely the computer will call
	 * @param	Raise	the percentage related to how likely the computer will raise
	 * @param	Fold	the percentage related to how likely the computer will fold
	 * @return			the decision made by the computer call = 2, raise = 0,fold = 1, default is to call
	*/
	
	public int getFCR(int call, int raise, int fold){
		
		int total = call +raise+fold;
		int amountShouldRaise = 0;
		
		//Adds random amount to the amount the computer should raise by
		Random randomGenerator = new Random();
		int raiseRandomer = randomGenerator.nextInt(2);
		int randomRaise = 0;
		switch (raiseRandomer) {
		case 0: randomRaise = 5; break;
		case 1: randomRaise = 10; break;
		case 2: randomRaise = 20; break;
		}
		
		// this random decides if the computer should change randomRaise to + or -
		// so a good hand might not be raised. or a bad had it might raise (bluff)
		Random randomGenerator1 = new Random();
		int plusOrMinus = randomGenerator1.nextInt(1);
		switch (plusOrMinus) {
		case 0: randomRaise = randomRaise * 1; break;
		case 1: randomRaise = randomRaise * -1; break;
		}
		
		
		
		//get the amount the computer should raise
		if (raise + randomRaise <= 30 ){
			amountShouldRaise = 20 + currentHighBet;
		}
		
		if (raise + randomRaise > 30 && raise < 70){
			amountShouldRaise = 40 + currentHighBet;
		}
		if (raise + randomRaise >= 70 ){
			amountShouldRaise = 60 + currentHighBet;
		}
		
		
		
		//int FCR = total %10; 
		Random randomGenerator2 = new Random();
		int FCR = randomGenerator2.nextInt(10);
		
		if(FCR < call){
			//call = 2
			//check = 3
			System.out.println("Computers decison is call");
			return 2;
		}
		
		if(FCR > call&& FCR <(call+raise)){
			//raise = 0
			System.out.println("Computers decison is raise");
			//setting the raise amount if we bet
			setBet(amountShouldRaise);
			return 0;
		}
		
		if(FCR >(call+raise) && FCR<(call+raise+ fold)){
			//fold = 1
			System.out.println("Computers decison is fold");
			return 1;
		}
		
		System.out.println("Computers decison is deafault call");
		return 2;
	}
	
	/*
	 * this method will just check the players pocket cards.
	 * check if they are a pair.
	 * check if the distance between the cards is low or high that could be
	 * used in a straight.
	 * check to see if the cards are the same suit
	 * 
	 * Adds the values of cards and if it passes a threshold then a decison can be made.
	 */
	public int getPocketCardDecision(){
		
		int highHoleCard = getPlayerHighHoleCard();
		int lowHoleCard = getPlayerLowHoleCard();
		int pocketPair = 0;
		int distance = 3;					// check to see if you can get a straight	
		int distanceMultiplier = 0;
		int flushMultiplier =0;
		int pocketPairMultiplier = 0;
		int lowMoneyMultiplier = 0;			//if low money then be more careful
		int threshold = 13;					//computer will call if over this point
		int thresholdLowerLimit = 5;		// computer will call or raise
		int thresholdHigherLimit = 10;		//computer will raise most likely
		boolean havePocketPair = false;		//check for pockets
		boolean flush = false;				// check to see if a flush would be possible
		
		
		
		Card firstPocketCard = playerHand[0];
		Card secondPocketCard = playerHand[1];
		
		int firstCardValue = firstPocketCard.getValue();
		int secondCardValue = secondPocketCard.getValue();
		
		//check for pocket pairs
		if(firstCardValue == secondCardValue){
			pocketPair = firstPocketCard.getValue();
			havePocketPair = true;
			pocketPairMultiplier = 7;
			
		}
		
		//check for cards that are close
		if(((highHoleCard - lowHoleCard) < distance) && havePocketPair != true){
			distanceMultiplier = 3;
		}
		
		//checks for cards are same suit
		if(firstPocketCard.getSuit() == secondPocketCard.getSuit()){
			flush = true;
			flushMultiplier = 3;
		}
		
		//checks if computer has lowMoney and should it play this hand
		if(lowMoney()==true){
			lowMoneyMultiplier = -5;
		}
		
		// adds the multipliers together
		int Multipliers = distanceMultiplier + flushMultiplier + pocketPairMultiplier + lowMoneyMultiplier;
		
		/*
		 * This adds the card values together and multipliers and if the total passes
		 * the threshold then the computer knows it has good pocket cards
		 */
		// it should just call
		if((firstCardValue + secondCardValue + Multipliers)>= threshold){
			
			// the total surpasses the theshold by a bit so it should call or maybe raise
			if((firstCardValue + secondCardValue + Multipliers)>= (threshold+ thresholdLowerLimit) ){
				
				// the total far surpasses the theshold so it should more likely raise
				if((firstCardValue + secondCardValue + Multipliers)>= (threshold+ thresholdHigherLimit) ){
					return getFCR(4,6,0);
					
				}

				return getFCR(6,4,0);
			}
			
			return getFCR(7,2,1);
		}
		
		else{
			return getFCR(1,0,9);
		}
		
	}
	
	
	public boolean lowMoney(){
		
		if (bigBlind == (currentMoney *4)){
			return true;
		}
		return false;
	}
	
	/*
	 * Make decisions about the hand that you currently have.
	 * 
	 * if it is a pair get the value of the pair and see what the pot it and what the current highest bet.
	 */
	
	public int getHandDecision(){
		
		int threshold = 13;					//computer will call if over this point
		int thresholdLowerLimit = 5;		// computer will call or raise
		int thresholdHigherLimit = 10;		//computer will raise most likely
		int pocketPairMultiplier = 0;
		int myPairMultiplier = 0;
		int myHighCardMultiplier = 0;
		int myLowCardMultiplier = 0;
		
		
		
		//highcard
		if( bestBoolean == 9){
			
			
			if(getPlayerHighHoleCard() == bestPokerHand[4].getValue()){
				myHighCardMultiplier = 2;
			}
			
			else if(getPlayerLowHoleCard() == bestPokerHand[3].getValue()){
				myLowCardMultiplier = 1;
			}
			
			
			// if there are only high cards minus 3 from the threshold it has to pass
			int onlyHighCards = -3;
						
			int Multipliers = myHighCardMultiplier + myLowCardMultiplier;
			
			if((getPlayerHighHoleCard() + getPlayerLowHoleCard() + Multipliers)>= (threshold + onlyHighCards)){
				return getFCR(90,0,10);
			}
			
		}
		
		//pair
		if( bestBoolean == 8){
			int pairValue = highestFirstPair;
			boolean havePocketPair = false;		//check for pockets
			boolean haveMyPair = false;		//check for pair on inculding one of your pocket cards
			int pocketPair =0;
			
			//check for pocket pairs
			if(getPlayerHighHoleCard() == getPlayerLowHoleCard()){
				pocketPair = getPlayerHighHoleCard();
				havePocketPair = true;
				pocketPairMultiplier = 7;
				
			}
			
			// check if the pair you have is on the table or in your hand
			if(pairValue == getPlayerHighHoleCard() || pairValue == getPlayerLowHoleCard()){
				myPairMultiplier = 3;
				haveMyPair = true;
			}
			// the pair is on the table so its not much use to you
			else{
				myPairMultiplier = -5;
			}
			
			int Multipliers = pocketPairMultiplier + myPairMultiplier;
			
			if((pairValue + pairValue + Multipliers) >= (threshold)){
				
				// the total surpasses the theshold by a bit so it should call or maybe raise
				if((pairValue + pairValue + Multipliers) >= (threshold+ thresholdLowerLimit) ){
					
					// the total far surpasses the theshold so it should more likely raise
					if((pairValue + pairValue + Multipliers) >= (threshold+ thresholdHigherLimit) ){
						return getFCR(6,4,0);
						
					}

					return getFCR(7,3,0);
				}
			
			
			return getFCR(80,20,0);
			
			}
			
			else{
				return getFCR(80,10,10);
			}
		}
		
		//two pair
		if( bestBoolean == 7){
			int firstPairValue = highestFirstPair;
			int secondPairValue = highestSecondPair;
			int nonPairValue = nonPair;
			int myPair = 0;
			int faceCardMutliplier = 0;
			
			//check that you might have both the other cards in you pocket card that make your two pair
			if( ( firstPairValue == getPlayerHighHoleCard() && secondPairValue == getPlayerLowHoleCard() ) ||  ( secondPairValue == getPlayerHighHoleCard() && firstPairValue == getPlayerLowHoleCard() ) ){
				
				return getFCR(5,5,0);
			}
			
			//check that you might have 1 the other cards in you pocket card that make your two pair
			else if( (firstPairValue == getPlayerHighHoleCard() || secondPairValue == getPlayerLowHoleCard() ) || ( secondPairValue == getPlayerHighHoleCard() || firstPairValue == getPlayerLowHoleCard() ) ){
				
				/*
				 * this will tell me which of the pair cards the AI has in it's hand.
				 * check is the hand on the table higher than the hand in my hand
				 */
					if (firstPairValue == getPlayerHighHoleCard()){
						myPair = firstPairValue;
					}
					if (firstPairValue == getPlayerLowHoleCard()){
						myPair = firstPairValue;
					}
					
					if (secondPairValue == getPlayerHighHoleCard()){
						myPair = secondPairValue;
					}
					if (secondPairValue == getPlayerLowHoleCard()){
						myPair = secondPairValue;
					}
					
					if(myPair == firstPairValue){
						if(myPair > 10){
							faceCardMutliplier = 3;
						}
					if( (myPair - firstPairValue) > 5 ){
							faceCardMutliplier = faceCardMutliplier+2;
							
					}
				}
				
			}
			
			// check if i have a good kicker
			else if(nonPairValue == 12 || nonPairValue == 11){}
			int Multipliers = myPair + faceCardMutliplier + pocketPairMultiplier;
			
			if((myPair + Multipliers) >= (threshold)){
					
					// the total surpasses the theshold by a bit so it should call or maybe raise
					if((myPair + Multipliers) >= (threshold+ thresholdLowerLimit) ){
						
						// the total far surpasses the theshold so it should more likely raise
						if((myPair + Multipliers) >= (threshold+ thresholdHigherLimit) ){
							return getFCR(6,4,0);
							
						}
	
						return getFCR(7,3,0);
					}
				
				
				return getFCR(80,20,0);
				
				}
			
			
		}
		//triples
		if( bestBoolean == 6){
			return getFCR(60,40,0);
		}
		if( bestBoolean == 5){
			return getFCR(50,50,0);
		}
		if( bestBoolean == 4){
			return getFCR(40,60,0);
		}
		if( bestBoolean == 3){
			return getFCR(30,70,0);
		}
		if( bestBoolean == 2){
			return getFCR(20,80,0);	
		}
		if( bestBoolean == 1){
			return getFCR(20,80,0);
		}
		if( bestBoolean == 0){
			return getFCR(10,90,0);
		}
		return getFCR(90,10,0);
	
		
	}
	
	
}
