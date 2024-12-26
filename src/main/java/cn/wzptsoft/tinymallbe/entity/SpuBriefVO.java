package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpuBriefVO {
    private Long id;
    private String title;
    private String description;
    private String img;
    private String price;
    private String discountPrice;
    private String tags;
}
