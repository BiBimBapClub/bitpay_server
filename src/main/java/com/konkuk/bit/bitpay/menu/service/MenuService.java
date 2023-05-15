package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;

import java.util.List;

public interface MenuService {

    Menu getMenuEntity(Long menuNumber);

    MenuResponseDto getMenu(Long menuNumber);

    boolean updateMenuRemainStatus(Long menuNumber, Integer orderCount);

    boolean isPossibleOrderQuantity(Long menuNumber, Integer orderCount);

    boolean updateStatusMenuFalse(Long menuNumber);

    List<MenuResponseDto> getMenuList();

}
