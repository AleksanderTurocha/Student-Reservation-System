package model;//package database;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne()
    private Dormitory dormitory;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}