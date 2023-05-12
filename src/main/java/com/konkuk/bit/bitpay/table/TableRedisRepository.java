package com.konkuk.bit.bitpay.table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRedisRepository extends CrudRepository<Table, Long> {

}
