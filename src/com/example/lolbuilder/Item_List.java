package com.example.lolbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class Item_List extends Activity {

	final int CHAMP_ICON_ID = 101;
	final int HSCROLL_VIEW_ID = 102;
	final int TABHOST_ID = 103;
	final int NEXTBUTTON_ID = 104;
	public int ITEM_SLOT_COUNT_INDEX = 0;
	private String CHAMP_NAME;
	
	List<Button> item_slot_buffer = new ArrayList<Button>();
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_menu);
        Intent basket = getIntent();
        final int bid = basket.getExtras().getInt("champChosen");
        CHAMP_NAME = basket.getExtras().getString("champName");
        init_hsv();
        init_tabhost();
        init_nextButton(bid);
        init_champ_portrait(bid);
        
	}
        
	public void addItemToSlot(View v){
        if(ITEM_SLOT_COUNT_INDEX < 6){	
        	final Button new_item = new Button(this);
        	final LinearLayout ll = (LinearLayout)findViewById(R.id.rlhsv);
        	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	new_item.setBackgroundResource(v.getId());
        	new_item.setLayoutParams(lp);
        	new_item.setId(v.getId());
        	item_slot_buffer.add(new_item);
        	ll.addView(new_item);
        	
        	new_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ll.removeView(v);
					item_slot_buffer.remove(v);
					ITEM_SLOT_COUNT_INDEX--;
					
				}
			});
        	
        	ITEM_SLOT_COUNT_INDEX++;
        }
    }
	

	public void init_tabhost(){
		HashMap<String, Integer> defenseList = new HashMap<String, Integer>();
		HashMap<String, Integer> adList = new HashMap<String, Integer>();
		HashMap<String, Integer> apList = new HashMap<String, Integer>();
		HashMap<String, Integer> endList = new HashMap<String, Integer>();
		HashMap<String, Integer> msList = new HashMap<String, Integer>();
		
		
		Button b;
		int butPos;
		ArrayList<Button> buttons = new ArrayList<Button>();
		RelativeLayout rl;
		RelativeLayout.LayoutParams lp;
		
		Database db = new Database(this);
        try{
        	db.openRead();
        	defenseList = db.getItemCategoryDefense();
        	adList = db.getItemCategoryAD();
        	endList = db.getItemCategoryEND();
        	msList = db.getItemCategoryMS();
        	apList = db.getItemCategoryAP();
        } 
        catch(Exception E){
        	Dialog d = new Dialog(this);
			d.setTitle("Uh Oh!");
			TextView tv = new TextView(this);
			tv.setText(E.getMessage());
			d.setContentView(tv);
			d.show();
        }
        finally{
        	db.close();
        }
        
        OnClickListener item_menu_listener = new OnClickListener() { 
			@Override
			public void onClick(View v) {
				addItemToSlot(v);
			}
			
        };
        
		//add buttons
        TabHost th = (TabHost)findViewById(R.id.tabhost);
        RelativeLayout.LayoutParams lpth = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lpth.addRule(RelativeLayout.BELOW, CHAMP_ICON_ID);
        th.setLayoutParams(lpth);
        RelativeLayout.LayoutParams lpth2 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lpth2.addRule(RelativeLayout.ABOVE, NEXTBUTTON_ID);
        th.setLayoutParams(lpth);
        th.setup();
        TabSpec ts = th.newTabSpec("item_tag");
        ts.setContent(R.id.defense);
        ts.setIndicator("DEF");
        butPos = 0;
        buttons = new ArrayList<Button>();
        
        for(Integer value : defenseList.values()){
        	rl = (RelativeLayout)findViewById(R.id.defense);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	b = new Button(this);
        //	if(value != R.drawable.frozen_heart){
        		
        	b.setId(value);
        	b.setBackgroundResource(value);
        	buttons.add(b);
        	if(butPos == 0){
        		lp.addRule(RelativeLayout.RIGHT_OF, 0);
        	}
        	else if(butPos % 4 == 0)
        		lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	else{
        		lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(butPos - 1).getId());
				if(butPos > 4)
					lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	}
        	rl.addView(b, lp);
        	b.setOnClickListener(item_menu_listener);
        	butPos++;
        	//}
        }
        
        th.addTab(ts);
        ts = th.newTabSpec("item1_tag");
        ts.setContent(R.id.health);
        ts.setIndicator("END");
        butPos = 0;
        buttons = new ArrayList<Button>();
        for(Integer value : endList.values()){
        	rl = (RelativeLayout)findViewById(R.id.health);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	b = new Button(this);
        	b.setId(value);
        	b.setBackgroundResource(value);
        	buttons.add(b);
        	if(butPos == 0){
        		lp.addRule(RelativeLayout.RIGHT_OF, 0);
        	}
        	else if(butPos % 4 == 0)
        		lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	else{
        		lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(butPos - 1).getId());
				if(butPos > 4)
					lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	}
        	rl.addView(b, lp);
        	b.setOnClickListener(item_menu_listener);
        	butPos++;
        }
        
        th.addTab(ts);
        ts = th.newTabSpec("item2_tag");
        ts.setContent(R.id.AD);
        ts.setIndicator("AD");
        butPos = 0;
        buttons = new ArrayList<Button>();
        for(Integer value : adList.values()){
        	rl = (RelativeLayout)findViewById(R.id.AD);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	b = new Button(this);
        	b.setId(value);
        	b.setBackgroundResource(value);
        	buttons.add(b);
        	if(butPos == 0){
        		lp.addRule(RelativeLayout.RIGHT_OF, 0);
        	}
        	else if(butPos % 4 == 0)
        		lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	else{
        		lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(butPos - 1).getId());
				if(butPos > 4)
					lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	}
        	rl.addView(b, lp);
        	b.setOnClickListener(item_menu_listener);
        	butPos++;
        }
        
        th.addTab(ts);
        ts = th.newTabSpec("item3_tag");
        ts.setContent(R.id.AP);
        ts.setIndicator("AP");
        th.addTab(ts);
        th.setId(TABHOST_ID);
        butPos = 0;
        buttons = new ArrayList<Button>();
        for(Integer value : apList.values()){
        	rl = (RelativeLayout)findViewById(R.id.AP);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	b = new Button(this);
        	b.setId(value);
        	b.setBackgroundResource(value);
        	buttons.add(b);
        	if(butPos == 0){
        		lp.addRule(RelativeLayout.RIGHT_OF, 0);
        	}
        	else if(butPos % 4 == 0)
        		lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	else{
        		lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(butPos - 1).getId());
				if(butPos > 4)
					lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	}
        	rl.addView(b, lp);
        	b.setOnClickListener(item_menu_listener);
        	butPos++;
        }
        
        ts = th.newTabSpec("item4_tag");
        ts.setContent(R.id.MS);
        ts.setIndicator("SPEED");
        butPos = 0;
        buttons = new ArrayList<Button>();
        for(Integer value : msList.values()){
        	rl = (RelativeLayout)findViewById(R.id.MS);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	b = new Button(this);
        	b.setId(value);
        	b.setBackgroundResource(value);
        	buttons.add(b);
        	if(butPos == 0){
        		lp.addRule(RelativeLayout.RIGHT_OF, 0);
        	}
        	else if(butPos % 4 == 0)
        		lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	else{
        		lp.addRule(RelativeLayout.RIGHT_OF, buttons.get(butPos - 1).getId());
				if(butPos > 4)
					lp.addRule(RelativeLayout.BELOW, buttons.get(butPos - 4).getId());
        	}
        	rl.addView(b, lp);
        	b.setOnClickListener(item_menu_listener);
        	butPos++;
        }
        th.addTab(ts);
        //set buttons
        //doran_shield.setOnClickListener(item_menu_listener);
		
	}
	
	public void init_hsv(){
		
        HorizontalScrollView hsv = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
        RelativeLayout.LayoutParams lphsv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lphsv.addRule(RelativeLayout.RIGHT_OF, CHAMP_ICON_ID);
        hsv.setLayoutParams(lphsv);
        hsv.setId(HSCROLL_VIEW_ID);
		
	}
	
	public void init_champ_portrait(int i){
		
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rlitem_menu);
        ImageView champIcon = new ImageView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        champIcon.setLayoutParams(lp);
        rl.addView(champIcon);
        champIcon.setId(CHAMP_ICON_ID);
        champIcon.setBackgroundResource(i);
		
	}
	
	public void init_nextButton(final int id) {
		
		Button b = (Button)findViewById(R.id.nextbutton);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		b.setLayoutParams(lp);
		b.setId(NEXTBUTTON_ID);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Class champ_stats_class = Class.forName("com.example.lolbuilder.Champ_Stats");
					Intent cs_intent = new Intent(Item_List.this, champ_stats_class);
					cs_intent.putExtra("champChosen", CHAMP_NAME);
					cs_intent.putExtra("champBanner", id);
					int[] array = new int[6];
					for(int i = 0; i < item_slot_buffer.size(); i++){
						Button b = item_slot_buffer.get(i);
						if(b != null)
							array[i] = b.getId();
						else
							array[i] = 0;
					}
					cs_intent.putExtra("item0", array[0]);
					cs_intent.putExtra("item1", array[1]);
					cs_intent.putExtra("item2", array[2]);
					cs_intent.putExtra("item3", array[3]);
					cs_intent.putExtra("item4", array[4]);
					cs_intent.putExtra("item5", array[5]);
					startActivity(cs_intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}


	

}
