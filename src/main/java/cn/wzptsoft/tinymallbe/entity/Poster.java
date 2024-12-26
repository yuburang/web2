package cn.wzptsoft.tinymallbe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
@Getter
@Setter
@Entity
public class Poster extends BaseEntity {
//    广告名称
    private String name;
//    描述
    private String description;
//    关联的多端
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "posterId")
    private List<PosterItem> items;

}
