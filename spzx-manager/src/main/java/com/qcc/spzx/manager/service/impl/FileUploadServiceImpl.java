package com.qcc.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.manager.properties.MinioProperties;
import com.qcc.spzx.manager.service.FileUploadService;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: FileUploadServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/12 19:30
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;

    /**
     * @title upload
     * @description 文件上传
     * @author quchenxi
     * @date 2024/1/12 20:29
     * @param file
     * @return java.lang.String
     */
    @Override
    public String upload(MultipartFile file) {
        try {
            // 创建一个MinioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl()) // 访问minio地址
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey()) // minio的用户名密码
                            .build();

            // 若指定名称的bucket不存在，则创建
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket " + minioProperties.getBucketName() + " already exists.");
            }

            // 获取上传的文件名称，根据当前日期添加分组（minio端会创建文件夹），并使用UUID进行唯一化，例：20240101/uuid01.jpg
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            String filename = dateDir + "/" + uuid + file.getOriginalFilename();

            // 以流的方式，文件上传
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(minioProperties.getBucketName()) // 上传的bucket名称
                            .object(filename) // 上传的文件名
                            .stream(file.getInputStream(), file.getSize(), -1) // 上传的文件流，文件大小
                            .build());

            // 获取上传的文件在minio中的路径，并返回
            String url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + filename;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            throw new QccException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
