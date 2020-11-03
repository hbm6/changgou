package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
//a域名访问b域名的数据
/**
 * 域名或者请求端口或者协议不一致的时候,就跨域了
 */
@CrossOrigin //跨域

public class BrandController {
    @Autowired
    protected BrandService brandService;

    //条件分页查询
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,
                                            @PathVariable("page") Integer page,
                                            @PathVariable("size") Integer size) {
        PageInfo<Brand> pages = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "条件分页查询成功", pages);

    }


    //分页查询
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable("page") Integer page,  //当前页
                                            @PathVariable("size") Integer size) {
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询成功!", pageInfo);

    }


    //根据条件搜索查询
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件搜索查询成功!", brandList);
    }


    //根据id删除
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除Brand成功");
    }


    /**
     * 查询所有品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();
        //相应结果封装
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功", brands);

    }

    /**
     * 根据主键查询
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        Brand byId = brandService.findById(id);
        //int i = 1/0;
        return new Result<Brand>(true, StatusCode.OK, "根据ID查询成功", byId);
    }

    /**
     * 添加品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加Brand成功");
    }

    @PutMapping("/{id}")
    public Result updateById(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改Brand成功");
    }
}
