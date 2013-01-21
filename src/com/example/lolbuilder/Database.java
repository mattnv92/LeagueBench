package com.example.lolbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

public class Database {
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static final String DATABASE_NAME = "LeagueBenchDB";
	private static final int DATABASE_VERSION = 1;
	
	//Champ Icons Table
	private static final String DATABASE_TABLE_CHAMP_ICONS = "champ_icon_table";
	
	public static final String KEY_ICONROWID = "_id";
	public static final String KEY_ICONNAME = "champ_name";
	public static final String KEY_ICON = "champ_icon";
	public static final String KEY_BUTTON = "champ_button";
	public static final String KEY_BANNER = "champ_banner";
	//Champ Icons Table
	
	//Champ Stats Table
	private static final String DATABASE_TABLE_CHAMPSTATS = "champ_stats_table";
	
	public static final String KEY_CHAMPSTATSID = "_id";
	public static final String KEY_CHAMPSTATSNAME = "champ_name";
	public static final String KEY_CHAMPHP = "champ_hp";
	public static final String KEY_CHAMPHPPL = "champ_hppl";
	public static final String KEY_CHAMPHR = "champ_hr";
	public static final String KEY_CHAMPHRPL = "champ_hrpl";
	public static final String KEY_CHAMPMP = "champ_mp";
	public static final String KEY_CHAMPMPPL = "champ_mppl";
	public static final String KEY_CHAMPMPR = "champ_mpr";
	public static final String KEY_CHAMPMPRPL = "champ_mprpl";
	public static final String KEY_CHAMPAD = "champ_ad";
	public static final String KEY_CHAMPADPL = "champ_adpl";
	public static final String KEY_CHAMPAP = "champ_ap";
	public static final String KEY_CHAMPAPPL = "champ_appl";
	public static final String KEY_CHAMPAS = "champ_as";
	public static final String KEY_CHAMPASPL = "champ_aspl";
	public static final String KEY_CHAMPAR = "champ_ar";
	public static final String KEY_CHAMPARPL = "champ_arpl";
	public static final String KEY_CHAMPMR = "champ_mr";
	public static final String KEY_CHAMPMRPL = "champ_mrpl";
	public static final String KEY_CHAMPMS = "champ_ms";
	public static final String KEY_CHAMPRANGE = "champ_range";
	//Champ Stats Table	
	
	//Items Table
	private static final String DATABASE_TABLE_ITEMSTATS = "items_table";
	
	public static final String KEY_ITEMSTATSID = "_id";
	public static final String KEY_ITEMNAME = "item_name";
	public static final String KEY_ITEMMS = "item_ms";
	public static final String KEY_ITEMHP = "item_hp";
	public static final String KEY_ITEMHPR = "item_hpr";
	public static final String KEY_ITEMAR = "item_ar";
	public static final String KEY_ITEMUNIQUEMS = "item_unique";
	public static final String KEY_ITEMAD = "item_ad";
	public static final String KEY_ITEMAP = "item_ap";
	public static final String KEY_ITEMMP = "item_mp";
	public static final String KEY_ITEMMR = "item_mr";
	public static final String KEY_ITEMMPR = "item_mpr";
	//public static final String KEY_ITEMCOST = "item_cost";
	//Items Table
	
	//Item Icons Table
	private static final String DATABASE_TABLE_ITEMICONS = "champ_itemicon_table";
	
	public static final String KEY_ITEMICONID = "_id";
	public static final String KEY_ITEMICONNAME = "item_name";
	public static final String KEY_ITEMICON = "item_icon";
	public static final String KEY_ITEMBUTTON = "item_button";
	
	//Build saver Table
	private static final String DATABASE_TABLE_SAVEBUILD = "save_build_table";
	
