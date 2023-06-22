package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hotkto.jewelryworkshop.DTOs.FinishedItemDTO;
import ru.hotkto.jewelryworkshop.models.FinishedItem;
import ru.hotkto.jewelryworkshop.repositories.FinishedItemsRepository;

@Component
public class FinishedItemMapper extends GenericMapper<FinishedItem, FinishedItemDTO> {

    FinishedItemsRepository finishedItemsRepository;
    FinishedItemMapper(FinishedItemsRepository finishedItemsRepository, ModelMapper modelMapper) {
        super(FinishedItem.class, FinishedItemDTO.class, modelMapper);
        this.finishedItemsRepository = finishedItemsRepository;
    }
    @Override
    protected void mapSpecificFields(FinishedItem source, FinishedItemDTO destination) {

    }

    @Override
    protected void mapSpecificFields(FinishedItemDTO destination, FinishedItem source) {

    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(FinishedItem.class, FinishedItemDTO.class).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FinishedItemDTO.class, FinishedItem.class).setPostConverter(toEntityConverter());
    }
}
