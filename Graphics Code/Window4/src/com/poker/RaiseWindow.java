package com.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RaiseWindow extends Activity implements View.OnClickListener{
    
	private Button ok;
	private Spinner raiseby;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raise_window);
        
        ok = (Button)this.findViewById(R.id.ok);
        ok.setOnClickListener(l_ok);
        raiseby = (Spinner)this.findViewById(R.id.spin1);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                    new String[] { "2", "5", "10" });
            raiseby.setAdapter(spinnerArrayAdapter);
    }
    
    /** Handles Ok button selection */
    private OnClickListener l_ok = new OnClickListener() {
        public void onClick(View v) {
        	String s = (String)raiseby.getSelectedItem();
        	int in = Integer.parseInt(s);
        	setResult(in);
        	finish();
        }
    };

    /** Not used. */
	@Override
	public void onClick(View v) {
		
	}
}
