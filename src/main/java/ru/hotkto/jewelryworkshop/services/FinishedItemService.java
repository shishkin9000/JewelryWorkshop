package ru.hotkto.jewelryworkshop.services;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hotkto.jewelryworkshop.DTOs.FinishedItemDTO;
import ru.hotkto.jewelryworkshop.constants.MiscellaneousConstants;
import ru.hotkto.jewelryworkshop.mappers.FinishedItemMapper;
import ru.hotkto.jewelryworkshop.models.FinishedItem;
import ru.hotkto.jewelryworkshop.models.GemType;
import ru.hotkto.jewelryworkshop.models.MetalType;
import ru.hotkto.jewelryworkshop.repositories.FinishedItemsRepository;
import ru.hotkto.jewelryworkshop.repositories.GemTypesRepository;
import ru.hotkto.jewelryworkshop.repositories.MetalTypesRepository;
import ru.hotkto.jewelryworkshop.utils.FileHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinishedItemService extends GenericService<FinishedItem, FinishedItemDTO> {
    FinishedItemsRepository finishedItemsRepository;
    MetalTypesRepository metalTypesRepository;
    GemTypesRepository gemTypesRepository;
    protected FinishedItemService(FinishedItemsRepository finishedItemsRepository,
                                  FinishedItemMapper finishedItemMapper,
                                  MetalTypesRepository metalTypesRepository,
                                  GemTypesRepository gemTypesRepository) {
        super(finishedItemsRepository, finishedItemMapper);
        this.finishedItemsRepository = finishedItemsRepository;
        this.metalTypesRepository = metalTypesRepository;
        this.gemTypesRepository = gemTypesRepository;
    }

    public Page<FinishedItemDTO> getAll(Pageable pageable) {
        Page<FinishedItem> finishedItemPage = finishedItemsRepository.findAll(pageable);
        List<FinishedItemDTO> finishedItemDTOList = genericMapper.toDTOs(finishedItemPage.getContent());
        return new PageImpl<>(finishedItemDTOList, pageable, finishedItemPage.getTotalElements());
    }

    public FinishedItemDTO create(final FinishedItemDTO newItem, Long metalId, Long gemId) throws NotFoundException {
        GemType gemType = gemTypesRepository.findById(gemId)
                .orElseThrow(() -> new NotFoundException("Камень с id=" + gemId + " не найден"));
        MetalType metalType = metalTypesRepository.findById(metalId)
                .orElseThrow(() -> new NotFoundException("Металл с id=" + metalId + " не найден"));
        List<GemType> gemTypeList = new ArrayList<>();
        List<MetalType> metalTypeList = new ArrayList<>();
        metalTypeList.add(metalType);
        gemTypeList.add(gemType);
        newItem.setGemTypes(gemTypeList);
        newItem.setMetalTypes(metalTypeList);
        newItem.setCreatedWhen(LocalDateTime.now());
        newItem.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return genericMapper.toDTO(finishedItemsRepository.save(genericMapper.toEntity(newItem)));
    }

    public FinishedItemDTO create(final FinishedItemDTO newItem, MultipartFile file, Long metalId, Long gemId) throws NotFoundException {
        String fileName = FileHelper.createFile(file, MiscellaneousConstants.FINISHED_ITEMS_DIRECTORY);
        GemType gemType = gemTypesRepository.findById(gemId)
                .orElseThrow(() -> new NotFoundException("Камень с id=" + gemId + " не найден"));
        MetalType metalType = metalTypesRepository.findById(metalId)
                .orElseThrow(() -> new NotFoundException("Металл с id=" + metalId + " не найден"));
        List<GemType> gemTypeList = new ArrayList<>();
        List<MetalType> metalTypeList = new ArrayList<>();
        metalTypeList.add(metalType);
        gemTypeList.add(gemType);
        newItem.setPhotoPath(fileName);
        newItem.setGemTypes(gemTypeList);
        newItem.setMetalTypes(metalTypeList);
        newItem.setCreatedWhen(LocalDateTime.now());
        newItem.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return genericMapper.toDTO(finishedItemsRepository.save(genericMapper.toEntity(newItem)));
    }

}
