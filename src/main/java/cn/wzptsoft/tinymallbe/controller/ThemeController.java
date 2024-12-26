package cn.wzptsoft.tinymallbe.controller;

import cn.wzptsoft.tinymallbe.entity.Theme;
import cn.wzptsoft.tinymallbe.service.ThemeService;
import cn.wzptsoft.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @GetMapping("/by/names")
    public Result<List<Theme>> getThemeByNames(@RequestParam(name="names") String names){
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeService.findByNames(nameList);
        return Result.ok(themes);
    }
    @GetMapping("/detail/{name}")
    public Result<Theme> getThemeByNameWithSpu(@PathVariable(name = "name") String name){
        Theme theme = themeService.findByName(name);
        return Result.ok(theme);
    }
}
