package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDeliveryAddressDaoMapper {

    default RestaurantDeliveryAddress mapRestaurantDeliveryAddressFromEntity(RestaurantDeliveryAddressEntity address) {
        return RestaurantDeliveryAddress.builder()
                .restaurantDeliveryAddressId(address.getRestaurantDeliveryAddressId())
                .deliveryTime(address.getDeliveryTime())
                .restaurant(Restaurant.builder()
                        .restaurantId(address.getRestaurant().getRestaurantId())
                        .name(address.getRestaurant().getName())
                        .build())
                .build();
    }

    RestaurantDeliveryAddressEntity mapRestaurantDeliveryAddressToEntity(RestaurantDeliveryAddress deliveryAddress);
}
