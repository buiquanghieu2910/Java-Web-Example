# Java_Web_Example

## Configuration-Generic-Hibernate-Repository
- **Description:** Tạo 1 Repository duy nhất để dùng cho tất cả các Domain Model
- **Technology:** Servlet
- **IDE:** IntelliJ
- **Server:** Tomcat 10.1.7
- **JDK:** 17
- **Database:** MySQL (file database test có sẵn trong thư mục [database/generic-repositoy-example.sql](https://github.com/buiquanghieu2910/Java_Web_Example/blob/master/Configuration-Generic-Hibernate-Repository/database/generic-repositoy-example.sql))

## Demo_Upload_Image_Spring_Boot
- **Description:** Upload image

## Minio-Spring-Boot-Example
- **Description:** Build server upload file sử dụng Spring boot + MinIO server
- **Technology:** 
  - Docker
  - Spring boot
  - MinIO
- **Spring boot verison:** v3.0.4
- **API:**
  - Get All File: /api/minio - method: GET
  - Upload File:  /api/minio - method: POST - body: FileBean
  - Update File:  /api/minio/** - method: PUT - param: path_file
  - Delete File:  /api/minio/** - method: DELETE - param: path_file
