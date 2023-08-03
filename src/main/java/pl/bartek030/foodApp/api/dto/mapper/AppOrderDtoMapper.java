package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppOrderDtoMapper {

    AppOrderDTO map(AppOrder appOrder);
}
