package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.EmployeePositionDTO;
import ru.hotkto.jewelryworkshop.models.EmployeePosition;
import ru.hotkto.jewelryworkshop.repositories.EmployeesPositionsRepository;

@Component
public class EmployeesPositionsMapper extends GenericMapper<EmployeePosition, EmployeePositionDTO> {

    EmployeesPositionsRepository employeesPositionsRepository;

    public EmployeesPositionsMapper(ModelMapper modelMapper,
                                    EmployeesPositionsRepository employeesPositionsRepository) {
        super(EmployeePosition.class, EmployeePositionDTO.class, modelMapper);
        this.employeesPositionsRepository = employeesPositionsRepository;
    }

    @Override
    protected void mapSpecificFields(EmployeePosition source, EmployeePositionDTO destination) {

    }

    @Override
    protected void mapSpecificFields(EmployeePositionDTO destination, EmployeePosition source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(EmployeePosition.class, EmployeePositionDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(EmployeePositionDTO.class, EmployeePosition.class).setPostConverter(toEntityConverter());
    }
}
