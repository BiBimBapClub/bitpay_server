package com.konkuk.bit.bitpay.healthcheck;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {
    @GetMapping
    public void healthcheck(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return;
    }
}
