package com.ittzg.ftp.util;

import sun.net.ftp.FtpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: ittzg
 * @CreateDate: 2019/4/14 20:43
 * @Description:
 */
public class FtpConnectionUtil {
    private static final Long version = 1L;
    private static Properties properties= new Properties();
    private static InputStream inputStream =null;
    static{
        inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
    }
    public static FtpClient getConnection() {
        // 获取配置文件中的主机Ip、端口、用户名与密码
        try {
            properties.load(inputStream);
            String hostIp = properties.getProperty("ftp.host");
            int port = Integer.parseInt(properties.getProperty("ftp.port"));
            String ftpuser = properties.getProperty("ftp.user");
            String ftppassword = properties.getProperty("ftp.password");




        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  void closeConnect() {

    }
}
