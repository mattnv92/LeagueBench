package com.example.lolbuilder;

import java.util.ArrayList;
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
	private final int CRIT_TV_ID = 1009;
	private final int MR_TV_ID = 1010;
	private final int AS_TV_ID = 1011;
	
	private final int CDR_TV_ID = 1013;
	private final int RANGE_TV_ID = 1014;
	private final int MPR_TV_ID = 1015;
	private final int LEVEL_BUTTON_ID = 1020;
	private final int TEXT_SIZE = 20;
	private String champName;
	private String buildName;
	public boolean click = true;
	
	//stats
	double healthStat = 0;
	double healthRegenStat = 0;
	double armorStat = 0;
	double mrStat = 0;
	double adStat = 0;
	double apStat = 0;
	double mpStat = 0;
	double mprStat = 0;
	double msStat = 0;
	double asStat = 0;
	double critStat = 0;
	double hprStat = 0;
	double cdrStat = 0;
	
	//stats from items
	private double itemHP = 0;
	private double itemHR = 0;
	private double itemAR = 0;
	private double itemMR = 0;
	private double itemAD = 0;
	private double itemAP = 0;
	private double itemMPR = 0;
	private double itemMP = 0;
	private double itemMS = 0;
	private double itemAS = 0;
	private double itemCRIT = 0;
	private double itemCDR = 0;
	private double itemRANGE = 0;
	double itemSpeed = 0;
	
	//previously set level
	int setLevel = 1;
	
	private Database db;
	private Context con;
	
	//champ stats list
	private HashMap<String, Double> champStats = new HashMap<String, Double>();
	private HashMap<String, Integer> saveBuild = new HashMap<String, Integer>();
	

	
	
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
		//HashMap<String, Double> fullUnique = new HashMap<String, Double>();
		
		ArrayList<Item> uniqueListMS = new ArrayList<Item>();
		HashMap<String, Double> uniqueListMPR = new HashMap<String, Double>();
		
		
	
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
			
			if(array[0] != 0){
				itemName = db.getItemById(array[0]);
				item0 = db.getItemStats(itemName);
				itemSpeed = item0.get("MS");
				itemHP += item0.get("Health");
				itemAR += item0.get("Armor");
				itemAD += item0.get("AD");
				itemAP += item0.get("AP");
				itemMP += item0.get("MP");
				itemMR += item0.get("MR");
				itemCRIT += item0.get("CRIT");
				itemAS += item0.get("AS");
				itemCDR += item0.get("CDR");
				itemHR += item0.get("HPR");
				itemMPR += item0.get("MPR");
				//adds in percent speeds (e.g phantom dancer gives .05 ms)
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				//look for unique stats
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				//uniqueMPR = db.getUniqueItemMPR(itemName);
				saveBuild.put("item0", array[0]);
				
			}
			if(array[1] != 0){
				itemName = db.getItemById(array[1]);
				item1 = db.getItemStats(itemName);
				itemHP += item1.get("Health");
				itemAR += item1.get("Armor");
				itemAD += item1.get("AD");
				itemAP += item1.get("AP");
				itemMP += item1.get("MP");
				itemMR += item1.get("MR");
				itemCRIT += item1.get("CRIT");
				itemAS += item1.get("AS");
				itemSpeed = item1.get("MS");
				itemCDR += item1.get("CDR");
				itemHR += item1.get("HPR");
				itemMPR += item1.get("MPR");
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				saveBuild.put("item1", array[1]);
			}
			if(array[2] != 0){
				itemName = db.getItemById(array[2]);
				item2 = db.getItemStats(itemName);
				itemHP += item2.get("Health");
				armorStat += item2.get("Armor");
				adStat += item2.get("AD");
				apStat += item2.get("AP");
				mpStat += item2.get("MP");
				mrStat += item2.get("MR");
				critStat += item2.get("CRIT");
				asStat += item2.get("AS");
				cdrStat += item2.get("CDR");
				hprStat += item2.get("HPR");
				itemSpeed = item2.get("MS");
				itemMPR += item2.get("MPR");
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				saveBuild.put("item2", array[2]);
			}
			if(array[3] != 0){
				itemName = db.getItemById(array[3]);
				item3 = db.getItemStats(itemName);
				itemHP += item3.get("Health");
				itemAR += item3.get("Armor");
				itemAD += item3.get("AD");
				itemAP += item3.get("AP");
				itemMP += item3.get("MP");
				itemMR += item3.get("MR");
				itemCRIT += item3.get("CRIT");
				itemAS += item3.get("AS");
				itemSpeed = item3.get("MS");
				itemCDR += item3.get("CDR");
				itemHR += item3.get("HPR");
				itemMPR += item3.get("MPR");
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				saveBuild.put("item3", array[3]);
			}
			if(array[4] != 0){
				itemName = db.getItemById(array[4]);
				item4 = db.getItemStats(itemName);
				itemHP += item4.get("Health");
				itemAR += item4.get("Armor");
				itemAD += item4.get("AD");
				itemAP += item4.get("AP");
				itemMP += item4.get("MP");
				itemMR += item4.get("MR");
				itemCRIT += item4.get("CRIT");
				itemAS += item4.get("AS");
				itemSpeed = item4.get("MS");
				itemCDR += item4.get("CDR");
				itemHR += item4.get("HPR");
				itemMPR += item4.get("MPR");
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				saveBuild.put("item4", array[4]);
			}
			if(array[5] != 0){
				itemName = db.getItemById(array[5]);
				item5 = db.getItemStats(itemName);
				itemHP += item5.get("Health");
				itemAR += item5.get("Armor");
				itemAD += item5.get("AD");
				itemAP += item5.get("AP");
				itemMP += item5.get("MP");
				itemMR += item5.get("MR");
				itemCRIT += item5.get("CRIT");
				itemAS += item5.get("AS");
				itemSpeed = item5.get("MS");
				itemCDR += item5.get("CDR");
				itemHR += item5.get("HPR");
				itemMPR += item5.get("MPR");
				if(itemSpeed > 0 && itemSpeed < 1)
					perSpeed += itemSpeed;
				uniqueListMS.add(db.getUniqueItemMS(itemName));
				saveBuild.put("item5", array[5]);
			}
			
			
			//This part of the code is used to not allow people to "Stack" boots (i.e. you should'nt get 50 ms from 2 boots of speeds)
			double currSpeed = 0;
			boolean hasBoot = false;
			String name;
			double speed;
			for(Item i : uniqueListMS){
				name = i.getName();
				speed = i.getStat();
				if(name.equals("Boots of Speed")){
					if(currSpeed < speed){
						currSpeed = speed;
					}
				}
				else
					if(name.equals("Mercury's Treads")){
						if(currSpeed < speed){
							currSpeed = speed;
						}
					}
				else
					if(name.equals("Sorcerer's Shoes")){
						if(currSpeed < speed){
							currSpeed = speed;
						}
					}
				else
					if(name.equals("Berserker's Greaves")){
						if(currSpeed < speed){	
							currSpeed = speed;
						}
					}
				else
					if(name.equals("Ninja Tabi")){
						if(currSpeed < speed){	
							currSpeed = speed;
						}
					}
				else
					if(name.equals("Boots of Swiftness")){
						if(currSpeed < speed){	
							currSpeed = speed;
						}
					}
				else
					if(name.equals("Ionian Boots of Lucidity")){
						if(currSpeed < speed){	
							currSpeed = speed;
						}
					}
				else 
					if(name.equals("Boots of Mobility")){
						if(currSpeed < speed){	
							currSpeed = speed;
						}
					}
			}
			itemMS += currSpeed; 
			// end of boots movement speed stack fixing
			//add unique mpr items to champion stats
			
			
			//time to add in the percentages of the speed after adding the appropriate ms from the boots chosen if any boots were chosen at all
			itemMS += perSpeed * itemMS;
			
			
			
			final TextView healthTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			healthTv.setTextColor(Color.rgb(255, 255, 255));
			healthTv.setText(padding + "Health" + " : " + round((champStats.get("HP") + itemHP)));
			healthTv.setLayoutParams(lp);
			healthTv.setId(HEALTH_TV_ID);
			healthTv.setTextSize(TEXT_SIZE);
			rl.addView(healthTv);
			
			final TextView healthRegenTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, healthTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			healthRegenTv.setTextColor(Color.rgb(255, 255, 255));
			healthRegenTv.setText(padding + "HealthR" + " : " + round(champStats.get("HR") + itemHR));
			healthRegenTv.setLayoutParams(lp);
			healthRegenTv.setId(HEALTH_REGEN_TV_ID);
			healthRegenTv.setTextSize(TEXT_SIZE);
			rl.addView(healthRegenTv);
			
			final TextView armorTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, healthRegenTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			armorTv.setTextColor(Color.rgb(255, 255, 255));
			armorTv.setTextSize(TEXT_SIZE);
			armorTv.setText(padding + "Armor" + " : " + round(champStats.get("AR") + itemAR));
			armorTv.setLayoutParams(lp);
			armorTv.setId(ARMOR_TV_ID);
			rl.addView(armorTv);
			
			final TextView adTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, armorTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			adTv.setTextColor(Color.rgb(255, 255, 255));
			adTv.setText(padding + "AttackD" + " : " + round((champStats.get("AD") + itemAD)));
			adTv.setLayoutParams(lp);
			adTv.setId(ATTACK_DAMAGE_TV_ID);
			adTv.setTextSize(TEXT_SIZE);
			rl.addView(adTv);
			
			final TextView apTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, adTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			apTv.setTextColor(Color.rgb(255, 255, 255));
			apTv.setText(padding + "AbilityP" + " : " + round(itemAP));
			apTv.setLayoutParams(lp);
			apTv.setId(ABILITY_POWER_TV_ID);
			apTv.setTextSize(TEXT_SIZE);
			rl.addView(apTv);
			
			final TextView mpTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, apTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			mpTv.setTextColor(Color.rgb(255, 255, 255));
			mpTv.setText(padding + "Mana" + " : " + round((champStats.get("MP") + itemMP)));
			mpTv.setLayoutParams(lp);
			mpTv.setId(MANA_TV_ID);
			mpTv.setTextSize(TEXT_SIZE);
			rl.addView(mpTv);
			
			final TextView msTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, mpTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			msTv.setTextColor(Color.rgb(255, 255, 255));
			msTv.setText(padding + "MoveS" + " : " + round((champStats.get("MS") + itemMS)));
			msTv.setLayoutParams(lp);
			msTv.setId(MOVEMENT_SPEED_TV_ID);
			msTv.setTextSize(TEXT_SIZE);
			rl.addView(msTv);
			
			final TextView mrTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, msTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			mrTv.setTextColor(Color.rgb(255, 255, 255));
			mrTv.setText(padding + "MagicR" + " : " + round((champStats.get("MR") + itemMR)));
			mrTv.setLayoutParams(lp);
			mrTv.setId(MR_TV_ID);
			mrTv.setTextSize(TEXT_SIZE);
			rl.addView(mrTv);
			
			final TextView asTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, mrTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			asTv.setTextColor(Color.rgb(255, 255, 255));
			asTv.setText(padding + "AttackS" + " : " + round((champStats.get("AS") + itemAS)));
			asTv.setLayoutParams(lp);
			asTv.setId(AS_TV_ID);
			asTv.setTextSize(TEXT_SIZE);
			rl.addView(asTv);
			
			final TextView critTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, asTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			critTv.setTextColor(Color.rgb(255, 255, 255));
			critTv.setText(padding + "Crit" + " : " + round(itemCRIT));
			critTv.setLayoutParams(lp);
			critTv.setId(CRIT_TV_ID);
			critTv.setTextSize(TEXT_SIZE);
			rl.addView(critTv);
			
			final TextView cdrTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, critTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			cdrTv.setTextColor(Color.rgb(255, 255, 255));
			cdrTv.setText(padding + "Cooldown" + " : " + round(itemCDR));
			cdrTv.setLayoutParams(lp);
			cdrTv.setId(CDR_TV_ID);
			cdrTv.setTextSize(TEXT_SIZE);
			rl.addView(cdrTv);
			
			final TextView mprTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, cdrTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			mprTv.setTextColor(Color.rgb(255, 255, 255));
			mprTv.setText(padding + "ManaR" + " : " + round((champStats.get("MPR") + itemMPR)));
			mprTv.setLayoutParams(lp);
			mprTv.setId(MPR_TV_ID);
			mprTv.setTextSize(TEXT_SIZE);
			rl.addView(mprTv);
			
			final TextView rangeTv = new TextView(this);
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.BELOW, mprTv.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, iv.getId());
			rangeTv.setTextColor(Color.rgb(255, 255, 255));
			rangeTv.setText(padding + "Range" + " : " + round((champStats.get("RANGE") + itemRANGE)));
			rangeTv.setLayoutParams(lp);
			rangeTv.setId(RANGE_TV_ID);
			rangeTv.setTextSize(TEXT_SIZE);
			rl.addView(rangeTv);
			
			final LinearLayout rel = (LinearLayout)findViewById(R.id.csrl);
			LinearLayout.LayoutParams lpl = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			Button b = (Button)rel.findViewById(R.id.levelbut);
			final PopupWindow popUp = new PopupWindow(this);
			
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
									healthTv.setText(padding + "Health" + " : " + round(((setLevel * champStats.get("HPPL")) + champStats.get("HP") + itemHP)));
									healthRegenTv.setText(padding + "HealthR" + " : " + round(((setLevel * champStats.get("HRPL")) + champStats.get("HR") + itemHR)));
									armorTv.setText(padding + "Armor" + " : " + round(((setLevel * champStats.get("ARPL")) + champStats.get("AR") + itemAR)));
									adTv.setText(padding + "AttackD" + " : " + round(((setLevel * champStats.get("ADPL")) + champStats.get("AD") + itemAD)));
									mpTv.setText(padding + "Mana" + " : " + round(((setLevel * champStats.get("MPPL")) + champStats.get("MP") + itemMP)));
									mrTv.setText(padding + "MagicR" + " : " + round(((setLevel * champStats.get("MRPL")) + champStats.get("MR") + itemMR)));
									asTv.setText(padding + "AttackS" + " : " + round(((setLevel * champStats.get("ASPL")) + champStats.get("AS") + itemAS)));
									mprTv.setText(padding + "ManaR" + " : " + round(((setLevel * champStats.get("MPRPL")) + champStats.get("MPR") + itemMPR)));
								}
								else{
									healthTv.setText(padding + "Health" + " : " + round((champStats.get("HP") + itemHP)));
									healthRegenTv.setText(padding + "HealthR" + " : " + round((champStats.get("HR") + itemHR)));
									armorTv.setText(padding + "Armor" + " : " + round((champStats.get("AR") + itemAR)));
									adTv.setText(padding + "AttackD" + " : " + round((champStats.get("AD") + itemAD)));
									mpTv.setText(padding + "Mana" + " : " + round((champStats.get("MP") + itemMP)));
									mrTv.setText(padding + "MagicR" + " : " + round((champStats.get("MR") + itemMR)));
									asTv.setText(padding + "AttackS" + " : " + round((champStats.get("AS") + itemAS)));
									mprTv.setText(padding + "ManaR" + " : " + round((champStats.get("MPR") + itemMPR)));
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
			//lpl.addRule(RelativeLayout.BELOW, R.id.champ_stats_sv);
			b.setLayoutParams(lpl);
			b.setId(LEVEL_BUTTON_ID);
			//rel.addView(b);
			
			LinearLayout ll = new LinearLayout(this);
			LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			TextView tvLvl = new TextView(this);
			tvLvl.setText("Set Level:");
			
			popUp.setContentView(ll);
			ll.addView(tvLvl, lllp);
			
			//Alert dialog for naming your build
			
			final AlertDialog saveD = new AlertDialog.Builder(this).create();
			final EditText buildEt = new EditText(this);
			saveD.setTitle("Name your Build");
			saveD.setView(buildEt);
			
			//error dialog if edit text field is left blank
			final AlertDialog saveBuildErr = new AlertDialog.Builder(this).create();
			
			saveBuildErr.setButton(Dialog.BUTTON_NEGATIVE, "No!", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					saveBuildErr.dismiss();
				}
			});
			
			saveBuildErr.setButton(Dialog.BUTTON_POSITIVE, "Yes!", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					try{
						db.openRead();
						db.overWriteSavedBuild(saveBuild, champName, buildName);
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
			});
			
			
			saveBuildErr.setTitle("Overwrite This Build?");
			
			saveD.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					buildName = buildEt.getText().toString();
					try{
					db.openRead();
					if(db.buildNameExists(buildName))
						saveBuildErr.show();
					else
						db.saveBuild(saveBuild, champName, buildName);
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
			});
			
			//listener used to update database about a saved build
			OnClickListener save_listener = new OnClickListener() { 
				@Override
				public void onClick(View v) {
						saveD.show();
		            }
				};
			
			//save button
			//lpl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//lpl.addRule(RelativeLayout.BELOW, LEVEL_BUTTON_ID);
			/*Button save = new Button(this);
			save.setText("Save");
			save.setOnClickListener(save_listener);
			rel.addView(save, lpl);
			*/
			
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
	
	double round(double val) {
		return Math.round( val * 100.0 ) / 100.0;
	}

}
