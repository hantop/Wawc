package com.wise.wawc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 终端购买界面
 * @author honesty
 */
public class OrderDeviceActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_device);
		Button bt_activity_order_device_submit = (Button)findViewById(R.id.bt_activity_order_device_submit);
		bt_activity_order_device_submit.setOnClickListener(onClickListener);
			
	}
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bt_activity_order_device_submit:
				OrderDeviceActivity.this.startActivity(new Intent(OrderDeviceActivity.this, OrderConfirmActivity.class));
				break;

			default:
				break;
			}
		}
	};
}