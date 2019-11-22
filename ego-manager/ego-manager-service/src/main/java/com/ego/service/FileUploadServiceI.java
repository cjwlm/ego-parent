package com.ego.service;

import com.ego.result.FileResult;

import java.io.InputStream;

/**
 * 文件上传service
 */
public interface FileUploadServiceI {
    /**
     * 文件上传
     */
    FileResult fileUpload(String fileName, InputStream is);
}
