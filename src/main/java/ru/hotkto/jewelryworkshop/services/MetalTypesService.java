package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.MetalTypeDTO;
import ru.hotkto.jewelryworkshop.mappers.MetalTypeMapper;
import ru.hotkto.jewelryworkshop.models.MetalType;
import ru.hotkto.jewelryworkshop.repositories.MetalTypesRepository;

import java.util.List;

@Service
public class MetalTypesService extends GenericService<MetalType, MetalTypeDTO> {
    MetalTypesRepository metalTypesRepository;

    protected MetalTypesService(MetalTypesRepository metalTypesRepository, MetalTypeMapper metalTypeMapper) {
        super(metalTypesRepository, metalTypeMapper);
        this.metalTypesRepository = metalTypesRepository;
    }

    public Page<MetalTypeDTO> getAll(Pageable pageable) {
        Page<MetalType> metalTypePage = metalTypesRepository.findAll(pageable);
        List<MetalTypeDTO> metalTypeDTOList = genericMapper.toDTOs(metalTypePage.getContent());
        return new PageImpl<>(metalTypeDTOList, pageable, metalTypePage.getTotalElements());
    }
}
