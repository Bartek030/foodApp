package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.MenuDao;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.MenuDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.MenuJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MenuRepository implements MenuDao {

    private final MenuJpaRepository menuJpaRepository;
    private final MenuDaoMapper menuDaoMapper;

    @Override
    public Optional<Menu> findById(final Long menuId) {
        return menuJpaRepository.findById(menuId)
                .map(menuDaoMapper::mapFromEntity);
    }

    @Override
    public void addMenu(final Menu menu) {
        menuJpaRepository.save(menuDaoMapper.mapToEntity(menu));
    }
}
