package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.mappers.ClientMapper;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService extends GenericService<Client, ClientDTO> {

    ClientsRepository clientsRepository;

    protected ClientService(ClientsRepository clientsRepository,
                            ClientMapper clientMapper) {
        super(clientsRepository, clientMapper);
        this.clientsRepository = clientsRepository;
    }

    public Page<ClientDTO> getAll(Pageable pageable) {
        Page<Client> clientsPage = clientsRepository.findAll(pageable);
        List<ClientDTO> clientDTOList = genericMapper.toDTOs(clientsPage.getContent());
        return new PageImpl<>(clientDTOList, pageable, clientsPage.getTotalElements());
    }

    public Page<ClientDTO> searchClients(ClientDTO clientDTO, Pageable pageable) {
        Page<Client> clientsPaginated = clientsRepository.searchClients(
                clientDTO.getFullName(),
                clientDTO.getPhone(),
                pageable
        );

        List<ClientDTO> clientDTOList = genericMapper.toDTOs(clientsPaginated.getContent());
        return new PageImpl<>(clientDTOList, pageable, clientsPaginated.getTotalElements());
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        clientDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        clientDTO.setCreatedWhen(LocalDateTime.now());
        return genericMapper.toDTO(clientsRepository.save(genericMapper.toEntity(clientDTO)));
    }
}
