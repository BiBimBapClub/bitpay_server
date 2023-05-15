package com.konkuk.bit.bitpay.admin;

import com.konkuk.bit.bitpay.menu.MenuRedisInitializer;
import com.konkuk.bit.bitpay.menu.domain.MenuRedisRepository;
import com.konkuk.bit.bitpay.order.repository.OrderDetailRepository;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import com.konkuk.bit.bitpay.table.TableInitializer;
import com.konkuk.bit.bitpay.table.repository.TableRedisRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final TableRedisRepository tableRedisRepository;
    private final MenuRedisRepository menuRedisRepository;

    private final TableInitializer tableInitializer;
    private final MenuRedisInitializer menuRedisInitializer;

    @Value("${spring.admin.password}")
    private String adminPassword;

    @GetMapping("/truncate")
    public void truncateDatabase(
            @RequestParam String password,
            HttpServletResponse response) throws Exception {
        if (password.equals(adminPassword)) {
            orderDetailRepository.deleteAll();
            orderRepository.deleteAll();
            tableRedisRepository.deleteAll();
            menuRedisRepository.deleteAll();

            tableInitializer.run();
            menuRedisInitializer.init();
            return;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return;
    }
}
