package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantDeliveryAddressJpaRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantDeliveryAddressRepositoryTest {

    @InjectMocks
    private RestaurantDeliveryAddressRepository restaurantDeliveryAddressRepository;

    @Mock
    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;

    @Mock
    private R

}