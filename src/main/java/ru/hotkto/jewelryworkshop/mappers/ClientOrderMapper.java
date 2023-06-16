package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.repositories.ClientOrdersRepository;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

@Component
public class ClientOrderMapper extends GenericMapper<ClientOrder, ClientOrderDTO> {

    ClientOrdersRepository clientOrdersRepository;
    ClientsRepository clientsRepository;
    EmployeesRepository employeesRepository;
    ClientMapper clientMapper;
    EmployeeMapper employeeMapper;


    public ClientOrderMapper(ModelMapper modelMapper,
                             ClientOrdersRepository clientOrdersRepository,
                             ClientsRepository clientsRepository,
                             EmployeesRepository employeesRepository,
                             ClientMapper clientMapper,
                             EmployeeMapper employeeMapper) {
        super(ClientOrder.class, ClientOrderDTO.class, modelMapper);
        this.clientOrdersRepository = clientOrdersRepository;
        this.employeesRepository = employeesRepository;
        this.clientsRepository = clientsRepository;
        this.clientMapper = clientMapper;
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(ClientOrder.class, ClientOrderDTO.class)
                .addMappings(mapping -> {
                    mapping.skip(ClientOrderDTO::setClientDTO);
                    mapping.skip(ClientOrderDTO::setEmployeeDTO);

                }).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(ClientOrderDTO.class, ClientOrder.class)
                .addMappings(mapping -> {
                    mapping.skip(ClientOrder::setClient);
                    mapping.skip(ClientOrder::setEmployee);
                }).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ClientOrder source, ClientOrderDTO destination) {
        destination.setClientDTO(clientMapper.toDTO(source.getClient()));
        destination.setEmployeeDTO(employeeMapper.toDTO(source.getEmployee()));
    }

    @Override
    protected void mapSpecificFields(ClientOrderDTO source, ClientOrder destination) {
        Long clientId = source.getClientDTO().getId();
        Long employeeId = source.getEmployeeDTO().getId();
        Client client = clientsRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client with id=" + clientId + " not found"));
        Employee employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee with id=" + employeeId + " not found"));
        destination.setClient(client);
        destination.setEmployee(employee);
    }
}
