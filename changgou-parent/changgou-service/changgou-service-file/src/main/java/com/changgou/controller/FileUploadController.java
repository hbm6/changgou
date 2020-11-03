package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    /***
     *  文件上传
     */
    @PostMapping()
    public Result upload(@RequestParam MultipartFile file) throws Exception {
        //封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字 1.jpg
                file.getBytes(),    //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename())//获取文件后缀名
        );
        //调用FastDFSUtil工具类将文件上传到FastDFS中
        String[] upload = FastDFSUtil.upload(fastDFSFile);
        //拼接访问地址 url="http://192.168.202/136:8080/group1/M00/00/00/wdjhwajdhasakjdka.jpeg"              服务器的地址nginx的端口
        //
        //String url= "http://192.168.202.136:8080/"+upload[0]+"/"+upload[1];
        String url = FastDFSUtil.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
