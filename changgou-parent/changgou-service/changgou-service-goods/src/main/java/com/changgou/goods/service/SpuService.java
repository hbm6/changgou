package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface SpuService {


    //逻辑还原
    void restore(Long spuId);

    //逻辑删除
    void logicDelete(Long spuId);


    //批量下架
    Integer pullMany(Long[] ids);

    //批量上架
    Integer putMany(Long[] ids);

    /**
     * 商品上架
     */
    void put(Long spuId);

    /**
     * 商品下架
     */
    void pull(Long spuId);


    /**
     * 商品审核
     */
    void audit(Long spuId);

    /**
     * 根据id查询goods数据
     */
    Goods findGoodsById(Long id);

    /**
     * 保存商品
     */
    void saveGoods(Goods goods);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(String id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     *
     * @param id
     * @return
     */
    Spu findById(String id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

}
