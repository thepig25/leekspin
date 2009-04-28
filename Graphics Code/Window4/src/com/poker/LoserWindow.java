package com.poker;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoserWindow extends Activity {
    
	private Button close;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loser_window);

        close = (Button)this.findViewById(R.id.close1);
        close.setOnClickListener(l_close);
    }
    
    /** Handles Close button selection */
    private OnClickListener l_close = new OnClickListener() {
        public void onClick(View v) {
        	finish();
        }
    };
}
