package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Setter
@Getter
public class Spu extends BaseEntity{
//    标题
    private String title;
//    描述
    private String description;
//        所属分类id
    private Long categoryId;
//        是否上架
    private Boolean onsale;
//    价格
    private String price;
//    默认SKU的id
    private Long defaultSkuId;
//    列表页的封面图片
    private String img;
//    标签
    private String  tags;
//    包含的SKU
    @OneToMany
    @JoinColumn(name = "spuId")
    private List<Sku> skus;
//    包含轮播图

    @OneToMany
    @JoinColumn(name = "spuId")
    private List<SpuImageItem> spuImageItems;
}
