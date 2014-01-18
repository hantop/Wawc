package com.wise.extend;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.wise.data.AdressData;
import com.wise.pubclas.Constant;
import com.wise.pubclas.GetSystem;
import com.wise.pubclas.NetThread;
import com.wise.pubclas.Variable;
import com.wise.sql.DBExcute;
import com.wise.wawc.HomeActivity;
import com.wise.wawc.MyCollectionActivity;
import com.wise.wawc.MyVehicleActivity;
import com.wise.wawc.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 地点信息
 * @author honesty
 */
public class AdressAdapter extends BaseAdapter{
	private static final String TAG = "AdressAdapter";
	Context context;
	Activity mActivity;
	List<AdressData> adressDatas;
	LayoutInflater mInflater;
	ProgressDialog myDialog = null;
	private MyHandler myHandler = null;
	private static final int addFavorite = 1;
	AdressData adressData = null;
	public AdressAdapter(Context context,List<AdressData> adressDatas,Activity mActivity){
		this.context = context;
		this.adressDatas = adressDatas;
		this.mActivity = mActivity;
		mInflater = LayoutInflater.from(context);
		myHandler = new MyHandler();
	}
	@Override
	public int getCount() {
		return adressDatas.size();
	}
	@Override
	public Object getItem(int arg0) {
		return adressDatas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_dealadress, null);
			holder = new ViewHolder();
			holder.tv_item_dealadress_name = (TextView) convertView.findViewById(R.id.tv_item_dealadress_name);
			holder.tv_item_dealadress_distance = (TextView) convertView.findViewById(R.id.tv_item_dealadress_distance);
			holder.tv_item_dealadress_adress = (TextView)convertView.findViewById(R.id.tv_item_dealadress_adress);
			holder.tv_item_dealadress_phone = (TextView)convertView.findViewById(R.id.tv_item_dealadress_phone);
			holder.bt_item_dealadress_collection = (Button)convertView.findViewById(R.id.bt_item_dealadress_collection);
			holder.bt_item_dealadress_call = (Button)convertView.findViewById(R.id.bt_item_dealadress_call);
			holder.bt_item_dealadress_navigation = (Button)convertView.findViewById(R.id.bt_item_dealadress_navigation);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		adressData = adressDatas.get(position);
		holder.tv_item_dealadress_name.setText(adressData.getName());
		if(adressData.getDistance() != -1){
			holder.tv_item_dealadress_distance.setText(adressData.getDistance() + "m");
		}
		
		holder.tv_item_dealadress_adress.setText(adressData.getAdress());
		holder.tv_item_dealadress_phone.setText("电话：" +adressData.getPhone());
		System.out.println("电话：" + adressData.getPhone());
		if(adressData.getPhone() == null || adressData.getPhone().equals("")){
			System.out.println("隐藏");
			holder.bt_item_dealadress_call.setVisibility(View.GONE);
		}else{
			System.out.println("显示");
			holder.bt_item_dealadress_call.setVisibility(View.VISIBLE);
		}
		//收藏
		holder.bt_item_dealadress_collection.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				
				//TODO  更新服务器   成功之后再操作 数据库
				
				if("".equals(Variable.auth_code)){
					Toast.makeText(context, "请登录",0).show();
					return;
				}else{
					myDialog = ProgressDialog.show(context,"提示", "收藏中...");
					myDialog.setCancelable(true);
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("cust_id", Variable.cust_id));
					params.add(new BasicNameValuePair("name", adressData.getName()));
					params.add(new BasicNameValuePair("address", adressData.getAdress()));
					params.add(new BasicNameValuePair("tel", adressData.getPhone()));
					params.add(new BasicNameValuePair("lon", String.valueOf(adressData.getLon())));
					params.add(new BasicNameValuePair("lat", String.valueOf(adressData.getLat())));
					
					new Thread(new NetThread.postDataThread(myHandler, Constant.BaseUrl + "favorite?auth_code=" + Variable.auth_code, params, addFavorite)).start();
				}
			}
		});
		//拨打电话
		holder.bt_item_dealadress_call.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ adressData.getPhone()));  
				mActivity.startActivity(intent);
			}
		});
		//导航
		holder.bt_item_dealadress_navigation.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				GeoPoint point = new GeoPoint((int) (Variable.Lat * 1E6),(int) (Variable.Lon * 1E6));
				GeoPoint point1 = new GeoPoint((int) (adressData.getLat() * 1E6),(int) (adressData.getLon() * 1E6));
				System.out.println(Variable.Lat + "/" + Variable.Lat);
				System.out.println(adressData.getLat() + "/" + adressData.getLat());
				GetSystem.FindCar(mActivity, point, point1, "point", "point1");
			}
		});
		return convertView;
	}
	private class ViewHolder {
		TextView tv_item_dealadress_name,tv_item_dealadress_adress,tv_item_dealadress_phone,tv_item_dealadress_distance;
		Button bt_item_dealadress_collection,bt_item_dealadress_call,bt_item_dealadress_navigation;
	}
	
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
			case addFavorite:
				myDialog.dismiss();
				Log.e("执行添加的结果：",msg.obj.toString());
				try {
					JSONObject jsonObject = new JSONObject(msg.obj.toString());
					if(Integer.valueOf(jsonObject.getString("status_code")) == 0){
						
						DBExcute dbExcute = new DBExcute();
				        ContentValues values = new ContentValues();
				        values.put("Cust_id", Variable.cust_id);
				        values.put("favorite_id", jsonObject.getString("favorite_id"));
				        values.put("name", adressData.getName());
				        values.put("address", adressData.getAdress());
				        values.put("tel", adressData.getPhone());
				        values.put("lon", adressData.getLon());
				        values.put("lat", adressData.getLat());
				        dbExcute.InsertDB(mActivity, values, Constant.TB_Collection);
						Toast.makeText(mActivity, "添加成功", 0).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
	}
}
