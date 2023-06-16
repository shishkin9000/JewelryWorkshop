package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.services.ClientOrderService;
import ru.hotkto.jewelryworkshop.services.ClientService;
import ru.hotkto.jewelryworkshop.services.EmployeeService;

@Controller
@RequestMapping("/orders")
public class ClientsOrdersController {

    ClientOrderService clientOrderService;
    ClientService clientService;
    EmployeeService employeeService;

    public ClientsOrdersController(ClientOrderService clientOrderService,
                                   EmployeeService employeeService,
                                   ClientService clientService) {
        this.clientOrderService = clientOrderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getAll(pageRequest);
        model.addAttribute("orders", clientOrderDTOs);
        return "orders/all";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "orders/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("orderForm") ClientOrderDTO clientOrderDTO,
                         @RequestParam("clientId") Long clientId,
                         @RequestParam("employeeId") Long employeeId) throws NotFoundException {
        clientOrderDTO.setClientDTO(clientService.getOne(clientId));
        clientOrderDTO.setEmployeeDTO(employeeService.getOne(employeeId));
        clientOrderService.create(clientOrderDTO);
        return "redirect:/orders";
    }
}
