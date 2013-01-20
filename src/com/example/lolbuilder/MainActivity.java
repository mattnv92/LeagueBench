package com.example.lolbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	Button cb_Button; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
       
    }

    public void initialize(){
    	cb_Button = (Button)findViewById(R.id.cb_Button);
    	cb_Button.setOnClickListener(this);
    	TextView tv = (TextView)findViewById(R.id.cb_et);
    	
    	/*DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		final int h = displaymetrics.heightPixels;
		final int w = displaymetrics.widthPixels;
    	tv.setText("" + h);*/
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		
		try {
			Class champ_list_class = Class.forName("com.example.lolbuilder.Champ_List");
			Intent cl_intent = new Intent(MainActivity.this, champ_list_class);
			startActivity(cl_intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
