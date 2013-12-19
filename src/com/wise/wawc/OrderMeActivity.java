package com.wise.wawc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * 我的订单
 * @author honesty
 */
public class OrderMeActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_me);
		ImageView iv_activity_order_me_menu = (ImageView)findViewById(R.id.iv_activity_order_me_menu);
		iv_activity_order_me_menu.setOnClickListener(onClickListener);
		ImageView iv_activity_order_me_home = (ImageView)findViewById(R.id.iv_activity_order_me_home);
		iv_activity_order_me_home.setOnClickListener(onClickListener);
	}
	OnClickListener onClickListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_activity_order_me_menu:
				ActivityFactory.A.LeftMenu();
				break;

			case R.id.iv_activity_order_me_home:
				ActivityFactory.A.ToHome();
				break;
			}
		}
	};
}