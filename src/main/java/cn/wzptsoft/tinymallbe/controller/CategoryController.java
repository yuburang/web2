package cn.wzptsoft.tinymallbe.controller;

import cn.wzptsoft.common.utils.Result;
import cn.wzptsoft.tinymallbe.entity.Category;
import cn.wzptsoft.tinymallbe.entity.CategoryVO;
import cn.wzptsoft.tinymallbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
//    查找所有商品分类
    @GetMapping("/all")
    public Result<CategoryVO> getAll(){
        CategoryVO categories = categoryService.getAll();
        return Result.ok(categories);
    }
//    查找根分类，用于宫格展示
    @GetMapping("/grid")
    public Result<List<Category>> getGridCategoryList(){
        List<Category> rootCategory = categoryService.getRootCategory();
        return Result.ok(rootCategory);
    }
}
