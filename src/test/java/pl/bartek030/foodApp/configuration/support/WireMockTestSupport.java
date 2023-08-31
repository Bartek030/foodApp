package pl.bartek030.foodApp.configuration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WireMockTestSupport {

//    String apiKey = "4dfbd69f10b6b585f09ac8983fa88e12";
//    String apiLanguage = "pl";
//    String units = "metric";

    default void stubForWeatherApi(final WireMockServer wireMockServer, final String cityName) {
        wireMockServer.stubFor(get(urlPathEqualTo("/data/2.5/weather"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("wiremock/weatherData-1.json")));

    }
}
