package com.example.lolbuilder;

import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Build_List extends ListActivity implements OnItemClickListener, OnItemLongClickListener {

	  private String[] Builds;
	  private HashMap<String, Integer> itemList = new HashMap<String, Integer>();
	  
	  protected void onCreate(Bundle savedInstanceState) {
		  	super.onCreate(savedInstanceState);
	        
	        // storing string resources into Array
	        getSavedBuilds();
	 
	        // Binding resources Array to ListAdapter
	        setListAdapter(new ArrayAdapter<String>(this, R.layout.build_list, Builds));
	        ListView lv = getListView();
	        lv.setBackgroundColor(Color.BLACK);
	        lv.setOnItemClickListener(this);
	        lv.setOnItemLongClickListener(this);
	        
	        
	       
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

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int pos,
			long arg3) {
		// Alert for when wanting to delete a build
		final int posItem = pos;
        final AlertDialog deleteB = new AlertDialog.Builder(this).create();
        deleteB.setTitle("Delete this build?");
        deleteB.setButton(Dialog.BUTTON_NEGATIVE, "No!", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				deleteB.dismiss();
			}
		});
        deleteB.setButton(Dialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Database db = new Database(Build_List.this);
				try{
					db.openRead();
					db.deleteBuild(Builds[posItem]);
					Class Build_List_class = Class.forName("com.example.lolbuilder.Build_List");
					Intent ma_intent = new Intent(Build_List.this, Build_List_class);
					startActivity(ma_intent);
				}
				catch(Exception e){
					Dialog d = new Dialog(Build_List.this);
					d.setTitle(e.getMessage());
					d.show();
				}
				finally{
					db.close();
				}
			}
		});
        deleteB.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				deleteB.dismiss();
			}
		});
		deleteB.show();
		
		return false;
	}

	/*Dialog d = new Dialog(this);
			d.setTitle(e.getMessage());
			d.show();*/
	
}
