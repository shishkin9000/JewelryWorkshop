package ru.hotkto.jewelryworkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.GemTypeDTO;
import ru.hotkto.jewelryworkshop.mappers.GemTypeMapper;
import ru.hotkto.jewelryworkshop.models.GemType;
import ru.hotkto.jewelryworkshop.repositories.GemTypesRepository;

import java.util.List;

@Service
public class GemTypesService extends GenericService<GemType, GemTypeDTO> {
    GemTypesRepository gemTypesRepository;

    protected GemTypesService(GemTypesRepository gemTypesRepository, GemTypeMapper gemTypeMapper) {
        super(gemTypesRepository, gemTypeMapper);
        this.gemTypesRepository = gemTypesRepository;
    }

    public Page<GemTypeDTO> getAll(Pageable pageable) {
        Page<GemType> gemTypePage = gemTypesRepository.findAll(pageable);
        List<GemTypeDTO> gemTypeDTOList = genericMapper.toDTOs(gemTypePage.getContent());
        return new PageImpl<>(gemTypeDTOList, pageable, gemTypePage.getTotalElements());
    }

}
