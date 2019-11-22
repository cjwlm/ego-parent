package com.ego.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101700707833";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChpx2ax0dAyXBBA1C7DzQmGhRnYQqkHzFsZ7gVc/F5n61D6ehqjh210Y6Zym5476+3uLkHADIACDzJY3QxW1zgnvaAEQ+h5NiSOqO2T4Tu6QAjkRhoHO1OZtLh5t8G9YbesYET5Ey0xlLW++aFz06Fbt/9l/MQvcvD/GKXKuYktSn4HYrtJaYyraGTiTWRAb0dflyXTpqQojxKHFfxiMUV5GGXYb7gxUl3tOTi11f6OeXmKznivHBWQ2ogzH3kQb5QazNL7kj7HcwcLwXsVdAErOAxlb9UmihP9fENTcm6HulzU0rTYsC/n9bp3RBQL9N2opLbFEbCYEQvz8i9EhiDAgMBAAECggEAEfyCMsKRjwfa0aEIzod2ifbE5mvJv1EpMqKnvNtVtu6pXLEeBx0g+SYD8+CuQg6JQqvvCnRfG9nOyamjOAquXt/RFZbQYncQEdUsehCG/f424b43NP5i+SI/vJrvndqb0xN1BT2Vu1eFyB3nHGRgnEAl9BWAGhDEJ15hqehACz1f07i90OfB2EiH9OIkS9CoHaDDFw3qnN+qzMXuqAvDTsCd9it5E1STsNBP13EcWJLtYMLci1nU58bR02dIU5osPxyMudqm5pTR9BJpe3qOBEPMdqtVx5BC/h3PIW0qqZ0ndbbUyNNyuHwjdaI41YY9ExtpdxJJkmr9o1ZQPjwo4QKBgQDOgi3HrwduN891+qlGdnKuiHvOcaoAuSx6dg3uqwVhsA4mYWdWZlMvQ82gN5zhyAbbttBiEpOUurXcZYOnaYN3ZInlmgxeyW1YaheI8466XeNbU7QHAIubNxDqhWj2eGOi3SQ4CYe8Fy5ypfWKHuLwgDwzyTECAorwJ/8r5UdE3QKBgQDIZOzCe5H5oOkdgTWNt/HrrP4JTwiq4DoWNcS05Yh3ZAOQfj+GbnOKfwg+64LKHOE+A4hvraveOCPBVLu3n95wuUxQsysNHgoGsTyBrhGZag8BVvAHJaTcExvjl1SaSS4/muDEcRjShw+n46b7LYqVZinTraGGldNcO+pTYUHM3wKBgQCgRtcjQUyCFdz103toDeyKW7HDk+J/Nzk3PsMndyLxI8SHs1eR7k8nwvcoH6GqdyYjhysKwyI58APQMBXTbIg1tRl1029AbhNo6Mala2teaBpauodiW+D+sel2K99AVEX4lKxUtcjcih1prDb7YWd/g/e1tbRMvA57wH9C4WvEHQKBgF4GbMh77mwwQfDkFVqfelVYYAHCIZD7kIWnSNqP8E4EkV+UvP24iWHsZNQQ8NcgYdcVFUlBNakYsj4eIc3wpxBBATvENWj8NTwaoQc/w2Y2S0AXqJvR396a4LUS5lFWEOsrIldJsZnmYUQnHswv/BxJSUrW5zkcx4U5X6MruRIFAoGBAMgfs+TcqypnVMFvMYuWrC8W578Rx7IdYxAKXiMbEQHewCFwxFkk/R5ssmAjPYYmM9ZF6Bl45Tsg923/msVKx9Wep9cP6XZw0QjWNLs6B0qS2XxEPrjtzfbTvX0YPv44i8k+YlxnVXBlGuTAMtRshsDqG645oI7CFYtnhZ2GIt7w";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoacdmsdHQMlwQQNQuw80JhoUZ2EKpB8xbGe4FXPxeZ+tQ+noao4dtdGOmcpueO+vt7i5BwAyAAg8yWN0MVtc4J72gBEPoeTYkjqjtk+E7ukAI5EYaBztTmbS4ebfBvWG3rGBE+RMtMZS1vvmhc9OhW7f/ZfzEL3Lw/xilyrmJLUp+B2K7SWmMq2hk4k1kQG9HX5cl06akKI8ShxX8YjFFeRhl2G+4MVJd7Tk4tdX+jnl5is54rxwVkNqIMx95EG+UGszS+5I+x3MHC8F7FXQBKzgMZW/VJooT/XxDU3Juh7pc1NK02LAv5/W6d0QUC/TdqKS2xRGwmBEL8/IvRIYgwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志路径
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
