package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.common.exception.BizException;
import cn.wzptsoft.common.utils.MD5Utils;
import cn.wzptsoft.tinymallbe.dto.UserLoginDTO;
import cn.wzptsoft.tinymallbe.entity.User;
import cn.wzptsoft.tinymallbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUserById(Long id){
        return userRepository.findFirstById(id);
    }
//    用户登录
//    用户登录请求数据
//    登陆失败false 成功true
    public User login(UserLoginDTO userLoginDTO){
        User user =
                userRepository.findByUsername(userLoginDTO.getUsername());
        if(user == null){
            throw new BizException("登陆失败:用户名或密码不正确");
        }
        String md5Password =
                MD5Utils.md5Digest(userLoginDTO.getPassword(),user.getSalt());
        if(!md5Password.equals(user.getPassword())){
            throw new BizException("登陆失败:用户名或密码不正确");
        }
        return user;
    }
}
