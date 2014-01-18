package com.wise.sql;

import com.wise.pubclas.Constant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
	private static final int VERSION = 1;
	private static final String DB_NAME = "DB_Wiwc";
	//基础表
	private static final String CREATE_TB_Base = "create table " + Constant.TB_Base + "(_id integer primary key autoincrement,Cust_id text,Title text,Content text)";
	//车友圈文章
	private static final String CREATE_TB_VehicleFriend = "create table " + Constant.TB_VehicleFriend + "(_id integer primary key autoincrement,Cust_id text,FriendID int,Content text)";
	//爱车故障
	private static final String CREATE_TB_Faults = "create table " + Constant.TB_Faults + "(_id integer primary key autoincrement,Cust_id text,CarID int,Content text)";
	//行程记录
	private static final String CREATE_TB_TripList = "create table " + Constant.TB_TripList + "(_id integer primary key autoincrement,Cust_id text,CarID int,Date text,Content text)";
	//行程记录
	private static final String CREATE_TB_Trip = "create table " + Constant.TB_Trip + "(_id integer primary key autoincrement,Cust_id text,TripID int,Content text)";
	//我的爱车
	private static final String CREATE_TB_Vehicle = "create table " + Constant.TB_Vehicle + "(_id integer primary key autoincrement,Cust_id text,obj_id int,obj_name text,car_brand text,car_series text,car_type text,engine_no text,frame_no text,insurance_company text,insurance_date text,annual_inspect_date text,maintain_company text,maintain_last_mileage text,maintain_next_mileage text,buy_date text)";
	//我的终端
	private static final String CREATE_TB_Devices = "create table " + Constant.TB_Devices + "(_id integer primary key autoincrement,Cust_id text,DeviceID int,Content text)";
	//我的收藏
	private static final String CREATE_TB_Collection = "create table " + Constant.TB_Collection + "(_id integer primary key autoincrement,Cust_id text,favorite_id text,name text,address text,tel text,lon text,lat text)";
	
	public DBHelper(Context context){
		super(context,DB_NAME,null,VERSION);
	}
	public DBHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TB_Base);
		db.execSQL(CREATE_TB_VehicleFriend);
		db.execSQL(CREATE_TB_Faults);
		db.execSQL(CREATE_TB_TripList);
		db.execSQL(CREATE_TB_Trip);
		db.execSQL(CREATE_TB_Vehicle);
		db.execSQL(CREATE_TB_Devices);
		db.execSQL(CREATE_TB_Collection);
		Log.e("创建表成功","创建表成功");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
