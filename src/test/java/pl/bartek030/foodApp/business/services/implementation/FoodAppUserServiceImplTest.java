package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUserCreation;
import pl.bartek030.foodApp.business.serviceModel.User;
import pl.bartek030.foodApp.infrastructure.database.repository.FoodAppUserRepository;
import pl.bartek030.foodApp.infrastructure.security.FoodAppUserDetailsService;
import pl.bartek030.foodApp.util.AddressExample;
import pl.bartek030.foodApp.util.FoodAppUserCreationExample;
import pl.bartek030.foodApp.util.FoodAppUserExample;
import pl.bartek030.foodApp.util.UserExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodAppUserServiceImplTest {

    @InjectMocks
    FoodAppUserServiceImpl foodAppUserService;

    @Mock
    FoodAppUserRepository foodAppUserDao;

    @Mock
    FoodAppUserDetailsService foodAppUserDetailsService;

    @Mock
    AddressServiceImpl addressService;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Test
    void givenFoodAppUserIdShouldFindFoodAppUser() {
        // given
        Long userId = 1L;
        FoodAppUser expected = FoodAppUserExample.someFoodAppUser1();

        when(foodAppUserDao.findById(any(Long.class))).thenReturn(Optional.of(FoodAppUserExample.someFoodAppUser1()));

        // when
        final FoodAppUser actual = foodAppUserService.findById(userId);

        // then
        assertEquals(expected, actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    void givenFoodAppUserIdShouldThrownException() {
        // given
        final Long userId = 4L;
        final String expectedExceptionMessage = "User with id: [%s] not found".formatted(userId);

        when(foodAppUserDao.findById(any(Long.class)))
                .thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> foodAppUserService.findById(userId)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenFoodAppUserDataShouldCreateUser() {
        // given
        final FoodAppUserCreation foodAppUserCreation = FoodAppUserCreationExample.someFoodAppUserCreation1();
        final FoodAppUser foodAppUser = FoodAppUserExample.someFoodAppUser1();
        final User user = UserExample.someUser1();

        when(passwordEncoder.encode(anyString()))
                .thenReturn("$2a$12$nBMvTSYlxYHhytUoO9hHBeocpipNfF20WxkRtqMP.6Am2rsSVKpFa");
        when(foodAppUserDetailsService.createUser(user)).thenReturn(user);
//        doNothing().when(foodAppUserDao).createFoodAppUser(foodAppUser);
        when(addressService.findAddressByData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(AddressExample.someAddress1()));

        // when
        foodAppUserService.addUser(foodAppUserCreation);

        // then
        verify(foodAppUserDetailsService, times(1)).createUser(user);
    }
}