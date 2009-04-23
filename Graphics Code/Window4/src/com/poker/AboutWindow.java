package com.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class AboutWindow extends Activity implements View.OnClickListener{
    
	private Button close;
	private Spinner raiseby;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_window);
        
        close = (Button)this.findViewById(R.id.close);
        close.setOnClickListener(l_close);
    }
    
    /** Handles Close button selection */
    private OnClickListener l_close = new OnClickListener() {
        public void onClick(View v) {
        	finish();
        }
    };
    
    /** Not used. */
	@Override
	public void onClick(View v) {
		
	}
}
