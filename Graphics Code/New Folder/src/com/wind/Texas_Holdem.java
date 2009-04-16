package com.wind;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Texas_Holdem extends Activity {
	
	private Button call;
    private Button check;
    private Button raise;
    private Button fold;
    private TextView console;

    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.main);
        
        ImageView imageView = (ImageView)findViewById(R.id.img1);
        imageView.setScaleType(ScaleType.FIT_START);
        call = (Button)this.findViewById(R.id.b_call);
        call.setOnClickListener(l_call);
        check = (Button)this.findViewById(R.id.b_check);
        check.setOnClickListener(l_check);
        raise = (Button)this.findViewById(R.id.b_raise);
        raise.setOnClickListener(l_raise);
        fold = (Button)this.findViewById(R.id.b_fold);
        fold.setOnClickListener(l_fold);
        console = (TextView)this.findViewById(R.id.tv1);
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
        	
        }
    };
    private OnClickListener l_fold = new OnClickListener() {
        public void onClick(View v) {
        	
        }
    };
    
    /** Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Save");
        menu.add(0, 1, 0, "Rotate");
        return true;
    }

    /** Handles menu item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case 0:
            // Call method to save game.
            return true;
        case 1:
        	if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }else{
            	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            return true;
        }
        return false;
    }

}