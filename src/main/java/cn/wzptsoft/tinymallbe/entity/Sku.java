package cn.wzptsoft.tinymallbe.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import cn.wzptsoft.common.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
public class Sku extends BaseEntity{
//    标题
    private String title;
//    价格
    private BigDecimal price;
//    是否上架
    private Boolean onsale;
//    图片
    private String img;
//    所属spu
    private Long spuId;
//    规格详情
    private String specs;
//    库存数量
    private Integer stock;

    public List<Spec> getSpecs(){
        if(this.specs == null){
            return Collections.emptyList();
        }
        return JsonUtils.jsonToObject(this.specs, new TypeReference<List<Spec>>() {
        });
    }

    public void setSpecs(List<Spec> specs){
        if(specs.isEmpty()){
            return;
        }
        this.specs = JsonUtils.objectToJson(specs);
    }
}
