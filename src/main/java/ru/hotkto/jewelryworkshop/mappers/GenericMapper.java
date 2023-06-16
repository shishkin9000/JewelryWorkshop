package ru.hotkto.jewelryworkshop.mappers;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import ru.hotkto.jewelryworkshop.DTOs.GenericDTO;
import ru.hotkto.jewelryworkshop.models.GenericModel;

import java.util.List;
import java.util.Objects;

public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO> implements Mapper<E, D> {

    private final Class<E> entityClass;

    private final Class<D> DTOclass;

    protected final ModelMapper modelMapper;

    public GenericMapper(Class<E> entityClass, Class<D> DTOclass, ModelMapper modelMapper) {
        this.entityClass = entityClass;
        this.DTOclass = DTOclass;
        this.modelMapper = modelMapper;
    }

    @Override
    public E toEntity(D DTO) {
        return Objects.isNull(DTO)
                ? null
                : modelMapper.map(DTO, entityClass);
    }

    @Override
    public D toDTO(E entity) {
        return Objects.isNull(entity)
                ? null
                :modelMapper.map(entity, DTOclass);
    }

    @Override
    public List<E> toEntities(List<D> DTOs) {
        return DTOs.stream().map(this::toEntity).toList();
    }

    @Override
    public List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    protected Converter<E, D> toDTOConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificFields(E source, D destination);
    protected abstract void mapSpecificFields(D destination, E source);

    @PostConstruct
    protected abstract void setupMapper();
}
