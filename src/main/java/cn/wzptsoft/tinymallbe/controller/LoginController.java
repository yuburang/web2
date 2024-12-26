package cn.wzptsoft.tinymallbe.controller;

import cn.wzptsoft.common.utils.JwtUtils;
import cn.wzptsoft.common.utils.Result;
import cn.wzptsoft.tinymallbe.dto.UserLoginDTO;
import cn.wzptsoft.tinymallbe.entity.TokenVO;
import cn.wzptsoft.tinymallbe.entity.User;
import cn.wzptsoft.tinymallbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//登录与注册
@RequestMapping
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

//    用户登录
//    userLoginParam 账号密码
//    返回 token
    @PostMapping("/login")
    public Result<TokenVO> login(@RequestBody @Valid UserLoginDTO userLoginParam){
    //    调用userService校验账号密码
        User user = userService.login(userLoginParam);
    //    生成JWT，返回前端，用于后续其他接口的登录验证
        String jwt = JwtUtils.generateToken(user.getId());
        TokenVO token = new TokenVO(jwt);
        return Result.ok(token);
    }
}
