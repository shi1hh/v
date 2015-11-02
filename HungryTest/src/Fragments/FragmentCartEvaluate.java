package Fragments;

import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Store;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.hungrytest.CartActivity;
import com.example.hungrytest.R;


import Fragments.FragmentCartSeller.MyAsyncTask;
import JSON.login;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class FragmentCartEvaluate extends Fragment {

	
	View view;
	LayoutInflater layoutInflater;
    TextView textView_evaluate;
    RatingBar ratingBar_favour_service;
    RatingBar ratingBar_favour_goods;
    TextView textView_servicescore;
    TextView textView_goodscore;
    Button button_all;
    TextView textView_good;
    TextView textView_notgood;
    Store mStore;
    List<Comment> mComments;
    MyAsyncTask mAsyncTask;
    static final String LOG_TAG="FragmentCartEvaluate";

	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		view=arg0.inflate(R.layout.fragment_cart_evaluate, null);
		layoutInflater=arg0;
        mAsyncTask= new MyAsyncTask();
		mAsyncTask.execute();
	
		return view;
	}
	void init()
	{
		
		initID();
		initTextViews();
		initRatingBar();
	}
	void initTextViews()
	{
		String text=mStore.getPraise().toString()+"%";
	    SpannableString spannableString=new SpannableString(text+textView_evaluate.getText());
	    spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    spannableString.setSpan(new AbsoluteSizeSpan(30,true), 0, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
	    textView_evaluate.setText(spannableString);
	    
	    textView_servicescore.setText(mStore.getServiceVal().toString()+textView_servicescore.getText());
	    textView_goodscore.setText(mStore.getGoodsVal().toString()+textView_goodscore.getText());
	}
	void initRatingBar()
	{
		ratingBar_favour_service.setRating(mStore.getServiceVal());
		ratingBar_favour_goods.setRating(mStore.getGoodsVal());
	}
	void initID()
	{
		mStore=((CartActivity)getActivity()).getStore();
		textView_evaluate=(TextView) view.findViewById(R.id.textView_evaluate);
		ratingBar_favour_service=(RatingBar) view.findViewById(R.id.ratingBar_favour_service);
		ratingBar_favour_goods=(RatingBar) view.findViewById(R.id.ratingBar_favour_goods);
		textView_servicescore=(TextView) view.findViewById(R.id.textView_servicescore);
		textView_goodscore=(TextView) view.findViewById(R.id.textView_goodscore);
		button_all=(Button) view.findViewById(R.id.button_all);
		textView_good=(TextView) view.findViewById(R.id.textView_good);
		textView_notgood=(TextView) view.findViewById(R.id.textView_notgood);
	}
	
	
	
	
	
	static int flag=0;
	class MyAsyncTask extends AsyncTask<Void, Void, Integer>
	{

		@Override
		protected Integer doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
            initID();
			BmobQuery<Comment> query=new BmobQuery<Comment>();
			query.addWhereEqualTo("store", mStore.getObjectId());
			query.findObjects(getActivity(), new FindListener<Comment>() {
				
				@Override
				public void onSuccess(List<Comment> arg0) {
					// TODO Auto-generated method stub
					Log.d(LOG_TAG, "onAsync success");
					mComments=arg0;
				   	initTextViews();
		    		initRatingBar();
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
	
}
