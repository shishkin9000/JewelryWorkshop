package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hotkto.jewelryworkshop.DTOs.FinishedItemDTO;
import ru.hotkto.jewelryworkshop.services.FinishedItemService;
import ru.hotkto.jewelryworkshop.services.GemTypesService;
import ru.hotkto.jewelryworkshop.services.MetalTypesService;

@Slf4j
@Controller
@RequestMapping("finishedItems")
public class FinishedItemsController {

    FinishedItemService finishedItemService;
    MetalTypesService metalTypesService;
    GemTypesService gemTypesService;

    public FinishedItemsController(FinishedItemService finishedItemService,
                                   MetalTypesService metalTypesService,
                                   GemTypesService gemTypesService) {
        this.finishedItemService = finishedItemService;
        this.metalTypesService = metalTypesService;
        this.gemTypesService = gemTypesService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "itemCode"));
        Page<FinishedItemDTO> finishedItems = finishedItemService.getAll(pageRequest);
        model.addAttribute("finishedItems", finishedItems);
        return "finishedItems/all";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("metalTypes", metalTypesService.getAll());
        model.addAttribute("gemTypes", gemTypesService.getAll());
        return "finishedItems/add";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("finishedItemForm") FinishedItemDTO finishedItemDTO,
                          @RequestParam("metalId") Long metalId,
                          @RequestParam("gemId") Long gemId,
                          @RequestParam(value = "photo", required = false) MultipartFile file) throws NotFoundException {
        log.info(finishedItemDTO.toString());
        if (file != null && file.getSize() > 0) {
            finishedItemService.create(finishedItemDTO, file, metalId, gemId);
        } else {
            finishedItemService.create(finishedItemDTO);
        }
        return "redirect:/finishedItems";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) throws NotFoundException {
        finishedItemService.hardDelete(id);
        return "redirect:/finishedItems";
    }

    @GetMapping("/update/{id}")
    public String deleteItem(@PathVariable Long id,
                             Model model) throws NotFoundException {
        model.addAttribute("item", finishedItemService.getOne(id));
        model.addAttribute("metalTypes", metalTypesService.getAll());
        model.addAttribute("gemTypes", gemTypesService.getAll());
        return "finishedItems/update";
    }

    @PostMapping("/update")
    public String deleteItem(@ModelAttribute("finishedItemForm") FinishedItemDTO finishedItemDTO,
                             @RequestParam("metalTypeId") Long metalTypeId,
                             @RequestParam("gemTypeId") Long gemTypeId,
                             @RequestParam(value = "photoPath", required = false) MultipartFile file)
            throws NotFoundException {
        finishedItemService.create(finishedItemDTO, file, gemTypeId, metalTypeId);
        return "redirect:/finishedItems";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id,
                       Model model) throws NotFoundException {
        FinishedItemDTO item = finishedItemService.getOne(id);
        //TODO исправить отображение файла
        model.addAttribute("item", item);
        model.addAttribute("metalTypes", metalTypesService.getAll());
        model.addAttribute("gemTypes", gemTypesService.getAll());

        return "finishedItems/info";
    }
}
