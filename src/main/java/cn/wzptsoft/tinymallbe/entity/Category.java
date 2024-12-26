package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

//商品分类
@Entity
@Setter
@Getter
public class Category extends BaseEntity{
//    分类名称
    private String name;
//    描述
    private String description;
//    是否根分类
    private Boolean isRoot;
//    分类的图片
    private String img;
//    上级分类
    private Long parentId;
//    排序值
    private Integer sort;
}
