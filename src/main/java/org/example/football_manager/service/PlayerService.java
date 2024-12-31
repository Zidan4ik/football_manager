package org.example.football_manager.service;

import org.example.football_manager.dto.PlayerRequestForAdd;
import org.example.football_manager.dto.PlayerResponseForView;
import org.example.football_manager.entity.Player;
import org.example.football_manager.entity.Team;

import java.util.List;

public interface PlayerService {
    Player save(Player player);

    Player save(PlayerRequestForAdd dto);

    Player getById(Long id);

    PlayerResponseForView getByIdInResponseView(Long id);

    List<Player> getAllByTeam(Team team);

    List<Player> getAll();

    List<PlayerResponseForView> getAllInResponseView();

    void deleteById(Long id);

}
