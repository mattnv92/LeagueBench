package com.example.lolbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button cb_Button; 
	private Button lb_Button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
       
    }

    public void initialize(){
    	
    	 OnClickListener lb_listener = new OnClickListener() { 
 			public void onClick(View v) {
 				try {
 					Class load_build = Class.forName("com.example.lolbuilder.Build_List");
 					Intent lb_intent = new Intent(MainActivity.this, load_build);
 					startActivity(lb_intent);
 				} catch (ClassNotFoundException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			}
 			
         };
    	
    	cb_Button = (Button)findViewById(R.id.cb_Button);
    	cb_Button.setOnClickListener(this);
    	cb_Button.setText("Create Build");
    	cb_Button.setTextSize(40);
    	lb_Button = (Button)findViewById(R.id.lb_Button);
    	lb_Button.setOnClickListener(lb_listener);
    	lb_Button.setText("Load Build");
    	lb_Button.setTextSize(40);
    	
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
