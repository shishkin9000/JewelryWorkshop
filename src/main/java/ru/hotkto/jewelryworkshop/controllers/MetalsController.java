package ru.hotkto.jewelryworkshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hotkto.jewelryworkshop.DTOs.MetalTypeDTO;
import ru.hotkto.jewelryworkshop.services.MetalTypesService;

@Controller
@RequestMapping("/metals")
public class MetalsController {

    MetalTypesService metalTypesService;

    public MetalsController(MetalTypesService metalTypesService) {
        this.metalTypesService = metalTypesService;
    }

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Page<MetalTypeDTO> metals = metalTypesService.getAll(pageRequest);
        model.addAttribute("metals", metals);
        return "metals/all";
    }
}
