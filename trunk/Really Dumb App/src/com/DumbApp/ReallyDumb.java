package com.DumbApp;

import java.io.IOException;



import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ReallyDumb extends Activity {
	private ImageButton check;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*check = (ImageButton)this.findViewById(R.id.ImageButton01);
        check.setOnClickListener((android.view.View.OnClickListener) l_check);*/
        startStuff();
    }
    

    
    public void startStuff(){
        
        Thread t = new Thread() {
        	public void run() {
        		// Start a new game in a separate thread.
        		//if(MenuWindow.Saved==true){
        			//myGame = new pokerGame(4, 2500, 50, money_array);
        		//}else{
        			//myGame = new pokerGame(4, 500, 50, null);
        		Background_ProcessClass test = new Background_ProcessClass();
        		try {
    				test.backGround_process();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		//}
        	}
        };
        t.start();
    }
    
}