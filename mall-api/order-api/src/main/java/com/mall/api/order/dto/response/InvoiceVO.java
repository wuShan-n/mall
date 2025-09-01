package com.mall.api.order.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
class InvoiceVO implements Serializable {
    private String invoiceTitle;
    private String invoiceTaxNumber;
    private String invoiceContent;
    private String invoiceEmail;
}