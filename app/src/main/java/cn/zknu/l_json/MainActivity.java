package cn.zknu.l_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zknu.l_json.net.CallBack;
import cn.zknu.l_json.net.HttpResult;
import cn.zknu.l_json.net.HttpUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="MainActivity" ;
    private Button btnJsonObject,btnGson,btnRetrofit;
    private TextView tvShowMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void init() {
        btnJsonObject.setOnClickListener(this);
        btnGson.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);
    }

    private void initView() {
        btnJsonObject=(Button)findViewById(R.id.btn_json_object);
        btnGson=(Button)findViewById(R.id.btn_gson);
        btnRetrofit=(Button)findViewById(R.id.btn_retrofit);
        tvShowMsg=(TextView)findViewById(R.id.tv_show_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_json_object:
                jsonObject();
                break;
            case R.id.btn_gson:
                gson();
                break;
            case R.id.btn_retrofit:
                retrofit();
                break;
        }
    }

    private void retrofit() {
        HttpUtil.requestGetByRetrofit(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
                tvShowMsg.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {

            }
        });
    }

    private void gson() {
        HttpUtil.requestGet(new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                HttpResult result=gson.fromJson(response,HttpResult.class);
                Log.i(TAG,result.toString());
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    private void jsonObject() {
        HttpUtil.requestGet(new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int retCode=jsonObject.getInt("retCode");
                    String message=jsonObject.getString("message");
                    String data=jsonObject.getString("data");

                    HttpResult result=new HttpResult(retCode,message,data);

                    Log.i(TAG,result.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
