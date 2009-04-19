package com.poker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuWindow extends Activity {
	
	private ImageButton start;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menu_window);
        
        start = (ImageButton)this.findViewById(R.id.b_start);
        start.setOnClickListener(l_start);
    }
    
    private OnClickListener l_start = new OnClickListener() {
        public void onClick(View v) {
        	startActivityForResult(new Intent("com.poker.action.GAME", null),0);
        }
    };
}
