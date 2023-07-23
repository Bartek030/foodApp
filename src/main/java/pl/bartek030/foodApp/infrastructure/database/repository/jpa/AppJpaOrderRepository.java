package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;

@Repository
public interface AppJpaOrderRepository extends JpaRepository<AppOrderEntity, Long> {
}
