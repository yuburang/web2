package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ThemeRepository extends JpaRepository<Theme,Long> {
    List<Theme> findByNameIn(List<String> names);
    Theme findByName(String name);
}
