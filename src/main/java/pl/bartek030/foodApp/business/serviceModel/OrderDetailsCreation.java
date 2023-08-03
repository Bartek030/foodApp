package pl.bartek030.foodApp.business.serviceModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsCreation {
    Long foodId;
    Integer quantity;
    String name;
}
