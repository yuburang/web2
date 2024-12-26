package cn.wzptsoft.common.exception;


import cn.wzptsoft.common.constant.RespCodeEnum;

/**
 * 自定义异常
 */
public class BizException extends RuntimeException {

	private Integer code;
	private String msg;

	public BizException(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public BizException(RespCodeEnum e) {
		this.code = e.getCode();
		this.msg = e.getMsg();
	}

	public BizException(String msg){
		this.code = RespCodeEnum.FAILED.getCode();
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}