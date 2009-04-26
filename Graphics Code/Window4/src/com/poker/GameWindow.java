package com.poker;

import java.lang.reflect.Field;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
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
	
	/** Thread to run the poker game. */
	private Thread t = null;
	/** True if the raise amount has been chosen. */
	private static boolean pressed;
	static boolean any_pressed;
	static boolean first = true;
	static boolean raised_done = false;
	static boolean game_over = false;
	static boolean commCardsDealt = false;
	static boolean p1Folded = false;
	static int command;
	static int currentPlayerInfo;
	static String player1_name = "";
	static String player2_name = "";
	static String player3_name = "";
	static String player4_name = "";
	static String player1_cash = "";
	static String player2_cash = "";
	static String player3_cash = "";
	static String player4_cash = "";
	static Card[] player1_hand;
	static Card[] player2_hand;
	static Card[] player3_hand;
	static Card[] player4_hand;
	
	/** Used to communicate between threads. */
    protected static final int GUIUPDATEIDENTIFIER = 0x101;
    protected static final int DRAWCOMMUNITYCARDS = 0x102;
    protected static final int DRAWPLAYERCARDS = 0x103;
    protected static final int SETBET = 0x104;
    protected static final int DRAWBLANKCARDS = 0x105;
    protected static final int SETPOTTEXT = 0x106;
    protected static final int SETPLAYERMONEY = 0x107;
    protected static final int DRAWOPPONENTCARDS = 0x108;
    protected static final int DRAWOPPONENTNAME = 0x109;
    protected static final int SETPLAYERSBESTHAND = 0x110;
	
    /** Choice of bets. */
    public static String[] bets = {"20","40","60"};
    
	static int blank_card_id;
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
    private static ImageView p2card1Img;
    private static ImageView p2card2Img;
    private static ImageView p3card1Img;
    private static ImageView p3card2Img;
    private static ImageView p4card1Img;
    private static ImageView p4card2Img;
    
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
    	
    	// Initialise Game Booleans.
    	first = true;
    	p1Folded = false;
    	game_over = false;
    	raised_done = false;
    	commCardsDealt = false;
    	
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
        
        // Create opponent card images. (Empty until end of game)
        p2card1Img = (ImageView) findViewById(id.p2card1);
        p2card2Img = (ImageView) findViewById(id.p2card2);
        p3card1Img = (ImageView) findViewById(id.p3card1);
        p3card2Img = (ImageView) findViewById(id.p3card2);
        p4card1Img = (ImageView) findViewById(id.p4card1);
        p4card2Img = (ImageView) findViewById(id.p4card2);
        
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
		p2card1Img.setImageResource(blank_card_id);
		p2card2Img.setImageResource(blank_card_id);
		p3card1Img.setImageResource(blank_card_id);
		p3card2Img.setImageResource(blank_card_id);
		p4card1Img.setImageResource(blank_card_id);
		p4card2Img.setImageResource(blank_card_id);

		// Make player cards pressable.
		pc1Img.setOnClickListener(l_player1);
		pc2Img.setOnClickListener(l_player1);
		p2card1Img.setOnClickListener(l_player2);
		p2card2Img.setOnClickListener(l_player2);
		p3card1Img.setOnClickListener(l_player3);
		p3card2Img.setOnClickListener(l_player3);
		p4card1Img.setOnClickListener(l_player4);
		p4card2Img.setOnClickListener(l_player4);
        
		// Set up game buttons.
        call = (ImageButton)this.findViewById(R.id.but_call);
        call.setOnClickListener(l_call);
        check = (ImageButton)this.findViewById(R.id.but_check);
        check.setOnClickListener(l_check);
        raise = (ImageButton)this.findViewById(R.id.but_raise);
        raise.setOnClickListener(l_raise);
        fold = (ImageButton)this.findViewById(R.id.but_fold);
        fold.setOnClickListener(l_fold);
    	
        first = true;
        
        t = new Thread() {
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
                       
                   case GameWindow.DRAWOPPONENTCARDS:
                       Card[] temp_oppenent_cards = (Card[])msg.obj;
                       int id = (int)msg.arg1;
                       drawOpponentCardImgs(temp_oppenent_cards, id);
                       break;
                       
                   case GameWindow.DRAWOPPONENTNAME:
                       String temp_oppenent_name = (String)msg.obj;
                       int id2 = (int)msg.arg1;
                       if(id2==1){
                    	   player1_name = temp_oppenent_name;
                       }
                       if(id2==2){
                    	   player2_name = temp_oppenent_name;
                       }
                       if(id2==3){
                    	   player3_name = temp_oppenent_name;
                       }
                       if(id2==4){
                    	   player4_name = temp_oppenent_name;
                       }
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
                       
                   case GameWindow.SETPLAYERMONEY:
                	   String temp_money = (String)msg.obj;
                	   int id4 = (int)msg.arg1;
                	   if(id4==1){
                    	   player1_cash = temp_money;
                       }
                       if(id4==2){
                    	   player2_cash = temp_money;
                       }
                       if(id4==3){
                    	   player3_cash = temp_money;
                       }
                       if(id4==4){
                    	   player4_cash = temp_money;
                       }
                       break;
                       
                   case GameWindow.SETPLAYERSBESTHAND:
                	   Card[] opp_best_hand = (Card[])msg.obj;
                	   int id3 = (int)msg.arg1;
                       if(id3==1){
                    	   player1_hand = opp_best_hand;
                       }
                       if(id3==2){
                    	   player2_hand = opp_best_hand;
                       }
                       if(id3==3){
                    	   player3_hand = opp_best_hand;
                       }
                       if(id3==4){
                    	   player4_hand = opp_best_hand;
                       }
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
        		// Opens the 'RaiseWindow.java' window named "RAISE" in the AndroidManifest XML file.
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
    private OnClickListener l_player1 = new OnClickListener() {
        public void onClick(View v) {
        	currentPlayerInfo = 1;
        	startActivityForResult(new Intent("com.poker.action.PLAYER", null),2);
        }
    };
    private OnClickListener l_player2 = new OnClickListener() {
        public void onClick(View v) {
        	currentPlayerInfo = 2;
        	startActivityForResult(new Intent("com.poker.action.PLAYER", null),2);
        }
    };
    private OnClickListener l_player3 = new OnClickListener() {
        public void onClick(View v) {
        	currentPlayerInfo = 3;
        	startActivityForResult(new Intent("com.poker.action.PLAYER", null),2);
        }
    };
    private OnClickListener l_player4 = new OnClickListener() {
        public void onClick(View v) {
        	currentPlayerInfo = 4;
        	startActivityForResult(new Intent("com.poker.action.PLAYER", null),2);
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
    
    /** Draws the players pocket cards to screen. */
    public static void drawOpponentCardImgs(Card[] cards, int id){
    	Card c1 = cards[0];
    	Card c2 = cards[1];
    	int resId1 = c1.getImageResourceId();
    	int resId2 = c2.getImageResourceId();
    	if(id==2){
            p2card1Img.setImageResource(resId1);
            p2card2Img.setImageResource(resId2);
    	}
    	if(id==3){
            p3card1Img.setImageResource(resId1);
            p3card2Img.setImageResource(resId2);
    	}
    	if(id==4){
            p4card1Img.setImageResource(resId1);
            p4card2Img.setImageResource(resId2);
    	}
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
    	//console.computeScroll();
    	AdjustScroll(console);
    }
    
    /** Returns the bet obtained from RaiseWindow. */
    public static int getBet(){
    	pressed = false;
    	return bet;
    }
    
    public static void AdjustScroll(TextView in_oTextView){
    	
        int l_nLineCount = in_oTextView.getLineCount();
        int l_nViewHeight = in_oTextView.getHeight();
        int l_nPixelsPerLine = in_oTextView.getLineHeight();

        //The difference between view height and total text height
        int l_nDifference = ( l_nLineCount * l_nPixelsPerLine ) - l_nViewHeight;
        if( l_nDifference < 1 ){
        	return;
        }
        
        in_oTextView.scrollTo( 0, l_nDifference );
    } 
    
    @Override
    public void onPause(){
          t.interrupt();
          super.onPause();
    }
   
    @Override
    public void onResume(){
     super.onResume();
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_BACK:
             t.interrupt();
             break;
        case KeyEvent.KEYCODE_HOME:
             t.interrupt();
             break;
        case KeyEvent.KEYCODE_ENDCALL :
             t.interrupt();
             break;
        default:
             break;
        }
        return super.onKeyDown(keyCode, event);
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
