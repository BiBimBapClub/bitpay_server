package com.konkuk.bit.bitpay.menu.web;

import com.konkuk.bit.bitpay.menu.service.MenuService;
import com.konkuk.bit.bitpay.menu.web.Dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    // TODO
    // 메뉴 굳이 안내려주고 프론트에서 처리할 수 있는 처리
    private final MenuService menuService;


    @PostMapping ("/{memberId}")
    public boolean updateStatusMenuFalse(@PathVariable Long memberId) {
        return menuService.updateStatusMenuFalse(memberId);
    }
    @GetMapping("")
    public List<MenuResponseDto> getMenuList() {
        return menuService.getMenuList();
    }

    @GetMapping("/{menu_id}")
    public MenuResponseDto getMenuById(@PathVariable Long menu_id) {
        return menuService.getMenu(menu_id);
    }



}
