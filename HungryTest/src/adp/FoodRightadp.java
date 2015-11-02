/*package adp;

import java.util.ArrayList;
import java.util.List;

import com.example.hungrytest.FoodCategoryActivity;
import com.example.hungrytest.R;


import model.FoodCategory;
import model.MyFood;
import JSON.login;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hungrytest.R;



public class FoodRightadp extends BaseAdapter {

	List<FoodCategory> lsf;//所有购买数据实例
	Context context;
	List<int[]> count=new ArrayList<int[]>();// 单项数量
	int sumCount = 0;// 总和数量
	int sum = 0;// 总和金额
    int lv1Position=0;//当前数据所属类别位置
	FoodLeftadp ladp;//左边LISTVIEW实例
	TextView carcount;// 购物车上面数量
	TextView tvSum;// 总和
	int minCharge=0;//最低消费
	Button btnPay;//结算
*//**
 * 构造函数
 * @param context
 * @param lsf  所有购买数据的实例
 * @param ladp  左边LISTVIEW引用
 *//*
	public FoodRightadp(Context context, List<FoodCategory> lsf,FoodLeftadp ladp) {

		this.context = context;
		this.lsf = lsf;
		this.ladp=ladp;
		for(FoodCategory l:lsf)
		{
			int[] newone=new int[l.getFood().length];
			count.add(newone);
		}
		
		carcount = (TextView) ((Activity) context)
				.findViewById(R.id.tv_car_count);
		tvSum = (TextView) ((Activity) context).findViewById(R.id.tv_sum);
		TextView tvminCharge=(TextView) ((Activity) context).findViewById(R.id.tv_min_charge);
		btnPay=(Button) ((Activity) context).findViewById(R.id.button_balance);
		minCharge=Integer.parseInt(tvminCharge.getText().toString().replace("￥","").replace("起送", "").trim());
	}
*//**
 * 设置当前左边LISTVIEW的实例引用
 * @param ladp 左边LISTVIEW
 *//*
	public void setLadp(FoodLeftadp ladp)
	{
		this.ladp=ladp;
	}
	
	 * 设置当前LISTVIEW所属类别
	 
	public void setLv1Position(int position) {
		this.lv1Position=position;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lsf.get(lv1Position).getFood().length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, final View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		final ViewHolder holder;
		if (v == null) {
			v = LayoutInflater.from(context).inflate(R.layout.lv_right, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) v.findViewById(R.id.tv_rig_name);
			holder.btnadd = (Button) v.findViewById(R.id.button_add);
			holder.tvPrice = (TextView) v.findViewById(R.id.tv_rig_price);
			holder.btnrem = (Button) v.findViewById(R.id.button_remove);
			holder.tvCount = (TextView) v.findViewById(R.id.tv_count);
			holder.tvName.setText(lsf.get(lv1Position).getFood()[position]);
			holder.tvPrice.setText(lsf.get(lv1Position).getPrice()[position]);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}

 * 添加购买数量按钮
 
		holder.btnadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int[] slocation = new int[2];
				v.getLocationInWindow(slocation);
				((FoodCategoryActivity) context).setAnima(v, slocation);// 设置动画

				holder.btnrem.setVisibility(View.VISIBLE);// 各种逻辑
				holder.tvCount.setVisibility(View.VISIBLE);
				carcount.setVisibility(View.VISIBLE);

				sum += Integer.parseInt(holder.tvPrice.getText().toString()
						.replace("￥", ""));
				count.get(lv1Position)[position]++;
				sumCount++;
				ladp.addBuyCount(lv1Position);

				if(sum>=minCharge)
				{btnPay.setVisibility(View.VISIBLE);}

				tvSum.setText("￥" + sum);
				holder.tvCount.setText("" + count.get(lv1Position)[position]);
				carcount.setText("" + sumCount);
	
			}
		});

 * 减少购买数量按钮
 
		holder.btnrem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("dd", "" + position);
				count.get(lv1Position)[position] -= 1;
				sumCount -= 1;
				sum -= Integer.parseInt(holder.tvPrice.getText().toString()
						.replace("￥", ""));
				ladp.ddaBuyCount(lv1Position);
				

				holder.tvCount.setText("" + count.get(lv1Position)[position]);
				tvSum.setText("￥" + sum);
				carcount.setText("" + sumCount);
				if (sumCount == 0) {
					carcount.setVisibility(View.INVISIBLE);
				}
				if (count.get(lv1Position)[position] == 0) {
					holder.btnrem.setVisibility(View.INVISIBLE);
					holder.tvCount.setVisibility(View.INVISIBLE);
				}
				if(sum<minCharge)
				{btnPay.setVisibility(View.INVISIBLE);}
	
			}
		});
		if (count.get(lv1Position)[position] > 0) {
			holder.tvCount.setVisibility(View.VISIBLE);
			holder.btnrem.setVisibility(View.VISIBLE);
		} else {
			holder.tvCount.setVisibility(View.INVISIBLE);
			holder.btnrem.setVisibility(View.INVISIBLE);
		}

		holder.tvName.setText(lsf.get(lv1Position).getFood()[position]);
		holder.tvPrice.setText(lsf.get(lv1Position).getPrice()[position]);
		holder.tvCount.setText("" + count.get(lv1Position)[position]);

		return v;

	}

	
	 * 添加按钮事件
	 
	static class ViewHolder {
		TextView tvName;
		TextView tvPrice;
		Button btnadd;
		Button btnrem;
		TextView tvCount;
	}
}*/
