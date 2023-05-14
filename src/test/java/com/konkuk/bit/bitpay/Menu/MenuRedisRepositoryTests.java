package com.konkuk.bit.bitpay.Menu;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.domain.MenuRedisRepository;
import com.konkuk.bit.bitpay.menu.domain.MenuType;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuRedisRepositoryTests {

    @Autowired
    private MenuRedisRepository menuRedisRepository;

    @Test
    @DisplayName("레디스 저장되는지 확인")
    public void testSaveAndGetMenu() {
        //given
        Menu menu = Menu.builder()
                .number(1L)
                .name("인싸세트(닭강정2 + 오뎅탕 + 오돌뼈 + 닭발 + 주먹밥2)")
                .price(48000)
                .remain(9999)
                .status(true)
                .type(MenuType.SET_MENU)
                .build();

        // when
        Menu savedMenu = menuRedisRepository.findById(1L).orElse(null);

        Assertions.assertNotNull(savedMenu);
        Assertions.assertEquals(menu.getNumber(), savedMenu.getNumber());
        Assertions.assertEquals(menu.getName(), savedMenu.getName());
        Assertions.assertEquals(menu.getPrice(), savedMenu.getPrice());
        Assertions.assertEquals(menu.getRemain(), savedMenu.getRemain());
        Assertions.assertEquals(menu.isStatus(), savedMenu.isStatus());
        Assertions.assertEquals(menu.getType(), savedMenu.getType());
    }

}
