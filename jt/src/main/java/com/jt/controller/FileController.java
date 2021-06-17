package com.jt.controller;

import com.jt.service.FileService;
import com.jt.service.ItemService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     *   业务需求：删除图片
     * - 请求路径：/file/deleteFile'
     * - 请求路径：delete
     * - 请求参数：virtualPath
     * - 返回值结果：SysResult()
     */
    @DeleteMapping("/deleteFile")
    public SysResult deleteFile(String virtualPath){
        File file = new File(virtualPath);
        // 删除指定文件
        file.delete();
        return SysResult.success();
    }

    /**
     *   业务需求：实现文件上传
     * - 请求路径: http://localhost:8091/file/upload
     * - 请求类型: post
     * - 请求参数: file 二进制字节信息
     * - 返回值结果:SysResult(ImageVO对象)
     *      说明：
     *          1.MultipartFile 由Spring提供处理文件文件API
     *          2.参数file是前端中name中的属性，属性为什么，传来的参数就叫什么
     *      步骤：
     *          1.获取文件名称
     *          2.指定具体上传路径
     *          3.拼接文件的全路径
     */
    @PostMapping("/upload")
    public SysResult uploadFile(MultipartFile file) throws IOException {
        ImageVO imageVO = fileService.upload(file);
        // 如果imageVO 为null 说明文件上传失败
        return imageVO==null ? SysResult.fail() : SysResult.success(imageVO);
    }
    //demo测试
    //@PostMapping("/upload")
    /*public SysResult upload(MultipartFile file) throws IOException {
        // 1.获取文件名称  file.getOriginalFilename
        String fileName = file.getOriginalFilename();
        // 2.定义上传路径 绝对路径    要注意把\换成/，因为在Linux系统中不识别\
        String fileDir = "D:/photo/2103";
        File dirFile = new File(fileDir); //创建文件对象
        if (!dirFile.exists()) {
            //如果文件不存在，则应该创建一个新的目录
            dirFile.mkdirs();   // 创建多级目录
        }
        // 3.指定文件上传的全路径  目录/文件名称  如：D:/photo/abc.jpg
        String filePath = fileDir + "/" +fileName;
        // 4.实现文件上传
        file.transferTo(new File(filePath));
        return SysResult.success();
    }*/

}
