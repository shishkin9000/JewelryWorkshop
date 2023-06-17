package ru.hotkto.jewelryworkshop.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.hotkto.jewelryworkshop.DTOs.GenericDTO;
import ru.hotkto.jewelryworkshop.mappers.GenericMapper;
import ru.hotkto.jewelryworkshop.models.Employee;
import ru.hotkto.jewelryworkshop.models.GenericModel;
import ru.hotkto.jewelryworkshop.repositories.GenericRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class GenericService<E extends GenericModel, D extends GenericDTO> {

    protected final GenericRepository<E> genericRepository;
    protected final GenericMapper<E, D> genericMapper;


    @SuppressWarnings("all")
    protected GenericService(GenericRepository<E> genericRepository, GenericMapper<E, D> genericMapper) {
        this.genericRepository = genericRepository;
        this.genericMapper = genericMapper;
    }

    public D create(D newObject) {
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(newObject)));
    }

    public D getOne(final Long id) throws NotFoundException {
        return genericMapper.toDTO(genericRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Record with id=" + id + " not found!")));
    }

    public List<D> getAll() {
        return genericMapper.toDTOs(genericRepository.findAll());
    }

    public D update(D updatedDTO) {
        return genericMapper.toDTO(genericRepository.save(genericMapper.toEntity(updatedDTO)));
    }

    public D softDelete(final Long id, Employee employee) throws NotFoundException {
        D objectForDelete = getOne(id);
        objectForDelete.setDeleted(true);
        objectForDelete.setDeletedWhen(LocalDateTime.now());
        objectForDelete.setDeletedBy(employee.getId() + " " +
                employee.getEmployeePosition() + " " +
                employee.getFullName());
        return objectForDelete;
    }

    public D restore(final Long id) throws NotFoundException {
        D objectForDelete = getOne(id);
        objectForDelete.setDeleted(false);
        objectForDelete.setDeletedWhen(null);
        objectForDelete.setDeletedBy(null);
        return objectForDelete;
    }

    public D hardDelete(final Long id) throws NotFoundException {
        D objectForDelete = getOne(id);
        genericRepository.deleteById(id);
        return objectForDelete;
    }
}
