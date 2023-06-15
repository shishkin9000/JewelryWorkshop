package ru.hotkto.jewelryworkshop.configurations;

import java.util.List;

public interface SecurityConstants {

    List<String> RESOUCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "webjars/bootstrap/5.3.0/**"
    );
}
