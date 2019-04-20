package com.ittzg.sftp;

import com.ittzg.sftp.util.SftpConnectionUtil;
import com.ittzg.sftp.util.download.SftpDownloadUtil;
import com.jcraft.jsch.ChannelSftp;
import org.junit.Test;

/**
 * @Author: ittzg
 * @CreateDate: 2019/4/14 20:40
 * @Description:
 */
public class TestSftp {
    @Test
    public void testConn(){
        ChannelSftp connection = SftpConnectionUtil.getConnection();
        System.out.println(connection.toString());
    }
    @Test
    public void testSftpSpwnLoad(){
        SftpDownloadUtil.dowmload("/usr/soft","D:/","apache-tomcat-7.0.69.tar.gz");
    }

}
