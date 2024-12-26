package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuRespository extends JpaRepository<Sku,Long> {
}
