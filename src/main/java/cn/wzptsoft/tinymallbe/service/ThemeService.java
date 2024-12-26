package cn.wzptsoft.tinymallbe.service;

import cn.wzptsoft.tinymallbe.entity.Theme;
import cn.wzptsoft.tinymallbe.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;
    public List<Theme> findByNames(List<String> names){
        return themeRepository.findByNameIn(names);
    }
    public Theme findByName(String name){
        return themeRepository.findByName(name);
    }
}
