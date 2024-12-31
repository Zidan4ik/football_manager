package org.example.football_manager.mapper;

import org.example.football_manager.dto.TeamRequestForAdd;
import org.example.football_manager.dto.TeamResponseForView;
import org.example.football_manager.entity.Team;

public class TeamMapper {

    public TeamResponseForView toDTOForView(Team entity) {
        return TeamResponseForView
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .commission(entity.getCommission())
                .balance(entity.getBalance())
                .build();
    }

    public Team toEntityForAdd(TeamRequestForAdd dto) {
        Team entity = new Team();
        entity.setId(dto.getId());
        entity.setBalance(dto.getBalance());
        entity.setName(dto.getName());
        entity.setCommission(dto.getCommission());
        return entity;
    }
}
