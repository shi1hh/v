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

	private Context context;//FoodCategoryActivity��ʵ��
	List<FoodCategory> lsf;//���й�����Ʒ����ʵ��
	/*FoodRightadp rigadp;//�ұ�LISTVIEWʵ��
*/	int select=0;//��ǰѡ��ITEM

	int count[];//���ڱ�����������
	/**
	 * ���캯��
	 * @param context FOODCATERORYATTʵ������
	 * @param lsf ���й�������
	 */
	public FoodLeftadp(Context context,	List<FoodCategory> lsf)
	{
		this.lsf=lsf;
		this.context=context;
	}
/**
 * ���캯��
 * @param context FOODCATERORYATTʵ������
 * @param lsf  ���й�������
 * @param rigadp �ұ�LISTVIEWʵ������
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
	 * �������Ϲ�������
	 * @param position ����ѡ����
	 */
	public void addBuyCount(int position)
	{
		this.count[position]++;
		this.notifyDataSetChanged();
	}
	/**
	 * ����
	 * @param position ȡ������ѡ����
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
