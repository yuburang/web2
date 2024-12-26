package cn.wzptsoft.common.exception;

import cn.wzptsoft.common.constant.RespCodeEnum;
import cn.wzptsoft.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局统一异常处理器
 */
@RestControllerAdvice
public class BizExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(BizExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public Result<Void> handleException(Exception e){
    logger.error("Default Exception Class:" + e);
    logger.error("Default Exception Msg:" + e.getMessage());
    logger.error("Default Exception:", e);
    return Result.error(RespCodeEnum.INTERNAL_SERVER_ERROR);
  }

  /**
   * 处理自定义异常
   * @param e
   * @return
   */
  @ExceptionHandler(BizException.class)
  public Result<Void> handleImoocMallException(BizException e){
    logger.error("BizException Class:" + e);
    logger.error("BizException Msg:" + e.getMsg());
    logger.error("BizException:", e);
    return Result.error(e.getCode(), e.getMsg());
  }

  @ExceptionHandler(BindException.class)
  public Result<Void> handleMethodArgumentNotValidException(BindException e){
    logger.error("BindException Class:" + e);
    logger.error("BindException Msg:" + e.getMessage());
    logger.error("BindException:", e);
    return handleBindingResult(e.getBindingResult());
  }

  private Result<Void> handleBindingResult(BindingResult result){
    // 把异常处理为对外暴露的提示
    List<String> list = new ArrayList<>();
    if (result.hasErrors()) {
      List<ObjectError> allErrors = result.getAllErrors();
      for (int i = 0; i < allErrors.size(); i++) {
        ObjectError objectError = allErrors.get(i);
        String message = objectError.getDefaultMessage();
        list.add(message);
      }
    }
    if (list.size() == 0){
      return Result.error(RespCodeEnum.FAILED);
    }
    // 去掉list的中括号
    String listStr = list.toString();
    String errMsg = listStr.substring(1, listStr.length()-1);
    return Result.error(RespCodeEnum.FAILED.getCode(), errMsg);
  }
}