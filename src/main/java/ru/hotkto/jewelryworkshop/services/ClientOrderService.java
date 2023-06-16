package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.ClientOrderDTO;
import ru.hotkto.jewelryworkshop.mappers.ClientOrderMapper;
import ru.hotkto.jewelryworkshop.models.ClientOrder;
import ru.hotkto.jewelryworkshop.repositories.ClientOrdersRepository;

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

    @Override
    public ClientOrderDTO create(final ClientOrderDTO newObject) {
        newObject.setCreatedWhen(LocalDateTime.now());
        newObject.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return genericMapper.toDTO(clientOrdersRepository.save(genericMapper.toEntity(newObject)));
    }
}
