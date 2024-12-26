package cn.wzptsoft.common.utils;

import cn.wzptsoft.common.constant.RespCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应数据
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编码：0表示成功，其他值表示失败
     */
    private Integer code;
    /**
     * 消息内容
     */
    private String msg = "";
    /**
     * 响应数据
     */
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
        this.code = RespCodeEnum.SUCCESS.getCode();
        this.msg = RespCodeEnum.SUCCESS.getMsg();
    }

    public static <T>Result<T> ok(){
        return new Result<T>();
    }
    public static <T>Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T>Result<T> ok(String msg) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        return result;
    }

    public static <T>Result<T> ok(T data, String msg) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(msg);
        return result;
    }


    public static <T>Result<T> error(RespCodeEnum RespCodeEnum) {
        return new Result<T>(RespCodeEnum.getCode(), RespCodeEnum.getMsg());
    }

    public static <T>Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }

}
