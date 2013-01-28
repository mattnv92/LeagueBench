package com.example.lolbuilder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    List<Button> item_slot_buffer = new ArrayList<Button>();
    View mainView;
    private ArrayList<Integer> hpIcons = new ArrayList<Integer>();
    private ArrayList<Integer> hpRIcons = new ArrayList<Integer>();
    private ArrayList<Integer> mpIcons = new ArrayList<Integer>();
    private ArrayList<Integer> mpRIcons = new ArrayList<Integer>();
    private ArrayList<Integer> mrIcons = new ArrayList<Integer>();
    private ArrayList<Integer> msIcons = new ArrayList<Integer>();
    private ArrayList<Integer> armorIcons = new ArrayList<Integer>();
    private ArrayList<Integer> adIcons = new ArrayList<Integer>();
    private ArrayList<Integer> apIcons = new ArrayList<Integer>();
    private ArrayList<Integer> asIcons = new ArrayList<Integer>();
    
    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();

        fragment.mContent = content;
        
        return fragment;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    	
   
    	LinearLayout ll = new LinearLayout(getActivity());
    	
    	if(mContent.equalsIgnoreCase("Health")){
    		View v = inflater.inflate(R.layout.gv_hp_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvHp = (GridView) v.findViewById(R.id.gvHp);
	    	
			gvHp.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(hpIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvHp.setAdapter(new ImageAdapterHp(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Health R")){
    		View v = inflater.inflate(R.layout.gv_hpr_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvHpR = (GridView) v.findViewById(R.id.gvHpR);
			
			gvHpR.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(hpRIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvHpR.setAdapter(new ImageAdapterHpR(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Mana")){
    		View v = inflater.inflate(R.layout.gv_mp_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvMp = (GridView) v.findViewById(R.id.gvMp);
			
			gvMp.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(mpIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvMp.setAdapter(new ImageAdapterMp(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Mana R")){
    		View v = inflater.inflate(R.layout.gv_mpr_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvMpR = (GridView) v.findViewById(R.id.gvMpR);
			
			gvMpR.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(mpRIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvMpR.setAdapter(new ImageAdapterMpR(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Movement S")){
    		View v = inflater.inflate(R.layout.gv_ms_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvMs = (GridView) v.findViewById(R.id.gvMs);
			
			gvMs.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(msIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvMs.setAdapter(new ImageAdapterMs(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Armor")){
    		View v = inflater.inflate(R.layout.gv_armor_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvArmor = (GridView) v.findViewById(R.id.gvArmor);
			
			gvArmor.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(armorIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvArmor.setAdapter(new ImageAdapterArmor(getActivity()));
    	}
    	
    	if(mContent.equalsIgnoreCase("Attack D")){
    		View v = inflater.inflate(R.layout.gv_ad_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvAd = (GridView) v.findViewById(R.id.gvAd);
				
				gvAd.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			        	Database db = new Database(getActivity());
			        	try{
			        		db.openRead();
			        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(adIcons.get(position)));
			        	}
			        	catch(Exception e){
			        		e.printStackTrace();
			        	}
			        	finally{
			        		db.close();
			        	}
			        }
			    });
		        
				gvAd.setAdapter(new ImageAdapterAd(getActivity()));
				
    	}
    	
    	if(mContent.equalsIgnoreCase("Ability P")){
    		View v = inflater.inflate(R.layout.gv_ap_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvAp = (GridView) v.findViewById(R.id.gvAp);
			
			gvAp.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(apIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvAp.setAdapter(new ImageAdapterAp(getActivity()));
    	}
    	

    	if(mContent.equalsIgnoreCase("Attack S")){
    		View v = inflater.inflate(R.layout.gv_as_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvAs = (GridView) v.findViewById(R.id.gvAs);
			
			gvAs.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(asIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvAs.setAdapter(new ImageAdapterAs(getActivity()));
    	}
    	
    	
    	if(mContent.equalsIgnoreCase("Magic R")){
    		View v = inflater.inflate(R.layout.gv_mr_inflate, ll, false);
        	ll.addView(v);
	    	GridView gvMr = (GridView) v.findViewById(R.id.gvMr);
			
			gvMr.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Database db = new Database(getActivity());
		        	try{
		        		db.openRead();
		        		((Item_List) getActivity()).addItemToSlot(db.getButtonById(mrIcons.get(position)));
		        	}
		        	catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	finally{
		        		db.close();
		        	}
		        }
		    });
			
			gvMr.setAdapter(new ImageAdapterMr(getActivity()));
    	}
    	
        return ll;
    }

	

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
    
    public class ImageAdapterArmor extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterArmor(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return armorIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return armorIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		armorIcons = db.getItemCategoryArmor();
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

	        imageView.setImageResource(armorIcons.get(position));
	        return imageView;
	    }
    }
    
    public class ImageAdapterHpR extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterHpR(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return hpRIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return hpRIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		hpRIcons = db.getItemCategoryHpR();
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

	        imageView.setImageResource(hpRIcons.get(position));
	        return imageView;
	    }
    }
    
    public class ImageAdapterMp extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterMp(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return mpIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return mpIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		mpIcons = db.getItemCategoryMp();
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

	        imageView.setImageResource(mpIcons.get(position));
	        return imageView;
	    }
    }
    
    public class ImageAdapterMpR extends BaseAdapter {
	    private Context mContext;
	    Database db;
	    
	    public ImageAdapterMpR(Context c) {
	        mContext = c;
	        db = new Database(c);
	        populate();
	    }

	    public int getCount() {
	        return mpRIcons.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return mpRIcons.get(position);
	    }
	    
	    public void populate() {
	    	try{
	    		db.openRead();
	    		mpRIcons = db.getItemCategoryMpR();
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

	        imageView.setImageResource(mpRIcons.get(position));
	        return imageView;
	    }
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
		    		apIcons = db.getItemCategoryAp();
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
	    
	    public class ImageAdapterAs extends BaseAdapter {
		    private Context mContext;
		    Database db;
		    
		    public ImageAdapterAs(Context c) {
		        mContext = c;
		        db = new Database(c);
		        populate();
		    }

		    public int getCount() {
		        return asIcons.size();
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return asIcons.get(position);
		    }
		    
		    public void populate() {
		    	try{
		    		db.openRead();
		    		asIcons = db.getItemCategoryAs();
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

		        imageView.setImageResource(asIcons.get(position));
		        return imageView;
		    }
	    }
	    
	    
	    public class ImageAdapterMs extends BaseAdapter {
		    private Context mContext;
		    Database db;
		    
		    public ImageAdapterMs(Context c) {
		        mContext = c;
		        db = new Database(c);
		        populate();
		    }

		    public int getCount() {
		        return msIcons.size();
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return msIcons.get(position);
		    }
		    
		    public void populate() {
		    	try{
		    		db.openRead();
		    		msIcons = db.getItemCategoryMs();
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

		        imageView.setImageResource(msIcons.get(position));
		        return imageView;
		    }
	    }
	    
	    public class ImageAdapterHp extends BaseAdapter {
		    private Context mContext;
		    Database db;
		    
		    public ImageAdapterHp(Context c) {
		        mContext = c;
		        db = new Database(c);
		        populate();
		    }

		    public int getCount() {
		        return hpIcons.size();
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return hpIcons.get(position);
		    }
		    
		    public void populate() {
		    	try{
		    		db.openRead();
		    		hpIcons = db.getItemCategoryHp();
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

		        imageView.setImageResource(hpIcons.get(position));
		        return imageView;
		    }

		   
		}
	    
	    public class ImageAdapterMr extends BaseAdapter {
		    private Context mContext;
		    Database db;
		    
		    public ImageAdapterMr(Context c) {
		        mContext = c;
		        db = new Database(c);
		        populate();
		    }

		    public int getCount() {
		        return mrIcons.size();
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return mrIcons.get(position);
		    }
		    
		    public void populate() {
		    	try{
		    		db.openRead();
		    		mrIcons = db.getItemCategoryMr();
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

		        imageView.setImageResource(mrIcons.get(position));
		        return imageView;
		    }

		   
		}

	   
	

}