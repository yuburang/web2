package cn.wzptsoft.tinymallbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public abstract class BaseEntity {
//    主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
//    创建时间
    @JsonIgnore
    private Date createTime;
//    创建者
    @JsonIgnore
    private Long creator;
//    更新时间
    @JsonIgnore
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    //    更新者
    @JsonIgnore
    private Long updater;
}
