package com.example.lolbuilder;

import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Build_List extends ListActivity {

	  private String[] Builds;
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        // storing string resources into Array
	        getSavedBuilds();
	 
	        // Binding resources Array to ListAdapter
	        setListAdapter(new ArrayAdapter<String>(this, R.layout.build_list, Builds));
	        
	        
	       
	    }
	  
	  public void getSavedBuilds() {
		  Database db = new Database(this);
		  try{
			  db.openRead();
			  Builds = db.getSavedBuilds();
		  }
		  catch(Exception e){
			  Dialog d = new Dialog(this);
				d.setTitle("Uh Oh!");
				TextView tv = new TextView(this);
				tv.setText(e.getMessage());
				d.setContentView(tv);
				d.show();
		  }
		  finally{
			  db.close();
		  }
	  }
}
