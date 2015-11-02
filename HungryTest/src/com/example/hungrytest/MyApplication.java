package com.example.hungrytest;

import java.io.File;

import cn.bmob.v3.Bmob;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import android.graphics.Bitmap;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class MyApplication extends Application {

	public void onCreate()
	{
		SDKInitializer.initialize(getApplicationContext());
		 Bmob.initialize(this, "cca49dd00e78087282cf4b400bdb7168");
		
		
		
		
		super.onCreate();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCacheExtraOptions(480, 800)
				// max width, max height閿涘苯宓嗘穱婵嗙摠閻ㄥ嫭鐦℃稉顏嗙处鐎涙ɑ鏋冩禒鍓佹畱閺堬拷婢堆囨毐鐎癸拷
				.discCacheExtraOptions(480, 800, null)
				// Can slow ImageLoader, use it carefully (Better don't use
				// it)/鐠佸墽鐤嗙紓鎾崇摠閻ㄥ嫯顕涚紒鍡曚繆閹垽绱濋張锟芥總鎴掔瑝鐟曚浇顔曠純顔跨箹娑擄拷
				.threadPoolSize(3)
				// 缁捐法鈻煎Ч鐘插敶閸旂姾娴囬惃鍕殶闁诧拷
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/娴ｇ姴褰叉禒銉╋拷姘崇箖閼奉亜绻侀惃鍕敶鐎涙绱︾�涙ê鐤勯悳锟�
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 鐏忓棔绻氱�涙娈戦弮璺猴拷娆戞畱URI閸氬秶袨閻⑩垥D5 閸旂姴鐦�
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)
				// 缂傛挸鐡ㄩ惃鍕瀮娴犺埖鏆熼柌锟�
				.discCache(
						new UnlimitedDiscCache(new File(Environment
								.getExternalStorageDirectory()
								+ "/myApp/imgCache")))
				// 閼奉亜鐣炬稊澶岀处鐎涙鐭惧锟�
				.defaultDisplayImageOptions(getDisplayOptions())
				.imageDownloader(
						new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
				.writeDebugLogs() // Remove for release app
				.build();// 瀵拷婵鐎锟�
		ImageLoader.getInstance().init(config);
	}

	private DisplayImageOptions getDisplayOptions() {
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.wodelogo) // 鐠佸墽鐤嗛崶鍓у閸︺劋绗呮潪鑺ユ埂闂傚瓨妯夌粈铏规畱閸ュ墽澧�
				.showImageForEmptyUri(R.drawable.wodelogo)// 鐠佸墽鐤嗛崶鍓уUri娑撹櫣鈹栭幋鏍ㄦЦ闁挎瑨顕ら惃鍕閸婃瑦妯夌粈铏规畱閸ュ墽澧�
				.showImageOnFail(R.drawable.wodelogo) // 鐠佸墽鐤嗛崶鍓у閸旂姾娴�/鐟欙絿鐖滄潻鍥┾柤娑擃參鏁婄拠顖涙閸婃瑦妯夌粈铏规畱閸ュ墽澧�
				.cacheInMemory(true)// 鐠佸墽鐤嗘稉瀣祰閻ㄥ嫬娴橀悧鍥ㄦЦ閸氾妇绱︾�涙ê婀崘鍛摠娑擄拷
				.cacheOnDisc(true)// 鐠佸墽鐤嗘稉瀣祰閻ㄥ嫬娴橀悧鍥ㄦЦ閸氾妇绱︾�涙ê婀猄D閸椻�茶厬
				.considerExifParams(true) // 閺勵垰鎯侀懓鍐JPEG閸ユ儳鍎欵XIF閸欏倹鏆熼敍鍫熸鏉烆剨绱濈紙鏄忔祮閿涳拷
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 鐠佸墽鐤嗛崶鍓у娴犮儱顩ф担鏇犳畱缂傛牜鐖滈弬鐟扮础閺勫墽銇�
				.bitmapConfig(Bitmap.Config.RGB_565)// 鐠佸墽鐤嗛崶鍓у閻ㄥ嫯袙閻胶琚崹锟�//
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis娑撹桨缍樼拋鍓х枂閻ㄥ嫪绗呮潪钘夊閻ㄥ嫬娆㈡潻鐔告闂傦拷
				// 鐠佸墽鐤嗛崶鍓у閸旂姴鍙嗙紓鎾崇摠閸撳稄绱濈�电itmap鏉╂稖顢戠拋鍓х枂
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 鐠佸墽鐤嗛崶鍓у閸︺劋绗呮潪钘夊閺勵垰鎯侀柌宥囩枂閿涘苯顦叉担锟�
				.displayer(new RoundedBitmapDisplayer(20))// 閺勵垰鎯佺拋鍓х枂娑撳搫娓剧憴鎺炵礉瀵冨娑撳搫顦跨亸锟�
				.displayer(new FadeInBitmapDisplayer(100))// 閺勵垰鎯侀崶鍓у閸旂姾娴囨總钘夋倵濞撴劕鍙嗛惃鍕З閻㈢粯妞傞梻锟�
				.build();// 閺嬪嫬缂撶�瑰本鍨�
		return options;
	}
	
	
}
