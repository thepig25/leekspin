package com.poker;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutWindow extends Activity {
    
	private Button close;
    MediaPlayer mp = new MediaPlayer();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_window);

        try {
			mp.setDataSource("data/leekspin.mp3");
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			System.out.println("IllegalStateException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
        try {
			mp.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        mp.start(); 
        close = (Button)this.findViewById(R.id.close);
        close.setOnClickListener(l_close);
    }
    
    /** Handles Close button selection */
    private OnClickListener l_close = new OnClickListener() {
        public void onClick(View v) {
        	mp.stop(); 
        	mp.reset();
        	finish();
        }
    };
}
