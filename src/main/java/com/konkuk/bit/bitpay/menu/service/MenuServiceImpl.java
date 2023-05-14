package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.domain.MenuRedisRepository;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRedisRepository menuRedisRepository;

    @Override
    public Menu getMenuEntity(Long menuNumber){
        return menuRedisRepository.findById(menuNumber).orElseThrow(IllegalAccessError::new);
    }
    @Override
    public MenuResponseDto getMenu(Long menuNumber) {
        Menu menu = getMenuEntity(menuNumber);
        return MenuResponseDto.builder()
                .remain(menu.getRemain())
                .status(menu.isStatus())
                .build();
    }

    @Override
    public boolean updateMenuRemainStatus(Long menuNumber, Integer orderCount) {
        Menu menu = getMenuEntity(menuNumber);
        final boolean orderOK = true;
        final boolean orderNotOK = false;
        int remainCount = menu.getRemain() - orderCount;

        if(remainCount > 0) //잔여량이 주문 개수 보다 클 때
            menu.update(orderCount,orderOK);
        else if(remainCount == 0) //잔여량이 주문 개수 보다 클 때
            menu.update(orderCount,orderNotOK);
        else
            return false;

        return true;
    }

    @Override
    public List<MenuResponseDto> getMenuList() {
        List<Menu> menuList = (List<Menu>) menuRedisRepository.findAll();
        List<MenuResponseDto> responseList = new ArrayList<>();
        for(Menu menu : menuList) {
            MenuResponseDto dto = MenuResponseDto.builder()
                    .remain(menu.getRemain())
                    .status(menu.isStatus())
                    .build();
            responseList.add(dto);
        }
        return responseList;
    }
}
