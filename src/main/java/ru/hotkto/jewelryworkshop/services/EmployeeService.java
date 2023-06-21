package ru.hotkto.jewelryworkshop.services;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.mappers.EmployeeMapper;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService extends GenericService<Employee, EmployeeDTO> {

    EmployeesRepository employeesRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    protected EmployeeService(EmployeesRepository employeesRepository,
                              EmployeeMapper employeeMapper,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(employeesRepository, employeeMapper);
        this.employeesRepository = employeesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Page<EmployeeDTO> getAll(Pageable pageable) {
        Page<Employee> employeePage = employeesRepository.findAll(pageable);
        List<EmployeeDTO> employeeDTOList = genericMapper.toDTOs(employeePage.getContent());
        return new PageImpl<>(employeeDTOList, pageable, employeePage.getTotalElements());
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO, EmployeePosition employeePosition) {
        employeeDTO.setEmployeePosition(employeePosition);
        employeeDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        employeeDTO.setCreatedWhen(LocalDateTime.now());
        employeeDTO.setPassword(bCryptPasswordEncoder.encode(employeeDTO.getPassword()));
        return genericMapper.toDTO(employeesRepository.save(genericMapper.toEntity(employeeDTO)));
    }


    public Page<EmployeeDTO> searchEmployees(EmployeeDTO employeeDTO, Pageable pageable) {
        Page<Employee> employeesPaginated = employeesRepository.searchEmployees(
                employeeDTO.getFullName(),
                employeeDTO.getPhone(),
                pageable
        );

        List<EmployeeDTO> clientDTOList = genericMapper.toDTOs(employeesPaginated.getContent());
        return new PageImpl<>(clientDTOList, pageable, employeesPaginated.getTotalElements());
    }
}
