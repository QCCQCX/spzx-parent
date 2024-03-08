package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.FileUploadService;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUploadController
 * @Description: 文件上传表现层
 * @Date 2024/1/12 19:28
 * @Author quchenxi
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system")
@Tag(name = "文件上传")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title fileUpload
     * @description 文件上传
     * @author quchenxi
     * @date 2024/1/12 19:31
     */
    @Operation(summary = "文件上传")
    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) {
        String url = fileUploadService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
