package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.MetalTypeDTO;
import ru.hotkto.jewelryworkshop.models.MetalType;
import ru.hotkto.jewelryworkshop.repositories.MetalTypesRepository;

@Component
public class MetalTypeMapper extends GenericMapper<MetalType, MetalTypeDTO> {

    MetalTypesRepository metalTypesRepository;

    public MetalTypeMapper(MetalTypesRepository metalTypesRepository, ModelMapper modelMapper) {
        super(MetalType.class, MetalTypeDTO.class, modelMapper);
        this.metalTypesRepository = metalTypesRepository;
    }

    @Override
    protected void mapSpecificFields(MetalType source, MetalTypeDTO destination) {

    }

    @Override
    protected void mapSpecificFields(MetalTypeDTO destination, MetalType source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(MetalType.class, MetalTypeDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(MetalTypeDTO.class, MetalType.class).setPostConverter(toEntityConverter());
    }
}
