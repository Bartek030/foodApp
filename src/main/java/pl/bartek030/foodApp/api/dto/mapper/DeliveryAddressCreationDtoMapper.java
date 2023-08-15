package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddressCreation;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DeliveryAddressCreationDtoMapper {

    DeliveryAddressCreation map(DeliveryAddressCreationDTO deliveryAddressCreationDTO);
}
