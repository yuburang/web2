package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster,Long> {
    Poster findByName(String name);
}
