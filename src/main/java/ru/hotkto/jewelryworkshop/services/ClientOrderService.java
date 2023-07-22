package ru.hotkto.jewelryworkshop.services;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderSearchDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.constants.ClientOrderStatusConstants;
import ru.hotkto.jewelryworkshop.mappers.ClientOrderMapper;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.repositories.ClientOrdersRepository;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;
import ru.hotkto.jewelryworkshop.repositories.EmployeesRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientOrderService extends GenericService<ClientOrder, ClientOrderDTO> {

    ClientOrdersRepository clientOrdersRepository;
    ClientService clientService;
    EmployeeService employeeService;
    protected ClientOrderService(ClientOrdersRepository clientOrdersRepository,
                                 ClientOrderMapper clientOrderMapper,
                                 ClientService clientService,
                                 EmployeeService employeeService) {
        super(clientOrdersRepository, clientOrderMapper);
        this.clientOrdersRepository = clientOrdersRepository;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    public Page<ClientOrderDTO> getAll(Pageable pageable) {
        Page<ClientOrder> clientsOrdersPage =  clientOrdersRepository.findAll(pageable);
        //Ниже исключили Заказы помеченные флагом "isDeleted"
        List<ClientOrderDTO> clientOrderDTOList = genericMapper.toDTOs(clientsOrdersPage.getContent()
                .stream()
                .filter(clientOrder -> !clientOrder.isDeleted()).toList());
        return new PageImpl<>(clientOrderDTOList, pageable, clientsOrdersPage.getTotalElements());
    }

    public Page<ClientOrderDTO> getAllLoose(Pageable pageable) {
        Page<ClientOrder> clientsOrdersPage =  clientOrdersRepository.searchLooseOrders(pageable);
        List<ClientOrderDTO> clientOrderDTOList = genericMapper.toDTOs(clientsOrdersPage.getContent());
        return new PageImpl<>(clientOrderDTOList, pageable, clientsOrdersPage.getTotalElements());
    }

    public Page<ClientOrderDTO> getMyOrders(EmployeeDTO employeeDTO, Pageable pageable) {
        Long employeeId = employeeDTO.getId();
        Page<ClientOrder> clientsOrdersPage =  clientOrdersRepository.getMyOrders(employeeId, pageable);
        List<ClientOrderDTO> clientOrderDTOList = genericMapper.toDTOs(clientsOrdersPage.getContent());
        return new PageImpl<>(clientOrderDTOList, pageable, clientsOrdersPage.getTotalElements());
    }

    @Override
    public ClientOrderDTO create(final ClientOrderDTO newObject) {
        newObject.setCreatedWhen(LocalDateTime.now());
        newObject.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        newObject.setStatus(ClientOrderStatusConstants.LOOSE);
        return genericMapper.toDTO(clientOrdersRepository.save(genericMapper.toEntity(newObject)));
    }

    public ClientOrderDTO update(ClientOrderDTO updatedOrder, Long clientId, Long employeeId) throws NotFoundException {
        ClientDTO clientDTO = clientService.getOne(clientId);
        EmployeeDTO employeeDTO = employeeService.getOne(employeeId);
        updatedOrder.setClientDTO(clientDTO);
        updatedOrder.setEmployeeDTO(employeeDTO);
        create(updatedOrder);
        return updatedOrder;
    }

    public ClientOrderDTO take(EmployeeDTO employeeDTO, ClientOrderDTO clientOrderDTO) {
        clientOrderDTO.setEmployeeDTO(employeeDTO);
        clientOrderDTO.setStatus(ClientOrderStatusConstants.IN_WORK);
        return genericMapper.toDTO(clientOrdersRepository.save(genericMapper.toEntity(clientOrderDTO)));
    }

    public ClientOrderDTO complete(Long clientOrderId) throws NotFoundException {
        ClientOrder clientOrder = clientOrdersRepository
                .findById(clientOrderId).orElseThrow(() -> new NotFoundException("Заказ № " + clientOrderId + " не найден"));
        clientOrder.setStatus(ClientOrderStatusConstants.DONE);
        return genericMapper.toDTO(clientOrdersRepository.save(clientOrder));
    }

    public Page<ClientOrderDTO> searchOrders(ClientOrderSearchDTO clientOrderSearchDTO, Pageable pageable) {
        LocalDate orderDateFrom = clientOrderSearchDTO.getOrderDateFrom();
        LocalDate orderDateTo = clientOrderSearchDTO.getOrderDateTo();
        String clientsName = clientOrderSearchDTO.getClientsName();
        Page<ClientOrder> ordersPaginated = clientOrdersRepository.searchOrders(
                orderDateFrom,
                orderDateTo,
                clientsName,
                pageable
        );

        List<ClientOrderDTO> clientDTOList = genericMapper.toDTOs(ordersPaginated.getContent());
        return new PageImpl<>(clientDTOList, pageable, ordersPaginated.getTotalElements());
    }

    public Page<ClientOrderDTO> searchExpiredOrders(ClientOrderSearchDTO clientOrderSearchDTO, Pageable pageable) {
        LocalDate orderDateFrom = clientOrderSearchDTO.getOrderDateFrom();
        LocalDate orderDateTo = clientOrderSearchDTO.getOrderDateTo();
        String clientsName = clientOrderSearchDTO.getClientsName();
        Page<ClientOrder> ordersPaginated = clientOrdersRepository.searchExpiredOrders(
                orderDateFrom,
                orderDateTo,
                clientsName,
                pageable
        );

        List<ClientOrderDTO> clientDTOList = genericMapper.toDTOs(ordersPaginated.getContent());
        return new PageImpl<>(clientDTOList, pageable, ordersPaginated.getTotalElements());
    }
}
