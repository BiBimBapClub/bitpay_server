package com.konkuk.bit.bitpay.menu;


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
                        .build()
        );
    }
}
