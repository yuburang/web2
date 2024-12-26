package cn.wzptsoft.tinymallbe.controller;

import cn.wzptsoft.common.utils.Result;
import cn.wzptsoft.tinymallbe.entity.Poster;
import cn.wzptsoft.tinymallbe.service.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("poster")
@Validated
public class PosterController {
    @Autowired
    private PosterService posterService;
// 查找指定的广告 name广告名称
    @GetMapping("/name/{name}")
    public Result<Poster> getByName(@PathVariable @NotBlank String name){
        Poster poster = posterService.getByName(name);
        return Result.ok(poster);
    }
}
