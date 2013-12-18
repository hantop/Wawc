package com.wise.extend;

import java.util.List;
import com.wise.data.CarData;
import com.wise.wawc.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarAdapter extends BaseAdapter{
	Context context;
	List<CarData> carDatas;
	LayoutInflater mInflater;
	public CarAdapter(Context context,List<CarData> carDatas){
		this.context = context;
		this.carDatas = carDatas;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return carDatas.size();
	}
	@Override
	public Object getItem(int arg0) {
		return carDatas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_cars, null);
			holder = new ViewHolder();
			holder.tv_item_carnumber = (TextView) convertView.findViewById(R.id.tv_item_carnumber);
			holder.iv_item_cars = (ImageView)convertView.findViewById(R.id.iv_item_cars);
			holder.ll_item_cars = (LinearLayout)convertView.findViewById(R.id.ll_item_cars);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CarData carData = carDatas.get(position);
		holder.tv_item_carnumber.setText(carData.getCarNumber());
		holder.iv_item_cars.setImageResource(R.drawable.ic_launcher);
		if(carData.isCheck()){
			holder.ll_item_cars.setBackgroundResource(R.color.bkg1);
		}else{
			holder.ll_item_cars.setBackgroundColor(Color.TRANSPARENT);
		}
		return convertView;
	}
	private class ViewHolder {
		TextView tv_item_carnumber;
		ImageView iv_item_cars;
		LinearLayout ll_item_cars;
	}
}
