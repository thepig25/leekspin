package com.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartWindow extends Activity implements View.OnClickListener{
    
	private Button start;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.start_window);
        
        start = (Button)this.findViewById(R.id.start);
        start.setOnClickListener(l_start);
    }
    
    /** Handles start button selection */
    private OnClickListener l_start = new OnClickListener() {
        public void onClick(View v) {
        	finish();
        }
    };

    /** Not used. */
	@Override
	public void onClick(View v) {
		
	}
}
