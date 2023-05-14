package com.konkuk.bit.bitpay.menu;


import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.domain.MenuRedisRepository;
import com.konkuk.bit.bitpay.menu.domain.MenuType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuRedisInitializer {


    private final static int MAX_REMAIN = 9999;

    private final MenuRedisRepository menuRedisRepository;

    @PostConstruct
    public void init() {
        menuRedisRepository.save(
                Menu.builder()
                        .number(1L)
                        .name("인싸세트(닭강정2 + 오뎅탕 + 오돌뼈 + 닭발 + 주먹밥2)")
                        .price(48000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SET_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(2L)
                        .name("너드세트1 (닭발 + 주먹밥)")
                        .price(15000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SET_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(3L)
                        .name("너드세트2 (오돌뼈 + 주먹밥)")
                        .price(25000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SET_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(4L)
                        .name("갱세트1 (닭발 + 오뎅탕 + 주먹밥)")
                        .price(25000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SET_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(5L)
                        .name("갱세트2 (오돌뼈 + 오뎅탕 + 주먹밥)")
                        .price(22000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SET_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(6L)
                        .name("닭강정")
                        .price(5000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.NOTHING)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(7L)
                        .name("어묵탕")
                        .price(9000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.SIDE_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(8L)
                        .name("오돌뼈")
                        .price(10000)
                        .remain(50)
                        .status(true)
                        .type(MenuType.SIDE_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(9L)
                        .name("닭발")
                        .price(12000)
                        .remain(50)
                        .status(true)
                        .type(MenuType.SIDE_MENU)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(10L)
                        .name("주먹밥")
                        .price(4000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.NOTHING)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(11L)
                        .name("콜라")
                        .price(2000)
                        .remain(100)
                        .status(true)
                        .type(MenuType.NOTHING)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(12L)
                        .name("사이다")
                        .price(2000)
                        .remain(100)
                        .status(true)
                        .type(MenuType.NOTHING)
                        .build()
        );

        menuRedisRepository.save(
                Menu.builder()
                        .number(13L)
                        .name("물")
                        .price(1000)
                        .remain(MAX_REMAIN)
                        .status(true)
                        .type(MenuType.NOTHING)
                        .build()
        );
    }
}
