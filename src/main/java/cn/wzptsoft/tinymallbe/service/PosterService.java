package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.tinymallbe.entity.Poster;
import cn.wzptsoft.tinymallbe.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosterService {
    @Autowired
    private PosterRepository posterRepository;
//    查找指定的广告
    public Poster getByName(String name){
        return posterRepository.findByName(name);
    }
}
