package com.konkuk.bit.bitpay.menu.web;

import com.konkuk.bit.bitpay.menu.Menu;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menus")
public class MenuController {
    // TODO
    // 메뉴 굳이 안내려주고 프론트에서 처리할 수 있는 처리

    @GetMapping("/")
    public void getMenuList(@RequestParam(required = false) boolean isOnlyValid ) {
        // isOnlyValid -> 지금 팔 수 있는 음식 리스트
    }

    @GetMapping("/{menu_id}")
    public void getMenuById(@PathVariable Long menu_id) {

    }

    @PostMapping("/")
    public void createMenu(@ModelAttribute Menu menu) {

    }

    @PostMapping("/{menu_id}")
    public void updateMenu(@PathVariable Long menu_id) {

    }

    @DeleteMapping("/{menu_id}")
    public void deleteMenuById(@PathVariable Long menu_id) {

    }

}
