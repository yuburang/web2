package cn.wzptsoft.tinymallbe.repository;

import cn.wzptsoft.tinymallbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findFirstById(Long id);
}
