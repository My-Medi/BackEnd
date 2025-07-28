package com.my_medi.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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


    public static String prefix() {
        return SERVER_URI;
    }
}
