package com.mall.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Encryption utility class
 */
public class EncryptUtils {
    
    private static final String DEFAULT_AES_KEY = "mall@2024AESKey!";
    private static final AES aes = SecureUtil.aes(DEFAULT_AES_KEY.getBytes(StandardCharsets.UTF_8));
    
    private EncryptUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * MD5 encryption
     */
    public static String md5(String str) {
        return SecureUtil.md5(str);
    }
    
    /**
     * SHA256 encryption
     */
    public static String sha256(String str) {
        return SecureUtil.sha256(str);
    }
    
    /**
     * AES encrypt
     */
    public static String aesEncrypt(String content) {
        return aes.encryptHex(content);
    }
    
    /**
     * AES decrypt
     */
    public static String aesDecrypt(String encryptHex) {
        return aes.decryptStr(encryptHex);
    }
    
    /**
     * Base64 encode
     */
    public static String base64Encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Base64 decode
     */
    public static String base64Decode(String base64Str) {
        byte[] bytes = Base64.getDecoder().decode(base64Str);
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    /**
     * Generate salt
     */
    public static String generateSalt() {
        return cn.hutool.core.util.RandomUtil.randomString(16);
    }
    
    /**
     * Password encryption with salt
     */
    public static String encryptPassword(String password, String salt) {
        return sha256(password + salt);
    }
    
    /**
     * Verify password
     */
    public static boolean verifyPassword(String password, String salt, String encryptedPassword) {
        return encryptedPassword.equals(encryptPassword(password, salt));
    }
}