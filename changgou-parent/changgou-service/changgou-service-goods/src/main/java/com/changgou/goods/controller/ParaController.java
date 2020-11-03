package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {
    @Autowired
    private ParaService service;

    @GetMapping
    public Result<Para> findAll() {
        List<Para> all = service.findAll();
        return new Result<Para>(true, StatusCode.OK, "查询参数成功", all);
    }

    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable("id") Integer id) {
        Para byId = service.findById(id);
        return new Result<>(true, StatusCode.OK, "根据id查询成功", byId);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<Para> paraPageInfo = service.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功", paraPageInfo);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Para para, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<Para> paraPageInfo = service.findPage(para, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页条件查询成功", paraPageInfo);
    }

    @PostMapping("/search")
    public Result<List<Para>> findList(@RequestBody Para para) {
        List<Para> list = service.findList(para);
        return new Result<List<Para>>(true, StatusCode.OK, "条件查询成功", list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        service.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Para para, @PathVariable Integer id) {
        para.setId(id);
        service.update(para);
        return new Result(true, StatusCode.OK, "修改成功");
    }

}
