package com.jack.admin.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author crazyjack262
 */
public class MD5Util {

    /**
     * md5 32位加密
     *
     * @param plaintext 要加密的明文
     * @return
     */
    public static String encrypt32(String plaintext) {
        StringBuffer buffer = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(plaintext.getBytes("UTF-8"));

            byte[] b = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * md5 16位加密
     *
     * @param plaintext 要加密的明文
     * @return
     */
    public static String encrypt16(String plaintext) {
        return encrypt32(plaintext).substring(8, 24);
    }

    /**
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws Exception {
        return digest(input, "MD5");
    }

    private static byte[] digest(InputStream input, String algorithm) throws Exception {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();

        } catch (Exception e) {
            throw e;
        }
    }

}
