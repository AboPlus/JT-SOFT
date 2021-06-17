package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {
    private String virtualPath; // 文件动态路径(虚拟路径)(路径的后半段)
    private String urlPath;     // 文件网络路径
    private String fileName;    // 文件名称
}
