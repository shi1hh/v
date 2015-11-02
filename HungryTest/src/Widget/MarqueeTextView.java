package Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;


	public class MarqueeTextView extends TextView {
		public MarqueeTextView(Context con) {
		  super(con);
	
		}

		public MarqueeTextView(Context context, AttributeSet attrs) {
		  super(context, attrs);
		}
		public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		  super(context, attrs, defStyle);
		}
		@Override
		public boolean isFocused() {
		return true;
		}
		@Override
		protected void onFocusChanged(boolean focused, int direction,
		   Rect previouslyFocusedRect) {  
		}
		}