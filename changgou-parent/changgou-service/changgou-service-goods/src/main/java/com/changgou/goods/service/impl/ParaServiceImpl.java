package com.changgou.goods.service.impl;

import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ParaServiceImpl implements ParaService {
    @Autowired
    private ParaMapper paraMapper;
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    @Override
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        Para para = paraMapper.selectByPrimaryKey(id);
        updateParaNum(para, -1);
        paraMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Para para) {

        paraMapper.updateByPrimaryKeySelective(para);
    }

    @Override
    public void add(Para para) {
        paraMapper.insertSelective(para);
        updateParaNum(para, 1);
    }

    @Override
    public List<Para> findList(Para para) {
        Example example = creatExample(para);
        return paraMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Para> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Para> paras = paraMapper.selectAll();

        return new PageInfo<Para>(paras);
    }

    @Override
    public PageInfo<Para> findPage(Para para, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = creatExample(para);
        List<Para> paras = paraMapper.selectByExample(example);
        return new PageInfo<Para>(paras);
    }


    public void updateParaNum(Para para, int count) {
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setParaNum(template.getParaNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    public Example creatExample(Para para) {
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(para.getId())) {
            criteria.andEqualTo("id", para.getId());
        }
        if (StringUtils.isEmpty(para.getName())) {
            criteria.andLike("name", "%" + para.getName() + "%");
        }
        if (StringUtils.isEmpty(para.getOptions())) {
            criteria.andEqualTo("option", para.getOptions());
        }
        if (StringUtils.isEmpty(para.getSeq())) {
            criteria.andEqualTo("seq", para.getSeq());
        }
        if (StringUtils.isEmpty(para.getTemplateId())) {
            criteria.andEqualTo("templateId", para.getTemplateId());
        }
        return example;
    }
}
