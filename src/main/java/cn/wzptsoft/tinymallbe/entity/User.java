package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
//用户
@Entity
@Setter
@Getter
public class User extends BaseEntity{
//    账号
    private String username;
//    密码
    private String password;
//    盐
    private String salt;
//    昵称
    private String nickname;
//    手机号
    private String mobile;
}
