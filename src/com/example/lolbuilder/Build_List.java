package com.example.lolbuilder;

import java.util.HashMap;

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
	  private HashMap<String, Integer> itemList = new HashMap<String, Integer>();
	  
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        // storing string resources into Array
	        getSavedBuilds();
	 
	        // Binding resources Array to ListAdapter
	        setListAdapter(new ArrayAdapter<String>(this, R.layout.build_list, Builds));
	        ListView lv = getListView();
	        lv.setOnItemClickListener(this);
	        
	        
	       
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
		Database db = new Database(this);
		try {
			db.openRead();
			Class item_list_class = Class.forName("com.example.lolbuilder.Item_List");
			Intent cl_intent = new Intent(Build_List.this, item_list_class);
			itemList = db.getSavedItems(Builds[pos]);
			String champName = db.getChampNameByBuildName(Builds[pos]);
			cl_intent.putExtra("champName", champName);
			cl_intent.putExtra("champChosen", db.getChampIdByName(champName));
			if(itemList.get("item0") != null)
				cl_intent.putExtra("item0", itemList.get("item0"));
			if(itemList.get("item1") != null)
				cl_intent.putExtra("item1", itemList.get("item1"));
			if(itemList.get("item2") != null)
				cl_intent.putExtra("item2", itemList.get("item2"));
			if(itemList.get("item3") != null)
				cl_intent.putExtra("item3", itemList.get("item3"));
			if(itemList.get("item4") != null)
				cl_intent.putExtra("item4", itemList.get("item4"));
			if(itemList.get("item5") != null)
				cl_intent.putExtra("item5", itemList.get("item5"));
			
			startActivity(cl_intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
