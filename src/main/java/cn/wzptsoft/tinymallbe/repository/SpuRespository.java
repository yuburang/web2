package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.Sku;
import cn.wzptsoft.tinymallbe.entity.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpuRespository extends JpaRepository<Spu,Long> {
    Page<Spu> findByCategoryId(Long categoryId, Pageable page);
    Page<Spu> findByCategoryIdIn(List<Long> subCategoryIds,Pageable page);
}
