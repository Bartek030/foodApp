package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.AddressDAO;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.AddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AddressJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    private final AddressJpaRepository addressJpaRepository;

    private final AddressDaoMapper addressDaoMapper;

    @Override
    public Optional<Address> findAddressByData(
            final String country,
            final String city,
            final String street,
            final String number,
            final String zipCode
    ) {
        return addressJpaRepository
                .findByCountryAndCityAndStreetAndNumberAndZipCode(country, city, street, number, zipCode)
                .map(addressDaoMapper::mapAddressFromEntity);
    }

    @Override
    public Address createAddress(final Address address) {
        final AddressEntity saved = addressJpaRepository.save(addressDaoMapper.mapAddressToEntity(address));
        return addressDaoMapper.mapAddressFromEntity(saved);
    }
}
