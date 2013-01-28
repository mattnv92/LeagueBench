package com.example.lolbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


import android.app.Dialog;
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
	public static final String KEY_ITEMAS = "item_as";
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
	public static final String KEY_SAVEID = "_id";
	public static final String KEY_SAVECHAMPNAME = "savechamp_name";
	public static final String KEY_SAVEBUILDNAME = "savebuild_name";
	public static final String KEY_SAVECHAMPICONID = "savechamp_iconid";
	public static final String KEY_SAVEITEM0 = "save_item0";
	public static final String KEY_SAVEITEM1 = "save_item1";
	public static final String KEY_SAVEITEM2 = "save_item2";
	public static final String KEY_SAVEITEM3 = "save_item3";
	public static final String KEY_SAVEITEM4 = "save_item4";
	public static final String KEY_SAVEITEM5 = "save_item5";
	
	
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
					+ KEY_ICONNAME + " TEXT NOT NULL, " + KEY_ICON + " INTEGER NOT NULL, " + KEY_BANNER + " INTEGER NOT NULL);" 
			);
			
			// Building table for Item Stats
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_ITEMSTATS + " (" + KEY_ITEMSTATSID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_ITEMNAME + " TEXT NOT NULL, " + KEY_ITEMMS + " REAL, " + KEY_ITEMHP + " INTEGER, " + KEY_ITEMHPR + 
					" INTEGER, " + KEY_ITEMAR + " INTEGER, " + KEY_ITEMAD + " INTEGER, " + KEY_ITEMAP + " INTEGER, " + KEY_ITEMAS + " REAL, " 
					+ KEY_ITEMMP + " INTEGER, " + KEY_ITEMMPR + " INTEGER, " + KEY_ITEMMR + " INTEGER, " + KEY_ITEMUNIQUEMS + " REAL);" 
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
					"CREATE TABLE " + DATABASE_TABLE_SAVEBUILD + " (" + KEY_SAVEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_SAVECHAMPNAME + " TEXT NOT NULL, " + KEY_SAVECHAMPICONID + " INTEGER NOT NULL, " + KEY_SAVEBUILDNAME + " TEXT NOT NULL, "
					+ KEY_SAVEITEM0 + " INTEGER, " + KEY_SAVEITEM1 + " INTEGER, " + KEY_SAVEITEM2 + " INTEGER, "
					+ KEY_SAVEITEM3 + " INTEGER, " + KEY_SAVEITEM4 + " INTEGER, "	+ KEY_SAVEITEM5 + " INTEGER);" 
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
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SAVEBUILD);
			onCreate(db);
		}
		
		public void generateChampIconsTable(SQLiteDatabase db) throws SQLiteException {
			ContentValues cv = new ContentValues();
			
			//do i need all these inserts and clears????????????????????????? check later
			cv.put(KEY_ICONNAME, "Ahri");
			cv.put(KEY_ICON, R.drawable.ahributtonnormal);
			cv.put(KEY_BANNER, R.drawable.ahri_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Akali");
			cv.put(KEY_ICON, R.drawable.akalibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.akali_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Alistar");
			cv.put(KEY_ICON, R.drawable.alistarbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.alistar_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Amumu");
			cv.put(KEY_ICON, R.drawable.amumubuttonnormal);
			cv.put(KEY_BANNER, R.drawable.amumu_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Anivia");
			cv.put(KEY_ICON, R.drawable.aniviabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.anivia_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Annie");
			cv.put(KEY_ICON, R.drawable.anniebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.annie_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ashe");
			cv.put(KEY_ICON, R.drawable.ashebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.ashe_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Blitzcrank");
			cv.put(KEY_ICON, R.drawable.blitzcrankbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.blitzcrank_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Brand");
			cv.put(KEY_ICON, R.drawable.brandbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.brand_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Caitlyn");
			cv.put(KEY_ICON, R.drawable.caitlynbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.caitlyn_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Cassiopeia");
			cv.put(KEY_ICON, R.drawable.cassiopeiabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.cassiopeia_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Cho`Gath");
			cv.put(KEY_ICON, R.drawable.chogathbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.chogath_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Corki");
			cv.put(KEY_ICON, R.drawable.corkibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.corki_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Darius");
			cv.put(KEY_ICON, R.drawable.dariusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.darius_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Diana");
			cv.put(KEY_ICON, R.drawable.dianabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.diana_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Draven");
			cv.put(KEY_ICON, R.drawable.dravenbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.draven_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Dr. Mundo");
			cv.put(KEY_ICON, R.drawable.drmundobuttonnormal);
			cv.put(KEY_BANNER, R.drawable.drmundo_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Elise");
			cv.put(KEY_ICON, R.drawable.elisebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.elise_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Evelynn");
			cv.put(KEY_ICON, R.drawable.evelynnbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.evelynn_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ezreal");
			cv.put(KEY_ICON, R.drawable.ezrealbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.ezreal_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Fiddlesticks");
			cv.put(KEY_ICON, R.drawable.fiddlesticksbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.fiddlesticks_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Fiora");
			cv.put(KEY_ICON, R.drawable.fiorabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.fiora_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Fizz");
			cv.put(KEY_ICON, R.drawable.fizzbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.fizz_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Galio");
			cv.put(KEY_ICON, R.drawable.galiobuttonnormal);
			cv.put(KEY_BANNER, R.drawable.galio_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Gangplank");
			cv.put(KEY_ICON, R.drawable.gangplankbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.gangplank_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Garen");
			cv.put(KEY_ICON, R.drawable.garenbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.garen_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Gragas");
			cv.put(KEY_ICON, R.drawable.gragasbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.gragas_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Graves");
			cv.put(KEY_ICON, R.drawable.gravesbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.graves_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Hecarim");
			cv.put(KEY_ICON, R.drawable.hecarimbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.hecarim_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Heimerdinger");
			cv.put(KEY_ICON, R.drawable.heimerdingerbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.heimerdinger_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Irelia");
			cv.put(KEY_ICON, R.drawable.ireliabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.irelia_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Janna");
			cv.put(KEY_ICON, R.drawable.jannabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.janna_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Jarvan IV");
			cv.put(KEY_ICON, R.drawable.jarvanbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.jarvan_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Jax");
			cv.put(KEY_ICON, R.drawable.jaxbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.jax_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Jayce");
			cv.put(KEY_ICON, R.drawable.jaycebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.jayce_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Karma");
			cv.put(KEY_ICON, R.drawable.karmabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.karma_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Karthus");
			cv.put(KEY_ICON, R.drawable.karthusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.karthus_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Kassadin");
			cv.put(KEY_ICON, R.drawable.kassadinbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.kassadin_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Katarina");
			cv.put(KEY_ICON, R.drawable.katarinabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.katarina_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Kayle");
			cv.put(KEY_ICON, R.drawable.kaylebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.kayle_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Kennen");
			cv.put(KEY_ICON, R.drawable.kennenbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.kennen_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Kha`Zix");
			cv.put(KEY_ICON, R.drawable.khazixbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.khazix_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Kog`Maw");
			cv.put(KEY_ICON, R.drawable.kogmawbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.kogmaw_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "LeBlanc");
			cv.put(KEY_ICON, R.drawable.leblancbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.leblanc_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Lee Sin");
			cv.put(KEY_ICON, R.drawable.leesinbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.leesin_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Leona");
			cv.put(KEY_ICON, R.drawable.leonabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.leona_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Lulu");
			cv.put(KEY_ICON, R.drawable.lulubuttonnormal);
			cv.put(KEY_BANNER, R.drawable.lulu_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Lux");
			cv.put(KEY_ICON, R.drawable.luxbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.lux_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Malphite");
			cv.put(KEY_ICON, R.drawable.malphitebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.malphite_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Malzahar");
			cv.put(KEY_ICON, R.drawable.malzaharbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.malzahar_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Maokai");
			cv.put(KEY_ICON, R.drawable.maokaibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.maokai_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Master Yi");
			cv.put(KEY_ICON, R.drawable.masteryibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.masteryi_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Miss Fortune");
			cv.put(KEY_ICON, R.drawable.missfortunebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.missfortune_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Mordekaiser");
			cv.put(KEY_ICON, R.drawable.mordekaiserbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.mordekaiser_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Morgana");
			cv.put(KEY_ICON, R.drawable.morganabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.morgana_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nami");
			cv.put(KEY_ICON, R.drawable.namibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nami_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nasus");
			cv.put(KEY_ICON, R.drawable.nasusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nasus_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nautilus");
			cv.put(KEY_ICON, R.drawable.nautilusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nautilus_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nidalee");
			cv.put(KEY_ICON, R.drawable.nidaleebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nidalee_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nocturne");
			cv.put(KEY_ICON, R.drawable.nocturnebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nocturne_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Nunu");
			cv.put(KEY_ICON, R.drawable.nunubuttonnormal);
			cv.put(KEY_BANNER, R.drawable.nunu_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Olaf");
			cv.put(KEY_ICON, R.drawable.olafbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.olaf_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Orianna");
			cv.put(KEY_ICON, R.drawable.oriannabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.orianna_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Pantheon");
			cv.put(KEY_ICON, R.drawable.pantheonbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.pantheon_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Poppy");
			cv.put(KEY_ICON, R.drawable.poppybuttonnormal);
			cv.put(KEY_BANNER, R.drawable.poppy_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Rammus");
			cv.put(KEY_ICON, R.drawable.rammusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.rammus_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Renekton");
			cv.put(KEY_ICON, R.drawable.renektonbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.renekton_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Rengar");
			cv.put(KEY_ICON, R.drawable.rengarbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.rengar_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Riven");
			cv.put(KEY_ICON, R.drawable.rivenbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.riven_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Rumble");
			cv.put(KEY_ICON, R.drawable.rumblebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.rumble_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ryze");
			cv.put(KEY_ICON, R.drawable.ryzebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.ryze_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Sejuani");
			cv.put(KEY_ICON, R.drawable.sejuanibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.sejuani_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Shaco");
			cv.put(KEY_ICON, R.drawable.shacobuttonnormal);
			cv.put(KEY_BANNER, R.drawable.shaco_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Shen");
			cv.put(KEY_ICON, R.drawable.shenbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.shen_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Singed");
			cv.put(KEY_ICON, R.drawable.singedbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.singed_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Shyvana");
			cv.put(KEY_ICON, R.drawable.shyvanabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.shyvana_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Sion");
			cv.put(KEY_ICON, R.drawable.sionbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.sion_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Sivir");
			cv.put(KEY_ICON, R.drawable.sivirbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.sivir_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Skarner");
			cv.put(KEY_ICON, R.drawable.skarnerbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.skarner_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Sona");
			cv.put(KEY_ICON, R.drawable.sonabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.sona_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Soraka");
			cv.put(KEY_ICON, R.drawable.sorakabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.soraka_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Swain");
			cv.put(KEY_ICON, R.drawable.swainbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.swain_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Syndra");
			cv.put(KEY_ICON, R.drawable.syndrabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.syndra_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Talon");
			cv.put(KEY_ICON, R.drawable.talonbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.talon_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Taric");
			cv.put(KEY_ICON, R.drawable.taricbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.taric_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Teemo");
			cv.put(KEY_ICON, R.drawable.teemobuttonnormal);
			cv.put(KEY_BANNER, R.drawable.teemo_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Thresh");
			cv.put(KEY_ICON, R.drawable.threshbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.thresh_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Tristana");
			cv.put(KEY_ICON, R.drawable.tristanabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.tristana_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Trundle");
			cv.put(KEY_ICON, R.drawable.trundlebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.trundle_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Tryndamere");
			cv.put(KEY_ICON, R.drawable.tryndamerebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.tryndamere_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Twisted Fate");
			cv.put(KEY_ICON, R.drawable.twistedfatebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.twistedfate_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Twitch");
			cv.put(KEY_ICON, R.drawable.twitchbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.twitch_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Udyr");
			cv.put(KEY_ICON, R.drawable.udyrbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.udyr_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Urgot");
			cv.put(KEY_ICON, R.drawable.urgotbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.urgot_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Varus");
			cv.put(KEY_ICON, R.drawable.varusbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.varus_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Vayne");
			cv.put(KEY_ICON, R.drawable.vaynebuttonnormal);
			cv.put(KEY_BANNER, R.drawable.vayne_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Veigar");
			cv.put(KEY_ICON, R.drawable.veigarbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.veigar_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Vi");
			cv.put(KEY_ICON, R.drawable.vibuttonnormal);
			cv.put(KEY_BANNER, R.drawable.vi_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Viktor");
			cv.put(KEY_ICON, R.drawable.viktorbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.viktor_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Vladimir");
			cv.put(KEY_ICON, R.drawable.vladimirbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.vladimir_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Volibear");
			cv.put(KEY_ICON, R.drawable.volibearbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.volibear_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Warwick");
			cv.put(KEY_ICON, R.drawable.warwickbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.warwick_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Wukong");
			cv.put(KEY_ICON, R.drawable.wukongbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.wukong_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Xerath");
			cv.put(KEY_ICON, R.drawable.xerathbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.xerath_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Xin Zhao");
			cv.put(KEY_ICON, R.drawable.xinzhaobuttonnormal);
			cv.put(KEY_BANNER, R.drawable.xinzhao_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Yorick");
			cv.put(KEY_ICON, R.drawable.yorickbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.yorick_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Zed");
			cv.put(KEY_ICON, R.drawable.zedbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.zed_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Ziggs");
			cv.put(KEY_ICON, R.drawable.ziggsbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.ziggs_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Zilean");
			cv.put(KEY_ICON, R.drawable.zileanbuttonnormal);
			cv.put(KEY_BANNER, R.drawable.zilean_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ICONNAME, "Zyra");
			cv.put(KEY_ICON, R.drawable.zyrabuttonnormal);
			cv.put(KEY_BANNER, R.drawable.zyra_banner);
			db.insertOrThrow(DATABASE_TABLE_CHAMP_ICONS, null, cv);
			cv.clear();
			
			
		}
		
		public void generateItemIconTable(SQLiteDatabase db) {
			ContentValues cv = new ContentValues();
			
			cv.put(KEY_ITEMICONNAME, "Abyssal Scepter");
			cv.put(KEY_ITEMICON, R.drawable.abysbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.abyssal_sceptre);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Aegis of the Legion");
			cv.put(KEY_ITEMICON, R.drawable.aegisbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.aegis_of_the_legion);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Amplifying Tome");
			cv.put(KEY_ITEMICON, R.drawable.amptbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.amplifying_tome);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Archangel's Staff");
			cv.put(KEY_ITEMICON, R.drawable.archstaffbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.archangels_staff);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Atma's Impaler");
			cv.put(KEY_ITEMICON, R.drawable.atmasbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.atmas_impaler);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Avarice Blade");
			cv.put(KEY_ITEMICON, R.drawable.ricebbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.avarice_blade);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Banshee's Veil");
			cv.put(KEY_ITEMICON, R.drawable.bansheebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.banshee_veil);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Berserker's Greaves");
			cv.put(KEY_ITEMICON, R.drawable.bgreavesbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.beserkers_greaves);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "B. F. Sword");
			cv.put(KEY_ITEMICON, R.drawable.bfbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.bf_sword);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Boots of Mobility");
			cv.put(KEY_ITEMICON, R.drawable.bombuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.boots_of_mobility);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Boots of Speed");
			cv.put(KEY_ITEMICON, R.drawable.bosbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.boots_of_speed);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Boots of Swiftness");
			cv.put(KEY_ITEMICON, R.drawable.bospeedbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.boswiftness);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Blasting Wand");
			cv.put(KEY_ITEMICON, R.drawable.blastingbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.blasting_wand);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Brawler's Gloves");
			cv.put(KEY_ITEMICON, R.drawable.brawgbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.brawlers_gloves);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Brutalizer");
			cv.put(KEY_ITEMICON, R.drawable.brutalizerbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.brutalizer_button);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Catalyst the Protector");
			cv.put(KEY_ITEMICON, R.drawable.ctpbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.ctp);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Chain Vest");
			cv.put(KEY_ITEMICON, R.drawable.chainvestbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.chain_vest);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Dagger");
			cv.put(KEY_ITEMICON, R.drawable.dagbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.dagger);
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
			
			cv.put(KEY_ITEMICONNAME, "Fiendish Codex");
			cv.put(KEY_ITEMICON, R.drawable.fcodexbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.fiendish_codex);
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
			
			cv.put(KEY_ITEMICONNAME, "Haunting Guise");
			cv.put(KEY_ITEMICON, R.drawable.hauntgbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.haunting_guise);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Last Whisper");
			cv.put(KEY_ITEMICON, R.drawable.lwbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.last_whisper);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Lich Bane");
			cv.put(KEY_ITEMICON, R.drawable.lichbanebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.lich_bane);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Long Sword");
			cv.put(KEY_ITEMICON, R.drawable.longswordbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.long_sword);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Manamune");
			cv.put(KEY_ITEMICON, R.drawable.munebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.manamune);
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
			
			cv.put(KEY_ITEMICONNAME, "Needlessly Large Rod");
			cv.put(KEY_ITEMICON, R.drawable.needrodbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.needlessly_large_rod);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Negatron Cloak");
			cv.put(KEY_ITEMICON, R.drawable.negcbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.negatron_cloak);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Pickaxe");
			cv.put(KEY_ITEMICON, R.drawable.pickaxebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.pickaxe);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Quicksilver Sash");
			cv.put(KEY_ITEMICON, R.drawable.qssbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.quick_silver_sash);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Recurve Bow");
			cv.put(KEY_ITEMICON, R.drawable.recbowbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.recursive_bow);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Rejuvenation Bead");
			cv.put(KEY_ITEMICON, R.drawable.rejbeadbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.rejuvenation_bead);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Ruby Crystal");
			cv.put(KEY_ITEMICON, R.drawable.rubybuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.ruby_crystal);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Ruby Sightstone");
			cv.put(KEY_ITEMICON, R.drawable.rubstonebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.ruby_sightstone);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Sapphire Crystal");
			cv.put(KEY_ITEMICON, R.drawable.sapphbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.sapphire_crystal);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Sightstone");
			cv.put(KEY_ITEMICON, R.drawable.sightstonebuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.sightstone);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Sorcerer's Shoes");
			cv.put(KEY_ITEMICON, R.drawable.sorcshoesbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.sorcerers_shoes);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Void Staff");
			cv.put(KEY_ITEMICON, R.drawable.voidstaffbuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.void_staff);
			db.insertOrThrow(DATABASE_TABLE_ITEMICONS, null, cv);
			cv.clear();
			
			cv.put(KEY_ITEMICONNAME, "Will of the Ancients");
			cv.put(KEY_ITEMICON, R.drawable.wotabuttonnormal);
			cv.put(KEY_ITEMBUTTON, R.drawable.wota);
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
						if(stat.contains("Attack Speed"))
							cv.put(KEY_ITEMAS, Double.parseDouble(num[0]));
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
	
	public boolean isDbOpen() {
		return ourHelper != null;
	}
	
	public Database openRead() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getReadableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public void saveBuild(HashMap<String, Integer> buildList, String champName, String buildName) throws SQLiteException {
			ContentValues cv = new ContentValues();
			int champId = getChampIdByName(champName);
			cv.put(KEY_SAVECHAMPNAME, champName);
			cv.put(KEY_SAVECHAMPICONID, champId);
			cv.put(KEY_SAVEBUILDNAME, buildName);
			if(buildList.get("item0") != null)
				cv.put(KEY_SAVEITEM0, buildList.get("item0"));
			if(buildList.get("item1") != null)
				cv.put(KEY_SAVEITEM1, buildList.get("item1"));
			if(buildList.get("item2") != null)
				cv.put(KEY_SAVEITEM2, buildList.get("item2"));
			if(buildList.get("item3") != null)
				cv.put(KEY_SAVEITEM3, buildList.get("item3"));
			if(buildList.get("item4") != null)
				cv.put(KEY_SAVEITEM4, buildList.get("item4"));
			if(buildList.get("item5") != null)
				cv.put(KEY_SAVEITEM5, buildList.get("item5"));
			
			ourDatabase.insertOrThrow(DATABASE_TABLE_SAVEBUILD, null, cv);
			cv.clear();
		
	}
	
	public int numBuilds() {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_SAVEBUILD, null);
		return c1.getCount();
	}
	
	public int getChampIdByName(String champName) {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_CHAMP_ICONS + " WHERE " 
		+ KEY_ICONNAME + " = ?", new String[]{champName});
		int resultCol = c1.getColumnIndex(KEY_ICON);
		c1.moveToFirst();
		return c1.getInt(resultCol);
	}
	
	public ArrayList<Integer> getChampButtons() throws SQLiteException{
		ArrayList<Integer> result = new ArrayList<Integer>();
		String[] columns = new String[] {KEY_ICONROWID, KEY_ICONNAME, KEY_ICON, KEY_BANNER};
		Cursor c = ourDatabase.query(DATABASE_TABLE_CHAMP_ICONS, columns, KEY_ICON, null, null, null, KEY_ICONNAME);
		int iIcon = c.getColumnIndex(KEY_ICON);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result.add(c.getInt(iIcon));
		}
		return result;
	}
	
	public String champNameById(int id) {
		Cursor c = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_CHAMP_ICONS + " WHERE " + KEY_ICON + " = " + id, null);
		int resultCol = c.getColumnIndex(KEY_ICONNAME);
		c.moveToFirst();
		return c.getString(resultCol);
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

	public ArrayList<Integer> getItemCategoryHp() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMHP + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
		
		
	}
	
	public ArrayList<Integer> getItemCategoryMp() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMP + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
		
		
	}
	
	public ArrayList<Integer> getItemCategoryMpR() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMPR + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;	
		
	}
	
	public ArrayList<Integer> getItemCategoryAp() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAP + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;	
		
	}
	
	public ArrayList<Integer> getItemCategoryMr() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMR + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;	
		
	}
	
	public ArrayList<Integer> getItemCategoryHpR() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMHPR + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
		
		
	}
	
	public ArrayList<Integer> getItemCategoryAP() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAP + " > 0" , null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
		
		
	}
	
	public int getButtonById(int id) throws SQLiteException {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMICONS + " WHERE " + KEY_ITEMICON + " = " + id, null);
		int resultCol = c1.getColumnIndex(KEY_ITEMBUTTON);
		if(c1.moveToFirst()){
			return c1.getInt(resultCol);
		}
		else
			return -1;
	}
	
	public ArrayList<Integer> getItemCategoryMS() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMS + " > 0" + " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMUNIQUEMS + " > 0" 
				+ " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAS + " > 0)", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
	}
	
	public ArrayList<Integer> getItemCategorySPEED() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMMS + " > 0" + " OR " + DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAS + " > 0)" , null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
	}
	
	
	public ArrayList<Integer> getItemCategoryAD() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND " 
				+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAD + " > 0", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
	}
	
	public ArrayList<Integer> getItemCategoryArmor() throws SQLiteException {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMSTATS + ", " + DATABASE_TABLE_ITEMICONS + " WHERE " 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMNAME + " = " + DATABASE_TABLE_ITEMICONS + "." + KEY_ITEMICONNAME + " AND (" 
		+ DATABASE_TABLE_ITEMSTATS + "." + KEY_ITEMAR + " > 0)", null);
		int iconCol = c1.getColumnIndex(KEY_ITEMICON);
		if(c1.moveToFirst()){
			result.add(c1.getInt(iconCol));
		}
		else
			return null;
		while(c1.moveToNext()){
			result.add(c1.getInt(iconCol));
		}
		
		return result;
		
		
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
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_ITEMICONS + " WHERE " + KEY_ITEMBUTTON + " = " + itemId, null);
		int resultCol = c1.getColumnIndex(KEY_ITEMICONNAME);
		c1.moveToFirst();
		return c1.getString(resultCol);
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
	
	public boolean buildNameExists(String buildName) {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_SAVEBUILD 
		+ " WHERE " + KEY_SAVEBUILDNAME + " = ?", new String[]{buildName});
		return c1.moveToFirst();
	}
	
	public String getChampNameByBuildName(String buildName) {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_SAVEBUILD 
		+ " WHERE " + KEY_SAVEBUILDNAME + " = ?", new String[]{buildName});
		int resultCol = c1.getColumnIndex(KEY_SAVECHAMPNAME);
		c1.moveToFirst();
		return c1.getString(resultCol);
	}
	
	public HashMap<String, Integer> getSavedItems(String buildName) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_SAVEBUILD 
				+ " WHERE " + KEY_SAVEBUILDNAME + " = ?", new String[]{buildName});
		int itemCol0 = c1.getColumnIndex(KEY_SAVEITEM0);
		int itemCol1 = c1.getColumnIndex(KEY_SAVEITEM1);
		int itemCol2 = c1.getColumnIndex(KEY_SAVEITEM2);
		int itemCol3 = c1.getColumnIndex(KEY_SAVEITEM3);
		int itemCol4 = c1.getColumnIndex(KEY_SAVEITEM4);
		int itemCol5 = c1.getColumnIndex(KEY_SAVEITEM5);
		
		int i0;
		int i1;
		int i2;
		int i3;
		int i4;
		int i5;
		
		c1.moveToFirst();
		if(!c1.isNull(itemCol0)){
			i0 = c1.getInt(itemCol0);
			result.put("item0", i0);
		}
		if(!c1.isNull(itemCol1)){
			i1 = c1.getInt(itemCol1);
			result.put("item1", i1);
		}
		if(!c1.isNull(itemCol2)){
			i2 = c1.getInt(itemCol2);
			result.put("item2", i2);
		}
		if(!c1.isNull(itemCol3)){
			i3 = c1.getInt(itemCol3);
			result.put("item3", i3);
		}
		if(!c1.isNull(itemCol4)){
			i4 = c1.getInt(itemCol4);
			result.put("item4", i4);
		}
		if(!c1.isNull(itemCol5)){
			i5 = c1.getInt(itemCol5);
			result.put("item5", i5);
		}

		return result;
	}
	
	public String[] getSavedBuilds() {
		Cursor c1 = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_SAVEBUILD , null);
		int resultCol = c1.getColumnIndex(KEY_SAVEBUILDNAME);
		String[] result = new String[c1.getCount()];
		if(c1.moveToFirst()){
			for(int i = 0; i < result.length; i++){
				result[i] = c1.getString(resultCol);
				c1.moveToNext();
			}
		}
		return result;
	}
	
	public void overWriteSavedBuild(HashMap<String, Integer> buildList, String champName, String buildName) {
		ContentValues cv = new ContentValues();
		int champId = getChampIdByName(champName);
		cv.put(KEY_SAVECHAMPNAME, champName);
		cv.put(KEY_SAVECHAMPICONID, champId);
		if(buildList.get("item0") != null)
			cv.put(KEY_SAVEITEM0, buildList.get("item0"));
		if(buildList.get("item1") != null)
			cv.put(KEY_SAVEITEM1, buildList.get("item1"));
		if(buildList.get("item2") != null)
			cv.put(KEY_SAVEITEM2, buildList.get("item2"));
		if(buildList.get("item3") != null)
			cv.put(KEY_SAVEITEM3, buildList.get("item3"));
		if(buildList.get("item4") != null)
			cv.put(KEY_SAVEITEM4, buildList.get("item4"));
		if(buildList.get("item5") != null)
			cv.put(KEY_SAVEITEM5, buildList.get("item5"));
		ourDatabase.update(DATABASE_TABLE_SAVEBUILD, cv, KEY_SAVEBUILDNAME + " = ?", new String[]{buildName});
	}
	
	public void deleteBuild(String buildName) {
		ourDatabase.delete(DATABASE_TABLE_SAVEBUILD, KEY_SAVEBUILDNAME + " = ?", new String[]{buildName});
	}
}
