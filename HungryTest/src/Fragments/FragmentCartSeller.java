package Fragments;

import java.util.List;

import map.MapActivity;
import model.Store;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.InitListener;

import com.baidu.platform.comapi.map.s;
import com.example.hungrytest.CartActivity;
import com.example.hungrytest.R;
import com.nostra13.universalimageloader.core.ImageLoader;


import android.content.Intent;
import android.graphics.Paint.Style;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class FragmentCartSeller extends Fragment implements OnClickListener {

	
	View view;
	LayoutInflater layoutInflater;
	TextView textView_speed;
	 TextView textView_min;
	 TextView textView_dispatch;
	 TextView textView_name;
	 RatingBar ratingBar_favour;
	 ImageView imageView_logo;
	 TextView textView_address;
	 TextView textView_worktime;
	 ImageButton ImageButton_map;
	 Store mStore;
	 MyAsyncTask mAsyncTask;
	 static final String LOG_TAG="FragmentCartSeller";

	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		view=arg0.inflate(R.layout.fragment_cart_seller, null);
		layoutInflater=arg0;
		mAsyncTask=new MyAsyncTask();
		mAsyncTask.execute();
	    
		return view;
	}
	private void initViews() {
		initRatingBar();
		initTextview();
		initImageLogo();
	}
	private void initID() {
        mStore=((CartActivity)getActivity()).getStore();
		textView_speed=(TextView) view.findViewById(R.id.textView_speed);
		textView_min=(TextView) view.findViewById(R.id.textView_min);
		textView_dispatch=(TextView) view.findViewById(R.id.textView_dispatch);
		ratingBar_favour=(RatingBar) view.findViewById(R.id.ratingBar_favour);
		imageView_logo=(ImageView) view.findViewById(R.id.imageView_logo);
		textView_name=(TextView) view.findViewById(R.id.textView_name);
		textView_address=(TextView) view.findViewById(R.id.textView_address);
		textView_worktime=(TextView) view.findViewById(R.id.textView_worktime);
		ImageButton_map=(ImageButton) view.findViewById(R.id.ImageButton_map);
		ImageButton_map.setOnClickListener(this);
	}
	private void initImageLogo() {
		Log.d(LOG_TAG, mStore.getLogo().getUrl());
		MyUtils.ImgLoader.LoadURLImg(mStore.getLogo().getFileUrl(getActivity()), imageView_logo);
	}

	private void initRatingBar() {
		ratingBar_favour.setRating(mStore.getFavourValue());
	}
	private void initTextview() {
		
		String text=getResources().getString(R.string.seller_speed);
	    SpannableString spannableString=new SpannableString(mStore.getAvgSpeed()+text);
	    spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    spannableString.setSpan(new AbsoluteSizeSpan(30,true), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    textView_speed.setText(spannableString);
	    
	    text=getResources().getString(R.string.seller_min);
	    spannableString=new SpannableString(mStore.getMinPay()+text);
	    spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    spannableString.setSpan(new AbsoluteSizeSpan(30,true), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    textView_min.setText(spannableString);
	    
	    text=getResources().getString(R.string.seller_dispatch);
	    spannableString=new SpannableString(mStore.getDispatch()+text);
	    spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    spannableString.setSpan(new AbsoluteSizeSpan(30,true), 0, spannableString.length()-text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    textView_dispatch.setText(spannableString);
	    
	    textView_address.setText(textView_address.getText()+mStore.getAddress());
	    textView_worktime.setText(textView_worktime.getText()+mStore.getWorkTime());
	    textView_name.setText(mStore.getName());
	    
	}
	
	static int flag=0;
	class MyAsyncTask extends AsyncTask<Void, Void, Integer>
	{

		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			initID();
			BmobQuery<Store> query=new BmobQuery<Store>();
			query.addWhereEqualTo("nameString", mStore.getName());
			query.findObjects(getActivity(), new FindListener<Store>() {
				
				@Override
				public void onSuccess(List<Store> arg0) {
					// TODO Auto-generated method stub
					mStore=arg0.get(0);
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
			   {return-1;}
				   
			}
			
			return flag;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			flag=0;
			mAsyncTask.cancel(true);
			super.onCancelled();
		}

		@Override
		protected void onCancelled(Integer result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			initViews();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
            flag=0;
			mAsyncTask.cancel(true);

		super.onDestroy();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(),MapActivity.class);
		intent.putExtra("storeName", ((CartActivity)getActivity()).getStore().getName());
		intent.putExtra("latitude", 21.870195);
		intent.putExtra("longitude", 111.976983);
		intent.putExtra("address", ((CartActivity)getActivity()).getStore().getAddress());
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.pagetoright, R.anim.pagetoright2);
	}
	
	
	
	
	
	
	
	
	
}