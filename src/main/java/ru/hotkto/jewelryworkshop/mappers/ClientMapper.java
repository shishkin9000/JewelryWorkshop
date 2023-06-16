package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.ClientDTO;
import ru.hotkto.jewelryworkshop.models.Client;
import ru.hotkto.jewelryworkshop.repositories.ClientsRepository;

@Component
public class ClientMapper extends GenericMapper<Client, ClientDTO> {

    ClientsRepository clientsRepository;

    public ClientMapper(ClientsRepository clientsRepository,
                        ModelMapper modelMapper) {
        super(Client.class, ClientDTO.class, modelMapper);
        this.clientsRepository = clientsRepository;
    }

    @Override
    protected void mapSpecificFields(Client source, ClientDTO destination) {

    }

    @Override
    protected void mapSpecificFields(ClientDTO destination, Client source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Client.class, ClientDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(ClientDTO.class, Client.class).setPostConverter(toEntityConverter());

    }
}
