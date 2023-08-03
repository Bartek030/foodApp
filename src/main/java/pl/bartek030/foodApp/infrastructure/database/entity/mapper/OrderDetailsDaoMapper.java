package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.infrastructure.database.entity.OrderDetailsEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderDetailsDaoMapper {

    OrderDetailsEntity mapToEntity(OrderDetails orderDetails);

    OrderDetails mapFromEntity(OrderDetailsEntity orderDetailsEntity);
}
