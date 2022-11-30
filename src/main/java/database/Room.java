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
@Entity(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dormitory dormitory;
    private int number;
    private int availablePlaces;
    private int occupiedPlaces;
    @OneToMany
    private List<Student> students = new ArrayList<>();
}