package adp;

import java.util.ArrayList;
import java.util.List;

import model.FoodCategory;
import model.Goods;
import model.GoodsCategory;

import com.example.hungrytest.CartActivity;
import com.example.hungrytest.R;



import Widget.PinnedHeaderExpandableListView;
import Widget.PinnedHeaderExpandableListView.HeaderAdapter;
import adp.WaimaiPinnedlistadp.ViewHolder;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class CartPinnedheadadp extends BaseExpandableListAdapter implements HeaderAdapter {
	private PinnedHeaderExpandableListView listView;
	Context context;
	List<GoodsCategory> goodsCategory;//���ﳵ����
	LayoutInflater layoutInflater;//���ڼ��ز���
	int sum = 0;// �ܺͽ��
	int sumCount = 0;// �ܺ�����
	int minCharge=0;//�������
	TextView tvSum;// �ܺ�
	TextView carcount;//�ײ����ﳵ������ʾ
	Button btnPay;//����
	View v;
	CartListViewLeftadp lftadp;//������LISTVIEW���ݽ��
	List<int[]> count=new ArrayList<int[]>();// ��������


	public CartPinnedheadadp(Context context,PinnedHeaderExpandableListView listView,List<GoodsCategory> goodsCategory,View v) {
		this.context=context;
		this.listView=listView;
		this.v=v;
	 this.goodsCategory=goodsCategory;
		carcount = (TextView)v
				.findViewById(R.id.tv_car_count);
		tvSum = (TextView) v.findViewById(R.id.tv_sum);
		layoutInflater=LayoutInflater.from(context);
		TextView tvminCharge=(TextView) v.findViewById(R.id.tv_min_charge);
		btnPay=(Button)v.findViewById(R.id.button_balance);
		minCharge=Integer.parseInt(tvminCharge.getText().toString().replace("��","").replace("����", "").trim());
		for(GoodsCategory l:this.goodsCategory)
		{
			int[] newone=new int[l.getGoods().size()];
			count.add(newone);
		}
	}

	public void setListenerAdp(CartListViewLeftadp baseAdapter) {
		this.lftadp=baseAdapter;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return goodsCategory.get(groupPosition).getGoods().get(childPosition);
	}
	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		final ViewHolder vh;
        if (view == null){
        	vh=new ViewHolder();
        	view=createChildrenView();
        	vh.tvName=(TextView) view.findViewById(R.id.textView_name);
        	vh.tvPrice=(TextView) view.findViewById(R.id.textView_price);
        	vh.tvNote=(TextView) view.findViewById(R.id.textView_note);
        	vh.ratingBar_favour=(RatingBar) view.findViewById(R.id.ratingBar_favour);
        	vh.textView_sells=(TextView) view.findViewById(R.id.textView_sells);
        	vh.button_add=(ImageButton) view.findViewById(R.id.button_add);
        	vh.button_dda=(ImageButton)view.findViewById(R.id.button_dda);
        	vh.textView_count=(TextView) view.findViewById(R.id.textView_count);
        	view.setTag(vh);
        }
        else {
			vh=(ViewHolder) view.getTag();
		}
		vh.button_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vh.button_dda.setVisibility(View.VISIBLE);//���ٰ�ť����
				vh.textView_count.setVisibility(View.VISIBLE);//����Text����
				carcount.setVisibility(View.VISIBLE);//�ײ����ﳵ����Text����
				sum += Integer.parseInt(vh.tvPrice.getText().toString()//�ܺͼ�����Ʒ�۸�
						.replace("��", ""));
				count.get(groupPosition)[childPosition]++;//��Ӧ�Ĺ�������++
				sumCount++;//��������++
				if(lftadp!=null)
				{lftadp.addBuyCount(groupPosition);}//�������ADP���¹�������
				if(sum>=minCharge)//�����ܺʹ��ڵ�����С����
				{btnPay.setVisibility(View.VISIBLE);}//���㰴ť����
				
				
				tvSum.setText("��" + sum);//�����ܶ�
				vh.textView_count.setText("" + count.get(groupPosition)[childPosition]);//�������ӹ���ť�����ı�����
				carcount.setText("" + sumCount);//���õײ����ﳵ�ı�����
				
                ImageView mImageView=new ImageView(context);
                mImageView.setBackgroundResource(R.drawable.cart_menu_add_button1);
                mImageView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
                RelativeLayout mLayouparent= (RelativeLayout) v.findViewById(R.id.relative_parent);
                
				mLayouparent.addView(mImageView);
				
				int[] locationStart=new int[2];
                int[] locationparent=new int[2];
                int[] locationEnd=new int[2];
                
       
                listView.getLocationOnScreen(locationparent);
				vh.button_add.getLocationInWindow(locationStart);
				carcount.getLocationOnScreen(locationEnd);
				locationStart[1]-=locationparent[1];
				locationEnd[1]-=locationparent[1];
	
				((CartActivity)context).setAnim(locationStart, locationEnd,mImageView);
				
			}
		});
        
        vh.button_dda.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count.get(groupPosition)[childPosition] -= 1;//��Ӧλ�ù�������-1
				sumCount -= 1;//��������-1
				sum -= Integer.parseInt(vh.tvPrice.getText().toString()
						.replace("��", ""));//�ܶ�--
				if(lftadp!=null)
				{lftadp.ddaBuyCount(groupPosition);}//�������ADP���¹�������
				
				vh.textView_count.setText("" + count.get(groupPosition)[childPosition]);//�������ӹ���ť�����ı�����
				tvSum.setText("��" + sum);//�����ܶ�
				carcount.setText("" + sumCount);//���õײ����ﳵ�ı�����
				if (sumCount == 0) {
					carcount.setVisibility(View.INVISIBLE);//���繺������0�����ﳵͷ������������ʧ
				}
				if (count.get(groupPosition)[childPosition] == 0) {//��Ӧλ�ù�������Ϊ0������ʧ
					vh.button_dda.setVisibility(View.INVISIBLE);
					vh.textView_count.setVisibility(View.INVISIBLE);
				}
				if(sum<minCharge)//������Ѳ�����������ʧ
				{btnPay.setVisibility(View.INVISIBLE);}
			}
		});
    	if (count.get(groupPosition)[childPosition] > 0) {//��Ӧλ�ù�����������0������
			vh.textView_count.setVisibility(View.VISIBLE);
			vh.button_dda.setVisibility(View.VISIBLE);
		} else {
			vh.textView_count.setVisibility(View.INVISIBLE);//������ʧ
			vh.button_dda.setVisibility(View.INVISIBLE);
		}
  
		vh.tvPrice.setText("��"+goodsCategory.get(groupPosition).getGoods().get(childPosition).getPrice()); 
		vh.textView_count.setText("" + count.get(groupPosition)[childPosition]);
        
        
		vh.tvName.setText(goodsCategory.get(groupPosition).getGoods().get(childPosition).getName()); 
		return view;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub

		return goodsCategory.get(groupPosition).getGoods().size();

	}
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return goodsCategory.get(groupPosition);
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return goodsCategory.size();
	}
	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder vh;
        if (view == null){
        	vh=new ViewHolder();
        	view=createGroupView();
        	vh.tvName=(TextView) view.findViewById(R.id.groupto);
        	view.setTag(vh);
        }
        else {
			vh=(ViewHolder) view.getTag();
		}
		
		vh.tvName.setText(goodsCategory.get(groupPosition).getCategoryName());
			return view;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}

	}
	@Override
	public void configureHeader(View header, int groupPosition,
			int childPosition, int alpha) {
		// TODO Auto-generated method stub
		String groupData = this.goodsCategory.get(groupPosition).getCategoryName();
		((TextView) header.findViewById(R.id.groupto)).setText(groupData);
	}
	private SparseIntArray groupStatusMap = new SparseIntArray(); 
	
	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		// TODO Auto-generated method stub
		groupStatusMap.put(groupPosition, status);
	}
	@Override
	public int getGroupClickStatus(int groupPosition) {
		// TODO Auto-generated method stub
		if (groupStatusMap.keyAt(groupPosition)>=0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}
	private View createChildrenView() {
		return layoutInflater.inflate(R.layout.pinnedlistview_cart_item, null);
	}
	
	private View createGroupView() {
		return layoutInflater.inflate(R.layout.pinnedlistview_cart_group, null);
	}
	

	
	
	static class ViewHolder {
		TextView tvName;
		TextView tvPrice;
		TextView tvNote;
		RatingBar ratingBar_favour;
		TextView textView_sells;
		ImageButton button_add;
		ImageButton button_dda;
		TextView textView_count;
	}

	
}
