package Fragments;



import java.util.ArrayList;
import java.util.List;

import model.Goods;
import model.GoodsCategory;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.hungrytest.R;


import Widget.PinnedHeaderExpandableListView;
import adp.CartListViewLeftadp;
import adp.CartPinnedheadadp;
import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentCartMenu extends Fragment {

	
	View view;
	LayoutInflater layoutInflater;
	private PinnedHeaderExpandableListView explistview;
	List<GoodsCategory> goodsCategories=new ArrayList<GoodsCategory>();
	CartPinnedheadadp cartPinnedheadadp;
	CartListViewLeftadp cartListViewLeftadp;
	ListView listView;
	int selectPosition=0;
	View selectView=null;
	Button btnRefresh;
	MyAsyncTask mAsyncTask;
	
	

	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		view=arg0.inflate(R.layout.fragment_cart_menu, null);
		layoutInflater=arg0;
		mAsyncTask= new MyAsyncTask();
		mAsyncTask.execute();
		
        
        
        
        
        
        
	    
		return view;
	}
	private void init() {
		// TODO Auto-generated method stub
		
		
		
        initExpListView();
        initListView();
        initRefrshBtn();
        explistview.setLeftListListener(listView);
        cartPinnedheadadp.setListenerAdp(cartListViewLeftadp);
	}
	private void initExpListView() {
		explistview=(PinnedHeaderExpandableListView) view.findViewById(R.id.explistview);
		explistview.setHeaderView(layoutInflater.inflate(R.layout.pinnedlistview_cart_header,
				explistview, false));
		cartPinnedheadadp=new CartPinnedheadadp(getActivity(), explistview,goodsCategories,view);

		explistview.setAdapter(cartPinnedheadadp);
	   int count=explistview.getCount();
	   for (int i = 0; i < count; i++) {
		explistview.expandGroup(i);

	   }
	   
	}
	
   private void initRefrshBtn() {
	btnRefresh=(Button) view.findViewById(R.id.button_refresh);
	btnRefresh.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			new MyAsyncTask().execute();
		}
	});
}
	
	
	private void initListView() {
		listView=(ListView) view.findViewById(R.id.listview_cart_left);
		cartListViewLeftadp=new CartListViewLeftadp(getActivity(), goodsCategories);
		listView.setAdapter(cartListViewLeftadp);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				arg1.setBackgroundResource(R.drawable.cart_listview_lf_blackground);
				if(selectPosition!=arg2)
				{
					arg0.getChildAt(selectPosition).setBackgroundResource(R.color.blackground_cart_listview);
					selectPosition=arg2;
					selectView=arg0.getChildAt(selectPosition);
				   
				}
				explistview.setSelectedGroup(arg2);
				
			}
		});
		 listView.post(new Runnable() {
             @Override
             public void run() {
                 listView.getChildAt(0).setBackgroundResource(R.drawable.cart_listview_lf_blackground);
                 listView.setItemChecked(0, true);
             }
         });
	}

	static int flag=0;
	class MyAsyncTask extends AsyncTask<Void,Void,Integer>
	{	

		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			int result=-1;
			flag=0;
			try {
				BmobQuery<GoodsCategory> query=new BmobQuery<GoodsCategory>();
				query.findObjects(getActivity(), new FindListener<GoodsCategory>() {
					
					@Override
					public void onSuccess(List<GoodsCategory> arg0) {
						// TODO Auto-generated method stub
						goodsCategories=arg0;
						flag=1;
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						flag=-1;
					}
				});
			   
			} catch (Exception e) {
				// TODO: handle exception
				flag=-1;
			}
			while (flag==0) {	
				if(isCancelled())
				{return -1;}
			}
			result=flag;
			return result;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			if(result==-1)
			{
				view.findViewById(R.id.progressbar_error).setVisibility(View.VISIBLE);
				view.findViewById(R.id.progressbar).setVisibility(View.GONE);
				view.findViewById(R.id.cart_menu).setVisibility(View.GONE);
			}
			else {
				view.findViewById(R.id.progressbar).setVisibility(View.GONE);
				view.findViewById(R.id.progressbar_error).setVisibility(View.GONE);
				view.findViewById(R.id.cart_menu).setVisibility(View.VISIBLE);
			}
			   init();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onCancelled(Integer result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mAsyncTask.cancel(true);
		super.onDestroy();
	}
	
	
	
}
