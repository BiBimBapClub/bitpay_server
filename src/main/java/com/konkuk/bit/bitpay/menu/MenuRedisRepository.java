package com.konkuk.bit.bitpay.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MenuRedisRepository extends CrudRepository<Menu, Long> {
}
