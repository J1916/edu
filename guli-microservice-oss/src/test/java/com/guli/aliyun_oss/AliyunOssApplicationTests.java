package com.guli.aliyun_oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AliyunOssApplicationTests {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAIF7P8NZPm2ztV";
    String accessKeySecret = "hKTpPclwTGHH2BzVoBARhmIk8eJfus";
    String bucketName = "1918-file";

    /**
     * 创建存储空间
     */
    @Test
    public void testCreateBucket() {

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 测试存储空间是否存在
     */
    @Test
    void testExist() {

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        boolean exists = ossClient.doesBucketExist(bucketName);
        System.out.println(exists);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * 设置存储空间的访问权限
     */
    @Test
    public void testAccessControl() {

        // 创建OSSClient实例。并创建连接
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //设置存储空间的访问权限为：公有读写
        ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicReadWrite);

        //关闭连接
        ossClient.shutdown();
    }

}
