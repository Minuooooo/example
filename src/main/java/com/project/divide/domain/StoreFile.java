package com.project.divide.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class StoreFile {

    @Value("${spring.servlet.multipart.location}")
    private String fileDir; //서버 내 저장소

    //파일 경로
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    //파일 저장
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //업로드된 파일명
        String storeFileName = createFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName))); //서버에 파일 저장
        return new UploadFile(originalFilename, storeFileName);
    }

    //파일명 생성
    public String createFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //파일 구분자 추출
    public String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        return originalFilename.substring(position + 1);
    }
}
