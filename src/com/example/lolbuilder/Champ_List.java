package com.example.lolbuilder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Champ_List extends Activity implements OnItemClickListener{
	
	private String CHAMP_NAME;
	private final int ICON_SIZE = 90;
	private ArrayList<Integer> champIcons = new ArrayList<Integer>();
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(this);
      
        
    }
	
	//move to the itemList class after choosing a champion from the gridview
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
		
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
		}
		
	}
	
	//used as a class to set up the champion image/buttons
	public class ImageAdapter extends BaseAdapter {
	
		 private Context mContext;
		 private Database db;
		// references to our images
		

		    public ImageAdapter(Context c) {
		        mContext = c;
		        db = new Database(mContext);
		        populateGrid();
		    }

		    //poplate the grid with the drawable buttons
		    private void populateGrid() {
				try{
					db.openRead();
					champIcons = db.getChampButtons();
				}
				catch(Exception e){
					Dialog d = new Dialog(mContext);
					d.setTitle("Uh Oh! Teemo!");
					TextView tv = new TextView(mContext);
					tv.setText(e.getMessage());
					d.setContentView(tv);
					d.show();
				}
				finally{
					db.close();
				}
				
			}

			public int getCount() {
		        return champIcons.size();
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return champIcons.get(position);
		    }

		    // create a new ImageView for each item referenced by the Adapter
		    public View getView(int position, View convertView, ViewGroup parent) {
		        ImageView imageView;
		        if (convertView == null) {  // if it's not recycled, initialize some attributes
		            imageView = new ImageView(mContext);
		            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		            imageView.setPadding(5, 5, 5, 5);
		        } else {
		            imageView = (ImageView) convertView;
		        }

		        imageView.setImageResource(champIcons.get(position));
		        return imageView;
		    }

		   


		
	}
	
	public void orderButtonsByName(){
		
		/*//get size of screen
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		float density  = getResources().getDisplayMetrics().density;
	    float dpHeight = metrics.heightPixels / density;
	    int width  = metrics.widthPixels / ((int)density);
		
		int numIcons = (width) / ICON_SIZE;
		
		Database db = new Database(this);
		ArrayList<Button> buttons = new ArrayList<Button>();
		try{
			db.openRead();
			RelativeLayout rl;
			RelativeLayout.LayoutParams lp;
			int butpos = 0;
			buttons = db.getChampButtons();
			for(int i = 0; i < buttons.size(); i++){
				rl = (RelativeLayout)findViewById(R.id.rlchamp_menu);
				lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				Button b = buttons.get(i);
				if(butpos == 0){
					lp.addRule(RelativeLayout.RIGHT_OF, 0);
				}
					else if(butpos % numIcons == 0){
						lp.addRule(RelativeLayout.BELOW, buttons.get(i - numIcons).getId());
					}
						else{
							lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(i - 1).getId());
							if(i > numIcons)
								lp.addRule(RelativeLayout.BELOW, buttons.get(i - numIcons).getId());
						}
				rl.addView(b, lp);
				b.setOnClickListener(this);
				butpos++;
			}
		}
		catch(Exception e){
			Dialog d = new Dialog(this);
			d.setTitle("Uh Oh! Teemo!");
			TextView tv = new TextView(this);
			tv.setText(e.getMessage());
			d.setContentView(tv);
			d.show();
		}
		finally{
			db.close();
		}*/
	}


	/*@Override
	public void onClick(View v) {

		try {
			Class item_list_class = Class.forName("com.example.lolbuilder.Item_List");
			Intent cl_intent = new Intent(Champ_List.this, item_list_class);
			Button b = (Button)v;
			CHAMP_NAME = getChampName(b);
			cl_intent.putExtra("champName", CHAMP_NAME);
			cl_intent.putExtra("champChosen", b.getId());
			startActivity(cl_intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	
	public String getChampName(int champId) {
		Database db = new Database(this);
		String result = "";
		try{
			db.openRead();
			result = db.champNameById(champId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return result;
	}
}
