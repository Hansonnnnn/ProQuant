package VO;

/**
 * Created by xiezhenyu on 2017/6/7.
 * 所有ajax请求返回类型，封装json结果，如果是true再拿数据，如果是false，输出错误信息
 */
public class RequestResult<T> {
    private boolean success;
    private T data;
    private String error;

    //如果是true的话一定有数据
    public RequestResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //如果是false的话，里面有错误信息
    public RequestResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
