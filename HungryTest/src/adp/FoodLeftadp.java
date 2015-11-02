package adp;

import java.util.List;


import android.R.integer;
import android.app.Activity;

import model.FoodCategory;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.hungrytest.R;

public class FoodLeftadp extends BaseAdapter {

	private Context context;//FoodCategoryActivity的实例
	List<FoodCategory> lsf;//所有购买物品数据实例
	/*FoodRightadp rigadp;//右边LISTVIEW实例
*/	int select=0;//当前选中ITEM

	int count[];//用于保存各项购买数量
	/**
	 * 构造函数
	 * @param context FOODCATERORYATT实例引用
	 * @param lsf 所有购买数据
	 */
	public FoodLeftadp(Context context,	List<FoodCategory> lsf)
	{
		this.lsf=lsf;
		this.context=context;
	}
/**
 * 构造函数
 * @param context FOODCATERORYATT实例引用
 * @param lsf  所有购买数据
 * @param rigadp 右边LISTVIEW实例引用
 */
	public FoodLeftadp(Context context)
	{
	/*	this.rigadp=rigadp;*/
		this.lsf=lsf;
		this.context=context;
		count=new int[lsf.size()];
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lsf.size();
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
	/**
	 * 增加右上购买数量
	 * @param position 购买选中项
	 */
	public void addBuyCount(int position)
	{
		this.count[position]++;
		this.notifyDataSetChanged();
	}
	/**
	 * 减少
	 * @param position 取消购买选中项
	 */
	public void ddaBuyCount(int position)
	{
		this.count[position]--;
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=convertView;
		ViewHolder vh;
		if(v==null)
		{
	        v=LayoutInflater.from(context).inflate(R.layout.lv_left,null);
	        vh=new ViewHolder();
	        vh.tvName=(TextView)v.findViewById(R.id.tv_lft_name);
			vh.tvName.setText(lsf.get(position).getCategory());
			vh.tvCount=(TextView)v.findViewById(R.id.tv_lft_count);
			vh.tvCount.setText(""+count[position]);
			v.setTag(vh);
		}
		else
		{
			vh=(ViewHolder) v.getTag();
			vh.tvName.setText(lsf.get(position).getCategory());
			vh.tvCount.setText(""+count[position]);
		}
		if(count[position]==0)
		{
			vh.tvCount.setVisibility(View.INVISIBLE);
		}
		else {
			vh.tvCount.setVisibility(View.VISIBLE);
		}
			
		return v;
	}
	class ViewHolder
	{
		TextView tvName;
		TextView tvCount;
	}

}
