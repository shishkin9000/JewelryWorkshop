package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderSearchDTO;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.constants.ClientOrderStatusConstants;
import ru.hotkto.jewelryworkshop.mappers.ClientOrderMapper;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.repositories.ClientOrdersRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientOrderService extends GenericService<ClientOrder, ClientOrderDTO> {

    ClientOrdersRepository clientOrdersRepository;
    protected ClientOrderService(ClientOrdersRepository clientOrdersRepository,
                                 ClientOrderMapper clientOrderMapper) {
        super(clientOrdersRepository, clientOrderMapper);
        this.clientOrdersRepository = clientOrdersRepository;
    }

    public Page<ClientOrderDTO> getAll(Pageable pageable) {
        Page<ClientOrder> clientsOrdersPage =  clientOrdersRepository.findAll(pageable);
        List<ClientOrderDTO> clientOrderDTOList = genericMapper.toDTOs(clientsOrdersPage.getContent());
        return new PageImpl<>(clientOrderDTOList, pageable, clientsOrdersPage.getTotalElements());
    }

    public Page<ClientOrderDTO> getAllLoose(Pageable pageable) {
        Page<ClientOrder> clientsOrdersPage =  clientOrdersRepository.searchLooseOrders(pageable);
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

    public ClientOrderDTO take(EmployeeDTO employeeDTO, ClientOrderDTO clientOrderDTO) {
        clientOrderDTO.setEmployeeDTO(employeeDTO);
        clientOrderDTO.setStatus(ClientOrderStatusConstants.IN_WORK);
        return genericMapper.toDTO(clientOrdersRepository.save(genericMapper.toEntity(clientOrderDTO)));
    }

    public Page<ClientOrderDTO> searchOrders(ClientOrderSearchDTO clientOrderSearchDTO, Pageable pageable) {
        LocalDate orderCreationDate = clientOrderSearchDTO.getOrderCreationDate();
        Page<ClientOrder> ordersPaginated = clientOrdersRepository.searchOrders(
                orderCreationDate,
                pageable
        );

        List<ClientOrderDTO> clientDTOList = genericMapper.toDTOs(ordersPaginated.getContent());
        return new PageImpl<>(clientDTOList, pageable, ordersPaginated.getTotalElements());
    }
}
