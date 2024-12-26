package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Theme extends BaseEntity {
//    标题
    private String title;
//    描述
    private String description;
//    专题名称
    private String name;
//    入口图片
    private String entranceImg;
//    当前专题包含的商品
    @ManyToMany
    @JoinTable(name = "theme_spu",joinColumns =
    @JoinColumn(name = "theme_id"),
    inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<Spu> spuList;
}
