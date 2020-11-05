package com.changgou.goods.dao;

import com.changgou.goods.pojo.Category;
import tk.mybatis.mapper.common.Mapper;


public interface CategoryMapper extends Mapper<Category> {
    //根据父节点查询Pid
    //"select tb.* from tb_brand tb , tb_category_brand tcb where tb.id = tcb.brand_id and tcb.category_id=#{pid}"
//    @Select()
//    List<Category> findByParentId(Integer pid);
}
