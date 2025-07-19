package com.my_medi.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    public static String convertToByte(MultipartFile multipartFile) {
        try {
            return Base64.getEncoder().encodeToString(multipartFile.getBytes());
        } catch (IOException e) {
            //TODO exception 처리
            throw new RuntimeException(e);
        }
    }
}
