package com.konkuk.bit.bitpay.menu.domain;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRedisRepository extends CrudRepository<Menu, Long> {
}
