package pl.bartek030.foodApp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(HomeController.HOME_URL)
public interface HomeController {

    String HOME_URL = "/";

    @GetMapping
    String homePage();
}
