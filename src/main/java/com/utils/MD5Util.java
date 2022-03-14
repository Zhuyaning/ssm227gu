package com.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class MD5Util {//NOSONAR

    /**
     * @param text 明文
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text) {
        // 加密后的字符串
		return DigestUtil.md5Hex(text);
    }

}
