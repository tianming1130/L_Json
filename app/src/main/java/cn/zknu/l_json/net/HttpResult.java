package cn.zknu.l_json.net;

/**
 * Created by Administrator on 2018\4\20 0020.
 */

public class HttpResult {
    private int retCode;
    private String message;
    private String data;

    public HttpResult(int retCode, String message, String data) {
        this.retCode = retCode;
        this.message = message;
        this.data = data;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "retCode=" + retCode +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
