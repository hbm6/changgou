package com.changgou.file;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装文件上传信息
 * 时间   time
 * 谁上传的 Author
 * 类型 type
 * 大小 size
 * 附加信息
 * 后缀
 * 文件内容->文件的字节码数组
 */
@Data
public class FastDFSFile implements Serializable {
    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名 jpg ,png
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile() {
    }

}
