package com.changgou.util;

import com.changgou.file.FastDFSFile;
import lombok.extern.log4j.Log4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 实现fast文件管理
 * 包含
 * 文件上传
 * 文件下载
 * 文件删除
 * 文件信息获取
 * storage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {
    /**
     * 加载  Tracker 连接信息
     */
    static {
        try {
            //查找classpath下的文件路径
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fastDFSFile 上传的文件信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        //创建一个Tracker访问的客户端对象TrackerClient
        TrackerClient client = new TrackerClient();

        //通过客户端访问TrackerServer服务,获取连接信息
        TrackerServer connection = client.getConnection();
        //通过链接信息可以获取storage的信息,创建storageClient链接对象储存storage的连接信息
        StorageClient storageClient = new StorageClient(connection, null);
        //通过storageClient访问storage实现文件的处理,并返回处理后的文件信息
        /*****
         *
         * upload_file()
         * 第一个参数:上传文件的字节数组
         * 第二个参数:文件的扩展名 jpg png ....
         * 第三个参数:附加参数 拍摄地址,拍摄设备....
         * @return String[] upload_file
         * String[0] upload_file 文件上传所存储的Storage 的组名字 group1
         * String[1] upload_file 文件存储到Storage的文件名字   /M00/02/04/haha.jpg
         */
        String[] upload_file = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);

        return upload_file;
    }

    /**
     * 文件的信息获取
     *
     * @param groupName      : 组名
     * @param remoteFileName : 文件完整储存路径
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient
        TrackerClient client = new TrackerClient();
        //通过TrackerClient获取链接信息
        TrackerServer connection = client.getConnection();
        //创建一个StorageClient对象把连接信息填入后获得一个完整的StorageClient
        StorageClient storageClient = new StorageClient(connection, null);
        //开始操作StorageClient处理文件
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     *
     * @param groupName               : 组名
     * @param remoteFileName:文件储存完整路径
     */
    public static InputStream downFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient
        TrackerClient client = new TrackerClient();
        //通过TrackerClient获取链接信息
        TrackerServer connection = client.getConnection();
        //创建一个StorageClient对象把连接信息填入后获得一个完整的StorageClient
        StorageClient storageClient = new StorageClient(connection, null);
        //开始操作StorageClient处理文件
        byte[] fileBytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(fileBytes);
    }

    /**
     * 删除文件
     *
     * @param groupName      : 组名
     * @param remoteFileName : 文件存储地址
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient
        TrackerClient client = new TrackerClient();
        //通过TrackerClient获取链接信息
        TrackerServer connection = client.getConnection();
        //创建一个StorageClient对象把连接信息填入后获得一个完整的StorageClient
        StorageClient storageClient = new StorageClient(connection, null);
        //开始操作StorageClient处理文件
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取Storages信息
     *
     * @param groupName 组名
     * @return Storages信息
     * @throws IOException
     */
    public static StorageServer getStorages(String groupName) throws IOException {
        TrackerClient client = new TrackerClient();
        TrackerServer connection = client.getConnection();
        return client.getStoreStorage(connection, groupName);

    }

    /**
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
        TrackerClient client = new TrackerClient();
        TrackerServer connection = client.getConnection();
        ServerInfo[] fetchStorages = client.getFetchStorages(connection, groupName, remoteFileName);
        return fetchStorages;
    }

    /**
     * 获取Tracker服务地址
     *
     * @return
     */
    public static String getTrackerUrl() {
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Tracker地址
            String ip = trackerServer.getInetSocketAddress().getHostString();
            int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
            return "http://" + ip + ":" + g_tracker_http_port;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
//        InputStream is = downFile("group1", "M00/00/00/wKjKiF-eZ6OALUe9AAAUxT_g4Kw207.jpg");
//        FileOutputStream fileOutputStream = new FileOutputStream("d:/1.jpg");
//        //定义一个缓冲区
//        byte[] bytes = new byte[1024];
//        while (is.read(bytes)!=-1){
//            fileOutputStream.write(bytes);
//        }
//        fileOutputStream.flush();
//        fileOutputStream.close();
//        is.close();
        //deleteFile("group1","M00/00/00/wKjKiF-eZ6OALUe9AAAUxT_g4Kw207.jpg");
        //ServerInfo[] group1s = getServerInfo("group1", "M00/00/00/wKjKiF-ed82AMWCEAAAUxT_g4Kw572.jpg");
        //String trackerUrl = getTrackerUrl();
        //System.out.println(trackerUrl);
    }

}
