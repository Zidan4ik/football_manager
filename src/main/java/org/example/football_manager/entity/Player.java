package org.example.football_manager.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private int age;
    private int amount_month;
    @ManyToOne
    private Team team;

    public Player(String nickname, int age, int amount_month, Team team) {
        this.nickname = nickname;
        this.age = age;
        this.amount_month = amount_month;
        this.team = team;
    }
}
