package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
@CrossOrigin
/**
 * 规格管理
 */
public class SpecController {
    @Autowired
    private SpecService specService;

    @GetMapping
    public Result<Spec> findAll() {
        List<Spec> specList = specService.findAll();
        return new Result<Spec>(true, StatusCode.OK, "查询规格成功");
    }

    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        Spec serviceById = specService.findById(id);
        return new Result<Spec>(true, StatusCode.OK, "查询成功", serviceById);
    }

    @PostMapping()
    public Result add(@RequestBody(required = false) Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable("id") Integer id) {
        spec.setId(id);
        specService.update(spec);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search")
    public Result<List<Spec>> findList(@RequestBody(required = false) Spec spec) {
        List<Spec> specList = specService.findList(spec);
        return new Result<>(true, StatusCode.OK, "条件查询成功", specList);

    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<Spec> specPageInfo = specService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功", specPageInfo);

    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Spec spec, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页条件查询成功", pageInfo);
    }
}
