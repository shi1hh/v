package MyUtils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ImgLoader {
     
	static ImageLoader imageLoader=ImageLoader.getInstance();
	public static void LoadURLImg(String url,ImageView imgView) {
		imageLoader.displayImage(url, imgView);
	}
	
	
	
}
