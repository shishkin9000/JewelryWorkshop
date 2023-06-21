package ru.hotkto.jewelryworkshop.controllers;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.services.EmployeeService;
import ru.hotkto.jewelryworkshop.services.EmployeesPositionsService;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    EmployeeService employeeService;
    EmployeesPositionsService employeesPositionsService;

    public EmployeesController(EmployeeService employeeService,
                               EmployeesPositionsService employeesPositionsService) {
        this.employeeService = employeeService;
        this.employeesPositionsService = employeesPositionsService;
    }

    @GetMapping
    public String getAll( @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "10") int pageSize,
                          Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"fullName"));
        Page<EmployeeDTO> employeeDTOS = employeeService.getAll(pageRequest);
        model.addAttribute("employees", employeeDTOS);
        return "employees/all";
    }

    @PostMapping("/search")
    public String searchEmployee(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                @ModelAttribute("employeeSearchForm") EmployeeDTO employeeDTO,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC,"full_name"));
        model.addAttribute("employees", employeeService.searchEmployees(employeeDTO, pageRequest));
        return "employees/all";
    }

    @GetMapping("/{id}")
    public String getInfo(@PathVariable Long id,
                          Model model) throws NotFoundException {
        model.addAttribute("employee", employeeService.getOne(id));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        model.addAttribute("formatter", formatter);
        return "employees/view-info";
    }

    @GetMapping("/add")
    public String addEmployee() {
        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employeeForm")EmployeeDTO employeeDTO,
                              @RequestParam(value = "employeePosition")Long position) throws NotFoundException {
        EmployeePosition employeePosition = null;
        switch (position.intValue()) {
            case 1 -> employeePosition = employeesPositionsService.getEmployeePositionEntity(1L);
            case 2 -> employeePosition = employeesPositionsService.getEmployeePositionEntity(2L);
            case 3 -> employeePosition = employeesPositionsService.getEmployeePositionEntity(3L);
        }
        employeeService.create(employeeDTO, employeePosition);
        return "redirect:/employees";
    }

    @GetMapping("/soft-delete/{id}")
    public String softDelete(@PathVariable Long id) throws NotFoundException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        employeeService.softDelete(id, name);
        return "redirect:/employees/" + id;
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) throws NotFoundException {
        employeeService.restore(id);
        return "redirect:/employees/" + id;
    }

    @GetMapping("/hard-delete/{id}")
    public String hardDelete(@PathVariable Long id) throws NotFoundException {
        employeeService.hardDelete(id);
        return "redirect:/employees";
    }
}
