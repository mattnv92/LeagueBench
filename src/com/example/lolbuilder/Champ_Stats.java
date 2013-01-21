package com.example.lolbuilder;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Champ_Stats extends Activity{
	
	private final int CHAMP_BANNER_ID = 1001;
	private final String padding = " ";
	private final int[] array = new int[6];
	private final int HEALTH_TV_ID = 1002;
	private final int HEALTH_REGEN_TV_ID = 1003;
	private final int ARMOR_TV_ID = 1004;
	private final int ATTACK_DAMAGE_TV_ID = 1005;
	private final int ABILITY_POWER_TV_ID = 1006;
	private final int MANA_TV_ID = 1007;
	private final int MOVEMENT_SPEED_TV_ID = 1008;
	private final int LEVEL_BUTTON_ID = 1009;
	private final int TEXT_SIZE = 20;
	public String champName;
	public boolean click = true;
	
	//stats
	double healthStat = 0;
	double healthRegenStat = 0;
	double armorStat = 0;
	double adStat = 0;
	double apStat = 0;
	double mpStat = 0;
	double msStat = 0;
	
	//stats from items
	private double itemHP = 0;
	private double itemHR = 0;
	
	//previously set level
	int setLevel = 1;
	
	private Database db;
	private Context con;
	
	//champ stats list
	private HashMap<String, Double> champStats = new HashMap<String, Double>();
	private HashMap<String, Double> saveBuild = new HashMap<String, Double>();
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_stats);
        Intent basket = getIntent();
        int id = basket.getExtras().getInt("champBanner");
        champName = basket.getExtras().getString("champChosen");
        array[0] = basket.getExtras().getInt("item0");
        array[1] = basket.getExtras().getInt("item1");
        array[2] = basket.getExtras().getInt("item2");
        array[3] = basket.getExtras().getInt("item3");
        array[4] = basket.getExtras().getInt("item4");
        array[5] = basket.getExtras().getInt("item5");
        init_banner(id);
    }
	
	public void init_banner(int BannerId) {
		
		con = Champ_Stats.this;
		db = new Database(this);
		HashMap<String, Double> item0 = new HashMap<String, Double>();
		HashMap<String, Double> item1 = new HashMap<String, Double>();
		HashMap<String, Double> item2 = new HashMap<String, Double>();
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		HashMap<String, Double> item4 = new HashMap<String, Double>();
		HashMap<String, Double> item5 = new HashMap<String, Double>();
		HashMap<String, Double> uniqueListMS = new HashMap<String, Double>();
		HashMap<String, Double> fullUnique = new HashMap<String, Double>();
		
		
		
		double itemSpeed = 0;
		double perSpeed = 0;
		
		String itemName;
		
		try{
			db.openRead();
			RelativeLayout rl;
			RelativeLayout.LayoutParams lp;
			
			rl = (RelativeLayout)findViewById(R.id.rlchamp_stats);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
			ImageView iv = db.button_to_banner(BannerId);
			iv.setId(CHAMP_BANNER_ID);
			lp.addRule(RelativeLayout.RIGHT_OF, 0);
			iv.setLayoutParams(lp);
			rl.addView(iv);
			
			//get champ stats
			champStats = db.champStatsByName(champName);
			healthStat += champStats.get("HP");
			armorStat += champStats.get("AR");
			adStat += champStats.get("AD");
			mpStat += champStats.get("MP");
			msStat += champStats.get("MS");
			
			
			if(array[0] != 0){
				itemName = db.getItemById(array[0]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item0 = db.getItemStats(itemName);
				itemSpeed = item0.get("MS");
				itemHP += item0.get("Health");
				armorStat += item0.get("Armor");
				adStat += item0.get("AD");
				apStat += item0.get("AP");
				mpStat += item0.get("MP");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
				
				saveBuild.put("item0", (double)array[0]);
				
			}
			if(array[1] != 0){
				itemName = db.getItemById(array[1]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item1 = db.getItemStats(itemName);
				itemHP += item1.get("Health");
				armorStat += item1.get("Armor");
				adStat += item1.get("AD");
				apStat += item1.get("AP");
				mpStat += item1.get("MP");
				itemSpeed = item1.get("MS");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
			}
			if(array[2] != 0){
				itemName = db.getItemById(array[2]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item2 = db.getItemStats(itemName);
				itemHP += item2.get("Health");
				armorStat += item2.get("Armor");
				adStat += item2.get("AD");
				apStat += item2.get("AP");
				mpStat += item2.get("MP");
				itemSpeed = item2.get("MS");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
			}
			if(array[3] != 0){
				itemName = db.getItemById(array[3]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item3 = db.getItemStats(itemName);
				itemHP += item3.get("Health");
				armorStat += item3.get("Armor");
				adStat += item3.get("AD");
				apStat += item3.get("AP");
				mpStat += item3.get("MP");
				itemSpeed = item3.get("MS");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
			}
			if(array[4] != 0){
				itemName = db.getItemById(array[4]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item4 = db.getItemStats(itemName);
				itemHP += item4.get("Health");
				armorStat += item4.get("Armor");
				adStat += item4.get("AD");
				apStat += item4.get("AP");
				mpStat += item4.get("MP");
				itemSpeed = item4.get("MS");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
			}
			if(array[5] != 0){
				itemName = db.getItemById(array[5]);
				uniqueListMS = db.getUniqueItemMS(itemName);
				if(uniqueListMS != null)
					fullUnique.put(itemName, uniqueListMS.get("MS"));
				item5 = db.getItemStats(itemName);
				itemHP += item5.get("Health");
				armorStat += item5.get("Armor");
				adStat += item5.get("AD");
				apStat += item5.get("AP");
				mpStat += item5.get("MP");
				itemSpeed = item5.get("MS");
				if(itemSpeed > 0 && itemSpeed < 1){
					perSpeed += itemSpeed;
				}
				else
					msStat += itemSpeed;
			}
			
			
			//This part of the code is used to not allow people to "Stack" boots (i.e. you should'nt get 50 ms from 2 boots of speeds)
			double currSpeed = 0;
			boolean hasBoot = false;
			String name;
			double speed;
			for(Map.Entry<String, Double> entry : fullUnique.entrySet()){
				name = entry.getKey().toString();
				speed = entry.getValue();
				if(name.contains("Boots of Speed") && !hasBoot){
						hasBoot = true;
						currSpeed = speed;
				}
				else
					if(currSpeed < speed && name.contains("Mercury's Treads")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else
					if(currSpeed < speed && name.contains("Sorcerer's Shoes")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else
					if(currSpeed < speed && name.contains("Berserker's Greaves")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else
					if(currSpeed < speed && name.contains("Ninja Tabi")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else
					if(currSpeed < speed && name.contains("Boots of Swiftness")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else
					if(currSpeed < speed && name.contains("Ionian Boots of Lucidity")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else 
					if(currSpeed < speed && name.contains("Boots of Mobility")){
						if(!hasBoot)
							hasBoot = true;
						currSpeed = speed;
					}
				else 
					msStat += currSpeed;
			}
			msStat += currSpeed;
			// end of movement speed stack fixing
			
			//time to add in the percentages of the speed after adding the appropriate ms from the boots chosen if any boots were chosen at all
			double tempSpeed = 0;
			tempSpeed = perSpeed * msStat;
			msStat += tempSpeed;
			
			final TextView healthTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			healthTv.setTextColor(Color.rgb(255, 255, 255));
			healthTv.setText(padding + "Health" + " : " + (champStats.get("HP") + itemHP));
			healthTv.setLayoutParams(lp);
			healthTv.setId(HEALTH_TV_ID);
			healthTv.setTextSize(TEXT_SIZE);
			rl.addView(healthTv);
			
			final TextView healthRegenTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, healthTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			healthRegenTv.setTextColor(Color.rgb(255, 255, 255));
			healthRegenTv.setText(padding + "HealthR" + " : " + champStats.get("HR"));
			healthRegenTv.setLayoutParams(lp);
			healthRegenTv.setId(HEALTH_REGEN_TV_ID);
			healthRegenTv.setTextSize(TEXT_SIZE);
			rl.addView(healthRegenTv);
			
			TextView armorTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, healthRegenTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			armorTv.setTextColor(Color.rgb(255, 255, 255));
			armorTv.setTextSize(TEXT_SIZE);
			armorTv.setText(padding + "Armor" + " : " + armorStat);
			armorTv.setLayoutParams(lp);
			armorTv.setId(ARMOR_TV_ID);
			rl.addView(armorTv);
			
			TextView adTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, armorTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			adTv.setTextColor(Color.rgb(255, 255, 255));
			adTv.setText(padding + "AttackD" + " : " + adStat);
			adTv.setLayoutParams(lp);
			adTv.setId(ATTACK_DAMAGE_TV_ID);
			adTv.setTextSize(TEXT_SIZE);
			rl.addView(adTv);
			
			TextView apTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, adTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			apTv.setTextColor(Color.rgb(255, 255, 255));
			apTv.setText(padding + "AbilityP" + " : " + apStat);
			apTv.setLayoutParams(lp);
			apTv.setId(ABILITY_POWER_TV_ID);
			apTv.setTextSize(TEXT_SIZE);
			rl.addView(apTv);
			
			TextView mpTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, apTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			mpTv.setTextColor(Color.rgb(255, 255, 255));
			mpTv.setText(padding + "Mana" + " : " + mpStat);
			mpTv.setLayoutParams(lp);
			mpTv.setId(MANA_TV_ID);
			mpTv.setTextSize(TEXT_SIZE);
			rl.addView(mpTv);
			
			TextView msTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, mpTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			msTv.setTextColor(Color.rgb(255, 255, 255));
			msTv.setText(padding + "MoveS" + " : " + msStat);
			msTv.setLayoutParams(lp);
			msTv.setId(MOVEMENT_SPEED_TV_ID);
			msTv.setTextSize(TEXT_SIZE);
			rl.addView(msTv);
			
			final RelativeLayout rel = (RelativeLayout)findViewById(R.id.csrl);
			RelativeLayout.LayoutParams lpl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			Button b = new Button(this);
			final PopupWindow popUp = new PopupWindow(this);
			b.setText("Level");
			
			//level up dialog
			final AlertDialog d = new AlertDialog.Builder(this).create();
			final EditText et = new EditText(this);
			et.setInputType(InputType.TYPE_CLASS_NUMBER);
			et.setText("" + setLevel);
			d.setTitle("Level Up");
			d.setView(et);
			
			final AlertDialog err = new AlertDialog.Builder(this).create();
			err.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					et.setText("" + setLevel);
					err.dismiss();
				}
			});
			err.setTitle("The Level you have chosen is invalid!");
			
			//this button is going to calculate the correct stats given the level of the champion
			d.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(et.getText().toString().equals(""))
						err.show();
						else{
						int temp = Integer.parseInt(et.getText().toString());
						if(temp > 18 || temp < 1){		
							err.show();
						}
						else{
							if(setLevel != temp){
								setLevel = temp;
								if(setLevel > 1){	
									healthTv.setText(padding + "Health" + " : " + ((setLevel * champStats.get("HPPL")) + champStats.get("HP") + itemHP));
									healthRegenTv.setText(padding + "HealthR" + " : " + ((setLevel * champStats.get("HRPL")) + champStats.get("HR") + itemHR));
								}
								else{
									healthTv.setText(padding + "Health" + " : " + (setLevel  + champStats.get("HP") + itemHP));
									healthRegenTv.setText(padding + "HealthR" + " : " + (setLevel + champStats.get("HR") + itemHR));
								}
							}
						}
					}
				}
			});
			
			
			
			OnClickListener b_listener = new OnClickListener() { 
				@Override
				public void onClick(View v) {
					d.show();
		            }
				};
				
			
			b.setOnClickListener(b_listener);
			lpl.addRule(RelativeLayout.BELOW, R.id.champ_stats_sv);
			b.setLayoutParams(lpl);
			b.setId(LEVEL_BUTTON_ID);
			rel.addView(b);
			
			LinearLayout ll = new LinearLayout(this);
			LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			TextView tvLvl = new TextView(this);
			tvLvl.setText("Set Level:");
			
			popUp.setContentView(ll);
			ll.addView(tvLvl, lllp);
			
			//listener used to update database about a saved build
			OnClickListener save_listener = new OnClickListener() { 
				@Override
				public void onClick(View v) {
					
						try{
							db.openRead();
							db.saveBuild(saveBuild, champName);
						}
						catch(Exception e){
							Dialog d = new Dialog(con);
							d.setTitle("Uh Oh!");
							TextView tv = new TextView(con);
							tv.setText(e.getMessage());
							d.setContentView(tv);
							d.show();
						}
						finally{
								db.close();
						}
		            }
				};
			
			//save button
			lpl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			lpl.addRule(RelativeLayout.BELOW, LEVEL_BUTTON_ID);
			Button save = new Button(this);
			save.setText("Save");
			save.setOnClickListener(save_listener);
			rel.addView(save, lpl);
			
			
		}
		catch(Exception e){
			Dialog d = new Dialog(this);
			d.setTitle("Uh Oh!");
			TextView tv = new TextView(this);
			tv.setText("The program's stepped on a shroom! Stupid Teemo!");
			d.setContentView(tv);
			d.show();
		}
		finally{
			db.close();
		}
		
	}
	

}
