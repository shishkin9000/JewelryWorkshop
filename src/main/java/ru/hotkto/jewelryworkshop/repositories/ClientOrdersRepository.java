package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.models.ClientOrder;

import java.time.LocalDate;

@Repository
public interface ClientOrdersRepository extends GenericRepository<ClientOrder> {


    @Query(nativeQuery = true,
    value = """
        select * from clients_orders co
        where cast(co.created_when as date) = :orderCreationDate
            """)
    Page<ClientOrder> searchOrders(@Param(value = "orderCreationDate") LocalDate orderCreationDate,
                                   Pageable pageable);

    @Query(nativeQuery = true,
            value = """
        select * from clients_orders co
        where co.status = 'свободен'
            """)
    Page<ClientOrder> searchLooseOrders(Pageable pageable);


    @Query(nativeQuery = true,
            value = """
        select * from clients_orders co
        where co.employee_id = :employeeId
            """)
    Page<ClientOrder> getMyOrders(@Param(value = "employeeId") Long employeeId, Pageable pageable);
}
