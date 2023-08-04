package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DeliveryAddressDaoMapper {

    DeliveryAddress mapDeliveryAddressFromEntity(DeliveryAddressEntity deliveryAddress);

    DeliveryAddressEntity mapDeliveryAddressToEntity(DeliveryAddress deliveryAddress);
}
