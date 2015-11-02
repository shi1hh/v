package Fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.json.JSONArray;

import model.Store;



import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.baidu.mapapi.map.Stroke;
import com.example.hungrytest.CartActivity;
import com.example.hungrytest.FoodCategoryActivity;
import com.example.hungrytest.LocationActivity;
import com.example.hungrytest.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import JSON.login;
import Widget.MarqueeTextView;
import Widget.MeasureExpandableListView;
import adp.MyPagerAdaper;
import adp.WaimaiPinnedlistadp;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.R.anim;
import android.R.integer;
import android.animation.ValueAnimator;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fragmentwaimai extends Fragment implements OnRefreshListener<ScrollView>,OnClickListener{
   
	
	public static final String  LOG_TAG="Fragmentwaimai";
		
	
	
	ViewPager vpViewPager;//���
	View view;//��������View����ʾ���
	LayoutInflater layoutInflater;//OnCreate���ݹ�����inflater
	List<View> views=new ArrayList<View>();//�������
	int diplayWidth;//��Ļ���
	int diplayheight;//��Ļ�߶�
	
	RelativeLayout relative_1;

	List<Store> Stores=new ArrayList<Store>();//�ŵ�����ģ������
	
	LinearLayout flowlayout;//�����������3��SPINNER
	EditText editText_search;//����View


	PullToRefreshScrollView mPullRefreshScrollView;//����ˢ�¿ؼ�ռ������Ļ
	ListView pinnedSectionListView;//����LISTVIEW
	Spinner[] spinners=new Spinner[6];//Spinner�����б�X6 3����ʵ��3������
 
	
	private MarqueeTextView marqueeTextView;//�������ĻTextView
    Button button_waimai_tile_3;//ͬ��
    Button button_waimai_tile_1;//ͬ��
    Button button_error;
    
    TextView tb_tv1;
    TextView tb_tv2;
    
    View mViewSearch;
    int mviewSearchHeight;
	
	int heightRela=0;
	int bit=0;
	
	MyAsyncTask mAsyncTask;
	
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		view=arg0.inflate(R.layout.fragment_waimai, null);
		layoutInflater=arg0;
		mAsyncTask=new MyAsyncTask();
		mAsyncTask.execute();
        
		return view;
	}
	private void init() {
		

	
		initViewPager();
		initSpinner();
		initPinnedlist();
		initFlowLayout();
	}
	public void initViewID() {
		
		
		
		diplayWidth=getActivity().getWindowManager().getDefaultDisplay().getWidth();
		diplayheight=getActivity().getWindowManager().getDefaultDisplay().getHeight();
		
		vpViewPager=(ViewPager) view.findViewById(R.id.viewpager_waimai_adv);
		
		marqueeTextView = (MarqueeTextView)view.findViewById(R.id.Textview_waimai_tile_2);  
	    marqueeTextView.setSelected(true);
        pinnedSectionListView=(ListView) view.findViewById(R.id.listview_waimai);
        
		editText_search=(EditText) view.findViewById(R.id.editText_search);
		relative_1= (RelativeLayout) view.findViewById(R.id.relative_1);
		
		mPullRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pull_refresh_scrollview);
		
		flowlayout=(LinearLayout) view.findViewById(R.id.flow_llay);
		
		button_waimai_tile_3=(Button) view.findViewById(R.id.button_waimai_tile_3);
		button_waimai_tile_1=(Button) view.findViewById(R.id.button_waimai_tile_1);
	    button_waimai_tile_1.setOnClickListener(this);

		spinners[0]=(Spinner) view.findViewById(R.id.spinner_waimai_listview1);
		spinners[1]=(Spinner) view.findViewById(R.id.spinner_waimai_listview2);
		spinners[2]=(Spinner) view.findViewById(R.id.spinner_waimai_listview3);
    	spinners[3]=(Spinner) view.findViewById(R.id.spinner_waimai_listview4);
		spinners[4]=(Spinner) view.findViewById(R.id.spinner_waimai_listview5);
		spinners[5]=(Spinner) view.findViewById(R.id.spinner_waimai_listview6);
		
		tb_tv1=(TextView) view.findViewById(R.id.tb_tv1);
		tb_tv1.setOnClickListener(this);
		tb_tv2=(TextView) view.findViewById(R.id.tb_tv2);
		tb_tv2.setOnClickListener(this);
		
		
		mViewSearch=view.findViewById(R.id.relative_1);
		mviewSearchHeight=mViewSearch.getHeight();
		
        mPullRefreshScrollView.scrollTo(0, 0);
		button_error=(Button) view.findViewById(R.id.button_refresh);
		button_waimai_tile_1.setOnClickListener(titleClickListener);
	    button_waimai_tile_3.setOnClickListener(titleClickListener);	
		marqueeTextView.setOnClickListener(titleClickListener);
		pinnedSectionListView.setOnItemClickListener(listviewlistener);
		mPullRefreshScrollView.setOnRefreshListener(this);
		button_error.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new MyAsyncTask().execute();
			}
		});
	
	}
	/**
	 * ���븡������Լ���ʵViewλ��
	 */
	public void initFlowLayout()
	{
		View topView=view.findViewById(R.id.divider1);
		mPullRefreshScrollView.listenerFlowViewScrollState(topView, flowlayout,mViewSearch);
	}
	/**
	 * ��ʼ��Spinner����
	 */
	public void initSpinner()
	{
		ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings1);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[0].setAdapter(arrayAdapter);
        arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings2);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[1].setAdapter(arrayAdapter);
        arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings3);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[2].setAdapter(arrayAdapter);
		arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings1);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[3].setAdapter(arrayAdapter);
		arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings2);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[4].setAdapter(arrayAdapter);
		arrayAdapter=new ArrayAdapter<String>((this.getActivity()),R.layout.spinner_waimai_listview,spinStrings3);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinners[5].setAdapter(arrayAdapter);
	
	}
	/**
	 * ��ʼ��ListView�����������߶�
	 */
	public void initPinnedlist() {
		WaimaiPinnedlistadp pinnedlistadp=new WaimaiPinnedlistadp(this.getActivity(),Stores);
		pinnedSectionListView.setAdapter(pinnedlistadp);
		setPinnedListHeight();
	}
	/**
	 * ����ListView�߶�
	 */
	private void setPinnedListHeight() {
		// TODO Auto-generated method stub
       Adapter lsAdapter=pinnedSectionListView.getAdapter();
       int totalheight=0;
      for(int i=0;i<lsAdapter.getCount();i++)
      {
    	  View v=lsAdapter.getView(i, null, pinnedSectionListView);
    	  v.measure(0, 0);
    	  totalheight+=v.getMeasuredHeight();
      }
      totalheight+=pinnedSectionListView.getDividerHeight()*(pinnedSectionListView.getCount()-1);
      ViewGroup.LayoutParams params=pinnedSectionListView.getLayoutParams();
      params.height=totalheight;
      pinnedSectionListView.setLayoutParams(params);
      }
	/**
	 * ��ʼ�����
	 */
	public void  initViewPager() {
		
		View v=layoutInflater.inflate(R.layout.waimaiadv1, null);
		views.add(v);
		MyPagerAdaper pagerAdaper=new MyPagerAdaper(views);
		vpViewPager.setAdapter(pagerAdaper);
		vpViewPager.setCurrentItem(0);	
		
	}

	OnItemClickListener listviewlistener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Intent intent =new Intent(Fragmentwaimai.this.getActivity(),CartActivity.class);
		    Bundle mBundle=new Bundle();
		    mBundle.putSerializable("store", Stores.get(arg2));
		    intent.putExtras(mBundle);
			startActivity(intent);
			Fragmentwaimai.this.getActivity().finish();
			Fragmentwaimai.this.getActivity().overridePendingTransition(R.anim.pagetoright,R.anim.pagetoright2);
		}
	};
	
	OnClickListener titleClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
	
		Intent intent=new Intent(getActivity(),LocationActivity.class);
			startActivityForResult(intent,0);
			Fragmentwaimai.this.getActivity().overridePendingTransition(R.anim.pagetoright, R.anim.pagetoright2);
		}
	};
	
	/**
	 * Store����
	 */
	public void getStoreData() {
		Stores.clear();
		
	
			

		
		
	/*	Store store=new Store();
		store.setName("��������ɳ��С��");
		store.setFavourValue(4);
		store.setMinPay(50);
		store.setDispatch(5);
		store.setLogoid(R.drawable.waimailogo1);
        store.setWorkState(true);
        store.setSpeed("18����/1����");
        Stores.add(store);
        
    	Store store1=new Store();
    	store1.setName("�ҹ�����ʳ��");
		store1.setFavourValue(3);
		store1.setMinPay(20);
		store1.setDispatch(0);
		store1.setLogoid(R.drawable.waimailogo2);
        store1.setWorkState(false);
        Stores.add(store1);
        
        Store store2=new Store();
    	store2.setName("����ɳ�ط�ζС��");
		store2.setFavourValue(4.5f);
		store2.setMinPay(20);
		store2.setDispatch(0);
		store2.setLogoid(R.drawable.waimailogo3);
        store2.setWorkState(false);
        Stores.add(store2);
        
        Store store3=new Store();
    	store3.setName("�ϵ»�");
		store3.setFavourValue(5);
		store3.setMinPay(30);
		store3.setDispatch(0);
		store3.setSpeed("15����/900��");
		store3.setLogoid(R.drawable.waimailogo4);
        store3.setWorkState(true);
        Stores.add(store3);
        
        Store store4=new Store();
    	store4.setName("����ɳ�ط�ζС��");
		store4.setFavourValue(4.5f);
		store4.setMinPay(20);
		store4.setDispatch(0);
		store4.setLogoid(R.drawable.waimailogo3);
        store4.setWorkState(false);
        Stores.add(store4);
        
        Store store5=new Store();
       	store5.setName("�ϵ»�");
   		store5.setFavourValue(5);
   		store5.setMinPay(30);
   		store5.setDispatch(0);
   		store5.setSpeed("15����/900��");
   		store5.setLogoid(R.drawable.waimailogo4);
           store.setWorkState(true);
           Stores.add(store5);
        
           Store store6=new Store();
    	store6.setName("������ʳ��");
		store6.setFavourValue(1);
		store6.setMinPay(10);
		store6.setDispatch(2);
		store6.setLogoid(R.drawable.waimailogo5);
        store6.setWorkState(false);
        Stores.add(store6);*/
        
        
	}
	/**
	 * Spinner����
	 */
