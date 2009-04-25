package com.poker;

import com.poker.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerWindow extends Activity implements View.OnClickListener{
	
	TextView player_name;
	TextView player_cash;
	private static ImageView card1Img;
    private static ImageView card2Img;
    private static ImageView card3Img;
    private static ImageView card4Img;
    private static ImageView card5Img;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_window);
        player_name = (TextView)this.findViewById(R.id.player_name);
        player_cash = (TextView)this.findViewById(R.id.player_cash);
        card1Img = (ImageView) findViewById(id.card_01);
        card2Img = (ImageView) findViewById(id.card_02);
        card3Img = (ImageView) findViewById(id.card_03);
        card4Img = (ImageView) findViewById(id.card_04);
        card5Img = (ImageView) findViewById(id.card_05);
        if(GameWindow.currentPlayerInfo == 1){
        	player_name.setText("\nYou: "+GameWindow.player1_name);
        	player_cash.setText("Money: "+GameWindow.player1_cash);
        	if(GameWindow.commCardsDealt==true ){
        		// Set all 5 cards.
        		Card card1 = GameWindow.player1_hand[0];
        		Card card2 = GameWindow.player1_hand[1];
        		Card card3 = GameWindow.player1_hand[2];
        		Card card4 = GameWindow.player1_hand[3];
        		Card card5 = GameWindow.player1_hand[4];
        		// Get image id's of cards.
            	int resId1 = card1.getImageResourceId();
            	int resId2 = card2.getImageResourceId();
            	int resId3 = card3.getImageResourceId();
            	int resId4 = card4.getImageResourceId();
            	int resId5 = card5.getImageResourceId();
            	// Draw cards.
                card1Img.setImageResource(resId1);
                card2Img.setImageResource(resId2);
                card3Img.setImageResource(resId3);
                card4Img.setImageResource(resId4);
                card5Img.setImageResource(resId5);
        	}else{
        		// If you've folded, draw all blank.
        		card1Img.setImageResource(GameWindow.blank_card_id);
                card2Img.setImageResource(GameWindow.blank_card_id);
                card3Img.setImageResource(GameWindow.blank_card_id);
                card4Img.setImageResource(GameWindow.blank_card_id);
                card5Img.setImageResource(GameWindow.blank_card_id);
        	}
        }else{
        	if(GameWindow.game_over != true){
            	if(GameWindow.currentPlayerInfo == 2){
                	player_name.setText("\nPlayer 2: "+GameWindow.player2_name);
                	player_cash.setText("Money: "+GameWindow.player2_cash);
                	card1Img.setImageResource(GameWindow.blank_card_id);
                    card2Img.setImageResource(GameWindow.blank_card_id);
                    card3Img.setImageResource(GameWindow.blank_card_id);
                    card4Img.setImageResource(GameWindow.blank_card_id);
                    card5Img.setImageResource(GameWindow.blank_card_id);
                }
                if(GameWindow.currentPlayerInfo == 3){
                	player_name.setText("\nPlayer 3: "+GameWindow.player3_name);
                	player_cash.setText("Money: "+GameWindow.player3_cash);
                	card1Img.setImageResource(GameWindow.blank_card_id);
                    card2Img.setImageResource(GameWindow.blank_card_id);
                    card3Img.setImageResource(GameWindow.blank_card_id);
                    card4Img.setImageResource(GameWindow.blank_card_id);
                    card5Img.setImageResource(GameWindow.blank_card_id);
                }
                if(GameWindow.currentPlayerInfo == 4){
                	player_name.setText("\nPlayer 4: "+GameWindow.player4_name);
                	player_cash.setText("Money: "+GameWindow.player4_cash);
                	card1Img.setImageResource(GameWindow.blank_card_id);
                    card2Img.setImageResource(GameWindow.blank_card_id);
                    card3Img.setImageResource(GameWindow.blank_card_id);
                    card4Img.setImageResource(GameWindow.blank_card_id);
                    card5Img.setImageResource(GameWindow.blank_card_id);
                }
            }
        	else{
        		if(GameWindow.currentPlayerInfo == 2){
                	player_name.setText("\nPlayer 2: "+GameWindow.player2_name);
                	player_cash.setText("Money: "+GameWindow.player2_cash);
                	if(GameWindow.commCardsDealt==true){
                		// Set all 5 cards.
                		Card card1 = GameWindow.player2_hand[0];
                		Card card2 = GameWindow.player2_hand[1];
                		Card card3 = GameWindow.player2_hand[2];
                		Card card4 = GameWindow.player2_hand[3];
                		Card card5 = GameWindow.player2_hand[4];
                		// Get image id's of cards.
                    	int resId1 = card1.getImageResourceId();
                    	int resId2 = card2.getImageResourceId();
                    	int resId3 = card3.getImageResourceId();
                    	int resId4 = card4.getImageResourceId();
                    	int resId5 = card5.getImageResourceId();
                    	// Draw cards.
                        card1Img.setImageResource(resId1);
                        card2Img.setImageResource(resId2);
                        card3Img.setImageResource(resId3);
                        card4Img.setImageResource(resId4);
                        card5Img.setImageResource(resId5);
                	}
                }
                if(GameWindow.currentPlayerInfo == 3){
                	player_name.setText("\nPlayer 3: "+GameWindow.player3_name);
                	player_cash.setText("Money: "+GameWindow.player3_cash);
                	if(GameWindow.commCardsDealt==true){
                		// Set all 5 cards.
                		Card card1 = GameWindow.player3_hand[0];
                		Card card2 = GameWindow.player3_hand[1];
                		Card card3 = GameWindow.player3_hand[2];
                		Card card4 = GameWindow.player3_hand[3];
                		Card card5 = GameWindow.player3_hand[4];
                		// Get image id's of cards.
                    	int resId1 = card1.getImageResourceId();
                    	int resId2 = card2.getImageResourceId();
                    	int resId3 = card3.getImageResourceId();
                    	int resId4 = card4.getImageResourceId();
                    	int resId5 = card5.getImageResourceId();
                    	// Draw cards.
                        card1Img.setImageResource(resId1);
                        card2Img.setImageResource(resId2);
                        card3Img.setImageResource(resId3);
                        card4Img.setImageResource(resId4);
                        card5Img.setImageResource(resId5);
                	}
                }
                if(GameWindow.currentPlayerInfo == 4){
                	player_name.setText("\nPlayer 4: "+GameWindow.player4_name);
                	player_cash.setText("Money: "+GameWindow.player4_cash);
                	if(GameWindow.commCardsDealt==true){
                		// Set all 5 cards.
                		Card card1 = GameWindow.player4_hand[0];
                		Card card2 = GameWindow.player4_hand[1];
                		Card card3 = GameWindow.player4_hand[2];
                		Card card4 = GameWindow.player4_hand[3];
                		Card card5 = GameWindow.player4_hand[4];
                		// Get image id's of cards.
                    	int resId1 = card1.getImageResourceId();
                    	int resId2 = card2.getImageResourceId();
                    	int resId3 = card3.getImageResourceId();
                    	int resId4 = card4.getImageResourceId();
                    	int resId5 = card5.getImageResourceId();
                    	// Draw cards.
                        card1Img.setImageResource(resId1);
                        card2Img.setImageResource(resId2);
                        card3Img.setImageResource(resId3);
                        card4Img.setImageResource(resId4);
                        card5Img.setImageResource(resId5);
                	}
                }
        	}
        }
    }
    
    /** Closes Window. */
	@Override
	public void onClick(View v) {
		finish();
	}
}
