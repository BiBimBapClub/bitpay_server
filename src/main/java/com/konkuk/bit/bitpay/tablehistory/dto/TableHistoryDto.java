package com.konkuk.bit.bitpay.tablehistory.dto;

import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TableHistoryDto {

    private Long id;
    private Integer tableNumber;
    private LocalDateTime timestamp;
    private String type;
    private String description;

    public TableHistoryDto(TableHistory source) {
        this.id = source.getId();
        this.tableNumber = source.getTableNumber();
        this.timestamp = source.getTimestamp();
        this.type = source.getType();
        this.description = source.getDescription();
    }
}
