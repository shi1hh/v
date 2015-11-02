package adp;

import java.util.List;

import model.GoodsCategory;
import com.example.hungrytest.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CartListViewLeftadp extends BaseAdapter {

	private Context context;//不解释
	private List<GoodsCategory> goodsCategorys;//显示数据
	int count[];//用于保存各项购买数量
	int select=0;//当前选中ITEM
	LayoutInflater layoutInflater;//用于加载布局
	
	
	public  CartListViewLeftadp(Context context,List<GoodsCategory> goodsCategories) {
		this.context=context;
		this.goodsCategorys=goodsCategories;
		this.layoutInflater=LayoutInflater.from(context);
		count=new int[goodsCategories.size()];
	}
	//右上角小图标显示数量++
	public void addBuyCount(int position)
	{
		this.count[position]++;
		this.notifyDataSetChanged();
	}
	//右上角小图标显示数量--
	public void ddaBuyCount(int position)
	{
		this.count[position]--;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsCategorys.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return goodsCategorys.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view;
		ViewHolder viewHolder;
		view=arg1;
		if (view==null) {
			viewHolder=new ViewHolder();
			view=layoutInflater.inflate(R.layout.lv_left, null);
			viewHolder.name=(TextView) view.findViewById(R.id.tv_lft_name);
			viewHolder.countTextView=(TextView) view.findViewById(R.id.tv_lft_count);
			viewHolder.tvCount=(TextView)view.findViewById(R.id.tv_lft_count);
			view.setTag(viewHolder);
			
		}
		else {
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.name.setText(goodsCategorys.get(arg0).getCategoryName());
		viewHolder.tvCount.setText(""+count[arg0]);
		if(count[arg0]==0)//数量0隐藏
		{
			viewHolder.tvCount.setVisibility(View.INVISIBLE);
		}
		else {
			viewHolder.tvCount.setVisibility(View.VISIBLE);
		}
		return view;
	}
	
	class ViewHolder
	{
		TextView name;
		TextView countTextView;
		TextView tvCount;
	}

}
