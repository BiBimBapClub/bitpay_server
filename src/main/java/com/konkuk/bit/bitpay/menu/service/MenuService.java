package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.Menu;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuUpdateDto;

import java.util.List;

public interface MenuService {

    Menu getMenuEntity(Long menuNumber);

    MenuResponseDto getMenu(Long menuNumber);

    boolean updateMenuRemainStatus(Long menuNumber, Integer orderCount);

    List<MenuResponseDto> getMenuList();

}
