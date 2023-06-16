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

    protected final GenericRepository<E> repository;
    protected final GenericMapper<E, D> mapper;


    protected GenericService(GenericRepository<E> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public D create(D newObject) {
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public D getOne(final Long id) throws NotFoundException {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Record with id=" + id + " not found!")));
    }

    public List<D> getAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public D update(D updatedDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(updatedDTO)));
    }

    public D softDelete(final Long id, Employee employee) throws NotFoundException {
        D objectForDelete = getOne(id);
        objectForDelete.setDeleted(true);
        objectForDelete.setDeletedWhen(LocalDateTime.now());
        objectForDelete.setDeletedBy(employee.getId() + " " +
                employee.getEmployeePosition() + " " +
                employee.getFirstName() + " " +
                employee.getLastName());
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
        repository.deleteById(id);
        return objectForDelete;
    }
}
