package ru.hotkto.jewelryworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.hotkto.jewelryworkshop.models.GenericModel;

@NoRepositoryBean
public interface GenericRepository<T extends GenericModel> extends JpaRepository <T, Long> {
}
