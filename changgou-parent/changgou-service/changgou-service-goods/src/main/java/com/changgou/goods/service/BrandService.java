package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    //查询全部
    List<Brand> findAll();

    //根据id查询
    Brand findById(Integer id);

    //增加品牌
    void add(Brand brand);

    //根据id修改品牌数据
    void update(Brand brand);

    //根据id删除
    void deleteById(Integer id);

    //根据品牌信息多条件搜索
    List<Brand> findList(Brand brand);

    /**
     * 分页搜索
     *
     * @param page :当前页
     * @param size :每页显示的条数
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 条件分页搜索
     *
     * @param page       :当前页
     * @param size       :每页显示的条数
     * @param brand:搜索条件
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

}
