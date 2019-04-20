package com.ittzg.sftp.util.download;

import com.ittzg.sftp.util.SftpConnectionUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: ittzg
 * @CreateDate: 2019/4/14 20:42
 * @Description:
 */
public class SftpDownloadUtil {
    public static void dowmload(String ftpFilePath,String FilePath,String FileName){
        InputStream fileStream = null;
        FileOutputStream fileOutputStream = null;
        ChannelSftp sftpClient = null;
        try {
            sftpClient = SftpConnectionUtil.getConnection();

            fileStream = sftpClient.get(ftpFilePath + "/" + FileName);
            fileOutputStream = new FileOutputStream(new File(FilePath + "/" + FileName));

            byte[] bytes = new byte[1024];
            System.out.println("正在下载:"+FileName+"......");
            int c;
            while ((c = fileStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, c);
                System.out.println(c);
            }
            System.out.println(FileName+"下载成功，请前往"+FilePath+"查看.......");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (SftpException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            SftpConnectionUtil.closeConnect();
            try {
                fileStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
