package org.example.football_manager.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int commission;
    private int balance;

    public Team(String name, int commission, int balance) {
        this.name = name;
        this.commission = commission;
        this.balance = balance;
    }
}
