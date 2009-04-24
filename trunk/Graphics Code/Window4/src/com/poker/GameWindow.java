package com.poker;

import java.lang.reflect.Field;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.poker.R.id;

public class GameWindow extends Activity{
	
	/** True if the raise amount has been chosen. */
	private static boolean pressed;
	static boolean any_pressed;
	static boolean first = true;
	static boolean raised_done = false;
	static int command;
	
	/** Used to communicate between threads. */
    protected static final int GUIUPDATEIDENTIFIER = 0x101;
    protected static final int DRAWCOMMUNITYCARDS = 0x102;
    protected static final int DRAWPLAYERCARDS = 0x103;
    protected static final int SETBET = 0x104;
    protected static final int DRAWBLANKCARDS = 0x105;
    protected static final int SETPOTTEXT = 0x106;
	
    /** Choice of bets. */
    public static String[] bets = {"20","40","60"};
    
	private static int blank_card_id;
	private static int bet = 0;
	private Button start;
	private ImageButton call;
    private ImageButton check;
    private ImageButton raise;
    private ImageButton fold;
    public static TextView console;
    public static TextView pot_tv;
    
    private static ImageView c1Img;
    private static ImageView c2Img;
    private static ImageView c3Img;
    private static ImageView c4Img;
    private static ImageView c5Img;
    private static ImageView pc1Img;
    private static ImageView pc2Img;
    public pokerGame myGame;
    
    /** Called when the Activity (UI) is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_window_begin);
        
        start = (Button)this.findViewById(R.id.but_start);
        start.setOnClickListener(l_start); 
        
        // Create card images.
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
		pc1Img.setImageResource(blank_card_id);
		pc2Img.setImageResource(blank_card_id);
		
    }
    
    //private final Handler mHandler = new Handler();
    
    public void startGame(){
    	
    	setContentView(R.layout.game_window);
    	
    	pressed = false;
    	
        console = (TextView)this.findViewById(R.id.tv1);
        console.setMovementMethod(new ScrollingMovementMethod());
        pot_tv = (TextView)this.findViewById(R.id.tvpot);
        
        // Create card images.
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
		pc1Img.setImageResource(blank_card_id);
		pc2Img.setImageResource(blank_card_id);
        
        call = (ImageButton)this.findViewById(R.id.but_call);
        call.setOnClickListener(l_call);
        check = (ImageButton)this.findViewById(R.id.but_check);
        check.setOnClickListener(l_check);
        raise = (ImageButton)this.findViewById(R.id.but_raise);
        raise.setOnClickListener(l_raise);
        fold = (ImageButton)this.findViewById(R.id.but_fold);
        fold.setOnClickListener(l_fold);
    	
        Thread t = new Thread() {
        	public void run() {
        		// Start a new game in a separate thread.
        		myGame = new pokerGame(4, 2500, 50);
        	}
        };
        t.start();
    }
    
    /** Handles messages from the game thread. */
    static Handler myViewUpdateHandler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
              switch (msg.what) {
                   case GameWindow.GUIUPDATEIDENTIFIER:
                	   	String temp = (String)msg.obj;
                        setConsoleText(temp);
                        break;
                        
                   case GameWindow.DRAWCOMMUNITYCARDS:
                        Card[] temp_cards = (Card[])msg.obj;
                        drawCardImgs(temp_cards);
                        break;
                        
                   case GameWindow.DRAWPLAYERCARDS:
                       Card[] temp_player_cards = (Card[])msg.obj;
                       drawPocketCardImgs(temp_player_cards);
                       break;
                       
                   case GameWindow.SETBET:
                       String[] temp_strings = (String[])msg.obj;
                       bets = temp_strings;
                       break;
                       
                   case GameWindow.DRAWBLANKCARDS:
                	   pc1Img.setImageResource(blank_card_id);
               		   pc2Img.setImageResource(blank_card_id);
                       break;
                       
                   case GameWindow.SETPOTTEXT:
                	   String temp_string1 = (String)msg.obj;
                	   pot_tv.setText(temp_string1);
                       break;
              }
              super.handleMessage(msg);
         }
    };
    
    /** Handles Main button selections */
    private OnClickListener l_start = new OnClickListener() {
        public void onClick(View v) {
        	startGame();
        }
    };
    private OnClickListener l_raise = new OnClickListener() {
        public void onClick(View v) {
        	if(first==true){
        		command = 0;
            	any_pressed = true;
            	first = false;
        	}else{
        		startActivityForResult(new Intent("com.poker.action.RAISE", null),0);
        		command = 0;
        		any_pressed = true;
        	}
        }
    };
    private OnClickListener l_call = new OnClickListener() {
        public void onClick(View v) {
        	command = 2;
        	any_pressed = true;
        }
    };
    private OnClickListener l_fold = new OnClickListener() {
        public void onClick(View v) {
        	command = 1;
        	any_pressed = true;
        }
    };
    private OnClickListener l_check = new OnClickListener() {
        public void onClick(View v) {
        	command = 3;
        	any_pressed = true;
        }
    };
    
    /** Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Save")
        	.setIcon(android.R.drawable.ic_menu_save);
        menu.add(0, 1, 0, "Rotate")
        	.setIcon(android.R.drawable.ic_menu_rotate);
        menu.add(0, 2, 0, "About")
        	;
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
        case 2:
        	startActivityForResult(new Intent("com.poker.action.ABOUT", null),1);
        }
        return false;
    }
    
    /** Gets data from a finished Activity.
     * i.e The raise window Activity. */
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == 0) {
        	String s = "Raised by ";
        	String st = Integer.toString(resultCode);
        	String sst = s + st +"\n";
        	console.append(sst);
        	bet = resultCode;
        	pressed = true;
        }
    }

    /** Draws the community cards to screen.
     * Can handle 3, 4 or 5 cards being drawn. */
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
    
    /** Draws the players pocket cards to screen. */
    public static void drawPocketCardImgs(Card[] cards){
    	Card c1 = cards[0];
    	Card c2 = cards[1];
    	int resId = c1.getImageResourceId();
        pc1Img.setImageResource(resId);
        resId = c2.getImageResourceId();
        pc2Img.setImageResource(resId);
    }

    /** Tells if the raise button has been pressed */
    public static boolean getRaisedPressed(){
    	return pressed;
    }
    
    /** Tells if any game button has been pressed */
    public static boolean getAnyPressed(){
    	return any_pressed;
    }
    
    /** Prints text to screen. */
    public static void setConsoleText(String st){
    	console.append(st);
    	console.computeScroll();
    }
    
    /** Returns the bet obtained from RaiseWindow. */
    public static int getBet(){
    	pressed = false;
    	return bet;
    }
    
    /** Saves the game if its interrupted by a phone call. 
    @Override
    protected void onPause() {  
    	super.onPause();
    	// save game
    }
    
    /** Loads the previously interrupted game. 
    @Override
    protected void onResume() {  
    	super.onResume();  
    	// load saved game.
    }
    */
}
