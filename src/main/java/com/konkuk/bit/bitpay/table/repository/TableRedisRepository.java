package com.konkuk.bit.bitpay.table.repository;

import com.konkuk.bit.bitpay.table.domain.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRedisRepository extends CrudRepository<Table, String> {
    Optional<Table> findByNumber(String tableNumber);

}
