package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.models.Employee;

@Repository
public interface EmployeesRepository extends GenericRepository<Employee> {
}
