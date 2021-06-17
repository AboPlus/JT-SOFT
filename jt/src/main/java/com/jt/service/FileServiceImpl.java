package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.localDir}")  // spel 表达式
    private String localDir;    // 属性动态赋值！！！
    @Value("${file.urlPath}")
    private String urlPath;

    /**
     * 业务说明：
     *      1.校验是否为图片类型
     *      2.防止恶意程序  如：木马.exe.jpg
     *      3.分文件目录存储   hash方式/date方式   此处采用date方式  yyyy/MM/dd
     *      4.重新设定文件名称UUID
     *      5.实现文件的上传
     *      6.封装VO对象之后返回
     */
    @Override
    public ImageVO upload(MultipartFile file) {
        // 1.校验图片类型  jpg|png|gif
        String fileName = file.getOriginalFilename();
        // 将字符全部转化为小写之后校验
        fileName = fileName.toLowerCase();
        // Java中\\ 当一个 \ 用，只出现一个 \ 会报错
        // matches(regex) 方法用于检测字符串是否匹配给定的正则表达式。
        if (!fileName.matches("^.+\\.(jpg|png|gif)$")) {
            return null;
        }
        // 2.防止恶意程序     通过宽度/高度进行判断(图片专有属性)
        //将文件强制转化为图片 ———— ImageIO.read()  能转换成图片有可能是图片，不能转化成图片一定不是图片
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width == 0 || height == 0) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 3.分目录存储  将当前时间格式化    /yyyy/MM/dd/    注意前后有/
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String fileDir = localDir + dateDir;
        File dirFile = new File(fileDir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 4.重新设定文件名称UUID
        // 动态生成UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 获取文件后缀
        int index = fileName.lastIndexOf('.');
        String fileType = fileName.substring(index);
        fileName = uuid + fileType;
        // 5.实现文件的上传    文件目录/文件名称 --> D:\photo\2021\06\17\UUID.jpg
        String realFilePath = fileDir + fileName;
        try {
            file.transferTo(new File(realFilePath));
            // 6.封装VO对象之后返回
            ImageVO imageVO = new ImageVO();
            // 存储文件的磁盘地址
            String virtualPath = realFilePath;
            // 存储文件的网络访问地址
            String urlPath = this.urlPath + dateDir + fileName;
            imageVO.setFileName(fileName).setVirtualPath(virtualPath).setUrlPath(urlPath);
            return imageVO;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
