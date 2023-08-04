package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.AppOrderDAO;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.AppOrderDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AppOrderJpaRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AppOrderRepository implements AppOrderDAO {

    private final AppOrderJpaRepository appOrderJpaRepository;
    private final AppOrderDaoMapper appOrderDaoMapper;
    private final FoodAppUserDaoMapper foodAppUserDaoMapper;

    @Override
    public Long countAllRecords() {
        return appOrderJpaRepository.count();
    }

    @Override
    public AppOrder addAppOrder(final AppOrder appOrder) {
        final AppOrderEntity newOrder = appOrderJpaRepository.save(appOrderDaoMapper.mapAppOrderToEntity(appOrder));
        return appOrderDaoMapper.mapAppOrderFromEntity(newOrder);
    }

    @Override
    public List<AppOrder> getAppOrdersByUserId(final FoodAppUser foodAppUser) {
        List<AppOrderEntity> appOrderEntities =
                appOrderJpaRepository.findAllByFoodAppUser(foodAppUserDaoMapper.mapFoodAppUserToEntity(foodAppUser));
        return appOrderEntities.stream()
                .map(appOrderDaoMapper::mapAppOrderFromEntity)
                .toList();
    }
}
