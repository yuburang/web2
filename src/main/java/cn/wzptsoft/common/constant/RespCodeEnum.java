package cn.wzptsoft.common.constant;

/**
 * 状态码枚举
 */
public enum RespCodeEnum {
    /**
     * 状态码枚举
     */
    SUCCESS(0, "请求成功"),
    FAILED(1, "请求失败"),
    NOT_LOGIN(2, "没有登录"),
    BAD_REQUEST(400, "请求的地址不存在或者包含不支持的参数"),
    UNAUTHORIZE(401, "未授权"),
    FORBIDDEN(403, "被禁止访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    UNPROCESABLE_ENTITY(422, "创建对象发生验证错误"),
    INTERNAL_SERVER_ERROR(500, "内部错误");

    /**
     * 状态码
     */
    Integer code;

    /**
     * 状态信息
     */
    String msg;

    RespCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
