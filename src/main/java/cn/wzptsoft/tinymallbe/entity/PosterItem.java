package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PosterItem extends BaseEntity {
//    广告子项
//    广告图片
    private String img;
//    关联的一端
    private Long posterId;
//    对应的商品ID
    private Long spuId;
}
