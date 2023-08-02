package ru.hotkto.jewelryworkshop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;

public class ThymeleafHotSwap {
    private final ThymeleafProperties thymeleafProperties;

    public ThymeleafHotSwap(ThymeleafProperties thymeleafProperties) {
        this.thymeleafProperties = thymeleafProperties;
    }

    @Value("${spring.thymeleaf.templates.root}")
    private String templatesRoot;

}