	//saved item iconstuff
	public static final String KEY_SAVECHAMPNAME = "savechamp_name";
	public static final String KEY_SAVEITEMBUTTON0 = "saveitem_button0";
	public static final String KEY_SAVEITEMBUTTON1 = "saveitem_button1";
	public static final String KEY_SAVEITEMBUTTON2 = "saveitem_button2";
	public static final String KEY_SAVEITEMBUTTON3 = "saveitem_button3";
	public static final String KEY_SAVEITEMBUTTON4 = "saveitem_button4";
	public static final String KEY_SAVEITEMBUTTON5 = "saveitem_button5";
	
	
	private static class DbHelper extends SQLiteOpenHelper{
	
		Context myContext;
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			myContext = context;
		}
		

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Building table for Champion Icons
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_CHAMP_ICONS + " (" + KEY_ICONROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_ICONNAME + " TEXT NOT NULL, " + KEY_ICON + " INTEGER NOT NULL, " + KEY_BUTTON + " INTEGER NOT NULL, "
					+ KEY_BANNER + " INTEGER NOT NULL);" 
			);
			// Building table for Item Stats
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_ITEMSTATS + " (" + KEY_ITEMSTATSID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_ITEMNAME + " TEXT NOT NULL, " + KEY_ITEMMS + " REAL, " + KEY_ITEMHP + " INTEGER, " + KEY_ITEMHPR + 
					" INTEGER, " + KEY_ITEMAR + " INTEGER, " + KEY_ITEMAD + " INTEGER, " + KEY_ITEMAP + " INTEGER, " +
					KEY_ITEMMP + " INTEGER, " + KEY_ITEMMPR + " INTEGER, " + KEY_ITEMMR + " INTEGER, " + KEY_ITEMUNIQUEMS + " REAL);" 
			);
			// Building table for Item Icons
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_ITEMICONS + " (" + KEY_ITEMICONID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_ITEMICONNAME + " TEXT NOT NULL, " + KEY_ITEMICON + " INTEGER NOT NULL, " + KEY_ITEMBUTTON + 
					" INTEGER NOT NULL);" 
			);
			// Building table for Champ Stats
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_CHAMPSTATS + " (" + KEY_CHAMPSTATSID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CHAMPSTATSNAME + " TEXT NOT NULL, "
					+ KEY_CHAMPHP + " REAL NOT NULL, " + KEY_CHAMPHPPL + " REAL NOT NULL, " + KEY_CHAMPHR + " REAL NOT NULL, "
					+ KEY_CHAMPHRPL + " REAL NOT NULL, " + KEY_CHAMPMP + " REAL NOT NULL, " + KEY_CHAMPMPPL + " REAL NOT NULL, "
					+ KEY_CHAMPMPR + " REAL NOT NULL, " + KEY_CHAMPMPRPL + " REAL NOT NULL, " + KEY_CHAMPAD + " REAL NOT NULL, "
					+ KEY_CHAMPADPL + " REAL NOT NULL, "  
					+ KEY_CHAMPAS + " REAL NOT NULL, " + KEY_CHAMPASPL + " REAL NOT NULL, " + KEY_CHAMPAR + " REAL NOT NULL, " 
					+ KEY_CHAMPARPL + " REAL NOT NULL, " + KEY_CHAMPMR + " REAL NOT NULL, " + KEY_CHAMPMRPL + " REAL NOT NULL, " 
					+ KEY_CHAMPMS + " REAL NOT NULL, " + KEY_CHAMPRANGE + " REAL NOT NULL);"
			);
			// Building table for saving/editing builds
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_SAVEBUILD + " (" + KEY_SAVECHAMPNAME + " TEXT NOT NULL, " + KEY_SAVEITEMBUTTON0 + " INTEGER, " 
					+ KEY_SAVEITEMBUTTON1 + "INTEGER, " + KEY_SAVEITEMBUTTON2 + "INTEGER, "
					+ KEY_SAVEITEMBUTTON3 + "INTEGER, " + KEY_SAVEITEMBUTTON4 + "INTEGER, "
					+ KEY_SAVEITEMBUTTON5 + "INTEGER);" 
			);
			
			try{
				generateChampIconsTable(db);
				generateItemIconTable(db);
				generateChampStatsTable(db);
				generateItemsTable(db);
			}
			catch(SQLiteException e){
				e.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CHAMP_ICONS);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ITEMSTATS);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ITEMICONS);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CHAMPSTATS);
			onCreate(db);
		}
		
		public void generateChampIconsTable(SQLiteDatabase db) throws SQLiteException {
			ContentValues cv = new ContentValues();
			
			//do i need all these inserts and clears????????????????????????? check later
			cv.put(KEY_ICONNAME, "Ahri");
			cv.put(KEY_ICON, R.drawable.ahributtonnormal);
			cv.put(KEY_BUTTON, R.drawable.ahri_button);
			cv.put(KEY_BANNER, R.drawable.ahri_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Akali");
			cv.put(KEY_ICON, R.drawable.akalibuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.akali_button);
			cv.put(KEY_BANNER, R.drawable.akali_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Alistar");
			cv.put(KEY_ICON, R.drawable.alistarbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.alistar_button);
			cv.put(KEY_BANNER, R.drawable.alistar_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Amumu");
			cv.put(KEY_ICON, R.drawable.amumubuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.amumu_button);
			cv.put(KEY_BANNER, R.drawable.amumu_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Anivia");
			cv.put(KEY_ICON, R.drawable.aniviabuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.anivia_button);
			cv.put(KEY_BANNER, R.drawable.anivia_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Annie");
			cv.put(KEY_ICON, R.drawable.anniebuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.annie_button);
			cv.put(KEY_BANNER, R.drawable.annie_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ashe");
			cv.put(KEY_ICON, R.drawable.ashebuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.ashe_button);
			cv.put(KEY_BANNER, R.drawable.ashe_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Blitzcrank");
			cv.put(KEY_ICON, R.drawable.blitzcrankbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.blitzcrank_button);
			cv.put(KEY_BANNER, R.drawable.blitzcrank_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Brand");
			cv.put(KEY_ICON, R.drawable.brandbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.brand_button);
			cv.put(KEY_BANNER, R.drawable.brand_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Caitlyn");
			cv.put(KEY_ICON, R.drawable.caitlynbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.caitlyn_button);
			cv.put(KEY_BANNER, R.drawable.caitlyn_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Cassiopeia");
			cv.put(KEY_ICON, R.drawable.cassiopeiabuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.cassiopeia_button);
			cv.put(KEY_BANNER, R.drawable.cassiopeia_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Cho`Gath");
			cv.put(KEY_ICON, R.drawable.chogathbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.chogath_button);
			cv.put(KEY_BANNER, R.drawable.chogath_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Corki");
			cv.put(KEY_ICON, R.drawable.corkibuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.corki_button);
			cv.put(KEY_BANNER, R.drawable.corki_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Darius");
			cv.put(KEY_ICON, R.drawable.dariusbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.darius_button);
			cv.put(KEY_BANNER, R.drawable.darius_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Diana");
			cv.put(KEY_ICON, R.drawable.dianabuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.diana_button);
			cv.put(KEY_BANNER, R.drawable.diana_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Draven");
			cv.put(KEY_ICON, R.drawable.dravenbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.draven_button);
			cv.put(KEY_BANNER, R.drawable.draven_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Dr. Mundo");
			cv.put(KEY_ICON, R.drawable.drmundobuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.drmundo_button);
			cv.put(KEY_BANNER, R.drawable.drmundo_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Elise");
			cv.put(KEY_ICON, R.drawable.elisebuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.elise_button);
			cv.put(KEY_BANNER, R.drawable.elise_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Evelynn");
			cv.put(KEY_ICON, R.drawable.evelynnbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.evelynn_button);
			cv.put(KEY_BANNER, R.drawable.evelynn_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ezreal");
			cv.put(KEY_ICON, R.drawable.ezrealbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.ezreal_button);
			cv.put(KEY_BANNER, R.drawable.ezreal_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Fiddlesticks");
			cv.put(KEY_ICON, R.drawable.fiddlesticksbuttonnormal);
			cv.put(KEY_BUTTON, R.drawable.fiddlesticks_button);
			cv.put(KEY_BANNER, R.drawable.fiddlesticks_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
		}
		
		public void generateItemIconTable(SQLiteDatabase db) {
			ContentValues cv = new ContentValues();
			
			cv.put(KEY_ITEMICONNAME, "Aegis of the Legion");
			cv.put(KEY_ITEMICON, R.drawable.aegisbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.aegis_of_the_legion);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Amplifying Tome");
			cv.put(KEY_ITEMICON, R.drawable.amptomebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.amplifying_tome);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Banshee's Veil");
			cv.put(KEY_ITEMICON, R.drawable.bansheebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.banshee_veil);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Boots of Speed");
			cv.put(KEY_ITEMICON, R.drawable.bosbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.boots_of_speed);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Blasting Wand");
			cv.put(KEY_ITEMICON, R.drawable.blastingbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.blasting_wand);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Brutalizer");
			cv.put(KEY_ITEMICON, R.drawable.brutalizerbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.brutalizer_button);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Doran's Shield");
			cv.put(KEY_ITEMICON, R.drawable.dsbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.doran_shield);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Doran's Ring");
			cv.put(KEY_ITEMICON, R.drawable.dringbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.doran_ring);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Faerie Charm");
			cv.put(KEY_ITEMICON, R.drawable.fcharmbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.faerie_charm);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Frozen Heart");
			cv.put(KEY_ITEMICON, R.drawable.frozenheartbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.frozen_heart);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Giant's Belt");
			cv.put(KEY_ITEMICON, R.drawable.giantbeltbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.giants_belt);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Lich Bane");
			cv.put(KEY_ITEMICON, R.drawable.lichbanebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.lich_bane);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Madred's Razors");
			cv.put(KEY_ITEMICON, R.drawable.madredsbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.madreds_razors);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Mercury's Treads");
			cv.put(KEY_ITEMICON, R.drawable.mercbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.mercury_treads);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Negatron Cloak");
			cv.put(KEY_ITEMICON, R.drawable.negcbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.negatron_cloak);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Rejuvenation Bead");
			cv.put(KEY_ITEMICON, R.drawable.rejbeadbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.rejuvenation_bead);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Sapphire Crystal");
			cv.put(KEY_ITEMICON, R.drawable.sapphbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.sapphire_crystal);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Void Staff");
			cv.put(KEY_ITEMICON, R.drawable.voidstaffbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.void_staff);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			
		}
		
		public void generateChampStatsTable(SQLiteDatabase db) {
			ContentValues cv = new ContentValues();
			Scanner input = new Scanner(myContext.getResources().openRawResource(R.raw.champstats));
			while(input.hasNext()){
				String stat;
				String[] num;
				StringTokenizer line = new StringTokenizer(input.nextLine());
				String champ_name = line.nextToken("~");
				cv.put(KEY_CHAMPSTATSNAME, champ_name);
				while(line.hasMoreTokens()){
					stat = line.nextToken("~");
					num = stat.split(" ");
					if(stat.contains("HP"))
						cv.put(KEY_CHAMPHP, Double.parseDouble(num[0]));
					if(stat.contains("hPPL"))
						cv.put(KEY_CHAMPHPPL, Double.parseDouble(num[0]));
					if(stat.contains("HR"))
						cv.put(KEY_CHAMPHR, Double.parseDouble(num[0]));
					if(stat.contains("hRPL"))
						cv.put(KEY_CHAMPHRPL, Double.parseDouble(num[0]));
					if(stat.contains("MP"))
						cv.put(KEY_CHAMPMP, Double.parseDouble(num[0]));
					if(stat.contains("mPPL"))
						cv.put(KEY_CHAMPMPPL, Double.parseDouble(num[0]));
					if(stat.contains("mpR"))
						cv.put(KEY_CHAMPMPR, Double.parseDouble(num[0]));
					if(stat.contains("mprPL"))
						cv.put(KEY_CHAMPMPRPL, Double.parseDouble(num[0]));
					if(stat.contains("DMG"))
						cv.put(KEY_CHAMPAD, Double.parseDouble(num[0]));
					if(stat.contains("dMGPL"))
						cv.put(KEY_CHAMPADPL, Double.parseDouble(num[0]));
					if(stat.contains("AS"))
						cv.put(KEY_CHAMPAS, Double.parseDouble(num[0]));
					if(stat.contains("aSPL"))
						cv.put(KEY_CHAMPASPL, Double.parseDouble(num[0]));
					if(stat.contains("AR"))
						cv.put(KEY_CHAMPAR, Double.parseDouble(num[0]));
					if(stat.contains("aRPL"))
						cv.put(KEY_CHAMPARPL, Double.parseDouble(num[0]));
					if(stat.contains("MR"))
						cv.put(KEY_CHAMPMR, Double.parseDouble(num[0]));
					if(stat.contains("mRPL"))
						cv.put(KEY_CHAMPMRPL, Double.parseDouble(num[0]));
					if(stat.contains("MS"))
						cv.put(KEY_CHAMPMS, Double.parseDouble(num[0]));
					if(stat.contains("RANGE"))
						cv.put(KEY_CHAMPRANGE, Double.parseDouble(num[0]));
				}
				
				db.insertOrThrow(DATABASE_TABLE_CHAMPSTATS, null, cv);
				cv.clear();
			}
		}
		
		public void generateItemsTable(SQLiteDatabase db) {
			ContentValues cv = new ContentValues();
			Scanner input = new Scanner(myContext.getResources().openRawResource(R.raw.items));
			while(input.hasNext()){
				String stat;
				String num[];
				StringTokenizer line = new StringTokenizer(input.nextLine());
				String item_name = line.nextToken("~");
				cv.put(KEY_ITEMNAME, item_name);
				while(line.hasMoreTokens()){
					stat = line.nextToken("~");
					num = stat.split(" ");
					if(stat.contains("unique")){
						if(stat.contains("Movement Speed"))
							cv.put(KEY_ITEMUNIQUEMS, Double.parseDouble(num[1]));
					}
					else{
						if(stat.contains("Movement Speed"))
							cv.put(KEY_ITEMMS, Double.parseDouble(num[0]));
						if(stat.contains("Health"))
							cv.put(KEY_ITEMHP, Integer.parseInt(num[0]));
						if(stat.contains("health Regen"))
							cv.put(KEY_ITEMHPR, Integer.parseInt(num[0]));
						if(stat.contains("Armor"))
							cv.put(KEY_ITEMAR, Integer.parseInt(num[0]));
						if(stat.contains("Attack Damage"))
							cv.put(KEY_ITEMAD, Integer.parseInt(num[0]));
						if(stat.contains("Ability Power"))
							cv.put(KEY_ITEMAP, Integer.parseInt(num[0]));
						if(stat.contains("Mana"))
							cv.put(KEY_ITEMMP, Integer.parseInt(num[0]));
						if(stat.contains("mana Regen"))
							cv.put(KEY_ITEMMPR, Integer.parseInt(num[0]));
						if(stat.contains("Magic Resist"))
							cv.put(KEY_ITEMMR, Integer.parseInt(num[0]));
					}	
					
				}
				db.insertOrThrow(DATABASE_TABLE_ITEMSTATS, null, cv);
				cv.clear();	
			}
			
			
		}
		
	}
	
	public Database(Context c) {
		ourContext = c;
	}
	
	public Database openRead() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getReadableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public void saveBuild(HashMap<String, Double> buildList, String champName) {
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_SAVECHAMPNAME, champName);
		if(buildList.get("item0") != null)
			cv.put(KEY_SAVEITEMBUTTON0, buildList.get("item0"));
		ourDatabase.insertOrThrow(DATABASE_TABLE_SAVEBUILD, null, cv);
		cv.clear();
		
	}
	
	public ArrayList<Integer> getChampButtons() throws SQLiteException{
		ArrayList<Integer> result = new ArrayList<Integer>();
		String[] columns = new String[] {KEY_ICONROWID, KEY_ICONNAME, KEY_ICON, KEY_BUTTON, KEY_BANNER};
		Cursor c = ourDatabase.query(DATABASE_TABLE_CHAMP_ICONS, columns, KEY_BUTTON, null, null, null, KEY_ICONNAME);
		int iIcon = c.getColumnIndex(KEY_ICON);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result.add(c.getInt(iIcon));
		}
		return result;
	}
	
	public String champNameById(int id) {
		String result;
		Cursor c = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_CHAMP_ICONS + " WHERE " + KEY_ICON + " = " + id, null);
		int resultCol = c.getColumnIndex(KEY_ICONNAME);
		c.moveToFirst();
		result = c.getString(resultCol);
		return result;
	}
	
	public ImageView button_to_banner(int buttonId) throws SQLiteException{
		ImageView result = new ImageView(ourContext);
		Cursor c = ourDatabase.rawQuery("SELECT " + KEY_BANNER + " FROM " + DATABASE_TABLE_CHAMP_ICONS + 
				" WHERE " + KEY_ICON + " = " + buttonId, null);
		int resultCol = c.getColumnIndex(KEY_BANNER);
		c.moveToFirst();
		result.setId(1001);
		result.setBackgroundResource(c.getInt(resultCol));
		return result;
		
		
	}

	public HashMap<String, Integer> getItemCategoryEND() throws SQLiteException {
		
		HashMap<String, Integer> itemList = new HashMap<String, Integer>();
		//ArrayList<String> items = new ArrayList<String>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMHP + " > 0" + " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMP + " > 0"
		+ " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMPR + " > 0)", null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return itemList;
		
		
	}
	
	public HashMap<String, Integer> getItemCategoryAP() throws SQLiteException {
		
		HashMap<String, Integer> itemList = new HashMap<String, Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAP + " > 0" , null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return itemList;
		
		
	}
	
	public HashMap<String, Integer> getItemCategoryMS() throws SQLiteException {
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMS + " > 0" + " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMUNIQUEMS + " > 0)", null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return result;
	}
	
	public HashMap<String, Integer> getItemCategorySPEED() throws SQLiteException {
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMS + " > 0", null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return result;
	}
	
	
	public HashMap<String, Integer> getItemCategoryAD() throws SQLiteException {
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAD + " > 0", null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return result;
	}
	
	public HashMap<String, Integer> getItemCategoryDefense() throws SQLiteException {
		
		HashMap<String, Integer> itemList = new HashMap<String, Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAR + " > 0" + " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMR + " > 0)", null);
		int nameCol = c1.getColumnIndex(KEY_ITEMNAME);
		int iconCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			itemList.put(c1.getString(nameCol), c1.getInt(iconCol));
		}
		
		return itemList;
		
		
	}
	
	public HashMap<String, Double> champStatsByName(String champName) {
		HashMap<String, Double> result = new HashMap<String, Double>();
		Cursor c = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_CHAMPSTATS + " WHERE " + KEY_CHAMPSTATSNAME + " = ?", new String[]{champName});
		
		int hpCol = c.getColumnIndex(KEY_CHAMPHP);
		int hpplCol = c.getColumnIndex(KEY_CHAMPHPPL);
		int hrCol = c.getColumnIndex(KEY_CHAMPHR);
		int hrplCol = c.getColumnIndex(KEY_CHAMPHRPL);
		int mpCol = c.getColumnIndex(KEY_CHAMPMP);
		int mpplCol = c.getColumnIndex(KEY_CHAMPMPPL);
		int mprCol = c.getColumnIndex(KEY_CHAMPMPR);
		int mprplCol = c.getColumnIndex(KEY_CHAMPMPRPL);
		int dmgCol = c.getColumnIndex(KEY_CHAMPAD);
		int dmgplCol = c.getColumnIndex(KEY_CHAMPADPL);
		int asCol = c.getColumnIndex(KEY_CHAMPAS);
		int asplCol = c.getColumnIndex(KEY_CHAMPASPL);
		int arCol = c.getColumnIndex(KEY_CHAMPAR);
		int arplCol = c.getColumnIndex(KEY_CHAMPARPL);
		int mrCol = c.getColumnIndex(KEY_CHAMPMR);
		int mrplCol = c.getColumnIndex(KEY_CHAMPMRPL);
		int msCol = c.getColumnIndex(KEY_CHAMPMS);
		int rangeCol = c.getColumnIndex(KEY_CHAMPRANGE);
		
		if(c.moveToFirst()){
			result.put("HP", c.getDouble(hpCol));
			result.put("HPPL", c.getDouble(hpplCol));
			result.put("HR", c.getDouble(hrCol));
			result.put("HRPL", c.getDouble(hrplCol));
			result.put("MP", c.getDouble(mpCol));
			result.put("MPPL", c.getDouble(mpplCol));
			result.put("MPR", c.getDouble(mprCol));
			result.put("MPRPL", c.getDouble(mprplCol));
			result.put("AD", c.getDouble(dmgCol));
			result.put("ADPL", c.getDouble(dmgplCol));
			result.put("AS", c.getDouble(asCol));
			result.put("ASPL", c.getDouble(asplCol));
			result.put("AR", c.getDouble(arCol));
			result.put("ARPL", c.getDouble(arplCol));
			result.put("MR", c.getDouble(mrCol));
			result.put("MRPL", c.getDouble(mrplCol));
			result.put("MS", c.getDouble(msCol));
			result.put("RANGE", c.getDouble(rangeCol));
		}
		else{
			result.put("HP", 0.0);
			result.put("HPPL", 0.0);
			result.put("HR", 0.0);
			result.put("HRPL", 0.0);
			result.put("MP", 0.0);
			result.put("MPPL", 0.0);
			result.put("MPR", 0.0);
			result.put("MPRPL", 0.0);
			result.put("AD", 0.0);
			result.put("ADPL", 0.0);
			result.put("AS", 0.0);
			result.put("ASPL", 0.0);
			result.put("AR", 0.0);
			result.put("ARPL", 0.0);
			result.put("MR", 0.0);
			result.put("MRPL", 0.0);
			result.put("MS", 0.0);
			result.put("RANGE", 0.0);
		}
		return result;
	}
	
	
	
	public HashMap<String, Double> getUniqueItemMS(String itemName) {
		
		HashMap<String, Double> result = new HashMap<String, Double>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + " WHERE " + KEY_ITEMNAME + " = ?", new String[] {itemName});
		int col = c1.getColumnIndex(KEY_ITEMUNIQUEMS);
		if(c1.moveToFirst()) {
			if(c1.getString(col) != null){
				result.put("MS", c1.getDouble(col));
				return result;
			}
		}
		return null;	
		
	}
	
	public String getItemById(int itemId) throws SQLiteException {
		String result;
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMICONS + " WHERE " + KEY_ITEMBUTTON + " = " + itemId, null);
		int resultCol = c1.getColumnIndex(KEY_ITEMICONNAME);
		c1.moveToFirst();
		result = c1.getString(resultCol);
		return result;
	}
	
	public HashMap<String, Double> getItemStats(String itemName) throws SQLiteException {
		
		HashMap<String, Double> itemMap = new HashMap<String, Double>();

	
		Cursor c2 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + " WHERE " + KEY_ITEMNAME + " = ?", new String[] {itemName});
		int healthCol = c2.getColumnIndex(KEY_ITEMHP);
		int armorCol = c2.getColumnIndex(KEY_ITEMAR);
		int adCol = c2.getColumnIndex(KEY_ITEMAD);
		int apCol = c2.getColumnIndex(KEY_ITEMAP);
		int mpCol = c2.getColumnIndex(KEY_ITEMMP);
		int msCol = c2.getColumnIndex(KEY_ITEMMS);
		
		if(c2.moveToFirst()){
			itemMap.put("Health", c2.getDouble(healthCol));
			itemMap.put("Armor", c2.getDouble(armorCol));
			itemMap.put("AD", c2.getDouble(adCol));
			itemMap.put("AP", c2.getDouble(apCol));
			itemMap.put("MP", c2.getDouble(mpCol));
			itemMap.put("MS", c2.getDouble(msCol));
			return itemMap;
		}
		 return null;
	}
}
