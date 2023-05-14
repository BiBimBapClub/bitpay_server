package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public boolean createMenu(Integer menuNumber) {
        return false;
    }

    @Override
    public MenuResponseDto getMenu(Integer menuNumber) {
        return null;
    }

    @Override
    public boolean updateMenuRemainStatus(Integer menuNumber, MenuUpdateDto menuUpdateDto) {
        return false;
    }

    @Override
    public List<MenuResponseDto> getMenuList() {
        return null;
    }
}
