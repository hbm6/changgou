package com.changgou.goods.service.impl;

import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category findById(Integer id) {

        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> findList(Category category) {
        Example example = creatExample(category);
        List<Category> categories = categoryMapper.selectByExample(example);

        return categories;
    }

    @Override
    public PageInfo<Category> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Category> categories = categoryMapper.selectAll();
        return new PageInfo<Category>(categories);
    }

    @Override
    public PageInfo<Category> findPage(Category category, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = creatExample(category);
        List<Category> categories = categoryMapper.selectByExample(example);
        return new PageInfo<Category>(categories);
    }

    @Override
    public List<Category> findByParentId(Integer pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);

    }

    Example creatExample(Category category) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category != null) {
            if (StringUtils.isEmpty(category.getId())) {
                criteria.andEqualTo("id", category.getId());
            }
            // 分类ID
            if (!StringUtils.isEmpty(category.getId())) {
                criteria.andEqualTo("id", category.getId());
            }
            // 分类名称
            if (!StringUtils.isEmpty(category.getName())) {
                criteria.andLike("name", "%" + category.getName() + "%");
            }
            // 商品数量
            if (!StringUtils.isEmpty(category.getGoodsNum())) {
                criteria.andEqualTo("goodsNum", category.getGoodsNum());
            }
            // 是否显示
            if (!StringUtils.isEmpty(category.getIsShow())) {
                criteria.andEqualTo("isShow", category.getIsShow());
            }
            // 是否导航
            if (!StringUtils.isEmpty(category.getIsMenu())) {
                criteria.andEqualTo("isMenu", category.getIsMenu());
            }
            // 排序
            if (!StringUtils.isEmpty(category.getSeq())) {
                criteria.andEqualTo("seq", category.getSeq());
            }
            // 上级ID
            if (!StringUtils.isEmpty(category.getParentId())) {
                criteria.andEqualTo("parentId", category.getParentId());
            }
            // 模板ID
            if (!StringUtils.isEmpty(category.getTemplateId())) {
                criteria.andEqualTo("templateId", category.getTemplateId());
            }

        }
        return example;
    }
}
