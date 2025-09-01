package com.mall.common.nacos.congfig;

import com.mall.common.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Configuration encryption processor
 * Automatically decrypt encrypted configuration items
 */
@Slf4j
public class ConfigEncryptProcessor implements EnvironmentPostProcessor {
    
    private static final String ENCRYPT_PREFIX = "ENC(";
    private static final String ENCRYPT_SUFFIX = ")";
    
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Properties props = new Properties();
        
        environment.getPropertySources().forEach(propertySource -> {
            if (propertySource instanceof PropertiesPropertySource) {
                PropertiesPropertySource source = (PropertiesPropertySource) propertySource;
                source.getSource().forEach((key, value) -> {
                    if (value instanceof String) {
                        String strValue = (String) value;
                        if (isEncrypted(strValue)) {
                            String decryptedValue = decrypt(strValue);
                            props.put(key, decryptedValue);
                            log.debug("Decrypted configuration key: {}", key);
                        }
                    }
                });
            }
        });
        
        if (!props.isEmpty()) {
            environment.getPropertySources().addFirst(
                new PropertiesPropertySource("decryptedProperties", props)
            );
        }
    }
    
    private boolean isEncrypted(String value) {
        return StringUtils.hasText(value) 
            && value.startsWith(ENCRYPT_PREFIX) 
            && value.endsWith(ENCRYPT_SUFFIX);
    }
    
    private String decrypt(String encryptedValue) {
        String encrypted = encryptedValue.substring(
            ENCRYPT_PREFIX.length(), 
            encryptedValue.length() - ENCRYPT_SUFFIX.length()
        );
        return EncryptUtils.aesDecrypt(encrypted);
    }
}