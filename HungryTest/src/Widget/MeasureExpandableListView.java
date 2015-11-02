package Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class MeasureExpandableListView extends ExpandableListView {

	public MeasureExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public MeasureExpandableListView(Context context) {
		super(context);
	}
	public MeasureExpandableListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	protected void  onMeasure(int arg0,int arg1) {
		int measureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(arg0, measureSpec);
	}

}
