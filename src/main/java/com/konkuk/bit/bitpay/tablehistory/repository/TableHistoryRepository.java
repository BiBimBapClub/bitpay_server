package com.konkuk.bit.bitpay.tablehistory.repository;

import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableHistoryRepository extends JpaRepository<TableHistory, Integer> {

    List<TableHistory> findTableHistoriesByTableNumberOrderByTimestampDesc(Integer tableNumber);
}
