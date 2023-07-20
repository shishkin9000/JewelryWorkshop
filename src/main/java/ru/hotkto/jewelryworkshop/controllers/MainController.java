package ru.hotkto.jewelryworkshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.services.ClientOrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {
    ClientOrderService clientOrderService;
    public MainController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    public List<ClientOrderDTO> getAllOrders() {
        return clientOrderService.getAll();
    }
    public long getLooseOrdersAmount(){
        return getAllOrders().stream().filter(clientOrderDTO -> clientOrderDTO.getStatus().equals("свободен")).count();
    }
    public long getCompletedOrdersAmount(){
        return getAllOrders().stream().filter(clientOrderDTO -> clientOrderDTO.getStatus().equals("выдан")).count();
    }

    public long getWaitingForClientOrders() {
        return getAllOrders().stream().filter(clientOrderDTO -> clientOrderDTO.getStatus().equals("выполнен")).count();
    }
    public long getExpiredOrdersAmount() {
        return getAllOrders().stream()
                .filter(
                        clientOrderDTO -> (
                                clientOrderDTO.getDeadline().isBefore(LocalDate.now())
                                && !clientOrderDTO.getStatus().equals("выдан")
                                && !clientOrderDTO.getStatus().equals("выполнен")
                ))
                .count();
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("looseOrdersAmount", getLooseOrdersAmount());
        model.addAttribute("completedOrdersAmount", getCompletedOrdersAmount());
        model.addAttribute("expiredOrdersAmount", getExpiredOrdersAmount());
        model.addAttribute("waitingForClient", getWaitingForClientOrders());
        return "index";
    }
}
