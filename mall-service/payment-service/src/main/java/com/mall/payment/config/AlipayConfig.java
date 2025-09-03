package com.mall.payment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Alipay configuration
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "payment.alipay")
public class AlipayConfig {
    
    /**
     * Alipay app ID
     */
    private String appId;
    
    /**
     * Merchant private key
     */
    private String privateKey;
    
    /**
     * Alipay public key
     */
    private String alipayPublicKey;
    
    /**
     * Gateway URL
     */
    private String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    
    /**
     * Return URL after payment
     */
    private String returnUrl;
    
    /**
     * Async notify URL
     */
    private String notifyUrl;
    
    /**
     * Sign type
     */
    private String signType = "RSA2";
    
    /**
     * Charset
     */
    private String charset = "UTF-8";
    
    /**
     * Format
     */
    private String format = "json";
}