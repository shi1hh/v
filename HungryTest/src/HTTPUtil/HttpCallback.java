package HTTPUtil;

public interface HttpCallback {
  void onFinish(String response);
   void onError(Exception e);
}
