package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.DeliveryAddressDTO;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DeliveryAddressDtoMapper {

    DeliveryAddressDTO map(DeliveryAddress deliveryAddress);
}
