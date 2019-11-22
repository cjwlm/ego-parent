package com.ego.service.impl;

import com.ego.result.FileResult;
import com.ego.service.FileUploadServiceI;
import com.ego.util.DateUtil;
import com.ego.util.FTPUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * 文件上传service
 */
@Service
public class FileUploadServiceImpl implements FileUploadServiceI {

    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private int ftpPort;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.path}")
    private String ftpPath;

    /**
     * 文件上传
     * @param fileName
     * @param is
     * @return
     */
    @Override
    public FileResult fileUpload(String fileName, InputStream is) {
        //修改文件上传路径， 修改为： /home/ftpuser/ego/年/月/日
        String dateStr = DateUtil.getDateStr(LocalDateTime.now(),DateUtil.pattern_date);
        //最终文件上传路径
        String basePath = ftpPath + dateStr;
        //文件上传，返回上传成功的文件名
        String result = FTPUtil.fileUpload(ftpHost, ftpPort,ftpUsername,ftpPassword,basePath,fileName,is);
        //判断
        FileResult fileResult = new FileResult();
        if (null == result || result.length() <1) {
            fileResult.setMessage("文件上传失败");
            fileResult.setError("error");
            return fileResult;
        }

        fileResult.setMessage("文件上传成功");
        fileResult.setSuccess("success");
        //修改返回的文件地址， 修改为：
        String fileUrl = "http://" + ftpHost + "/" + dateStr + "/" + result;
        fileResult.setFileUrl(fileUrl);
        return fileResult;
    }
}