private String[] spinStrings1={"���� ","С����ʳ ","����","���"};
private String[] spinStrings2={"��������","�������","�������","�ٶ����"};
private  String[] spinStrings3={"˧ѡ","����"};

static int flag=0;
class  MyAsyncTask extends AsyncTask<Void, Void, Integer>
	{
   
		
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
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
		
			if(result==-1)
			{
				Toast.makeText(getActivity(), "����ʧ��", Toast.LENGTH_SHORT).show();
				view.findViewById(R.id.progressbar).setVisibility(View.GONE);
				view.findViewById(R.id.pull_refresh_scrollview1).setVisibility(View.GONE);
				view.findViewById(R.id.progressbar_error).setVisibility(View.VISIBLE);
			}
			else {
				 init();
				view.findViewById(R.id.progressbar).setVisibility(View.GONE);
				view.findViewById(R.id.progressbar_error).setVisibility(View.GONE);
				view.findViewById(R.id.pull_refresh_scrollview1).setVisibility(View.VISIBLE);
			}
			
			super.onPostExecute(result);
			mPullRefreshScrollView.onRefreshComplete();
		}

		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			int result=-1;
		    try {
		    	initViewID();
		  
		    	
		    
		    	flag=0;
		    	BmobQuery<Store> query=new BmobQuery<Store>();
				 query.findObjects(getActivity(), new FindListener<Store>() {
					
					@Override
					public void onSuccess(List<Store> arg0) {
						// TODO Auto-generated method stub

						Stores=arg0;
						flag=1;
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						flag=-1;
						
					}
				});
				 while (flag==0) {
					if(mAsyncTask.isCancelled())
					{flag=0;
						return-1;}
				}
			} catch (Exception e) {
				// TODO: handle exception
				result=-1;
			}
		    result=flag;
			return result;
		}
	}
	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub
		Stores.clear();
		mAsyncTask=new MyAsyncTask();
		mAsyncTask.execute();
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tb_tv1:
			int[]location=new int[2];
			mViewSearch.getLocationOnScreen(location);
			MyUtils.MyAnima.lineAnimator(mViewSearch, location[1],0,500).start();
			Log.d("12", "qoqo"+location[1]);
			break;
		case R.id.tb_tv2:
			Log.d("12", "onclick+"+mViewSearch.getHeight());
		    MyUtils.MyAnima.lineAnimator(mViewSearch, 0, mviewSearchHeight,500).start();
			
			
			break;

		default:
			break;
		}
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
            flag=0;
           
			mAsyncTask.cancel(true);

		super.onDestroy();
	}
	
	
}
