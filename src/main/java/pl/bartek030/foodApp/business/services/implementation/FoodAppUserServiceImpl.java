package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bartek030.foodApp.business.dao.FoodAppUserDAO;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.business.services.AddressService;
import pl.bartek030.foodApp.business.services.FoodAppUserService;
import pl.bartek030.foodApp.infrastructure.security.FoodAppUserDetailsService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class FoodAppUserServiceImpl implements FoodAppUserService {

    private final FoodAppUserDAO foodAppUserDao;
    private final FoodAppUserDetailsService foodAppUserDetailsService;
    private final AddressService addressService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public FoodAppUser findById(final Long foodAppUserId) {
        return foodAppUserDao.findById(foodAppUserId)
                .orElseThrow(() -> new RuntimeException("User with id: [%s] not found".formatted(foodAppUserId)));
    }

    @Override
    @Transactional
    public void authenticateUser(final AppUserLogin appUserLogin) {
        final UserDetails userDetails = foodAppUserDetailsService.loadUserByUsername(appUserLogin.getUsername());
        if(!passwordEncoder.matches(appUserLogin.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Password does not match");
        }
    }

    @Override
    @Transactional
    public void addUser(final FoodAppUserCreation foodAppUserCreation) {
        AppUser appUser = foodAppUserDetailsService.createUser(buildAppUser(foodAppUserCreation));
        foodAppUserDao.createFoodAppUser(buildFoodAppUser(foodAppUserCreation, appUser));
    }

    private FoodAppUser buildFoodAppUser(final FoodAppUserCreation foodAppUserCreation, final AppUser appUser) {
        return FoodAppUser.builder()
                .name(foodAppUserCreation.getName())
                .surname(foodAppUserCreation.getSurname())
                .email(foodAppUserCreation.getEmail())
                .phone(foodAppUserCreation.getPhone())
                .address(buildAddress(foodAppUserCreation))
                .appUser(appUser)
                .build();
    }

    private Address buildAddress(final FoodAppUserCreation foodAppUserCreation) {
        final Address address = Address.builder()
                .country(foodAppUserCreation.getCountry())
                .city(foodAppUserCreation.getCity())
                .street(foodAppUserCreation.getStreet())
                .number(foodAppUserCreation.getNumber())
                .zipCode(foodAppUserCreation.getZipCode())
                .build();
        return addressService.findAddressByData(
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getZipCode()
        ).orElseGet(() -> addressService.createAddress(address));
    }

    private AppUser buildAppUser(final FoodAppUserCreation foodAppUserCreation) {
        return AppUser.builder()
                .userName(foodAppUserCreation.getEmail())
                .password(passwordEncoder.encode(foodAppUserCreation.getPassword()))
                .active(true)
                .roles(buildUserRoleList(foodAppUserCreation))
                .build();
    }

    private Set<Role> buildUserRoleList(final FoodAppUserCreation foodAppUserCreation) {
        Set<Role> roles = new HashSet<>();
        if (foodAppUserCreation.getIsOwner()) {
            final Role ownerRole = Role.builder()
                    .id(2L)
                    .role("OWNER")
                    .build();
            roles.add(ownerRole);
        } else {
            final Role defaultRole = Role.builder()
                    .id(3L)
                    .role("DEFAULT")
                    .build();
            roles.add(defaultRole);
        }
        return roles;
    }
}
