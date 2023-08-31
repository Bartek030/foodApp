package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.AppUser;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodAppUserJpaRepository;
import pl.bartek030.foodApp.infrastructure.security.AppUserEntity;
import pl.bartek030.foodApp.infrastructure.security.UserDaoMapper;
import pl.bartek030.foodApp.util.AppUserEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodAppUserRepositoryTest {

    @InjectMocks
    private FoodAppUserRepository foodAppUserRepository;

    @Mock
    private FoodAppUserJpaRepository foodAppUserJpaRepository;

    @Mock
    private FoodAppUserDaoMapper foodAppUserDaoMapper;

    @Mock
    private UserDaoMapper userDaoMapper;

    @Test
    void shouldFindUserByIdCorrectly() {
        // given
        Long id = 1L;

        when(foodAppUserJpaRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(FoodAppUserEntityExample.someFoodAppUserEntity1()));
        when(foodAppUserDaoMapper.mapFoodAppUserFromEntity(any(FoodAppUserEntity.class)))
                .thenReturn(FoodAppUserExample.someFoodAppUser1());

        // when
        final Optional<FoodAppUser> result = foodAppUserRepository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getFoodAppUserId());
    }

    @Test
    void shouldFindUserByEmailCorrectly() {
        // given
        final String email = "SOMEEMAIL@EMAIL.COM";

        when(foodAppUserJpaRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(FoodAppUserEntityExample.someFoodAppUserEntity1()));
        when(foodAppUserDaoMapper.mapFoodAppUserFromEntity(any(FoodAppUserEntity.class)))
                .thenReturn(FoodAppUserExample.someFoodAppUser1());

        // when
        final Optional<FoodAppUser> result = foodAppUserRepository.findByEmail(email);

        // then
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    @Test
    void shouldCreateFoodAppUserCorrectly() {
        // given
        final FoodAppUser foodAppUser = FoodAppUserExample.someFoodAppUser1();

        when(foodAppUserDaoMapper.mapFoodAppUserToEntity(any(FoodAppUser.class)))
                .thenReturn(FoodAppUserEntityExample.someFoodAppUserEntity1());
        when(userDaoMapper.mapUserToEntity(any()))
                .thenReturn(AppUserEntityExample.someAppUserEntity());

        // when
        foodAppUserRepository.createFoodAppUser(foodAppUser);

        // then
        Mockito.verify(foodAppUserDaoMapper, times(1)).mapFoodAppUserToEntity(foodAppUser);
    }
}