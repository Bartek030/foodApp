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
import pl.bartek030.foodApp.api.controller.MenuController;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;
import pl.bartek030.foodApp.api.dto.mapper.MenuCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.MenuDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;
import pl.bartek030.foodApp.business.services.MenuService;
import pl.bartek030.foodApp.util.MenuCreationDTOExample;
import pl.bartek030.foodApp.util.MenuCreationExample;
import pl.bartek030.foodApp.util.MenuDTOExample;
import pl.bartek030.foodApp.util.MenuExample;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MenuControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations="classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MenuControllerImplTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private MenuCreationDtoMapper menuCreationDtoMapper;

    @MockBean
    private MenuDtoMapper menuDtoMapper;

    @MockBean
    private MenuService menuService;

    @Test
    void shouldReturnMenusFromRestaurant() throws Exception {
        // given
        Long restaurantId = 1L;
        final MenuDTO menuDTO = MenuDTOExample.someMenuDTO1();
        when(menuService.getMenusByRestaurantId(any(Long.class))).thenReturn(List.of(
                MenuExample.someMenu1(),
                MenuExample.someMenu2(),
                MenuExample.someMenu3()
        ));
        when(menuDtoMapper.map(any(Menu.class))).thenReturn(menuDTO);

        // when then
        String endpoint = MenuController.MENU_URL + MenuController.RESTAURANTS_MENUS_URL;
        mockMvc.perform(get(endpoint, restaurantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(menuDTO.getName())))
                .andExpect(jsonPath("$[0].category", Matchers.is(menuDTO.getCategory())));
    }

    @Test
    void shouldAddMenu() throws Exception {
        // given
        final MenuCreationDTO menuCreationDTO = MenuCreationDTOExample.someMenuCreationDTO1();

        when(menuCreationDtoMapper.map(any(MenuCreationDTO.class))).thenReturn(MenuCreationExample.someMenuCreation1());
        doNothing().when(menuService).addMenu(any(MenuCreation.class));

        ObjectWriter ow = objectMapper.writer();
        String requestJson = ow.writeValueAsString(menuCreationDTO);

        // when then
        String endpoint = MenuController.MENU_URL + MenuController.NEW_MENU_URL;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}