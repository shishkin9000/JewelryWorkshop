package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

@Component
public class EmployeeMapper extends GenericMapper<Employee, EmployeeDTO>{

    EmployeesRepository employeesRepository;
    public EmployeeMapper(ModelMapper modelMapper, EmployeesRepository employeesRepository) {
        super(Employee.class, EmployeeDTO.class, modelMapper);
        this.employeesRepository = employeesRepository;
    }

    @Override
    protected void mapSpecificFields(Employee source, EmployeeDTO destination) {

    }

    @Override
    protected void mapSpecificFields(EmployeeDTO destination, Employee source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Employee.class, EmployeeDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(EmployeeDTO.class, Employee.class).setPostConverter(toEntityConverter());
    }
}
