package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.GemTypeDTO;
import ru.hotkto.jewelryworkshop.models.GemType;
import ru.hotkto.jewelryworkshop.repositories.GemTypesRepository;

@Component
public class GemTypeMapper extends GenericMapper<GemType, GemTypeDTO> {

    GemTypesRepository gemTypesRepository;

    public GemTypeMapper(GemTypesRepository gemTypesRepository, ModelMapper modelMapper) {
        super(GemType.class, GemTypeDTO.class, modelMapper);
        this.gemTypesRepository = gemTypesRepository;
    }

    @Override
    protected void mapSpecificFields(GemType source, GemTypeDTO destination) {

    }

    @Override
    protected void mapSpecificFields(GemTypeDTO destination, GemType source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(GemType.class, GemTypeDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(GemTypeDTO.class, GemType.class).setPostConverter(toEntityConverter());
    }
}
