/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library;

import android.R.integer;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

	View mTopView;
	View mFlowView;
	View search;
	int searchheight;
	int scrolllast=0;
	boolean isFirstScroll=true;
	boolean isRunning=false;
	
	
	public PullToRefreshScrollView(Context context) {
		super(context);
	}

	public PullToRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullToRefreshScrollView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullToRefreshScrollView(Context context, Mode mode, AnimationStyle style) {
		super(context, mode, style);
		
	}

	@Override
	public final Orientation getPullToRefreshScrollDirection() {
		return Orientation.VERTICAL;
	}

	@Override
	protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
		ScrollView scrollView;
		if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
			scrollView = new InternalScrollViewSDK9(context, attrs);
		} else {
			scrollView = new ScrollView(context, attrs);
		}

		scrollView.setId(R.id.scrollview);
		return scrollView;
	}

	@Override
	protected boolean isReadyForPullStart() {
		return mRefreshableView.getScrollY() == 0;
	}

	@Override
	protected boolean isReadyForPullEnd() {
		View scrollViewChild = mRefreshableView.getChildAt(0);
		if (null != scrollViewChild) {
			return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
		}
		return false;
	}

	@TargetApi(9)
	final class InternalScrollViewSDK9 extends ScrollView {

		public InternalScrollViewSDK9(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
				int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

			final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
					scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

			// Does all of the hard work...
			OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, deltaX, scrollX, deltaY, scrollY,
					getScrollRange(), isTouchEvent);
			
	
			int[] topViewlocation=new int[2];
			mTopView.getLocationOnScreen(topViewlocation);
			Log.d("lol","divider"+topViewlocation[1]);
			int[] scrollview=new int[2];
			this.getLocationOnScreen(scrollview);
			Log.d("lol","flow"+scrollview[1]);
			int[] searchViewlocation=new int[2];
			search.getLocationOnScreen(searchViewlocation);
			
			
/*			Log.d("lol2", "searchViewlocation="+searchViewlocation[1]+"  e "+(searchViewlocation[1]-scrollY));
			
			if(isFirstScroll)
			{
			searchheight=searchViewlocation[1];
			isFirstScroll=false;
			}
			Log.d("lol", "sroly"+maxOverScrollY+"  deltay"+search.getY()+"   touch"+searchheight);
			
			
			if(!isTouchEvent){
			  if(scrollY>scrolllast&&scrollY<searchheight){
			  ViewGroup.LayoutParams layoutParams = search.getLayoutParams();  
              layoutParams.height =searchViewlocation[1]-scrollY;  
              search.setLayoutParams(layoutParams);
               scrolllast=scrollY;
			  }
			  else if(scrollY>=0){
				  ViewGroup.LayoutParams layoutParams = search.getLayoutParams();  
			      
				  if(layoutParams.height!=searchViewlocation[1])
				  {
				  layoutParams.height =searchViewlocation[1];  
				  search.setLayoutParams(layoutParams);
				  }
	             
	          
				  scrolllast=scrollY;
			}
			}*/
			  
		/*	 
			  else if(scrollY<scrolllast&&scrollY<searchheight){
				  ViewGroup.LayoutParams layoutParams = search.getLayoutParams();  
	              layoutParams.height =searchViewlocation[1]-(scrolllast-scrollY);  
	              search.setLayoutParams(layoutParams);
	               scrolllast=scrollY;
			}*/
	/*		if(scrollY>scrolllast&&scrollY<=searchheight)
			{
				if((scrollY-scrolllast)>=searchheight)
				Log.d("lol2", "searchViewlocation="+searchViewlocation[1]+"  e "+(searchViewlocation[1]-scrollY));
				if(!isRunning)
				{}
				scrolllast=scrollY;
			}*/
	/*		else if(scrollY<scrolllast&&scrollY<=searchheight)  {
				if((scrolllast-scrollY)>=searchheight)
				if(!isRunning)
				{lineAnimator(search, 0, searchViewlocation[1], 100).start();}
				scrolllast=scrollY;
			}*/

		
			
			
			
			
			if(mTopView != null && mFlowView != null) {
	
				if(topViewlocation[1]>= scrollview[1]) {
				
					mFlowView.setVisibility(View.GONE);
			
				} else {
					mFlowView.setVisibility(View.VISIBLE);

				}
			}
			
			

			return returnValue;
		}

		/**
		 * Taken from the AOSP ScrollView source
		 */
		private int getScrollRange() {
			int scrollRange = 0;
			if (getChildCount() > 0) {
				View child = getChildAt(0);
				scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
			}
			return scrollRange;
		}
	}
	
	
	public void listenerFlowViewScrollState(View topView, LinearLayout flowView,View searchView) {
		mTopView = topView;
		mFlowView = flowView;
		search=searchView;
	}
	
	public  ValueAnimator lineAnimator(final View view, int start, final int end,long duration) {  
        ValueAnimator animator = ValueAnimator.ofInt(start, end);  
        isRunning=true;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
  
            @Override  
            public void onAnimationUpdate(ValueAnimator valueAnimator) {  
                int value = (Integer) valueAnimator.getAnimatedValue();  
                
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();  
                layoutParams.height = value;  
                view.setLayoutParams(layoutParams);  
                if (value==end) {
					isRunning=false;
				}
            }  
        });  
        animator.setDuration(duration);  
        return animator;  
    }  
	


	
	
}
