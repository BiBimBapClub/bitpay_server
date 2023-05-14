package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuUpdateDto;

import java.util.List;

public interface MenuService {

    boolean createMenu(Integer menuNumber);

    MenuResponseDto getMenu(Integer menuNumber);

    boolean updateMenuRemainStatus(Integer menuNumber, MenuUpdateDto menuUpdateDto);

    List<MenuResponseDto> getMenuList();

}
