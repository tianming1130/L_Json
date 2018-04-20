package cn.zknu.l_json.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018\4\9 0009.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    public static void requestGetByRetrofit(Callback<HttpResult> callback){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/json.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService=retrofit.create(ApiService.class);

        Call<HttpResult> call=apiService.getJson();
        call.enqueue(callback);
    }
    public static void  requestGet(final CallBack<String> callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConn=null;
                try {
                    String baseUrl="http://10.0.2.2/json.php";
                    URL url=new URL(baseUrl);
                    urlConn= (HttpURLConnection) url.openConnection();

                    urlConn.setRequestMethod("GET");
                    urlConn.setConnectTimeout(5*1000);
                    urlConn.setReadTimeout(5*1000);
                    urlConn.connect();
                    if (urlConn.getResponseCode()==200) {
                        InputStream is = urlConn.getInputStream();
                        callBack.onResponse(streamToString(is));
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                }
            }
        }).start();
    }
    private static String streamToString(InputStream is) {
        BufferedInputStream bis=new BufferedInputStream(is);
        StringBuilder sBuilder=new StringBuilder();
        try {
            byte[] buffer=new byte[1024];
            int len=-1;

            while ((len=bis.read(buffer))!=-1){
                sBuilder.append(new String(buffer,0,len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sBuilder.toString();
        }
    }
}
