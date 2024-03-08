package com.qcc.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUploadService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/12 19:29
 * @Author quchenxi
 * @Version 1.0
 */
public interface FileUploadService {
    /**
     * @title upload
     * @description 文件上传
     * @author quchenxi
     * @date 2024/1/12 20:29
     * @param file
     * @return java.lang.String
     */
    String upload(MultipartFile file);
}
