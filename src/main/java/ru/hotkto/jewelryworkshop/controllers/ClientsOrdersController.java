package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderSearchDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.constants.ClientOrderStatusConstants;
import ru.hotkto.jewelryworkshop.services.ClientOrderService;
import ru.hotkto.jewelryworkshop.services.ClientService;
import ru.hotkto.jewelryworkshop.services.EmployeeService;
import ru.hotkto.jewelryworkshop.services.customUserDetails.CustomUserDetails;
import ru.hotkto.jewelryworkshop.utils.ContextUserTaker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
    ) throws NotFoundException {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getAll(pageRequest);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("orders", clientOrderDTOs);
        model.addAttribute("formatter", formatter);
        return "clientsOrders/all";
    }

    @PostMapping("/search")
    public String searchOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               @ModelAttribute("orderSearchForm") ClientOrderSearchDTO clientOrderSearchDTO,
                               Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("formatter", formatter);
        if (Objects.isNull(clientOrderSearchDTO.getOrderDateFrom())
                && Objects.isNull(clientOrderSearchDTO.getOrderDateTo())
                && Objects.equals(clientOrderSearchDTO.getClientsName(), "")
                && !clientOrderSearchDTO.getIsDeadlineExpired()) {
            pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
            Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getAll(pageRequest);
            model.addAttribute("orders", clientOrderDTOs);
            return "clientsOrders/all";
        }
        if (clientOrderSearchDTO.getIsDeadlineExpired()) {
            model.addAttribute("orders", clientOrderService.searchExpiredOrders(clientOrderSearchDTO, pageRequest));
        } else {
            model.addAttribute("orders", clientOrderService.searchOrders(clientOrderSearchDTO, pageRequest));
        }

        return "clientsOrders/all";
    }

    @GetMapping("my-orders")
    public String getMyOrders(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            Model model
    ) throws NotFoundException {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "created_when"));
        EmployeeDTO employeeDTO = ContextUserTaker.getContextUser(employeeService, SecurityContextHolder.getContext());
        Page<ClientOrderDTO> clientOrderDTOs = clientOrderService.getMyOrders(employeeDTO, pageRequest);
//        model.addAttribute("inWork", ClientOrderStatusConstants.IN_WORK);
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("orders", clientOrderDTOs);
        return "employees/my-orders";
    }

    @GetMapping("/{id}")
    public String getInfo(@PathVariable Long id, Model model) throws NotFoundException {
        ClientOrderDTO clientOrderDTO = clientOrderService.getOne(id);
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("formatter", formatter);
        model.addAttribute("order", clientOrderDTO);
        model.addAttribute("now", now);
        return "clientsOrders/info";
    }

    @GetMapping("complete/{id}")
    public String completeOrder(@PathVariable Long id, Model model) throws NotFoundException {
        clientOrderService.complete(id);
        return "redirect:/clientsOrders";
    }

    @GetMapping("/add")
    public String addOrder(Model model) throws NotFoundException {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("defaultEmployee", employeeService.getOne(1L));
        return "clientsOrders/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("orderForm") ClientOrderDTO clientOrderDTO,
                         @RequestParam("clientId") Long clientId,
                         @RequestParam("employeeId") Long employeeId) throws NotFoundException {
        clientOrderDTO.setClientDTO(clientService.getOne(clientId));
        clientOrderDTO.setEmployeeDTO(employeeService.getOne(employeeId));
        clientOrderService.create(clientOrderDTO);
        return "redirect:/clientsOrders";
    }

    @GetMapping("/take/{clientOrderId}")
    public String takeOrder(@PathVariable Long clientOrderId) throws NotFoundException {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = Long.valueOf(customUserDetails.getUserId());
        EmployeeDTO employeeDTO = employeeService.getOne(employeeId);
        ClientOrderDTO clientOrderDTO = clientOrderService.getOne(clientOrderId);
        clientOrderService.take(employeeDTO, clientOrderDTO);
        return "redirect:/clientsOrders";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) throws NotFoundException {
        ClientOrderDTO clientOrderDTO = clientOrderService.getOne(id);
        List<ClientDTO> clients = clientService.getAll();
        List<EmployeeDTO> employees = employeeService.getAll();
        model.addAttribute("order", clientOrderDTO);
        model.addAttribute("clients", clients);
        model.addAttribute("employees", employees);
        return "clientsOrders/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("orderForm") ClientOrderDTO clientOrderDTO,
                         @RequestParam("id") Long orderId
                        ,@RequestParam("clientId") Long clientId,
                         @RequestParam("employeeId") Long employeeId
    ) throws NotFoundException {
        clientOrderDTO.setId(orderId);
        clientOrderService.update(clientOrderDTO, clientId, employeeId);
        return "redirect:/clientsOrders/" + orderId;
    }

    @GetMapping("/soft-delete/{id}")
    public String softDelete(@PathVariable("id") Long id) throws NotFoundException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        clientOrderService.softDelete(id, name);
        return "redirect:/clientsOrders/" + id;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable("id") Long id) throws NotFoundException {
        clientOrderService.restore(id);
        return "redirect:/clientsOrders/" + id;
    }
}
