package org.example.football_manager.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TeamResponseForView {
    private Long id;
    private String name;
    private Integer commission;
    private int balance;
}
