package com.ego.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * 文件上传工具类
 */
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    /**
     * 文件上传
     */
    public static String fileUpload(String host, int port, String username, String password,
                                    String path, String fileName, InputStream is) {
        //判断操作是否正确
        boolean flag = false;
        //返回的文件名
        String remote = null;
        //创建ftp客户端
        FTPClient ftpClient = new FTPClient();
        try {
            //设置编码字符集
            ftpClient.setControlEncoding("UTF-8");
            //链接FTP服务器
            ftpClient.connect(host,port);
            //登录
            flag = ftpClient.login(username,password);
            if(!flag) {
                logger.error("文件服务器用户名或密码错误");
                return null;
            }
            //获取登录的状态码
            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return null;
            }
            /******************创建目录 一次只能创建一个目录**************/
            String basePath = "/";
            for (String p : path.split("/")) {
                basePath += (p + "/");
                //判断目录是否已经存在
                boolean hasPath = ftpClient.changeWorkingDirectory(basePath);
                if (!hasPath) {
                    //创建目录 一次只能创建一个目录，失败的原因都是权限问题
                    ftpClient.makeDirectory(basePath);
                }
            }

            //重新指定上传的路径
            ftpClient.changeWorkingDirectory(path);
            /*********************************************************/

            //指定上传方式为二进制方式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //得到文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("文件后缀为：" + suffix);

            remote = UUIDUtil.getUUID() + suffix;
            //remote指定上传远程服务器的文件名 local指本地的输入流
            flag = ftpClient.storeFile(remote,is);
            if (!flag) {
                logger.error("文件上传失败，可能是权限、防火墙等问题");
                return null;
            }
        } catch (IOException e) {
            logger.error("文件上传错误，错误原因：" + e.getMessage());
        }finally {
            try {
                if (is != null)
                    is.close();

                ftpClient.logout();

                if (ftpClient.isConnected())
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("文件上传错误，错误原因：" +e.getMessage());
                }
            }
            return remote;
        }

        public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream(new File("C:/Users/asus/Desktop/flower.jpg"));
        fileUpload("192.168.83.100",21,"ftpuser","shsxtego",
                "/home/ftpuser/ego/2019/09/10", "flower.jpg", is);

    }
}
