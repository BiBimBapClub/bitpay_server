package com.konkuk.bit.bitpay.tablehistory.repository;

import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableHistoryRepository extends JpaRepository<TableHistory, Long> {

    List<TableHistory> findTableHistoriesByTableNumberOrderByTimestampDesc(Integer tableNumber);
}
