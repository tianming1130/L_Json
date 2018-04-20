package cn.zknu.l_json.net;

/**
 * Created by Administrator on 2018\4\16 0016.
 */

public interface CallBack<T> {
    void onResponse(T response);
    void onFailed(Exception e);
}
