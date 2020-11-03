package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<Category> findAll() {
        List<Category> all = categoryService.findAll();
        return new Result<Category>(true, StatusCode.OK, "查全部出成功", all);
    }

    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        Category byId = categoryService.findById(id);
        return new Result<Category>(true, StatusCode.OK, "根据id查询成功", byId);
    }

    @PostMapping("/search")
    public Result<Category> findList(@RequestBody Category category) {
        List<Category> serviceList = categoryService.findList(category);
        return new Result<Category>(true, StatusCode.OK, "根据条件查询", serviceList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<Category> page1 = categoryService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功", page1);
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Category category, @PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<Category> page1 = categoryService.findPage(category, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页条件查询成功", page1);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Category category, @PathVariable Integer id) {
        category.setId(id);
        categoryService.update(category);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @PostMapping
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 根据父ID查询
     */
    @RequestMapping("/list/{pid}")
    public Result<Category> findByPrantId(@PathVariable("pid") Integer pid) {
        List<Category> byParentId = categoryService.findByParentId(pid);
        return new Result<Category>(true, StatusCode.OK, "根据夫id查询成功", byParentId);
    }
}
