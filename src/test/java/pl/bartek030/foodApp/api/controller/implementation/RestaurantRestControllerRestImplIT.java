package pl.bartek030.foodApp.api.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import pl.bartek030.foodApp.api.controller.rest.RestaurantRestController;
import pl.bartek030.foodApp.api.controller.rest.implementation.RestaurantRestControllerRestImpl;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.RestaurantDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;
import pl.bartek030.foodApp.business.services.RestaurantDeliveryAddressService;
import pl.bartek030.foodApp.business.services.RestaurantService;
import pl.bartek030.foodApp.util.MenuCreationDTOExample;
import pl.bartek030.foodApp.util.RestaurantCreationExample;
import pl.bartek030.foodApp.util.RestaurantDTOExample;
import pl.bartek030.foodApp.util.RestaurantExample;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RestaurantRestControllerRestImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantRestControllerRestImplIT {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantCreationDtoMapper restaurantCreationDtoMapper;

    @MockBean
    private RestaurantDtoMapper restaurantDtoMapper;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantDeliveryAddressService restaurantDeliveryAddressService;

    @Test
    void shouldReturnOwnersRestaurants() throws Exception {
        // given
        Long userID = 1L;
        when(restaurantService.findRestaurantsByFoodAppUserId(any(Long.class)))
                .thenReturn(List.of(
                        RestaurantExample.someRestaurant1(),
                        RestaurantExample.someRestaurant2()
                ));
        when(restaurantDtoMapper.map(any(Restaurant.class)))
                .thenReturn(RestaurantDTOExample.someRestaurantDTO1())
                .thenReturn(RestaurantDTOExample.someRestaurantDTO2());

        // when then
        String endpoint = RestaurantRestController.RESTAURANT_URL + RestaurantRestController.USERS_RESTAURANTS_URL;
        mockMvc.perform(get(endpoint, userID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(RestaurantDTOExample.someRestaurantDTO1().getName())))
                .andExpect(jsonPath("$[1].name", Matchers.is(RestaurantDTOExample.someRestaurantDTO2().getName())));
    }

    @Test
    void shouldAddRestaurant() throws Exception {
        // given
        final MenuCreationDTO menuCreationDTO = MenuCreationDTOExample.someMenuCreationDTO1();

        when(restaurantCreationDtoMapper.map(any(RestaurantCreationDTO.class)))
                .thenReturn(RestaurantCreationExample.someRestaurantCreation1());
        doNothing().when(restaurantService).addRestaurant(any(RestaurantCreation.class));

        ObjectWriter ow = objectMapper.writer();
        String requestJson = ow.writeValueAsString(menuCreationDTO);

        // when then
        String endpoint = RestaurantRestController.RESTAURANT_URL + RestaurantRestController.NEW_RESTAURANT_URL;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnRestaurantListByAddress() throws Exception {
        // given
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("country", "Polska");
        parameters.add("city", "Warszawa");
        parameters.add("street", "Dluga");
        parameters.add("page", "1");

        when(restaurantDeliveryAddressService.getRestaurantsByCountryAndCityAndStreet(
                anyString(), anyString(), anyString(), anyInt()
                )).thenReturn(List.of(
                        RestaurantExample.someRestaurant1(),
                        RestaurantExample.someRestaurant2()
                ));
        when(restaurantDtoMapper.map(any(Restaurant.class)))
                .thenReturn(RestaurantDTOExample.someRestaurantDTO1())
                .thenReturn(RestaurantDTOExample.someRestaurantDTO2());

        // when then
        String endpoint = RestaurantRestController.RESTAURANT_URL;
        mockMvc.perform(get(endpoint).params(parameters))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(RestaurantDTOExample.someRestaurantDTO1().getName())))
                .andExpect(jsonPath("$[1].name", Matchers.is(RestaurantDTOExample.someRestaurantDTO2().getName())));
    }
}