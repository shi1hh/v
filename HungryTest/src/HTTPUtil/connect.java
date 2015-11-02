package HTTPUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class connect {
	
	static String key="d6abf6506992e1b394ac5604f9d6e882";
	static String url="http://apis.juhe.cn/baidu/getLocate?"
			+"lng=111.97699&lat=21.870025&r=&cid=1&page=&pnums=&dtype=&key="+key;
	


public static void conServer(final String url,final HttpCallback htpcb)
{
	new Thread(new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
		HttpURLConnection htpconn=null;
	try
	{
	
      URL url1=new URL(url);
	  htpconn=(HttpURLConnection) url1.openConnection();
	  htpconn.setReadTimeout(8000);
	  htpconn.setRequestMethod("GET");
	  htpconn.setConnectTimeout(8000);
	  BufferedReader br=new BufferedReader(new InputStreamReader(htpconn.getInputStream()));
	  String str="";
	  String line=null;
	  while((line=br.readLine())!=null)
	  {
		  str=str+line;
	  }
      if(!str.equals(""))
      {
    	  htpcb.onFinish(str);
      }
	    
		Log.d("response", str);
	}catch(Exception e)
      {e.printStackTrace();}	
	finally
	{
		if(htpconn!=null)
		{
			htpconn.disconnect();
		}
	}}}).start();
}

public static void getRestauData(final HttpCallback htpcb)
{
	new Thread(new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
		HttpURLConnection htpconn=null;
	try
	{
	
      URL url1=new URL(url);
	  htpconn=(HttpURLConnection) url1.openConnection();
	  htpconn.setReadTimeout(8000);
	  htpconn.setRequestMethod("GET");
	  htpconn.setConnectTimeout(8000);
	  BufferedReader br=new BufferedReader(new InputStreamReader(htpconn.getInputStream()));
	  String str="";
	  String line=null;
	  while((line=br.readLine())!=null)
	  {
		  str=str+line;
	  }
      if(!str.equals(""))
      {
    	  htpcb.onFinish(str);
      }
	    
		Log.d("response", str);
	}catch(Exception e)
      {e.printStackTrace();}	
	finally
	{
		if(htpconn!=null)
		{
			htpconn.disconnect();
		}
	}}}).start();

}

public static void login(final String acount,final String psw,final HttpCallback htpcb)
{
	new Thread(new Runnable(){
		@Override
		public void run() {
	HttpURLConnection htpconn=null;
	try {
		URL url1=new URL("http://10.0.2.2:8888/myweb/NewFile1.jsp?");
		htpconn=(HttpURLConnection) url1.openConnection();
		htpconn.setConnectTimeout(8000);
		htpconn.setDoOutput(true);
		htpconn.setDoInput(true);
		htpconn.setRequestMethod("POST");
		String post_str="username="+URLEncoder.encode(acount,"utf-8")+"&password="+URLEncoder.encode(psw,"utf-8");
		OutputStream ops=htpconn.getOutputStream();
		ops.write(post_str.getBytes());
		ops.flush();
		if(htpconn.getResponseCode()==200)
		{
			Log.d("wew", "url2");
			BufferedReader bfr=new BufferedReader(new InputStreamReader(htpconn.getInputStream()));
			String line=null;
			String response="";
			while((line=bfr.readLine())!=null)
			{
				response+=line;
			}
			Log.d("ww", response);
			htpcb.onFinish(response);
		}
		else
		{
			System.out.println("¡¥Ω” ß∞‹.........");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		htpcb.onError(e);
	}
	finally{if(htpconn!=null){htpconn.disconnect();}}}}).start();
	
	}

}