package cn.wzptsoft.tinymallbe.interceptor;

import cn.wzptsoft.common.constant.RespCodeEnum;
import cn.wzptsoft.common.exception.BizException;
import cn.wzptsoft.common.utils.JwtUtils;
import cn.wzptsoft.tinymallbe.entity.User;
import cn.wzptsoft.tinymallbe.security.ThreadContext;
import cn.wzptsoft.tinymallbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,Object handler) throws Exception{
        //跨域支持
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        String bearerToken = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(bearerToken) ||
    !bearerToken.startsWith("Bearer")){
            throw new BizException(RespCodeEnum.UNAUTHORIZE);
        }
        String tokens[] = bearerToken.split(" ");
        if(!(tokens.length == 2)){
            throw new BizException(RespCodeEnum.UNAUTHORIZE);
        }
        String jwt = tokens[1];
    //    解析请求头中的jwt
        String subject = JwtUtils.getSubject(jwt);
        User user = userService.getUserById(Long.parseLong(subject));
        if(user == null){
            throw new BizException(RespCodeEnum.UNAUTHORIZE);
        }
        ThreadContext.setUser(user);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,Object handler,Exception ex) throws Exception{
    //    上下文属性值清除，防止内存泄露
        ThreadContext.clear();
    }
}
