package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryVO {
    private List<Category> roots;
    private List<Category> subs;
}
