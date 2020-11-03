package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ParaService {
    List<Para> findAll();

    Para findById(Integer id);

    void delete(Integer id);

    void update(Para para);

    void add(Para para);

    List<Para> findList(Para para);

    PageInfo<Para> findPage(Integer page, Integer size);

    PageInfo<Para> findPage(Para para, Integer page, Integer size);
}
