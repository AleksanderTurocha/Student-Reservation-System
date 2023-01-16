package model;//package database;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "dormitories")
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String standard;
    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Room> rooms;


}