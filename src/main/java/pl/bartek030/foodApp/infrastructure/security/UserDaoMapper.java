package pl.bartek030.foodApp.infrastructure.security;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.AppUser;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserDaoMapper {

    AppUserEntity mapUserToEntity(AppUser appUser);

    AppUser mapUserFromEntity(AppUserEntity appUserEntity);
}
