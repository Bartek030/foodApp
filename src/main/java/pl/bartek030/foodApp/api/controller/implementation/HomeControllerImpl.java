package pl.bartek030.foodApp.api.controller.implementation;

import org.springframework.stereotype.Controller;
import pl.bartek030.foodApp.api.controller.HomeController;

@Controller
public class HomeControllerImpl implements HomeController {

    @Override
    public String homePage() {
        return "home";
    }
}
