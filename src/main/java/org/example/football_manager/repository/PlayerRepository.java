package org.example.football_manager.repository;

import org.example.football_manager.entity.Player;
import org.example.football_manager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeam(Team team);
}
