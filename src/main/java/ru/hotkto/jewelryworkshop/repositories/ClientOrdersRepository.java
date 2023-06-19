package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.models.ClientOrder;

@Repository
public interface ClientOrdersRepository extends GenericRepository<ClientOrder> {


    @Query(nativeQuery = true,
    value = """
    select * from clients_orders
    where  = coalesce(:employeeId, '%')
    and client_id = coalesce(:clientId, '%')
    and is_completed = :isCompleted;
            """)
    Page<ClientOrder> searchOrders(@Param(value = "employeeId") String employeeName,
                                   @Param(value = "clientId") String clientName,
                                   @Param(value = "isCompleted") Boolean isCompleted,
                                   Pageable pageable);
}
