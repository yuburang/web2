package cn.wzptsoft.tinymallbe.controller;

import cn.wzptsoft.common.utils.BeanConverter;
import cn.wzptsoft.common.utils.PageData;
import cn.wzptsoft.common.utils.Result;
import cn.wzptsoft.tinymallbe.entity.Sku;
import cn.wzptsoft.tinymallbe.entity.Spu;
import cn.wzptsoft.tinymallbe.entity.SpuBriefVO;
import cn.wzptsoft.tinymallbe.service.SkuService;
import cn.wzptsoft.tinymallbe.service.SpuService;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {
    @Autowired
    private SpuService spuService;
    @Autowired
    private SkuService skuService;

    @GetMapping("/sku")
    public Result<List<Sku>>  getSku(@RequestParam String ids){

        String[] strb = ids.split(",");
        if (strb[0].equals("")){
            return Result.ok("[]");
        }else{
            Sku sku;
            List<Sku> skus = new ArrayList<>();
            skus.add(skuService.getSku(Long.parseLong(strb[0])));
            for(int i = 1;i<strb.length;i++){
                sku =  skuService.getSku(Long.parseLong(strb[i]));
                skus.add(sku);
            }
            //sku = skuService.getSku(Long.parseLong(strb[0]));
            return Result.ok(skus);

        }
    }
//    商品列表分页查询
//    start 起始数
//    count 每页查询数
    @GetMapping("/page")
    public Result<PageData<SpuBriefVO>>
    getSpuByPage(@RequestParam(defaultValue = "0") Integer start,
                 @RequestParam(defaultValue = "10") Integer count){
    //    计算第几页
        int pageNum = start/count;
        Page<Spu> pageResult = this.spuService.getSpuByPage(pageNum,count);
        List<SpuBriefVO> simplePageResult =
                BeanConverter.convert(pageResult.getContent(),SpuBriefVO.class);
    //    封装到PageDate
        PageData<SpuBriefVO> pageDataResult = new PageData<>
                (simplePageResult,pageResult.getTotalElements());
        return Result.ok(pageDataResult);
    }
    //查找指定商品详情
    //id 商品id
    @GetMapping("/detail/{id}")
    public Result<Spu> getDetail(@PathVariable Long id){
        Spu spu = spuService.getSpu(id);
        return Result.ok(spu);
    }
//    查找指定分类下的商品
    @GetMapping("/category/{id}")
    public Result<PageData<SpuBriefVO>> getByCategoryId(@PathVariable Long id,
                                                    @RequestParam(defaultValue = "false") Boolean isRoot,
                                                    @RequestParam(defaultValue = "0") Integer start,
                                                    @RequestParam(defaultValue = "10") Integer count){
        int pageNum = start/count;

        Page<Spu> pageResult = this.spuService.getByCategory(id,isRoot,pageNum,count);
        //得到的分页结果，里面每个对象是Spu类型，而这个前端不需要看Spu的关联信息，所以需要将Spu对象转为不带关联字段掉的VO类型对象
        List<SpuBriefVO> simplePageResult = BeanConverter.convert(pageResult.getContent(),SpuBriefVO.class);
        //最后封装到PageDate类型对象中，返回前端
        PageData<SpuBriefVO> pageDataResult = new PageData<>(simplePageResult,pageResult.getTotalElements());
        return Result.ok(pageDataResult);

    }
}
