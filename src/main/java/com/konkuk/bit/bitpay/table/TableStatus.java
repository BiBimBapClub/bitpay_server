package com.konkuk.bit.bitpay.table;

public enum TableStatus {
    CLEAN("청소완료"),
    CLEAN_REQUEST("청소요청"),
    ACTIVE("사용중");

    private final String status;

    TableStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
