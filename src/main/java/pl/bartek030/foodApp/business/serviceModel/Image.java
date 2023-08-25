package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"name"})
public class Image {

    Long id;
    byte[] content;
    String name;
}
