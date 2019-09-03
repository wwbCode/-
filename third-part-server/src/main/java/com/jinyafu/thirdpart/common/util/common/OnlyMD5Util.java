package com.jinyafu.thirdpart.common.util.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OnlyMD5Util {
    public OnlyMD5Util() {
    }

    public static String md5L32(String text) {
        return md5L32(text, (String)null);
    }

    public static String md5L32(String text, String charset) {
        String result = null;
        if (null != text && !"".equals(text)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] btInput = null == charset ? text.getBytes() : text.getBytes(charset);
                md.update(btInput);
                byte[] btResult = md.digest();
                StringBuffer sb = new StringBuffer();
                byte[] var7 = btResult;
                int var8 = btResult.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    byte b = var7[var9];
                    int bt = b & 255;
                    if (bt < 16) {
                        sb.append(0);
                    }

                    sb.append(Integer.toHexString(bt));
                }

                result = sb.toString();
            } catch (NoSuchAlgorithmException var12) {
                var12.printStackTrace();
            } catch (UnsupportedEncodingException var13) {
                var13.printStackTrace();
            }
        } else {
            result = text;
        }

        return result;
    }

    public static String md5U32(String text) {
        String result = md5L32(text);
        if (null != result && !"".equals(result)) {
            result = result.toUpperCase();
        }

        return result;
    }

    public static String md5U32(String text, String charset) {
        String result = md5L32(text, charset);
        if (null != result && !"".equals(result)) {
            result = result.toUpperCase();
        }

        return result;
    }

    public static String md5L16(String text) {
        String value = md5L32(text);
        return null == value ? "" : value.substring(8, 24);
    }

    public static String md5U16(String text) {
        String value = md5U32(text);
        return null == value ? "" : value.substring(8, 24);
    }
}

