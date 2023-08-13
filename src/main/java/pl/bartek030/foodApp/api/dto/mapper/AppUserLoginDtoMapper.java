package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.AppUserLoginDTO;
import pl.bartek030.foodApp.business.serviceModel.AppUserLogin;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppUserLoginDtoMapper {

    AppUserLogin map(AppUserLoginDTO appUserLoginDTO);
}
