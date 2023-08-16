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
        classes = {FoodAppApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AbstractIntegrationTest {

    private AddressJpaRepository addressJpaRepository;
    private AppOrderJpaRepository appOrderJpaRepository;
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private FoodJpaRepository foodJpaRepository;
    private MenuJpaRepository menuJpaRepository;
    private OrderDetailsJpaRepository orderDetailsJpaRepository;
    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;


    // TODO: DODAC REPOZYTORIA DO CZYSZCZENIA BAZY PO KAZDYM TESCIE
    @BeforeEach
    public void beforeEach() {
        addressJpaRepository.deleteAll();
        appOrderJpaRepository.deleteAll();
        deliveryAddressJpaRepository.deleteAll();
        foodAppUserJpaRepository.deleteAll();
        foodJpaRepository.deleteAll();
        menuJpaRepository.deleteAll();
        orderDetailsJpaRepository.deleteAll();
        restaurantDeliveryAddressJpaRepository.deleteAll();
        restaurantJpaRepository.deleteAll();
    }
}
