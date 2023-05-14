package com.konkuk.bit.bitpay.menu.web;

import com.konkuk.bit.bitpay.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    // TODO
    // 메뉴 굳이 안내려주고 프론트에서 처리할 수 있는 처리
    private final MenuService menuService;

    @GetMapping("/")
    public void getMenuList(@RequestParam(required = false) boolean isOnlyValid ) {
        menuService.getMenuList();
    }

    @GetMapping("/{menu_id}")
    public void getMenuById(@PathVariable Long menu_id) {
        menuService.getMenu(menu_id);
    }

}
