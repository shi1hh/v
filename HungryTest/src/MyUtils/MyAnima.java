package MyUtils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class MyAnima {
   
	public static ValueAnimator lineAnimator(final View view, int start, int end,long duration) {  
        ValueAnimator animator = ValueAnimator.ofInt(start, end);  
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
  
            @Override  
            public void onAnimationUpdate(ValueAnimator valueAnimator) {  
                int value = (Integer) valueAnimator.getAnimatedValue();  
  
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();  
                layoutParams.height = value;  
                view.setLayoutParams(layoutParams);  
            }  
        });  
        animator.setDuration(duration);  
        return animator;  
    }  
	
}
