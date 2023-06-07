package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.stereotype.Repository;
import ru.hotkto.jewelryworkshop.models.ClientOrder;

@Repository
public interface ClientOrdersRepository extends GenericRepository<ClientOrder> {
}
