package com.guli.aliyun_oss.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author helen
 * @since 2019/6/28
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

	@Value("${aliyun.oss.file.endpoint}")
	private String endpoint;

	@Value("${aliyun.oss.file.accessKeyId}")
	private String keyId;

	@Value("${aliyun.oss.file.accessKeySecret}")
	private String keySecret;

	@Value("${aliyun.oss.file.bucketName}")
	private String bucketName;

	@Value("${aliyun.oss.file.fileHost}")
	private String fileHost;

	public static String END_POINT;
	public static String ACCESS_KEY_ID;
	public static String ACCESS_KEY_SECRET;
	public static String BUCKET_NAME;
	public static String FILE_HOST;

	@Override
	public void afterPropertiesSet() throws Exception {

		END_POINT = endpoint;
		ACCESS_KEY_ID = keyId;
		ACCESS_KEY_SECRET = keySecret;
		BUCKET_NAME = bucketName;
		FILE_HOST = fileHost;
	}
}
