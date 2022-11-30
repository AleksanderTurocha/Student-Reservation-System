package database;//package database;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne
    private Dormitory dormitory;
    @ManyToOne
    private Room room;
}