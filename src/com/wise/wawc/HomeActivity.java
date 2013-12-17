package com.wise.wawc;

import com.wise.extend.HScrollLayout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 首页
 * @author honesty
 */
public class HomeActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ImageView iv_activity_home_menu = (ImageView)findViewById(R.id.iv_activity_home_menu);
		iv_activity_home_menu.setOnClickListener(onClickListener);
		ImageView iv_activity_car_home_search = (ImageView)findViewById(R.id.iv_activity_car_home_search);
		iv_activity_car_home_search.setOnClickListener(onClickListener);
		Button bt_activity_home_help = (Button)findViewById(R.id.bt_activity_home_help);
		bt_activity_home_help.setOnClickListener(onClickListener);
		Button bt_activity_home_risk = (Button)findViewById(R.id.bt_activity_home_risk);
		bt_activity_home_risk.setOnClickListener(onClickListener);
		//ScrollLayout_car
		HScrollLayout ScrollLayout_car = (HScrollLayout)findViewById(R.id.ScrollLayout_car);
		HScrollLayout ScrollLayout_other = (HScrollLayout)findViewById(R.id.ScrollLayout_other);
		LayoutInflater mLayoutInflater = LayoutInflater.from(HomeActivity.this);
        for (int i = 0; i < 5; i++) {
        	View mapView = mLayoutInflater.inflate(R.layout.item_home_car, null);
        	ScrollLayout_car.addView(mapView);
        	ImageView item_home_car_icon = (ImageView)mapView.findViewById(R.id.item_home_car_icon);
        	item_home_car_icon.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Toast.makeText(HomeActivity.this, "onclicklistener", Toast.LENGTH_SHORT).show();
				}
			});
		}
        for (int i = 0; i < 2; i++) {
        	View mapView = mLayoutInflater.inflate(R.layout.item_home_car, null);
        	ScrollLayout_other.addView(mapView);
        	ImageView item_home_car_icon = (ImageView)mapView.findViewById(R.id.item_home_car_icon);
        	item_home_car_icon.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Toast.makeText(HomeActivity.this, "onclicklistener", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	OnClickListener onClickListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_activity_home_menu:
				ActivityFactory.A.LeftMenu();
				break;
			case R.id.iv_activity_car_home_search:
				ActivityFactory.A.RightMenu();
				break;
			case R.id.bt_activity_home_help:
				HomeActivity.this.startActivity(new Intent(HomeActivity.this, ShareLocationActivity.class));
				break;
			case R.id.bt_activity_home_risk:
				HomeActivity.this.startActivity(new Intent(HomeActivity.this, ShareLocationActivity.class));
				break;
			}
		}
	};
}