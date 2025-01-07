package com.htu.yyzx.controller;

import cn.hutool.core.util.RandomUtil;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import com.htu.yyzx.utils.MinioUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * minio文件上传控制器
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/file")
public class MinioFileController {
    @Resource
    private MinioUtils minioUtils;

    /**
     * 上传文件到 Minio 服务器，返回访问地址
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file) throws Exception {

        // 文件大小
        long size = file.getSize();
        if (size == 0) {
            return R.error(ErrorCode.OPERATION_ERROR, "禁止上传空文件");
        }

        // 文件名称
        String fileName = file.getOriginalFilename();

        // 文件后缀
        String ext = "";

        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return R.error(ErrorCode.OPERATION_ERROR, "禁止上传无后缀的文件");
        }

        ext = fileName.substring(index);

        // 文件类型
        String contentType = file.getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // 根据日期打散目录，使用 UUID 重命名文件
        String filePath = RandomUtil.randomString(32) + ext;

        log.info("文件名称：{}", fileName);
        log.info("文件大小：{}", size);
        log.info("文件类型：{}", contentType);
        log.info("文件路径：{}", filePath);

        // 上传文件到客户端
        minioUtils.upload(file, filePath);

        // 返回最终的访问路径
        return R.success(filePath, "上传成功");
    }

    /**
     * 上传文件
     *
     * @param file     文件
     * @param fileName 文件名称
     * @return {@link BaseResponse }
     */
    @GetMapping("/multipartUpload")
    public BaseResponse<String> uploadFile(@RequestParam("file") MultipartFile file, String fileName) {
        minioUtils.upload(file, fileName);
        return R.success("上传成功");

    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return {@link ResponseEntity }
     */
    @GetMapping("/dowload")
    public ResponseEntity dowloadFile(@RequestParam("fileName") String fileName) {
        return minioUtils.download(fileName);
    }

    /**
     * @param fileName 文件名称
     * @return {@link BaseResponse }
     * @Description 得到文件url
     */
    @GetMapping("/getUrl")
    public BaseResponse<HashMap> getFileUrl(@RequestParam("fileName") String fileName) {
        HashMap map = new HashMap();
        map.put("FileUrl", minioUtils.getFileUrl(fileName));
        return R.success(map);
    }
}