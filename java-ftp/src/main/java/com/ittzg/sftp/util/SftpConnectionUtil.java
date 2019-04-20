package com.ittzg.sftp.util;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: ittzg
 * @CreateDate: 2019/4/14 20:43
 * @Description:
 */
public class SftpConnectionUtil {
    private static final Long version = 1L;
    private static ChannelSftp sftpClient;
    private static Session session;
    private static Properties properties= new Properties();
    private static InputStream inputStream =null;
    static{
        inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
    }
    public static ChannelSftp getConnection() {
        try {
            // 获取配置文件中的主机Ip、端口、用户名与密码
            properties.load(inputStream);
            String hostIp = properties.getProperty("sftp.host");
            int port = Integer.parseInt(properties.getProperty("sftp.port"));
            String sftpuser = properties.getProperty("sftp.user");
            String sftppassword = properties.getProperty("sftp.password");
            //创建
            JSch jsch = new JSch();

            session = jsch.getSession(sftpuser, hostIp, port);
            session.setPassword(sftppassword);
            // 设置sftp不检查证书
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            //连接类型
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftpClient = (ChannelSftp) channel;
            System.out.println("success connection......");
            return sftpClient;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 关闭资源
     */
    public static  void closeConnect() {
        if (sftpClient != null) {
            if (sftpClient.isConnected()) {
                sftpClient.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
