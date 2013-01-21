package com.example.lolbuilder;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Build_List extends ListActivity implements OnItemClickListener {

	  private String[] Builds;
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        // storing string resources into Array
	        getSavedBuilds();
	 
	        // Binding resources Array to ListAdapter
	        setListAdapter(new ArrayAdapter<String>(this, R.layout.build_list, Builds));
	        //ListView lv = getListView();
	        //lv.setOnItemClickListener(this);
	        
	        
	       
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg3) {
		// TODO Auto-generated method stub
		/*Database db = new Database(this);
		try {
			
			Class item_list_class = Class.forName("com.example.lolbuilder.Item_List");
			Intent cl_intent = new Intent(Champ_List.this, item_list_class);
			int cid = champIcons.get(pos);
			CHAMP_NAME = getChampName(cid);
			cl_intent.putExtra("champName", CHAMP_NAME);
			cl_intent.putExtra("champChosen", cid);
			startActivity(cl_intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
	}
}
