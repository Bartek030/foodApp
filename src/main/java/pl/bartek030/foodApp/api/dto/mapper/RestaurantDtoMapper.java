package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.AddressDTO;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDtoMapper {

    default RestaurantDTO map(final Restaurant restaurant) {
        return RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .addressDTO(AddressDTO.builder()
                        .country(restaurant.getAddress().getCountry())
                        .city(restaurant.getAddress().getCity())
                        .street(restaurant.getAddress().getStreet())
                        .number(restaurant.getAddress().getNumber())
                        .zipCode(restaurant.getAddress().getZipCode())
                        .build())
                .build();
    }
}
