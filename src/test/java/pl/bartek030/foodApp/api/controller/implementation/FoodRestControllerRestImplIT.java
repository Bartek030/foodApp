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
import pl.bartek030.foodApp.api.controller.rest.FoodRestController;
import pl.bartek030.foodApp.api.controller.rest.implementation.FoodRestControllerRestImpl;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.mapper.FoodCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.FoodDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.FoodCreation;
import pl.bartek030.foodApp.business.services.FoodService;
import pl.bartek030.foodApp.util.*;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FoodRestControllerRestImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations="classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FoodRestControllerRestImplIT {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private FoodCreationDtoMapper foodCreationDtoMapper;

    @MockBean
    private FoodDtoMapper foodDtoMapper;

    @MockBean
    FoodService foodService;

    @Test
    void shouldReturnFoodsFromMenu() throws Exception {
        // given
        Long menuId = 1L;
        final FoodDTO foodDTO = FoodDTOExample.someFoodDTO1();
        when(foodService.getFoodsFromMenu(any(Long.class))).thenReturn(List.of(
                FoodExample.someFood1(),
                FoodExample.someFood2(),
                FoodExample.someFood3()
        ));
        when(foodDtoMapper.map(any(Food.class))).thenReturn(foodDTO);

        // when then
        String endpoint = FoodRestController.FOOD_URL + FoodRestController.MENUS_FOODS_URL;
        mockMvc.perform(get(endpoint, menuId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(foodDTO.getName())))
                .andExpect(jsonPath("$[0].price", Matchers.is(foodDTO.getPrice()), BigDecimal.class));
    }

    @Test
    void shouldAddFood() throws Exception {
        // given
        final FoodCreationDTO foodCreationDTO = FoodCreationDTOExample.someFoodCreationDTO1();

        when(foodCreationDtoMapper.map(any(FoodCreationDTO.class))).thenReturn(FoodCreationExample.someFoodCreation1());
        doNothing().when(foodService).addFood(any(FoodCreation.class));

        ObjectWriter ow = objectMapper.writer();
        String requestJson = ow.writeValueAsString(foodCreationDTO);

        // when then
        String endpoint = FoodRestController.FOOD_URL + FoodRestController.NEW_FOOD_URL;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}