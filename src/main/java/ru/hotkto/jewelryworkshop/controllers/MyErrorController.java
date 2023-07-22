package ru.hotkto.jewelryworkshop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error() {
        return "errors/common-error";
    }

}
