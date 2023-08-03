package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.AppOrderDAO;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.AppOrderDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AppOrderJpaRepository;

@Repository
@AllArgsConstructor
public class AppOrderRepository implements AppOrderDAO {

    private final AppOrderJpaRepository appOrderJpaRepository;
    private final AppOrderDaoMapper appOrderDaoMapper;

    @Override
    public Long countAllRecords() {
        return appOrderJpaRepository.count();
    }

    @Override
    public AppOrder addAppOrder(final AppOrder appOrder) {
        final AppOrderEntity newOrder = appOrderJpaRepository.save(appOrderDaoMapper.mapToEntity(appOrder));
        return appOrderDaoMapper.mapFromEntity(newOrder);
    }
}
