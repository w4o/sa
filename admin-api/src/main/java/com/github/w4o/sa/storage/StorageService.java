package com.github.w4o.sa.storage;

import com.github.w4o.sa.domain.SysStorage;
import com.github.w4o.sa.service.SysStorageService;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.InputStream;

/**
 * @author frank
 * @date 2019-05-27
 */
@Data
public class StorageService {

    private String active;
    private Storage storage;

    private final SysStorageService sysStorageService;

    public StorageService(SysStorageService sysStorageService) {
        this.sysStorageService = sysStorageService;
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public SysStorage store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        storage.store(inputStream, contentLength, contentType, key);

        String url = generateUrl(key);
        SysStorage storage = new SysStorage();
        storage.setName(fileName);
        storage.setSize((int) contentLength);
        storage.setType(contentType);
        storage.setKey(key);
        storage.setUrl(url);
        return sysStorageService.add(storage);
    }

    /**
     * 生成key
     */
    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);
        String key;
        SysStorage storage;
        do {
            key = RandomStringUtils.randomAlphanumeric(20) + suffix;
            storage = sysStorageService.findByKey(key);
        }
        while (storage != null);
        return key;
    }

    /**
     * 生成url
     */
    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
