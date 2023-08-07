package pl.bartek030.foodApp.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.api.dto.weatherApiDTO.WeatherDataDTO;
import pl.bartek030.foodApp.business.serviceModel.weatherApi.WeatherData;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface WeatherDataDtoMapper {

    WeatherDataDTO map(WeatherData weatherData);
}
