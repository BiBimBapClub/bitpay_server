package com.konkuk.bit.bitpay.tablehistory;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableHistoryRepository extends JpaRepository<TableHistory, Long> {

    Optional<TableHistory> findTableHistoryByTableNumber(Integer tableNumber);
}
