package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuUpdateDto;

import java.util.List;

public interface MenuService {

    MenuResponseDto getMenu(Long menuNumber);

    boolean updateMenuRemainStatus(Long menuNumber, MenuUpdateDto menuUpdateDto);

    List<MenuResponseDto> getMenuList();

}
