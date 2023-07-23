package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDtoMapper {

//    @Mapping(source = "foodAppUser", target = "foodAppUser")
//    Restaurant map(final RestaurantDTO restaurant);

    default Restaurant mapto(final RestaurantDTO restaurantDTO) {
        return Restaurant.builder()
                .name(restaurantDTO.getName())
                .foodAppUser(FoodAppUser.builder()
                        .foodAppUserId(restaurantDTO.getFoodAppUser().getFoodAppUserId())
                        .build())
                .address(Address.builder()
                        .country(restaurantDTO.getAddress().getCountry())
                        .city(restaurantDTO.getAddress().getCity())
                        .number(restaurantDTO.getAddress().getNumber())
                        .zipCode(restaurantDTO.getAddress().getZipCode())
                        .build())
                .build();
    }
}
