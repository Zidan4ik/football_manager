package org.example.football_manager.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.football_manager.entity.Team;

@Builder
@Getter
public class PlayerResponseForView {
    private Long id;
    private String nickname;
    private Integer age;
    private Integer amountMonth;
    private Team team;
}
