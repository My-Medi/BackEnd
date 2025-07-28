package com.my_medi.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
public class ImageUtil {
    private static String SERVER_URI;

    @Value("${app.server.uri}")
    public void setServerUri(String SERVER_URI) {
        this.SERVER_URI = SERVER_URI + "/";
    }

    public static String appendUri(String s3URI) {
        StringBuffer stringBuffer = new StringBuffer(SERVER_URI);
        return stringBuffer.append(s3URI).toString();
    }
    public static String convertToByte(MultipartFile multipartFile) {
        try {
            return Base64.getEncoder().encodeToString(multipartFile.getBytes());
        } catch (IOException e) {
            //TODO exception 처리
            throw new RuntimeException(e);
        }
    }

    public static String prefix() {
        return SERVER_URI;
    }
}
