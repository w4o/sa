package com.github.w4o.sa.storage.config;

import com.github.w4o.sa.service.SysStorageService;
import com.github.w4o.sa.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author frank
 * @date 2019-05-27
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

    private final StorageProperties properties;
    private final SysStorageService sysStorageService;

    @Autowired
    public StorageAutoConfiguration(StorageProperties properties, SysStorageService sysStorageService) {
        this.properties = properties;
        this.sysStorageService = sysStorageService;
    }

    @Bean
    public StorageService storageService() {

        final String local = "local";
        final String aliyun = "aliyun";
        final String tencent = "tencent";
        final String qiniu = "qiniu";
        StorageService storageService = new StorageService(sysStorageService);
        String active = this.properties.getActive();
        storageService.setActive(active);
        switch (active) {
            case local:
                storageService.setStorage(localStorage());
                break;
            case aliyun:
                storageService.setStorage(aliyunStorage());
                break;
            case tencent:
                storageService.setStorage(tencentStorage());
                break;
            case qiniu:
                storageService.setStorage(qiniuStorage());
                break;
            default:
                throw new RuntimeException("当前存储模式 " + active + " 不支持");
        }
        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }

    @Bean
    public AliyunStorage aliyunStorage() {
        AliyunStorage aliyunStorage = new AliyunStorage();
        StorageProperties.Aliyun aliyun = this.properties.getAliyun();
        aliyunStorage.setAccessKeyId(aliyun.getAccessKeyId());
        aliyunStorage.setAccessKeySecret(aliyun.getAccessKeySecret());
        aliyunStorage.setBucketName(aliyun.getBucketName());
        aliyunStorage.setEndpoint(aliyun.getEndpoint());
        return aliyunStorage;
    }

    @Bean
    public TencentStorage tencentStorage() {
        TencentStorage tencentStorage = new TencentStorage();
        StorageProperties.Tencent tencent = this.properties.getTencent();
        tencentStorage.setSecretId(tencent.getSecretId());
        tencentStorage.setSecretKey(tencent.getSecretKey());
        tencentStorage.setBucketName(tencent.getBucketName());
        tencentStorage.setRegion(tencent.getRegion());
        return tencentStorage;
    }

    @Bean
    public QiniuStorage qiniuStorage() {
        QiniuStorage qiniuStorage = new QiniuStorage();
        StorageProperties.Qiniu qiniu = this.properties.getQiniu();
        qiniuStorage.setAccessKey(qiniu.getAccessKey());
        qiniuStorage.setSecretKey(qiniu.getSecretKey());
        qiniuStorage.setBucketName(qiniu.getBucketName());
        qiniuStorage.setEndpoint(qiniu.getEndpoint());
        return qiniuStorage;
    }

}
