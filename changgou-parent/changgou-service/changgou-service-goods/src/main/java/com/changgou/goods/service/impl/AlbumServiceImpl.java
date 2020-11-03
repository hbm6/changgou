package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {


    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改 album
     *
     * @param album
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }

    /**
     * 添加album
     *
     * @param album
     */
    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件查询
     *
     * @param album
     * @return
     */
    @Override
    public List<Album> findList(Album album) {
        Example example = creatExample(album);
        return albumMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Album> albums = albumMapper.selectAll();
        return new PageInfo<Album>(albums);
    }

    /**
     * 根据条件分页查询
     *
     * @param album
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = creatExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        return new PageInfo<Album>(albums);
    }

    //抽取条件查询
    public Example creatExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            //如果 id不等空 SELECT count(0) FROM tb_brand WHERE (id = ?)
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            //如果image不等空SELECT count(0) FROM tb_brand WHERE (id = ?and image=?)
            if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            // 如果标题不等空SELECT count(0) FROM tb_brand WHERE (id = ?and image=? and title like %?%)
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            // 如果标题不等空SELECT count(0) FROM tb_brand WHERE (id = ?and image=? and title like %?%and imageItems = ?)
            if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }
}
