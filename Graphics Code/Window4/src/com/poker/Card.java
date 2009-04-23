package com.poker;

import java.lang.reflect.Field;

public class Card {
        
        public final static int HEARTS = 1; 
        public final static int SPADES = 0;   
        public final static int DIAMONDS = 2;
        public final static int CLUBS = 3;
           
        public final static int ACE = 1;      // Codes for the non-numeric cards.
        public final static int JACK = 11;    //   Cards 2 through 10 have their 
        public final static int QUEEN = 12;   //   numerical values for their codes.
        public final static int KING = 13;
        
        private final int suit;
        private final int value;
        
        public Card(int theValue, int theSuit) {
                value = theValue;
                suit = theSuit;
        }
        
        public String getPngName() {
                char value_char1;
                char value_char2;
                
                // Get suit as a char.
                String suit_str = Integer.toString(suit);
                char suit_char = suit_str.charAt(0);
                
                // Get value as two char's.
                String value_str = Integer.toString(value);
                if(value_str.length() == 1){
                        value_char1 = '0';
                        value_char2 = value_str.charAt(0);
                }else{
                        value_char1 = value_str.charAt(0);
                        value_char2 = value_str.charAt(1);
                }
                // All card images start with letter 'a'
                return new String(new char[]{'a',suit_char,value_char1,value_char2});
        }
        /**
        public String getPngName() {
        String str = Integer.toString(i);
        char c = str.charAt(0);
        return new String(new char[]{suit,value});
        }
        */
        
        public int getImageResourceId(){
        Field f;
        int id = -1;
        try {
                f = R.drawable.class.getDeclaredField(getPngName());
                id = f.getInt(null);
        } catch (SecurityException e) {
                e.printStackTrace();
        } catch (NoSuchFieldException e) {
                e.printStackTrace();
        } catch (IllegalArgumentException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        }
        return id;
        }
        
        public int getSuit() {
              return suit;
        }

        public int getValue() {
              return value;
        }

        public String getSuitAsString() {
              switch ( suit ) {
              case SPADES:   return "Spades";
              case HEARTS:   return "Hearts";
              case DIAMONDS: return "Diamonds";
              case CLUBS:    return "Clubs";
              default:       return "Card Not Found";
              }
        }
        
        public String getValueAsString() {
              
                 switch ( value ) {
                 //case 0:   return "Ace";
                 case 0:   return "2";
                 case 1:   return "3";
                 case 2:   return "4";
                 case 3:   return "5";
                 case 4:   return "6";
                 case 5:   return "7";
                 case 6:   return "8";
                 case 7:   return "9";
                 case 8:  return "10";
                 case 9:  return "Jack";
                 case 10:  return "Queen";
                 case 11:  return "King";
                 case 12:  return "Ace";
                 default:  return "Card Not Found";
              }
        }
        
        public String toString() {
                return getValueAsString() + " of " + getSuitAsString();
        }
}
