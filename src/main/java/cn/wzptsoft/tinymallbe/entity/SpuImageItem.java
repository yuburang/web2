package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class SpuImageItem extends BaseEntity{
//    图片
    private String img;
//    spuid
    private String spuId;
}
