package cn.zknu.l_json.net;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018\4\20 0020.
 */

public interface ApiService {
    @GET("json.php")
    Call<HttpResult> getJson();
}
