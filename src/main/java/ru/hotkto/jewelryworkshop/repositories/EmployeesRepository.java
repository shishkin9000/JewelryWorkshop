package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.DTOs.EmployeeDTO;
import ru.hotkto.jewelryworkshop.models.Employee;

@Repository
public interface EmployeesRepository extends GenericRepository<Employee> {
    Employee findByLogin(String login);


    @Query(nativeQuery = true,
    value = """
        select * from employees e
        where e.full_name ilike '%' || coalesce(:name, '%') || '%'
        and e.phone ilike '%' || coalesce(:phone, '%') || '%'
        and e.is_deleted = false

        """)
    Page<Employee> searchEmployees(@Param(value = "name") String name,
                                      @Param(value = "phone") String phone,
                                      Pageable pageable);
}
