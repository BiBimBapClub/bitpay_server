package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.Menu;
import com.konkuk.bit.bitpay.menu.MenuRedisRepository;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuUpdateDto;
import com.konkuk.bit.bitpay.table.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRedisRepository menuRedisRepository;

    @Override
    public MenuResponseDto getMenu(Long menuNumber) {
        Menu menu = menuRedisRepository.findById(menuNumber).orElseThrow(IllegalAccessError::new);
        return MenuResponseDto.builder()
                .remain(menu.getRemain())
                .status(menu.isStatus())
                .build();
    }

    @Override
    public boolean updateMenuRemainStatus(Long menuNumber, Integer orderCount) {
        Menu menu = menuRedisRepository.findById(menuNumber).orElseThrow(IllegalAccessError::new);
        
        return false;
    }

    @Override
    public List<MenuResponseDto> getMenuList() {
        return null;
    }
}
