package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.MenuDao;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.MenuDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.MenuJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MenuRepository implements MenuDao {

    private final MenuJpaRepository menuJpaRepository;
    private final MenuDaoMapper menuDaoMapper;
    private final RestaurantDaoMapper restaurantDaoMapper;

    @Override
    public Optional<Menu> findById(final Long menuId) {
        return menuJpaRepository.findById(menuId)
                .map(menuDaoMapper::mapFromEntity);
    }

    @Override
    public void addMenu(final Menu menu) {
        menuJpaRepository.save(menuDaoMapper.mapToEntity(menu));
    }

    @Override
    public List<Menu> findByRestaurant(final Restaurant restaurant) {
        final List<MenuEntity> allById =
                menuJpaRepository.findAllByRestaurant(restaurantDaoMapper.mapToEntity(restaurant));
        return allById.stream()
                .map(menuDaoMapper::mapFromEntity)
                .toList();
    }
}
