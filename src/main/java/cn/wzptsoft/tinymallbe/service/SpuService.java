package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.tinymallbe.entity.BaseEntity;
import cn.wzptsoft.tinymallbe.entity.Category;
import cn.wzptsoft.tinymallbe.entity.Spu;
import cn.wzptsoft.tinymallbe.repository.CategoryRepository;
import cn.wzptsoft.tinymallbe.repository.SpuRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuService {
    @Autowired
    private SpuRespository spuRespository;
    @Autowired
    private CategoryRepository categoryRepository;
//    商品表分页查询
    public Page<Spu> getSpuByPage(Integer pageNum,Integer size){
        Pageable page = PageRequest.of(pageNum,size, Sort.by("createTime").descending());
        return spuRespository.findAll(page);
    }
    public Spu getSpu(Long id){
        return spuRespository.getById(id);
    }
    public Page<Spu> getByCategory(Long categoryId,Boolean isRoot,int pageNum,Integer size){
        Pageable page = PageRequest.of(pageNum,size,Sort.by("createTime").descending());
        Page<Spu> pageResult;
    //    如果不是根分类，直接以categoryId为条件查询
        if(!isRoot){
            pageResult = spuRespository.findByCategoryId(categoryId,page);
        }else {
            //    如果是根分类，则先查出根分类下的二级分类，再以二级分类为条件进行查询
            List<Category> subCategorys = categoryRepository.findByParentId(categoryId);
        //    List<Category> -->List<Long>
            List<Long> subCategoryIds = subCategorys.stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());
            pageResult = spuRespository.findByCategoryIdIn(subCategoryIds,page);
        }
        return pageResult;
    }
}
