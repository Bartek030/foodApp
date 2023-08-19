package pl.bartek030.foodApp.configuration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

public interface WireMockTestSupport {

    String apiKey = "4dfbd69f10b6b585f09ac8983fa88e12";
    String apiLanguage = "pl";
    String units = "metric";

    default void stubForWeatherApi(final WireMockServer wireMockServer, final String cityName) {
        wireMockServer.stubFor(get(WireMock.urlPathEqualTo("/data/2.5/weather?appid=" + apiKey +
                "&lang=" + apiLanguage + "&units=" + units + "&q=" + cityName))
                .willReturn(aResponse()
                        .withBodyFile("wiremock/weatherData-1.json")));
    }
}
