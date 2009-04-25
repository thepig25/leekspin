package com.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PlayerWindow extends Activity implements View.OnClickListener{
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_window);
    }
    
    
    /** Closes Window. */
	@Override
	public void onClick(View v) {
		finish();
	}
}
