package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;
import ru.hotkto.jewelryworkshop.services.ClientService;

@Controller
@Slf4j
@RequestMapping("/clients")
public class ClientsController {

    ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"fullName"));
        Page<ClientDTO> clients = clientService.getAll(pageRequest);
        model.addAttribute("clients", clients);
        return "clients/all";
    }

    @PostMapping("/search")
    public String searchClients(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                  @ModelAttribute("clientSearchForm") ClientDTO clientDTO,
                                  Model model) {
            PageRequest pageRequest = PageRequest.of(page - 1,
                                                    pageSize, Sort.by(Sort.Direction.ASC,
                                         "full_name"));
            model.addAttribute("clients", clientService.searchClients(clientDTO, pageRequest));
            return "clients/all";
    }

    @GetMapping("/add")
    public String addClient() {
        return "clients/add";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute("clientForm") ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String viewInfo(@PathVariable Long id,
                           Model model) throws NotFoundException {
        ClientDTO clientDTO = clientService.getOne(id);
        model.addAttribute("client", clientDTO);
        return "clients/view-info";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         Model model) throws NotFoundException {
        ClientDTO clientDTO = clientService.getOne(id);
        model.addAttribute("client", clientDTO);
        return "clients/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("clientForm") ClientDTO clientDTO) {
        clientService.update(clientDTO);
        return "redirect:/clients";
    }

    @GetMapping("/soft-delete/{id}")
    public String softDelete(@PathVariable Long id) throws NotFoundException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        clientService.softDelete(id, name);
        return "redirect:/clients/" + id;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) throws NotFoundException {
        clientService.restore(id);
        return "redirect:/clients/" + id;
    }
}
