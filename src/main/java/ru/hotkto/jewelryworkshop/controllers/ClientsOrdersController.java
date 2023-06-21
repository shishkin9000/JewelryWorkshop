package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderSearchDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.services.ClientOrderService;
import ru.hotkto.jewelryworkshop.services.ClientService;
import ru.hotkto.jewelryworkshop.services.EmployeeService;
import ru.hotkto.jewelryworkshop.services.customUserDetails.CustomUserDetails;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/clientsOrders")
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
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getAll(pageRequest);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("orders", clientOrderDTOs);
        model.addAttribute("formatter", formatter);
        model.addAttribute("employeeId", ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
        return "clientsOrders/all";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "clientsOrders/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("orderForm") ClientOrderDTO clientOrderDTO,
                         @RequestParam("clientId") Long clientId) throws NotFoundException {
        clientOrderDTO.setClientDTO(clientService.getOne(clientId));
        clientOrderService.create(clientOrderDTO);
        return "redirect:/clientsOrders";
    }

    @PostMapping("/take/{employeeId}{clientOrderId}")
    public String takeOrder(@PathVariable Long employeeId,
                            @PathVariable final Long clientOrderId) throws NotFoundException {
        EmployeeDTO employeeDTO = employeeService.getOne(employeeId);
        ClientOrderDTO clientOrderDTO = clientOrderService.getOne(clientOrderId);
        clientOrderService.take(employeeDTO, clientOrderDTO);
        return "redirect:/clientsOrders";
    }

    @PostMapping("/search")
    public String searchOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               @ModelAttribute("orderSearchForm") ClientOrderSearchDTO clientOrderSearchDTO,
                               Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "created_by"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("formatter", formatter);
        if (clientOrderSearchDTO.getOrderCreationDate() == null) {
            pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
            Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getAll(pageRequest);
            model.addAttribute("orders", clientOrderDTOs);
            return "clientsOrders/all";
        }

        model.addAttribute("orders", clientOrderService.searchOrders(clientOrderSearchDTO, pageRequest));

        return "clientsOrders/all";
    }
}
