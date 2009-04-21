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
    
    private static Card c_1 = new Card(8,0);
    private static Card c_2 = new Card(4,1);
    private static Card c_3 = new Card(10,2);
    private static Card c_4 = new Card(11,0);
    private static Card c_5 = new Card(3,3);
    
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
        
        int id = 0;
        Field f;
        try {
			f = R.drawable.class.getDeclaredField("card_back");
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

		c1Img.setImageResource(id);
		c2Img.setImageResource(id);
		c3Img.setImageResource(id);
		c4Img.setImageResource(id);
		c5Img.setImageResource(id);
        
		startActivityForResult(new Intent("com.poker.action.START", null),0);
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
        	int resId3 = c_3.getImageResourceId();
            c2Img.setImageResource(resId3);
        }
    };
    private OnClickListener l_check = new OnClickListener() {
        public void onClick(View v) {
        	int resId2 = c_2.getImageResourceId();
            c2Img.setImageResource(resId2);
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

    public static void setCards(String st){
    	for(;;){
    		Card card = new Card(8,0);
        	int resIda = card.getImageResourceId();
            c1Img.setImageResource(resIda);
            
            
            int resId = c_1.getImageResourceId();
            c1Img.setImageResource(resId);
            
            int resId2 = c_2.getImageResourceId();
            c2Img.setImageResource(resId2);
            
            int resId3 = c_3.getImageResourceId();
            c3Img.setImageResource(resId3);
            
            int resId4 = c_4.getImageResourceId();
            c4Img.setImageResource(resId4);
            
            int resId5 = c_5.getImageResourceId();
            c5Img.setImageResource(resId5);
    	}
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
