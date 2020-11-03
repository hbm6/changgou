package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * 品牌dao
 * 使用通用mapper 继承Mapper<T> tk包下
 * 增加数据,调用Mapper.insert()
 */
@Component
public interface BrandMapper extends Mapper<Brand> {

}
