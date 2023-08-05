package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;

import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppOrderDaoMapper {

    @Mapping(target = "orderDetails", ignore = true)
    AppOrderEntity mapAppOrderToEntity(AppOrder appOrder);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    AppOrder mapAppOrderFromEntity(AppOrderEntity appOrderEntity);

    default AppOrder mapAppOrderFromEntityWithCollections(AppOrderEntity appOrderEntity) {
        return mapAppOrderFromEntity(appOrderEntity)
                .withRestaurant(Restaurant.builder()
                        .restaurantId(appOrderEntity.getRestaurant().getRestaurantId())
                        .name(appOrderEntity.getRestaurant().getName())
                        .build())
                .withOrderDetails(appOrderEntity.getOrderDetails().stream()
                        .map(orderDetails -> {
                            return OrderDetails.builder()
                                    .orderDetailsId(orderDetails.getOrderDetailsId())
                                    .quantity(orderDetails.getQuantity())
                                    .food(Food.builder()
                                            .foodId(orderDetails.getFood().getFoodId())
                                            .name(orderDetails.getFood().getName())
                                            .price(orderDetails.getFood().getPrice())
                                            .build())
                                    .build();
                        }).collect(Collectors.toSet()))
                .withFoodAppUser(FoodAppUser.builder()
                        .foodAppUserId(appOrderEntity.getFoodAppUser().getFoodAppUserId())
                        .name(appOrderEntity.getFoodAppUser().getName())
                        .surname(appOrderEntity.getFoodAppUser().getSurname())
                        .phone(appOrderEntity.getFoodAppUser().getPhone())
                        .address(Address.builder()
                                .addressId(appOrderEntity.getFoodAppUser().getAddress().getAddressId())
                                .country(appOrderEntity.getFoodAppUser().getAddress().getCountry())
                                .city(appOrderEntity.getFoodAppUser().getAddress().getCity())
                                .street(appOrderEntity.getFoodAppUser().getAddress().getStreet())
                                .number(appOrderEntity.getFoodAppUser().getAddress().getNumber())
                                .zipCode(appOrderEntity.getFoodAppUser().getAddress().getZipCode())
                                .build())
                        .build());
    }
}
