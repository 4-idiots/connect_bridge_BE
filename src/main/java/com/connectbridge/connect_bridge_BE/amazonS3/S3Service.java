package com.connectbridge.connect_bridge_BE.amazonS3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.servlet.multipart.location}")
    String baseDir;

    public String upload(MultipartFile img, String dirName) throws IOException {
        File uploadFile = convert(img)
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        System.out.println("make File uploadFile : " + uploadFile);
    return upload(uploadFile, dirName);
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(baseDir + "/"+file.getOriginalFilename());

        if(convertFile.createNewFile()){
            try(FileOutputStream fos = new FileOutputStream(convertFile)){
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String upload(File uploadFile, String dirName){

        String fileName = dirName + "/" +UUID.randomUUID()+uploadFile.getName();

        String uploadImgUrl = putS3(uploadFile,fileName);
        removeNewFile(uploadFile);
        return uploadImgUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 업로드에 필요한 로컬 img를 삭제한다.
    private void removeNewFile(File targetFile){
        if(targetFile.delete()){
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // s3에 있는 사진 정보를 삭제.
    public void deleteS3(String imgUrl){
            String extract = "https://cnbimg.s3.ap-northeast-2.amazonaws.com/";
            String fileName = imgUrl.substring(extract.length());

            DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
            amazonS3Client.deleteObject(request);
    }
}