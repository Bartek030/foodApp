package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@With
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    byte[] content;

    String name;
}
