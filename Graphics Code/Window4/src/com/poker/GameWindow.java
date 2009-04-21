package com.poker;

import java.lang.reflect.Field;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.poker.R.id;

public class GameWindow extends Activity{

	private static int blank_card_id;
	private static int bet = -1;
	private ImageButton call;
    private ImageButton check;
    private ImageButton raise;
    private ImageButton fold;
    public static TextView console;
    
    private ImageView card;
    private static ImageView c1Img;
    private static ImageView c2Img;
    private static ImageView c3Img;
    private static ImageView c4Img;
    private static ImageView c5Img;
    private static ImageView pc1Img;
    private static ImageView pc2Img;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_window);
        
        call = (ImageButton)this.findViewById(R.id.but_call);
        call.setOnClickListener(l_call);
        check = (ImageButton)this.findViewById(R.id.but_check);
        check.setOnClickListener(l_check);
        raise = (ImageButton)this.findViewById(R.id.but_raise);
        raise.setOnClickListener(l_raise);
        fold = (ImageButton)this.findViewById(R.id.but_fold);
        fold.setOnClickListener(l_fold);
        
        console = (TextView)this.findViewById(R.id.tv1);
        console.setMovementMethod(new ScrollingMovementMethod()); 
        
        c1Img = (ImageView) findViewById(id.card1);
        c2Img = (ImageView) findViewById(id.card2);
        c3Img = (ImageView) findViewById(id.card3);
        c4Img = (ImageView) findViewById(id.card4);
        c5Img = (ImageView) findViewById(id.card5);
        
        pc1Img = (ImageView) findViewById(id.pcard1);
        pc2Img = (ImageView) findViewById(id.pcard2);
        
        // Get the ID of the blank_card image.
        blank_card_id = 0;
        Field f;
        try {
			f = R.drawable.class.getDeclaredField("card_back");
			blank_card_id = f.getInt(null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// Draw all card images as blank.
		c1Img.setImageResource(blank_card_id);
		c2Img.setImageResource(blank_card_id);
		c3Img.setImageResource(blank_card_id);
		c4Img.setImageResource(blank_card_id);
		c5Img.setImageResource(blank_card_id);
        
		pokerGame test = new pokerGame(2, 2500, 50);
		//startGame();
    }
    
    public void startGame(){
    	// Let the window load for 1/2 a second.
		long startTime = System.currentTimeMillis();
		long curTime;
		for(;;){
			curTime = System.currentTimeMillis();
			if(curTime > (startTime+500)){
				break;
			}
		}
        // Start a new poker game.
        pokerGame test = new pokerGame(2, 2500, 50);
    }
    
    /** Handles Main button selections */
    private OnClickListener l_raise = new OnClickListener() {
        public void onClick(View v) {
        	startActivityForResult(new Intent("com.poker.action.RAISE", null),0);
        }
    };
    private OnClickListener l_call = new OnClickListener() {
        public void onClick(View v) {
        	
        }
    };
    private OnClickListener l_fold = new OnClickListener() {
        public void onClick(View v) {
        }
    };
    private OnClickListener l_check = new OnClickListener() {
        public void onClick(View v) {
        }
    };
    
    /** Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Save")
        	.setIcon(android.R.drawable.ic_menu_save);
        menu.add(0, 1, 0, "Rotate")
        	.setIcon(android.R.drawable.ic_menu_rotate);
        return true;
    }

    /** Handles menu item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 0:
            // Call method to save game.
        	
            return true;
        case 1:
        	if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else{	
            	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            return true;
        }
        return false;
    }
    
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == 0) {
        	String st = Integer.toString(resultCode);
        	String s = "\nRaised by ";
        	String sst = s + st;
        	console.append(sst);
        	bet = resultCode;
        }
    }
    
    public static void setConsoleText(String st){
    	console.append(st);
    	console.computeScroll();
    }

    public static void drawCardImgs(Card[] cards){
    	
    	if(cards.length == 3){
        	Card c1 = cards[0];
        	Card c2 = cards[1];
        	Card c3 = cards[2];
        	int resId = c1.getImageResourceId();
            c1Img.setImageResource(resId);
            resId = c2.getImageResourceId();
            c2Img.setImageResource(resId);
            resId = c3.getImageResourceId();
            c3Img.setImageResource(resId);
            c4Img.setImageResource(blank_card_id);
            c5Img.setImageResource(blank_card_id);
    	}
    	
    	if(cards.length == 4){
        	Card c1 = cards[0];
        	Card c2 = cards[1];
        	Card c3 = cards[2];
        	Card c4 = cards[3];
        	int resId = c1.getImageResourceId();
            c1Img.setImageResource(resId);
            resId = c2.getImageResourceId();
            c2Img.setImageResource(resId);
            resId = c3.getImageResourceId();
            c3Img.setImageResource(resId);
            resId = c4.getImageResourceId();
            c4Img.setImageResource(resId);
            c5Img.setImageResource(blank_card_id);
    	}
    	
    	if(cards.length == 5){
        	Card c1 = cards[0];
        	Card c2 = cards[1];
        	Card c3 = cards[2];
        	Card c4 = cards[3];
        	Card c5 = cards[4];
        	int resId = c1.getImageResourceId();
            c1Img.setImageResource(resId);
            resId = c2.getImageResourceId();
            c2Img.setImageResource(resId);
            resId = c3.getImageResourceId();
            c3Img.setImageResource(resId);
            resId = c4.getImageResourceId();
            c4Img.setImageResource(resId);
            resId = c5.getImageResourceId();
            c5Img.setImageResource(resId);
    	}
    }
    
    public static void drawPocketCardImgs(Card[] cards){
    	Card c1 = cards[0];
    	Card c2 = cards[1];
    	int resId = c1.getImageResourceId();
        pc1Img.setImageResource(resId);
        resId = c2.getImageResourceId();
        pc2Img.setImageResource(resId);
    }
    
    public static int getBet(){
    	int temp_bet=0;
    	//long startTime = System.currentTimeMillis();
    	//long curTime;
    	for(;;){
    		//curTime = System.currentTimeMillis();
    		if(bet >= 0){
    			temp_bet = bet;
        		break;
    		}
    	}
    	return temp_bet;
    }
}
