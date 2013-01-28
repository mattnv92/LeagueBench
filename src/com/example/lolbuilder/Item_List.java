package com.example.lolbuilder;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.viewpagerindicator.TitlePageIndicator;


public class Item_List extends BaseSampleActivity {

	final int CHAMP_ICON_ID = 101;
	final int HSCROLL_VIEW_ID = 102;
	final int TABHOST_ID = 103;
	final int NEXTBUTTON_ID = 104;
	public int ITEM_SLOT_COUNT_INDEX = 0;
	private String CHAMP_NAME;
	private int item0Id = 0;
	private int item1Id = 0;
	private int item2Id = 0;
	private int item3Id = 0;
	private int item4Id = 0;
	private int item5Id = 0;
	
	List<Button> item_slot_buffer = new ArrayList<Button>();
	
	//private HashMap<String, Integer> defenseList = new HashMap<String, Integer>();
	private ArrayList<Integer> defenseIcons = new ArrayList<Integer>();
	//private HashMap<String, Integer> adList = new HashMap<String, Integer>();
	//private HashMap<String, Integer> apList = new HashMap<String, Integer>();
	//private HashMap<String, Integer> endList = new HashMap<String, Integer>();
	private ArrayList<Integer> endIcons = new ArrayList<Integer>();
	private ArrayList<Integer> adIcons = new ArrayList<Integer>();
	private ArrayList<Integer> apIcons = new ArrayList<Integer>();
	private ArrayList<Integer> speedIcons = new ArrayList<Integer>();
	//private HashMap<String, Integer> msList = new HashMap<String, Integer>();
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themed_titles);
        Intent basket = getIntent();
        final int bid = basket.getExtras().getInt("champChosen");
        CHAMP_NAME = basket.getExtras().getString("champName");
        
        //Looking for items to put into the slots if you load a saved build
        if(basket.hasExtra("item0"))
        	item0Id = basket.getExtras().getInt("item0");
        if(basket.hasExtra("item1"))
        	item1Id = basket.getExtras().getInt("item1");
        if(basket.hasExtra("item2"))
        	item2Id = basket.getExtras().getInt("item2");
        if(basket.hasExtra("item3"))
        	item3Id = basket.getExtras().getInt("item3");
        if(basket.hasExtra("item4"))
        	item4Id = basket.getExtras().getInt("item4");
        if(basket.hasExtra("item5"))
        	item5Id = basket.getExtras().getInt("item5");
        
        //Button b = new Button(this);
       if(item0Id > 0)
        	addItemToSlot(item0Id);
        if(item1Id > 0)
        	addItemToSlot(item1Id);
        if(item2Id > 0)
        	addItemToSlot(item2Id);
        if(item3Id > 0)
        	addItemToSlot(item3Id);
        if(item4Id > 0)
        	addItemToSlot(item4Id);
        if(item5Id > 0)
        	addItemToSlot(item5Id);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.setCurrentItem(4);
        
        init_hsv();
        init_nextButton(bid);
        init_champ_portrait(bid);
    }

	
	

	
	/*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabsetup);
        //Intent basket = getIntent();
        //final int bid = basket.getExtras().getInt("champChosen");
        //CHAMP_NAME = basket.getExtras().getString("champName");
        if(basket.hasExtra("item0"))
        	item0Id = basket.getExtras().getInt("item0");
        if(basket.hasExtra("item1"))
        	item1Id = basket.getExtras().getInt("item1");
        if(basket.hasExtra("item2"))
        	item2Id = basket.getExtras().getInt("item2");
        if(basket.hasExtra("item3"))
        	item3Id = basket.getExtras().getInt("item3");
        if(basket.hasExtra("item4"))
        	item4Id = basket.getExtras().getInt("item4");
        if(basket.hasExtra("item5"))
        	item5Id = basket.getExtras().getInt("item5");
        
        //Button b = new Button(this);
       if(item0Id > 0)
        	addItemToSlot(item0Id);
        if(item1Id > 0)
        	addItemToSlot(item1Id);
        if(item2Id > 0)
        	addItemToSlot(item2Id);
        if(item3Id > 0)
        	addItemToSlot(item3Id);
        if(item4Id > 0)
        	addItemToSlot(item4Id);
        if(item5Id > 0)
        	addItemToSlot(item5Id);
        
        //init_hsv();
        //init_tabhost();
        //init_nextButton(bid);
        //init_champ_portrait(bid);
        
        //starting code
        //PagerAdapter pa = new TestFragmentAdapter(getSupportFragmentManager());
        ViewPager vp = (ViewPager)findViewById(R.id.pager);
        TitlePageIndicator tpi = (TitlePageIndicator)findViewById(R.id.indicator);
        //tpi.setViewPager(vp);
      
	}*/
	
        
	public void addItemToSlot(int id){
        if(ITEM_SLOT_COUNT_INDEX < 6){	
        	final Button new_item = new Button(this);
        	final LinearLayout ll = (LinearLayout)findViewById(R.id.rlhsv);
        	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	new_item.setBackgroundResource(id);
        	new_item.setLayoutParams(lp);
        	new_item.setId(id);
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
		
		Database db = new Database(this);
        try{
        	db.openRead();
        	//defenseIcons = db.getItemCategoryDefense();
        	//adList = db.getItemCategoryAD();
        	//endList = db.getItemCategoryEND();
        	//msList = db.getItemCategoryMS();
        	//apList = db.getItemCategoryAP();
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
        th.addTab(ts);
        
		GridView gvDef = (GridView) findViewById(R.id.gvIlDef);
				
		gvDef.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Database db = new Database(Item_List.this);
	        	try{
	        		db.openRead();
	        		addItemToSlot(db.getButtonById(defenseIcons.get(position)));
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	finally{
	        		db.close();
	        	}
	        }
	    });
		
		//gvDef.setSelector(new ColorDrawable(Color.TRANSPARENT));

        ts = th.newTabSpec("item1_tag");
        ts.setContent(R.id.health);
        ts.setIndicator("END");
        th.addTab(ts);
        
       
		
        
        ts = th.newTabSpec("item2_tag");
        ts.setContent(R.id.AD);
        ts.setIndicator("AD");
        th.addTab(ts);
        
        GridView gvAd = (GridView) findViewById(R.id.gvIlAd);
		
		gvAd.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Database db = new Database(Item_List.this);
	        	try{
	        		db.openRead();
	        		addItemToSlot(db.getButtonById(adIcons.get(position)));
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	finally{
	        		db.close();
	        	}
	        }
	    });
        
		gvAd.setAdapter(new ImageAdapterAd(this));

        ts = th.newTabSpec("item3_tag");
        ts.setContent(R.id.AP);
        ts.setIndicator("AP");
        th.addTab(ts);
        
        GridView gvAp = (GridView) findViewById(R.id.gvIlAp);
		
		gvAp.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Database db = new Database(Item_List.this);
	        	try{
	        		db.openRead();
	        		addItemToSlot(db.getButtonById(apIcons.get(position)));
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	finally{
	        		db.close();
	        	}
	        }
	    });
        
		gvAp.setAdapter(new ImageAdapterAp(this));
        
        ts = th.newTabSpec("item4_tag");
        ts.setContent(R.id.MS);
        ts.setIndicator("SPEED");
        th.addTab(ts);
        
        GridView gvSpeed = (GridView) findViewById(R.id.gvIlSpeed);
		
		gvSpeed.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Database db = new Database(Item_List.this);
	        	try{
	        		db.openRead();
	        		addItemToSlot(db.getButtonById(speedIcons.get(position)));
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	finally{
	        		db.close();
	        	}
	        }
	    });
        
		gvSpeed.setAdapter(new ImageAdapterSpeed(this));
		
	}
	
	public void init_hsv(){
		
        HorizontalScrollView hsv = (HorizontalScrollView)findViewById(R.id.horizontalScrollView0);
        RelativeLayout.LayoutParams lphsv = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lphsv.addRule(RelativeLayout.RIGHT_OF, CHAMP_ICON_ID);
        hsv.setLayoutParams(lphsv);
        hsv.setId(HSCROLL_VIEW_ID);
		
	}
	
	public void init_champ_portrait(int i){
		
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.fld);
        ImageView champIcon = new ImageView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        champIcon.setLayoutParams(lp);
        rl.addView(champIcon);
        champIcon.setId(CHAMP_ICON_ID);
        champIcon.setBackgroundResource(i);
		
	}
	
	public void init_nextButton(final int id) {
		
		Button b = (Button)findViewById(R.id.nextbut);
		//RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		//b.setLayoutParams(lp);
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
					e.printStackTrace();
				}
				
			}
		});
	}
	
	
	
	public class ImageAdapterAd extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterAd(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return adIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return adIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		adIcons = db.getItemCategoryAD();
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	finally{
	    		db.close();
	    	}
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(adIcons.get(position));
	        return imageView;
	    }

	   
	}
	
	public class ImageAdapterAp extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterAp(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return apIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return apIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		apIcons = db.getItemCategoryAP();
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	finally{
	    		db.close();
	    	}
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(apIcons.get(position));
	        return imageView;
	    }

	   
	}
	
	public class ImageAdapterSpeed extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterSpeed(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return speedIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return speedIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		speedIcons = db.getItemCategoryMS();
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	finally{
	    		db.close();
	    	}
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(speedIcons.get(position));
	        return imageView;
	    }

	   
	}
	

}
