package com.project.divide.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {

    private String uploadFile; //회원이 업로드한 이미지명
    private String storeFile; //서버 내부에서 관리될 이미지명
}
