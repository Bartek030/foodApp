package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;

import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppOrderDaoMapper {

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    AppOrderEntity mapAppOrderToEntity(AppOrder appOrder);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    AppOrder mapAppOrderFromEntity(AppOrderEntity appOrderEntity);

    default AppOrder mapAppOrderFromEntityWithRestaurantAndOrderDetails(AppOrderEntity appOrderEntity) {
        return mapAppOrderFromEntity(appOrderEntity)
                .withRestaurant(Restaurant.builder()
                        .name(appOrderEntity.getRestaurant().getName())
                        .build())
                .withOrderDetails(appOrderEntity.getOrderDetails().stream()
                        .map(orderDetails -> {
                            return OrderDetails.builder()
                                    .quantity(orderDetails.getQuantity())
                                    .food(Food.builder()
                                            .name(orderDetails.getFood().getName())
                                            .price(orderDetails.getFood().getPrice())
                                            .build())
                                    .build();
                        }).collect(Collectors.toSet()));
    }
}
