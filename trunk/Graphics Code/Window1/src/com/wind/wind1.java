package com.wind;

import com.wind.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class wind1 extends Activity{
    
    private Button deal;
    private Button bet;
    private Button fold;
    private TextView bestHand;
    private TextView pot;
    
    public void onCreate(Bundle savedInstanceState){
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.main);
        ImageView imageView = (ImageView)findViewById(R.id.img1);; 
        imageView.setScaleType(ScaleType.FIT_START);
        deal = (Button)this.findViewById(R.id.but1);
		deal.setOnClickListener(l_deal);
        bet = (Button)this.findViewById(R.id.but2);
		bet.setOnClickListener(l_bet);
        fold = (Button)this.findViewById(R.id.but3);
		fold.setOnClickListener(l_fold);
        bestHand = (TextView)this.findViewById(R.id.textv3);
        pot = (TextView)this.findViewById(R.id.textv2);
    }
    private OnClickListener l_deal = new OnClickListener() {
        public void onClick(View v) {
        	// code to do something when the deal button is pressed.
        	pot.setText("€1");
        }
    };
    private OnClickListener l_bet = new OnClickListener() {
        public void onClick(View v) {
        	//...
        	pot.setText("€2.50");
        }
    };
    private OnClickListener l_fold = new OnClickListener() {
        public void onClick(View v) {
        	//...
        	pot.setText("€43.75");
        }
    };
    public void setBestHandText(String s){
        bestHand.setText(s);
    }
    public void setPotText(String s){
        pot.setText(s);
    }
}