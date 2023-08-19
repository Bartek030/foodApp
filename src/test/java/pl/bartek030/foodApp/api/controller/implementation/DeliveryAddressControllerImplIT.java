package pl.bartek030.foodApp.api.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.bartek030.foodApp.api.controller.DeliveryAddressController;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.DeliveryAddressDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddressCreation;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;
import pl.bartek030.foodApp.util.DeliveryAddressCreationDTOExample;
import pl.bartek030.foodApp.util.DeliveryAddressCreationExample;
import pl.bartek030.foodApp.util.DeliveryAddressDTOExample;
import pl.bartek030.foodApp.util.DeliveryAddressExample;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeliveryAddressController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeliveryAddressControllerImplIT {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryAddressDtoMapper deliveryAddressDtoMapper;

    @MockBean
    private DeliveryAddressCreationDtoMapper deliveryAddressCreationDtoMapper;

    @MockBean
    private RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Test
    void shouldAddDeliveryAddress() throws Exception {
        // given
        final DeliveryAddressCreationDTO address = DeliveryAddressCreationDTOExample.someDeliveryAddressCreationDTO1();

        when(deliveryAddressCreationDtoMapper.map(any(DeliveryAddressCreationDTO.class)))
                .thenReturn(DeliveryAddressCreationExample.someDeliveryAddressCreation1());
        doNothing().when(restaurantDeliveryAddressService).addDeliveryAddress(any(DeliveryAddressCreation.class));

        ObjectWriter ow = objectMapper.writer();
        String requestJson = ow.writeValueAsString(address);

        // when then
        String endpoint = DeliveryAddressController.DELIVERY_ADDRESS_URL
                + DeliveryAddressController.NEW_DELIVERY_ADDRESS_URL;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAddressesByRestaurant() throws Exception {
        // given
        Long restaurantId = 1L;
        when(restaurantDeliveryAddressService.findDeliveryAddressByRestaurant(any(Long.class)))
                .thenReturn(List.of(
                        DeliveryAddressExample.someDeliveryAddress1(),
                        DeliveryAddressExample.someDeliveryAddress2(),
                        DeliveryAddressExample.someDeliveryAddress3()
                ));
        when(deliveryAddressDtoMapper.map(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressDTOExample.someDeliveryAddressDto1());

        // when then
        String endpoint = DeliveryAddressController.DELIVERY_ADDRESS_URL
                + DeliveryAddressController.RESTAURANTS_URL;
        mockMvc.perform(get(endpoint, restaurantId))
                .andExpect(status().isOk());
    }
}