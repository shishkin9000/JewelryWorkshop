package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.models.Client;
@Repository
public interface ClientsRepository extends GenericRepository<Client> {

    @Query(nativeQuery = true,
    value = """
        select * from clients c
        where c.full_name ilike '%' || coalesce(:fullName, '%') || '%'
        and c.phone ilike '%' || coalesce(:phone, '%') || '%'
        and c.is_deleted = false
        """)
    Page<Client> searchClients(@Param(value = "fullName") String fullName,
                               @Param(value = "phone") String phone,
                               Pageable pageable);


}
