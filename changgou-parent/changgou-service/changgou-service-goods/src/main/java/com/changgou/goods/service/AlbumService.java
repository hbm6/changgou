package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 这是相册接口层
 */
public interface AlbumService {
    //查询全部
    List<Album> findAll();

    //根据id查询
    Album findById(Long id);

    //根据id修改
    void update(Album album);

    //添加
    void add(Album album);

    //根据id删除
    void delete(Long id);

    //多条件查询
    List<Album> findList(Album album);

    //无条件分页查询
    PageInfo<Album> findPage(Integer page, Integer size);

    //待条件分页查询
    PageInfo<Album> findPage(Album album, Integer page, Integer size);

}
