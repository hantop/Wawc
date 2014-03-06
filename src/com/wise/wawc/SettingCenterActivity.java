package com.wise.wawc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.wise.pubclas.Constant;
import com.wise.pubclas.NetThread;
import com.wise.pubclas.Variable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 设置中心
 * @author 王庆文
 */
public class SettingCenterActivity extends Activity{
	
	TextView tv_value;
	ImageView iv_traffic,iv_status,iv_alert,iv_remind;

    boolean isTraffic,isStatus,isAlert,isRemind;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_center);
		
		tv_value = (TextView)findViewById(R.id.tv_value);
		Button bt_setting_menu = (Button)findViewById(R.id.bt_setting_menu);
		bt_setting_menu.setOnClickListener(onClickListener);
		RelativeLayout rl_traffic = (RelativeLayout)findViewById(R.id.rl_traffic);
		rl_traffic.setOnClickListener(onClickListener);
		RelativeLayout rl_status = (RelativeLayout)findViewById(R.id.rl_status);
		rl_status.setOnClickListener(onClickListener);
		RelativeLayout rl_alert = (RelativeLayout)findViewById(R.id.rl_alert);
		rl_alert.setOnClickListener(onClickListener);
		RelativeLayout rl_remind = (RelativeLayout)findViewById(R.id.rl_remind);
		rl_remind.setOnClickListener(onClickListener);
		RelativeLayout rl_center = (RelativeLayout)findViewById(R.id.rl_center);
		rl_center.setOnClickListener(onClickListener);
		
		iv_traffic = (ImageView)findViewById(R.id.iv_traffic);
		iv_status = (ImageView)findViewById(R.id.iv_status);
		iv_alert = (ImageView)findViewById(R.id.iv_alert);
		iv_remind = (ImageView)findViewById(R.id.iv_remind);
		
		TextView tv_share_gift = (TextView)findViewById(R.id.tv_share_gift);
		tv_share_gift.setOnClickListener(onClickListener);
		TextView tv_feedback = (TextView)findViewById(R.id.tv_feedback);
		tv_feedback.setOnClickListener(onClickListener);
		TextView tv_score = (TextView)findViewById(R.id.tv_score);
		tv_score.setOnClickListener(onClickListener);
		TextView tv_about = (TextView)findViewById(R.id.tv_about);
		tv_about.setOnClickListener(onClickListener);		
		getsp();
	}
	
	OnClickListener onClickListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.bt_setting_menu:
                ActivityFactory.A.LeftMenu();
                break;
            case R.id.rl_traffic:
                if(isTraffic){
                    isTraffic = false;
                    iv_traffic.setVisibility(View.GONE);
                }else{
                    isTraffic = true;
                    iv_traffic.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_status:
                if(isStatus){
                    isStatus = false;
                    iv_status.setVisibility(View.GONE);
                }else{
                    isStatus = true;
                    iv_status.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_alert:
                if(isAlert){
                    isAlert = false;
                    iv_alert.setVisibility(View.GONE);
                }else{
                    isAlert = true;
                    iv_alert.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_remind:
                if(isRemind){
                    isRemind = false;
                    iv_remind.setVisibility(View.GONE);
                }else{
                    isRemind = true;
                    iv_remind.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rl_center:
                startActivity(new Intent(SettingCenterActivity.this, MyVehicleActivity.class));
                break;
            case R.id.tv_share_gift:
                startActivity(new Intent(SettingCenterActivity.this, WapActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(SettingCenterActivity.this, FeedBackActivity.class));
                break;
            case R.id.tv_score:
                break;
            case R.id.tv_about:
                startActivity(new Intent(SettingCenterActivity.this, AboutActivity.class));
                break;
            }
        }
    };
    Handler handler = new Handler(){
        
    };
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(Constant.sharedPreferencesName, Context.MODE_PRIVATE);
        int index = preferences.getInt(Constant.DefaultVehicleID, 0);
        tv_value.setText("车辆" + Variable.carDatas.get(index).getObj_name() + "的位置");
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences(Constant.sharedPreferencesName, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean("isTraffic", isTraffic);
        editor.putBoolean("isStatus", isStatus);
        editor.putBoolean("isAlert", isAlert);
        editor.putBoolean("isRemind", isRemind);
        editor.commit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();        
        String url = Constant.BaseUrl + "customer/" + Variable.cust_id +"/push?auth_code=" + Variable.auth_code;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("if_vio_noti", isTraffic ? "1" : "0"));   
        params.add(new BasicNameValuePair("if_fault_noti", isStatus ? "1" : "0"));   
        params.add(new BasicNameValuePair("if_alert_noti", isAlert ? "1" : "0"));   
        params.add(new BasicNameValuePair("if_event_noti", isRemind ? "1" : "0"));   
        new Thread(new NetThread.putDataThread(handler, url, params, 999)).start();
    }
    private void getsp(){
        SharedPreferences preferences = getSharedPreferences(Constant.sharedPreferencesName, Context.MODE_PRIVATE);
        isTraffic = preferences.getBoolean("isTraffic", false);
        isStatus = preferences.getBoolean("isStatus", false);
        isAlert = preferences.getBoolean("isAlert", false);
        isRemind = preferences.getBoolean("isRemind", false);
        if(isTraffic){
            iv_traffic.setVisibility(View.VISIBLE);
        }
        if(isStatus){
            iv_status.setVisibility(View.VISIBLE);
        }
        if(isAlert){
            iv_alert.setVisibility(View.VISIBLE);
        }
        if(isRemind){
            iv_remind.setVisibility(View.VISIBLE);
        }
    }
}