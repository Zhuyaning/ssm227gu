package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 类说明 :
 */

public class FileUtil {
    private FileUtil() {
    }

    public static byte[] fileToByte(File file) throws IOException {
        // 将数据转为流
        @SuppressWarnings("resource")
        InputStream content = Files.newInputStream(file.toPath());
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1000];
        int rc = 0;
        while ((rc = content.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        // 获得二进制数组
        return swapStream.toByteArray();
    }
}
