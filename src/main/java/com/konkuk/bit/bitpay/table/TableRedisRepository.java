package com.konkuk.bit.bitpay.table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRedisRepository extends CrudRepository<Table, Integer> {
    Optional<Table> findByNumber(Integer tableNumber);

}
