package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MenuDaoMapper {

    MenuEntity mapToEntity(Menu menu);

    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Menu mapFromEntity(MenuEntity menuEntity);
}
