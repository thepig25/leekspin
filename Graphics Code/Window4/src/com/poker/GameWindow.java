package com.poker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameWindow extends Activity{

	private ImageButton call;
    private ImageButton check;
    private ImageButton raise;
    private ImageButton fold;
    public TextView console;
    //private ImageView card;

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
        
        //card = (ImageView)this.findViewById(R.id.card2);
        //card.setImageResource(R.id.card2);
        //card.setLayoutParams(new Gallery.LayoutParams(20, 50));
        //card.setImageBitmap(bm);
        /**
        //card = (ImageView)this.findViewById(R.id.card1);
        //card.setImageResource(01);
        AbsoluteLayout ably;
        ably = (AbsoluteLayout)this.findViewById(R.id.ab_lay);
        
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.card);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view
        ably.addView(i);
        setContentView(ably);

    	//android:layout_width="wrap_content"
    	//android:layout_height="wrap_content"
    	//android:layout_y="270px" android:layout_x="240px">
    	 
    	 */
    }
    
    /** Handles Main button selections */
    private OnClickListener l_call = new OnClickListener() {
        public void onClick(View v) {
        	
        }
    };
    private OnClickListener l_check = new OnClickListener() {
        public void onClick(View v) {
        	
        }
    };
    private OnClickListener l_raise = new OnClickListener() {
        public void onClick(View v) {
        	startActivityForResult(new Intent("com.poker.action.RAISE", null),0);
        	
        }
    };
    private OnClickListener l_fold = new OnClickListener() {
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
        }
    }
    
    public void setConsoleText(String st){
    	console.append(st);
    }

}
