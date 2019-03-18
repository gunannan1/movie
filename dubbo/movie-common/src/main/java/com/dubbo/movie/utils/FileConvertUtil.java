package com.dubbo.movie.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 目前测试只用了本地文件，以后可以改成在文件服务器上上传与读取
 */
@Slf4j
@Data
public class FileConvertUtil {


    // 输入一个路径，然后将路径里的文件转换成字符串返回给我
    public static String getFileStrByAddress(String fileAddress){
        BufferedReader bufferedReader = null;
        try{

            InputStream is=new FileInputStream("/Users/gunannan/IdeaProjects/movie/dubbo/doc/cgs2.json");
            InputStreamReader isr=new InputStreamReader(is);
            bufferedReader=new BufferedReader(isr);

            StringBuffer stringBuffer = new StringBuffer();
            while(true){
                String lineStr = bufferedReader.readLine();
                if(lineStr == null){
                    break;
                }
                stringBuffer.append(lineStr);
            }

            isr.close();
            return stringBuffer.toString();
        }catch (Exception e){
            log.error("获取文件信息失败",e);
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        FileConvertUtil fileConvertUtil = new FileConvertUtil();
        String fileStrByAddress = fileConvertUtil.getFileStrByAddress("seats/cgs.json");

        System.out.println(fileStrByAddress);
    }

}
