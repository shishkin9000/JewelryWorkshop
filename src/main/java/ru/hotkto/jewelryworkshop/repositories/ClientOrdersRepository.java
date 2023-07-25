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
            select co.* from clients_orders co
            join clients c on c.id = co.client_id
            where cast(co.created_when as date) >= coalesce(:orderDateFrom, cast('1990-01-01' as date))
            and cast(co.created_when as date) <= coalesce(:orderDateTo, cast('2100-01-01' as date))
            and c.full_name ilike '%' || coalesce(:clientsName, '%') || '%'
            and co.deadline <= now()
            and co.status <> 'выполнен'
            and co.status <> 'выдан'
            and co.status ILIKE coalesce(:looseStatus,'%')
            """)
    Page<ClientOrder> searchExpiredOrders(@Param(value = "orderDateFrom") LocalDate orderDateFrom,
                                   @Param(value = "orderDateTo") LocalDate orderDateTo,
                                   @Param(value = "clientsName") String clientsName,
                                   @Param(value = "looseStatus") String looseStatus,
                                   Pageable pageable);

    @Query(nativeQuery = true,
            value = """
            select co.* from clients_orders co
            join clients c on c.id = co.client_id
            where cast(co.created_when as date) >= coalesce(:orderDateFrom, cast('1990-01-01' as date))
            and cast(co.created_when as date) <= coalesce(:orderDateTo, cast('2100-01-01' as date))
            and c.full_name ilike '%' || coalesce(:clientsName, '%') || '%'
            and co.deadline > now()
            and co.status ILIKE coalesce(:looseStatus,'%')
            """)
    Page<ClientOrder> searchOrders(@Param(value = "orderDateFrom") LocalDate orderDateFrom,
                                   @Param(value = "orderDateTo") LocalDate orderDateTo,
                                   @Param(value = "clientsName") String clientsName,
                                   @Param(value = "looseStatus") String looseStatus,
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
