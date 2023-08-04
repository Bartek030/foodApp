package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddressDaoMapper {

    Address mapAddressFromEntity(AddressEntity addressEntity);

    AddressEntity mapAddressToEntity(Address address);
}
