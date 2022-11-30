package database;//package database;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "Dormitories")
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String standard;
    private int availablePlaces;
    @OneToMany
    private List<Room> rooms = new ArrayList<>();
    private int occupiedPlaces;
}