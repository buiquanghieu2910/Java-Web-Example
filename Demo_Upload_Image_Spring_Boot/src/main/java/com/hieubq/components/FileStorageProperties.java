/**
 * 2023
 * FileStorageProperties.java, BUI QUANG HIEU
 */
package com.hieubq.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author BUI_QUANG_HIEU
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
