package com.konkuk.bit.bitpay.menu.service;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.domain.MenuRedisRepository;
import com.konkuk.bit.bitpay.menu.domain.MenuType;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRedisRepository menuRedisRepository;
    private final int ODOLBONE = 8;
    private final int CHICKENFOOT = 8;


    @Override
    public Menu getMenuEntity(Long menuNumber){
        return menuRedisRepository.findById(menuNumber).orElseThrow(IllegalAccessError::new);
    }
    @Override
    public MenuResponseDto getMenu(Long menuNumber) {
        Menu menu = getMenuEntity(menuNumber);
        return MenuResponseDto.builder()
                .number(menuNumber)
                .name(menu.getName())
                .remain(menu.getRemain())
                .status(menu.isStatus())
                .build();
    }

    public void updateRedis(Integer orderCount, boolean isOk, Menu menu)
    {
        menu.update(orderCount,isOk);
        menuRedisRepository.save(
                Menu.builder()
                        .number(menu.getNumber())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .remain(menu.getRemain())
                        .status(menu.isStatus())
                        .type(menu.getType())
                        .build()
        );
    }
    @Override
    @Transactional
    public boolean updateMenuRemainStatus(Long menuNumber, Integer orderCount) {
        Menu menu = getMenuEntity(menuNumber);
        final boolean orderOK = true;
        final boolean orderNotOK = false;
        int remainCount = menu.getRemain() - orderCount;

        if(remainCount > 0) //잔여량이 주문 개수 보다 클 때
            updateRedis(orderCount,orderOK,menu);
        else if(remainCount == 0) //잔여량이 주문 개수 보다 클 때
        {
            updateRedis(orderCount,orderNotOK,menu);
            if(menuNumber == ODOLBONE)
            {
                updateRedis(orderCount,orderNotOK,getMenuEntity(1L));
                updateRedis(orderCount,orderNotOK,getMenuEntity(3L));
                updateRedis(orderCount,orderNotOK,getMenuEntity(5L));
            }
            else if(menuNumber == CHICKENFOOT)
            {
                updateRedis(orderCount,orderNotOK,getMenuEntity(1L));
                updateRedis(orderCount,orderNotOK,getMenuEntity(2L));
                updateRedis(orderCount,orderNotOK,getMenuEntity(4L));
            }
        }
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
                    .number(menu.getNumber())
                    .name(menu.getName())
                    .remain(menu.getRemain())
                    .status(menu.isStatus())
                    .build();
            responseList.add(dto);
        }
        return responseList;
    }
}
