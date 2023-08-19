package pl.bartek030.foodApp.configuration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import pl.bartek030.foodApp.FoodAppApplication;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.*;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(
        classes = FoodAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    @Autowired
    private AddressJpaRepository addressJpaRepository;

    @Autowired
    private AppOrderJpaRepository appOrderJpaRepository;

    @Autowired
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    @Autowired
    private FoodAppUserJpaRepository foodAppUserJpaRepository;

    @Autowired
    private FoodJpaRepository foodJpaRepository;

    @Autowired
    private MenuJpaRepository menuJpaRepository;

    @Autowired
    private OrderDetailsJpaRepository orderDetailsJpaRepository;

    @Autowired
    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;


    @BeforeEach
    public void beforeEach() {
        restaurantDeliveryAddressJpaRepository.deleteAll();
        orderDetailsJpaRepository.deleteAll();
        appOrderJpaRepository.deleteAll();
        foodJpaRepository.deleteAll();
        menuJpaRepository.deleteAll();
        restaurantJpaRepository.deleteAll();
        deliveryAddressJpaRepository.deleteAll();
        foodAppUserJpaRepository.deleteAll();
        addressJpaRepository.deleteAll();
    }
}
