package pl.bartek030.foodApp.business.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.RestaurantDeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantDeliveryAddressServiceImpl implements RestaurantDeliveryAddressService {

    private final RestaurantDeliveryAddressDao restaurantDeliveryAddressDao;

    @Override
    public List<RestaurantDeliveryAddress> findByAddress(final DeliveryAddress deliveryAddress) {
        return restaurantDeliveryAddressDao.findByAddress(deliveryAddress);
    }
}
