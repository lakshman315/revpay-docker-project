package com.revpay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    // Constructor injection via Lombok
    private final IdempotencyInterceptor idempotencyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Applies only to financial transaction endpoints
        registry.addInterceptor(idempotencyInterceptor)
                .addPathPatterns(
                        "/api/wallet/send",
                        "/api/wallet/add-funds",
                        "/api/wallet/withdraw",
                        "/api/wallet/pay-invoice"
                );
    }
}