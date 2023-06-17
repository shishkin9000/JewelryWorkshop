package ru.hotkto.jewelryworkshop.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeePositionDTO;
import ru.hotkto.jewelryworkshop.mappers.EmployeesPositionsMapper;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.repositories.EmployeesPositionsRepository;

@Service
public class EmployeesPositionsService extends GenericService<EmployeePosition, EmployeePositionDTO>{

    EmployeesPositionsRepository employeesPositionsRepository;

    protected EmployeesPositionsService(EmployeesPositionsRepository employeesPositionsRepository,
                                        EmployeesPositionsMapper employeesPositionsMapper) {
        super(employeesPositionsRepository, employeesPositionsMapper);
        this.employeesPositionsRepository = employeesPositionsRepository;
    }

    public EmployeePosition getEmployeePositionEntity(final Long id) throws NotFoundException {
        return employeesPositionsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Должность с id=" + id + " не найдена")
        );
    }
}
