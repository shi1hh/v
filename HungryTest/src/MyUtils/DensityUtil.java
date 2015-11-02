package MyUtils;

import android.R.integer;
import android.content.Context;

public class DensityUtil  {

	public  static int dip2px(Context context,int value)
	{
		float scale=context.getResources().getDisplayMetrics().density;
		return (int)(value*scale+0.5f);
		
	}
	public static int px2dip(Context context,int value)
	{
		float scale=context.getResources().getDisplayMetrics().density;
		return (int)(value/scale+0.5f);
	}
	
	
}
