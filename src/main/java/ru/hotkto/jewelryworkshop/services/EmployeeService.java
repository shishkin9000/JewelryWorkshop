package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.mappers.EmployeeMapper;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

import java.util.List;

@Service
public class EmployeeService extends GenericService<Employee, EmployeeDTO> {

    EmployeesRepository employeesRepository;
    protected EmployeeService(EmployeesRepository employeesRepository,
                              EmployeeMapper employeeMapper) {
        super(employeesRepository, employeeMapper);
        this.employeesRepository = employeesRepository;
    }

    public Page<EmployeeDTO> getAll(Pageable pageable) {
        Page<Employee> employeePage = employeesRepository.findAll(pageable);
        List<EmployeeDTO> employeeDTOList = genericMapper.toDTOs(employeePage.getContent());
        return new PageImpl<>(employeeDTOList, pageable, employeePage.getTotalElements());
    }




}
