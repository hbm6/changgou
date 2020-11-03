package com.changgou.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    List<Category> findList(Category category);

    PageInfo<Category> findPage(Integer page, Integer size);

    PageInfo<Category> findPage(Category category, Integer page, Integer size);

    List<Category> findByParentId(Integer pid);

    void delete(Integer id);

    void update(Category category);

    void add(Category category);

}
