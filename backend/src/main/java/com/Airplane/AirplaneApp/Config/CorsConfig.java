package com.Airplane.AirplaneApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // اجازه ارسال اطلاعات احراز هویت (مثلاً کوکی‌ها و توکن‌ها)
        config.setAllowCredentials(true);

        // مشخص کردن دامنه مجاز (اگر نیاز به چندین دامنه دارید، `allowedOriginPatterns` استفاده شود)
        config.addAllowedOriginPattern("*");

        // متدهای مجاز
        config.addAllowedMethod("*");

        // هدرهای مجاز
        config.addAllowedHeader("*");

        // تنظیم مقدار زمان ذخیره تنظیمات در مرورگر
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
