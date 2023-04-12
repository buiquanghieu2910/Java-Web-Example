package com.example.services;

import com.example.beans.FileBean;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MinioService {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Value("${base_url}")
    private String baseURL;

    /**
     * GetListObjects
     *
     * @return List<FileBean>
     */
    public ResponseEntity<List<FileBean>> getListObjects() {
        List<FileBean> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                objects.add(FileBean.builder()
                        .filename(item.get().objectName())
                        .size(item.get().size())
                        .url(getPreSignedUrl(item.get().objectName()))
                        .build());
            }
            return ResponseEntity.ok(objects);
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GetPreSignedUrl
     *
     * @param filename
     * @return String
     */
    private String getPreSignedUrl(String filename) {
        return baseURL + "/minio/".concat(filename);
    }

    /**
     * ResponseEntity
     *
     * @param fileBean
     * @return ResponseEntity<FileBean>
     */
    public ResponseEntity<FileBean> uploadFile(FileBean fileBean) {
        try {
            this.createBucketWithNotExist();
            String fileName = fileBean.getFile().getName() + "-" + System.currentTimeMillis() + "-" + Math.round(Math.random() * 1E9)
                    + "." + FilenameUtils.getExtension(fileBean.getFile().getOriginalFilename());
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(fileBean.getFile().getInputStream(), fileBean.getFile().getSize(), -1)
                    .build());

            return ResponseEntity.ok(FileBean.builder()
                    .title(fileBean.getTitle())
                    .description(fileBean.getDescription())
                    .size(fileBean.getFile().getSize())
                    .url(getPreSignedUrl(fileName))
                    .filename(fileName)
                    .build());
        } catch (Exception e) {
            log.error("Happened error when upload file: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GetObject
     *
     * @param filename
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> getObject(String filename) {
        try {
            InputStream stream;
            stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(IOUtils.toByteArray(stream));
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DeleteObject
     *
     * @param filename
     * @return Boolean
     */
    public Boolean deleteObject(String filename) {
        boolean del = false;
        try {
            minioClient.deleteObjectTags(DeleteObjectTagsArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build());
            del = true;
        } catch (Exception e) {
            log.error("Happened error when get list objects from minio: ", e);
        }
        return del;
    }

    public void createBucketWithNotExist() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

}