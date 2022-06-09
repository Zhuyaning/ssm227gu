package com.utils;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CommonUtil {
    private CommonUtil() {
    }

    private static final String BASE = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random;
    private static final SnowflakeGenerator snowflakeGenerator;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
            snowflakeGenerator = new SnowflakeGenerator();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取随机字符串
     */
    public static synchronized String getRandomString(Integer len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(BASE.charAt(random.nextInt(BASE.length())));
        }
        return sb.toString();
    }

    public static synchronized long getSnowFlakeString() {
        return snowflakeGenerator.next();
    }
}
