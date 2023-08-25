package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Image;
import pl.bartek030.foodApp.infrastructure.database.entity.ImageEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ImageDaoMapper {


    ImageEntity mapImageToEntity(Image image);

    Image mapFromEntity(ImageEntity imageEntity);
}
