package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.bartek030.foodApp.api.controller.RestaurantController;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantCreationDtoMapper restaurantCreationDtoMapper;
    private final RestaurantDtoMapper restaurantDtoMapper;

    private final RestaurantService restaurantService;
    private final RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Override
    public String addRestaurant(final RestaurantCreationDTO restaurant) {
        restaurantService.addRestaurant(restaurantCreationDtoMapper.map(restaurant));
        return "restaurant-success";
    }

    @Override
    public String getRestaurantPage() {
        return "restaurants";
    }

    @Override
    public String getOwnersRestaurants(final Model model) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Restaurant> restaurants = restaurantService.findRestaurantsByFoodAppUserEmail(email);
        final List<RestaurantDTO> restaurantDTOList = restaurants.stream()
                .map(restaurantDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("restaurants", restaurantDTOList));
        return "restaurantsOwnerList";
    }

    @Override
    public String getRestaurantsByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street,
            final Integer page,
            final Model model
    ) {

        final List<Restaurant> restaurants =
                restaurantDeliveryAddressService.getRestaurantsByCountryAndCityAndStreet(country, city, street, page);
        final List<RestaurantDTO> restaurantDTOList = restaurants.stream()
                .map(restaurantDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("restaurants", restaurantDTOList));
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        model.addAttribute("street", street);
        model.addAttribute("page", page);
        return "restaurantsList";
    }
}
