package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.Category;
import cn.wzptsoft.tinymallbe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByIsRootOrderBySortAsc(Boolean isRoot);
    List<Category> findByParentId(Long categoryId);
}
