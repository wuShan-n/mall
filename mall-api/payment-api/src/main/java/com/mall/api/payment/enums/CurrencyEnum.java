package com.mall.api.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Currency enumeration
 */
@Getter
@AllArgsConstructor
public enum CurrencyEnum {
    CNY("CNY", "Chinese Yuan", "¥"),
    USD("USD", "US Dollar", "$"),
    EUR("EUR", "Euro", "€"),
    GBP("GBP", "British Pound", "£"),
    JPY("JPY", "Japanese Yen", "¥"),
    HKD("HKD", "Hong Kong Dollar", "HK$"),
    SGD("SGD", "Singapore Dollar", "S$"),
    AUD("AUD", "Australian Dollar", "A$");
    
    private final String code;
    private final String name;
    private final String symbol;
    
    public static CurrencyEnum of(String code) {
        for (CurrencyEnum currency : values()) {
            if (currency.getCode().equals(code)) {
                return currency;
            }
        }
        return CNY;
    }
}
