package pl.bartek030.foodApp.business.util;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OffsetDateTimeWrapper {

    public OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now();
    }
}
