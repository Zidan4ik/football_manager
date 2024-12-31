package org.example.football_manager.mapper;

import org.example.football_manager.dto.PlayerRequestForAdd;
import org.example.football_manager.dto.PlayerResponseForView;
import org.example.football_manager.entity.Player;

public class PlayerMapper {
    public PlayerResponseForView toDTOForView(Player entity) {
        return PlayerResponseForView
                .builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .age(entity.getAge())
                .amountMonth(entity.getAmount_month())
                .team(entity.getTeam())
                .build();
    }

    public Player toEntityForAdd(PlayerRequestForAdd dto) {
        Player entity = new Player();
        entity.setId(dto.getId());
        entity.setTeam(dto.getTeam());
        entity.setNickname(dto.getNickname());
        entity.setAge(dto.getAge());
        entity.setAmount_month(dto.getExperienceMonths());
        return entity;
    }
}
