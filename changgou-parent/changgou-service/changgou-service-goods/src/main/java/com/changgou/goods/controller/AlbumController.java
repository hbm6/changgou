package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@CrossOrigin //跨域
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //根据id查询
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable("id") Long id) {
        Album byId = albumService.findById(id);

        return new Result<Album>(true, StatusCode.OK, "查询相册成功!", byId);
    }

    //查询所有
    @GetMapping()
    public Result<Album> findAll() {
        List<Album> albumList = albumService.findAll();
        return new Result<Album>(true, StatusCode.OK, "查询相册成功!", albumList);
    }

    //新增
    @PostMapping()
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "添加成功!");
    }

    //修改
    @PutMapping("/{id}")
    public Result update(@RequestBody Album album, Long id) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改成功!");
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //    多条件查询
    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        List<Album> list = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "查询成功!", list);
    }

    //分页查询
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        PageInfo<Album> albumPageInfo = albumService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "分页查询成功!", albumPageInfo);

    }

    //分页条件查询
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody Album album, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<Album> albumPageInfo = albumService.findPage(album, page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "条件分页查询成功", albumPageInfo);
    }
}
