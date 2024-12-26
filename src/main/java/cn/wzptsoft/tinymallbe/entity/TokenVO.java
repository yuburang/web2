package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;
//登录成功后返回对象
@Getter
@Setter
public class TokenVO {
    private String token;
    public TokenVO(String token){
        this.token = token;
    }
}
