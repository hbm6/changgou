package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    /**
     * 带Selective会忽略空值
     */
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    /**
     * 根据id修改
     */
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /*
        根据条件搜索
        自定义条件搜索对象
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = creatExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page :当前页
     * @param size :每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        /**
         *
         * 第一个参数:当前页
         * 第二个参数:每页显示多少条
         * 分页实现,后面的查询紧跟集合查询
         */
        PageHelper.startPage(page, size);
        List<Brand> brands = brandMapper.selectAll();
        //封装pageinfo->list
        return new PageInfo<Brand>(brands);
    }

    @Override
    /**
     * 分页加条件搜索
     */
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索数据 name不为空则根据名字模糊搜索,letter不为空则根据letter搜索
        Example example = creatExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装PageInfo<Brand>
        return new PageInfo<Brand>(brands);
    }

    /**
     * 抽取根据条件查询方法
     * 条件构建
     */
    public Example creatExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (brand != null) {
            //brand.name!=null根据名字模糊查询 where name like '%华为%'
            if (!StringUtil.isEmpty(brand.getName())) {
                /**
                 * 第一个参数时javabean的属性名
                 * 第二个搜索的条件,占位符参数
                 */
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //brand.letter!=null根据首字母查询 where name = 'H'
            if (!StringUtil.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }

        }
        return example;
    }

}
