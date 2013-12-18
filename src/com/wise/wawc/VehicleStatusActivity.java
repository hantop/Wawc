package com.wise.wawc;

import java.util.ArrayList;
import java.util.List;
import com.wise.data.CarData;
import com.wise.data.EnergyItem;
import com.wise.extend.CarAdapter;
import com.wise.extend.EnergyCurveView;
import com.wise.extend.OnViewTouchListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 我的车况,爱车车况
 * @author honesty
 */
public class VehicleStatusActivity extends Activity{
	private static final String TAG = "VehicleStatusActivity";
	private EnergyCurveView erenergyCurve;
    private DisplayMetrics dm = new DisplayMetrics();
    TextView tv_activity_vehicle_status_oil,tv_activity_vehicle_status_fault;
    CarAdapter carAdapter;
	List<CarData> carDatas;
    String oil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_status);
		RelativeLayout rl_activity_vehicle_status_oil = (RelativeLayout)findViewById(R.id.rl_activity_vehicle_status_oil);
		rl_activity_vehicle_status_oil.setOnClickListener(onClickListener);
		ImageView iv_activity_vehicle_status_data_next = (ImageView)findViewById(R.id.iv_activity_vehicle_status_data_next);
		iv_activity_vehicle_status_data_next.setOnClickListener(onClickListener);
		ImageView iv_activity_vehicle_status_data_previous = (ImageView)findViewById(R.id.iv_activity_vehicle_status_data_previous);
		iv_activity_vehicle_status_data_previous.setOnClickListener(onClickListener);
		ImageView iv_activity_vehicle_status_back = (ImageView)findViewById(R.id.iv_activity_vehicle_status_back);
		iv_activity_vehicle_status_back.setOnClickListener(onClickListener);
		tv_activity_vehicle_status_oil = (TextView)findViewById(R.id.tv_activity_vehicle_status_oil);
		tv_activity_vehicle_status_fault = (TextView)findViewById(R.id.tv_activity_vehicle_status_fault);
		tv_activity_vehicle_status_fault.setOnClickListener(onClickListener);
		erenergyCurve = (EnergyCurveView) findViewById(R.id.erenergycurve);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        erenergyCurve.setWindowsWH(dm);
        ArrayList<EnergyItem> energys = new ArrayList<EnergyItem>();
        energys.add(new EnergyItem("1", 9.0f, "无"));
        energys.add(new EnergyItem("2", 8.0f, "无"));
        energys.add(new EnergyItem("3", 7.0f, "无"));
        energys.add(new EnergyItem("4", 6.5f, "无"));
        energys.add(new EnergyItem("5", 7.8f, "无"));
        energys.add(new EnergyItem("6", 8.0f, "无"));
        energys.add(new EnergyItem("7", 7.0f, "无"));
        tv_activity_vehicle_status_oil.setText("9.0L");
        erenergyCurve.initPoints(energys);
        erenergyCurve.setOnViewTouchListener(new OnViewTouchListener() {			
			@Override
			public void OnViewTouch(String value) {
		        oil = value;
				tv_activity_vehicle_status_oil.setText(oil + "L");
			}
		});
        GetData();
        GridView gv_activity_vehicle_status = (GridView)findViewById(R.id.gv_activity_vehicle_status);
        carAdapter = new CarAdapter(VehicleStatusActivity.this, carDatas);
        gv_activity_vehicle_status.setAdapter(carAdapter);
        
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
		LayoutParams params = new LayoutParams(carDatas.size() * (px + 10),LayoutParams.WRAP_CONTENT);
		gv_activity_vehicle_status.setLayoutParams(params);
		gv_activity_vehicle_status.setColumnWidth(px);
		gv_activity_vehicle_status.setHorizontalSpacing(10);
		gv_activity_vehicle_status.setStretchMode(GridView.NO_STRETCH);
		gv_activity_vehicle_status.setNumColumns(carDatas.size());
		gv_activity_vehicle_status.setOnItemClickListener(onItemClickListener);
	}
	private void GetData(){
		carDatas = new ArrayList<CarData>();
		for(int i = 0 ; i < 10 ; i++){
			CarData carData = new CarData();
			carData.setCarLogo(1);
			carData.setCarNumber("粤B12345");
			carData.setCheck(false);
			carDatas.add(carData);
		}
	}
	
	OnClickListener onClickListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_activity_vehicle_status_back:
				finish();
				break;
			case R.id.tv_activity_vehicle_status_fault:
				startActivity(new Intent(VehicleStatusActivity.this, CarFaultActivity.class));
				break;
			case R.id.rl_activity_vehicle_status_oil:
				startActivity(new Intent(VehicleStatusActivity.this, TravelActivity.class));
				break;
			case R.id.iv_activity_vehicle_status_data_next:
				Toast.makeText(VehicleStatusActivity.this, "NEXT", Toast.LENGTH_SHORT).show();
				ArrayList<EnergyItem> energys = new ArrayList<EnergyItem>();
		        energys.add(new EnergyItem("1", 4.0f, "无"));
		        energys.add(new EnergyItem("2", 5.0f, "无"));
		        energys.add(new EnergyItem("3", 4.0f, "无"));
		        energys.add(new EnergyItem("4", 6.5f, "无"));
		        energys.add(new EnergyItem("5", 3.8f, "无"));
		        energys.add(new EnergyItem("6", 8.0f, "无"));
		        energys.add(new EnergyItem("7", 7.0f, "无"));
		        tv_activity_vehicle_status_oil.setText("4.0L");
		        erenergyCurve.initPoints(energys);
		        erenergyCurve.RefreshView();
				break;
			case R.id.iv_activity_vehicle_status_data_previous:
				ArrayList<EnergyItem> energy = new ArrayList<EnergyItem>();
		        energy.add(new EnergyItem("1", 9.0f, "无"));
		        energy.add(new EnergyItem("2", 8.0f, "无"));
		        energy.add(new EnergyItem("3", 7.0f, "无"));
		        energy.add(new EnergyItem("4", 6.5f, "无"));
		        energy.add(new EnergyItem("5", 7.8f, "无"));
		        energy.add(new EnergyItem("6", 8.0f, "无"));
		        energy.add(new EnergyItem("7", 7.0f, "无"));
		        tv_activity_vehicle_status_oil.setText("9.0L");
		        erenergyCurve.initPoints(energy);
		        erenergyCurve.RefreshView();
				break;
			}
		}
	};
	OnItemClickListener onItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			for(int i = 0 ; i < carDatas.size() ; i++){
				carDatas.get(i).setCheck(false);
			}
			carDatas.get(arg2).setCheck(true);
			carAdapter.notifyDataSetChanged();
		}
	};
}