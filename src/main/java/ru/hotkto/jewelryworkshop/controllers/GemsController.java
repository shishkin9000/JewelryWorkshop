package ru.hotkto.jewelryworkshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.GemTypeDTO;
import ru.hotkto.jewelryworkshop.services.GemTypesService;

@Controller
@RequestMapping("/gems")
public class GemsController {

    GemTypesService gemTypesService;

    public GemsController(GemTypesService gemTypesService) {
        this.gemTypesService = gemTypesService;
    }

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<GemTypeDTO> gems = gemTypesService.getAll(pageRequest);
        model.addAttribute("gems", gems);
        return "gems/all";
    }

    @GetMapping("/add")
    public String add() {
        return "gems/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("gemForm") GemTypeDTO gemTypeDTO) {
        gemTypesService.create(gemTypeDTO);
        return "redirect:/gems";
    }
}
