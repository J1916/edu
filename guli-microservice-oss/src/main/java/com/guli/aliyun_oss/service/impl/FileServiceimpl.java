package com.guli.aliyun_oss.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.guli.aliyun_oss.service.FileService;
import com.guli.aliyun_oss.util.ConstantPropertiesUtil;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.util.ExceptionUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author 1916
 * @create 2020-01-12 16:47
 */
@Service
public class FileServiceimpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceimpl.class);

    //获取阿里云存储相关常量
    @Value("${aliyun.oss.file.endpoint}")
   private String endPoint;
    @Value("${aliyun.oss.file.accessKeyId}")
   private String accessKeyId ;
    @Value("${aliyun.oss.file.accessKeySecret}")
   private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketName}")
   private String bucketName ;
    @Value("${aliyun.oss.file.fileHost}")
   private String fileHost;

    @Override
    public String upload(MultipartFile file) {

        OSSClient ossClient =null;

        try {
            //创建oss客户端对象，并建立连接
           ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            //判断oss存储空间是否存在，不存在就创建，存在就获取
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName); //创建存储空间
                //设置该存储空间的访问权限为：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            InputStream inputStream = file.getInputStream();
            //文件日期

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //生成文件名：uuid.扩展名
            String oldFileName = file.getOriginalFilename(); //获取原文件文件名
            String fileName = UUID.randomUUID().toString();
            String fileType = oldFileName.substring(oldFileName.lastIndexOf("."));
            String newFileName = fileName+fileType; //新文件名
            String fileUrl = ConstantPropertiesUtil.FILE_HOST +"/"+filePath+"/"+newFileName;
            //文件上传到阿里云oss
            ossClient.putObject(bucketName,fileUrl,inputStream);
            //返回文件存储url地址
//   Bucket 域名： 1916-file.oss-cn-beijing.aliyuncs.com = bucketName+"."+endPoint
//   https://1916-file.oss-cn-beijing.aliyuncs.com/avatar/文件名
            return " https://"+bucketName+"."+endPoint+"/"+fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(ExceptionUtil.getMessage(e),"文件上传失败");
            throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        finally {
            ossClient.shutdown(); //关闭连接;
        }
    }


}
