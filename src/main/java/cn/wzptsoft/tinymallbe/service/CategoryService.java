package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.tinymallbe.entity.Category;
import cn.wzptsoft.tinymallbe.entity.CategoryVO;
import cn.wzptsoft.tinymallbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    //查找所有商品分类
    public CategoryVO getAll(){
        List<Category> roots =
                categoryRepository.findAllByIsRootOrderBySortAsc(true);
        List<Category> subs =
                categoryRepository.findAllByIsRootOrderBySortAsc(false);
        CategoryVO result = new CategoryVO();
        result.setRoots(roots);
        result.setSubs(subs);
        return result;
    }
//    查找根分类，用于宫格展示
    public List<Category> getRootCategory(){
        List<Category> roots =
                categoryRepository.findAllByIsRootOrderBySortAsc(true);
        return roots;
    }
}
