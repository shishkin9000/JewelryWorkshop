package ru.hotkto.jewelryworkshop.mappers;

import ru.hotkto.jewelryworkshop.DTOs.GenericDTO;
import ru.hotkto.jewelryworkshop.models.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> DTOs);

    List<D> toDTOs(List<E> entities);
}
