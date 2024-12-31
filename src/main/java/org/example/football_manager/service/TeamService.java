package org.example.football_manager.service;

import org.example.football_manager.dto.PlayerRequestForTransfer;
import org.example.football_manager.dto.TeamRequestForAdd;
import org.example.football_manager.dto.TeamResponseForView;
import org.example.football_manager.entity.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);

    Team save(TeamRequestForAdd dto);

    List<Team> getAll();

    List<TeamResponseForView> getAllInResponseForView();

    Team getById(Long id);

    TeamResponseForView getByIdResponseForView(Long id);

    void deleteById(Long id);

    void transferPlayer(PlayerRequestForTransfer request);

    void decreaseBalance(Team team, int amountMoney);

    void increaseBalance(Team team, int amountMoney);
}
