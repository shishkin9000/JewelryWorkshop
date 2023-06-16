package ru.hotkto.jewelryworkshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

//    @PostMapping("/search")
//    public String searchDirectors(@RequestParam(value = "page", defaultValue = "1") int page,
//                                  @RequestParam(value = "size", defaultValue = "10") int pageSize,
//                                  @ModelAttribute("clientSearchForm") Client client,
//                                  Model model) {
//        if (StringUtils.hasText(client.getFullName()) || StringUtils.hasLength(client.getPhone())) {
//            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "fullName"));
//            model.addAttribute("clients", directorService.searchDirectors(directorDTO.getDirectorsFullName().trim(), pageRequest));
//            return "directors/all";
//        } else {
//            return "redirect:/directors";
//        }
//    }
}
