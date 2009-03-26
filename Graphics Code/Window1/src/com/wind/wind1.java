package com.wind;

import com.wind.R;

import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;


//import android.content.pm.ActivityInfo;
//import android.os.ServiceManager;

public class wind1 extends Activity {
    
    Button deal;
    Button bet;
    Button fold;
    
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 

        setContentView(R.layout.main);
        
        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.table); 
        //int width = bitmapOrg.getWidth();
        //int height = bitmapOrg.getHeight();
        BitmapDrawable bmd = new BitmapDrawable(bitmapOrg);
        ImageView imageView = (ImageView)findViewById(R.id.img1);; 
        imageView.setScaleType(ScaleType.FIT_START);
        
        deal = (Button)this.findViewById(R.id.but1);
        bet = (Button)this.findViewById(R.id.but2);
        fold = (Button)this.findViewById(R.id.but3);
        

    }

}