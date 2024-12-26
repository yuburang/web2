package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.tinymallbe.entity.Sku;
import cn.wzptsoft.tinymallbe.repository.SkuRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuService {
    @Autowired
    private SkuRespository skuRespository;
    public Sku getSku(Long id){
        return skuRespository.getById(id);

    }
}
